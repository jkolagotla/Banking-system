<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reports</title>
</head>
<body>
    <h2>Click to get Report</h2>
    <form action="<%= request.getContextPath() %>/BankingServlet" method="post">
        <input type="hidden" name="action" value="generateDepositsReport">
        <button type="submit">Generate Deposits Report</button>
    </form><br>
    <form action="<%= request.getContextPath() %>/BankingServlet" method="post">
        <input type="hidden" name="action" value="generateWithdrawalsReport">
        <button type="submit">Generate Withdrawals Report</button>
    </form><br>
    <form action="<%= request.getContextPath() %>/BankingServlet" method="post">
        <input type="hidden" name="action" value="exportReportToPDF">
        <button type="submit">Export Report to PDF</button>
    </form><br>
    <form action="<%= request.getContextPath() %>/BankingServlet" method="post">
        <input type="hidden" name="action" value="exportReportToExcel">
        <button type="submit">Export Report to Excel</button>
    </form><br>
</body>
</html>