package com.example;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BankingSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, AccountDetails> accounts = new ConcurrentHashMap<>();
    private static final AccountDAO accountDAO = new AccountDAO();
    private static final TransactionDAO transactionDAO = new TransactionDAO();
    private static final List<Transaction> transactionHistory = new ArrayList<>();
    protected static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        System.out.println("Welcome to the Advanced Banking System!");

        if (!login(scanner)) {
            System.out.println("Invalid credentials. Exiting the system.");
            return;
        }

        // Load accounts from database
        loadAccountsFromDatabase();

        // Keep the system running until the user decides to exit
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Account");
            System.out.println("2. Remove Account");
            System.out.println("3. View Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. View Transactions");
            System.out.println("7. Display Accounts Above Threshold");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    handleAddAccount();
                    break;
                case 2:
                    handleRemoveAccount();
                    break;
                case 3:
                    handleViewAccount();
                    break;
                case 4:
                    handleDeposit();
                    break;
                case 5:
                    handleWithdraw();
                    break;
                case 6:
                    handleViewTransactions();
                    break;
                case 7:
                    handleDisplayAccountsAboveThreshold();
                    break;
                case 8:
                    exit = true;
                    executorService.shutdown();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void loadAccountsFromDatabase() {
        executorService.submit(() -> {
            try {
                List<AccountDetails> accountList = accountDAO.getAllAccounts();
                for (AccountDetails account : accountList) {
                    accounts.put(account.getAccountNumber(), account);
                }
            } catch (SQLException e) {
                System.err.println("Error loading accounts: " + e.getMessage());
            }
        });
    }

    public static void handleAddAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        if (initialDeposit < 0) {
            System.out.println("Initial balance cannot be negative. Please try again.");
            return;
        }

        AccountDetails account = new AccountDetails(accountNumber, name, initialDeposit);
        addAccount(account);
    }

    public static void addAccount(AccountDetails account) {
        executorService.submit(() -> {
            try {
                accountDAO.createAccount(account);
                accounts.put(account.getAccountNumber(), account);
                synchronized (System.out) {
                    System.out.println("Account added successfully!");
                }
            } catch (SQLException e) {
                synchronized (System.out) {
                    System.err.println("Error creating account: " + e.getMessage());
                }
            }
        });
    }

    public static void handleRemoveAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        executorService.submit(() -> removeAccount(accountNumber));
        System.out.println("Account removed successfully!");
    }

    public static void removeAccount(String accountNumber) {
        try {
            accountDAO.deleteAccount(accountNumber);
            accounts.remove(accountNumber);
        } catch (SQLException e) {
            System.err.println("Error removing account: " + e.getMessage());
        }
    }

    private static void handleViewAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Future<?> future = executorService.submit(() -> {
            AccountDetails account = getAccount(accountNumber);
            if (account != null) {
                synchronized (System.out) {
                    System.out.println(account.getAccountDetails());
                }
            } else {
                synchronized (System.out) {
                    System.out.println("Account not found.");
                }
            }
        });
        waitForFuture(future);
    }

    public static AccountDetails getAccount(String accountNumber) {
        AccountDetails account = accounts.get(accountNumber);
        if (account == null) {
            try {
                account = accountDAO.getAccount(accountNumber);
                if (account != null) {
                    accounts.put(accountNumber, account);
                }
            } catch (SQLException e) {
                System.err.println("Error retrieving account: " + e.getMessage());
            }
        }
        return account;
    }

    public static void handleDeposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Future<?> future = executorService.submit(() -> handleDeposit(accountNumber, amount));
        waitForFuture(future);
    }

    public static void handleDeposit(String accountNumber, double amount) {
        AccountDetails account = getAccount(accountNumber);
        if (account != null && amount >= 0) {
            account.deposit(amount);
            Transaction transaction = new Transaction(accountNumber, "DEPOSIT", amount, "Deposit made", new Timestamp(System.currentTimeMillis()));
            try {
                transactionDAO.createTransaction(transaction);
                synchronized (transactionHistory) {
                    transactionHistory.add(transaction);
                }
                accountDAO.updateAccount(account);
                synchronized (System.out) {
                    System.out.println("Deposit successful!");
                }
            } catch (SQLException e) {
                synchronized (System.out) {
                    System.err.println("Error during deposit: " + e.getMessage());
                }
            }
        } else {
            synchronized (System.out) {
                System.out.println("Account not found or invalid deposit.");
            }
        }
    }

    public static void handleWithdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Future<?> future = executorService.submit(() -> handleWithdraw(accountNumber, amount));
        waitForFuture(future);
    }

    public static void handleWithdraw(String accountNumber, double amount) {
        AccountDetails account = getAccount(accountNumber);
        if (account != null && amount >= 0) {
            if (account.withdraw(amount)) {
                Transaction transaction = new Transaction(accountNumber, "WITHDRAW", amount, "Withdrawal made", new Timestamp(System.currentTimeMillis()));
                try {
                    transactionDAO.createTransaction(transaction);
                    synchronized (transactionHistory) {
                        transactionHistory.add(transaction);
                    }
                    accountDAO.updateAccount(account);
                    synchronized (System.out) {
                        System.out.println("Withdrawal successful!");
                    }
                } catch (SQLException e) {
                    synchronized (System.out) {
                        System.err.println("Error during withdrawal: " + e.getMessage());
                    }
                }
            } else {
                synchronized (System.out) {
                    System.out.println("Insufficient funds.");
                }
            }
        } else {
            synchronized (System.out) {
                System.out.println("Account not found or invalid withdrawal.");
            }
        }
    }

    public static void handleViewTransactions() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Future<List<Transaction>> future = executorService.submit(() -> viewTransactions(accountNumber));
        waitForFuture(future);
    }

    public static List<Transaction> viewTransactions(String accountNumber) {
        try {
            List<Transaction> transactions = transactionDAO.getTransactionsByAccount(accountNumber);
            if (transactions.isEmpty()) {
                synchronized (System.out) {
                    System.out.println("No transactions found for this account.");
                }
            } else {
                synchronized (System.out) {
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
                }
            }
            // Return transactions for testing assertions
            return transactions;
        } catch (SQLException e) {
            System.err.println("Error retrieving transactions: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static void handleDisplayAccountsAboveThreshold() {
        System.out.print("Enter threshold balance: ");
        double threshold = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Future<List<AccountDetails>> future = executorService.submit(() -> handleDisplayAccountsAboveThreshold(threshold));
        waitForFuture(future);
    }

    // Adding overloaded method for testing
    public static List<AccountDetails> handleDisplayAccountsAboveThreshold(double threshold) {
        List<AccountDetails> filteredAccounts = accounts.values().stream()
                .filter(account -> account.getBalance() > threshold)
                .collect(Collectors.toList());
        
        // For console output (can be removed in actual implementation)
        synchronized (System.out) {
            filteredAccounts.forEach(account -> System.out.println(account.getAccountDetails()));
        }
        
        // Return the list for test assertions
        return filteredAccounts;
    }

    public static boolean login(Scanner scanner) {
        final String USERNAME = "admin";
        final String PASSWORD = "password";
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            return true;
        } else {
            System.out.println("Incorrect username or password.");
            return false;
        }
    }

    private static void waitForFuture(Future<?> future) {
        try {
            future.get(); // Wait for the task to complete
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error waiting for task to complete: " + e.getMessage());
        }
    }
}
