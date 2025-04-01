package com.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportingService {

    public void generateDepositsBarChart(String filePath) throws IOException, SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql = "SELECT EXTRACT(MONTH FROM timestamp) AS month, SUM(amount) AS total FROM transactions WHERE transaction_type = 'DEPOSIT' GROUP BY month";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String month = rs.getString("month");
                double total = rs.getDouble("total");
                dataset.addValue(total, "Deposits", month);
            }
        }
        JFreeChart chart = ChartFactory.createBarChart("Monthly Deposits", "Month", "Amount", dataset);
        ChartUtils.saveChartAsJPEG(new java.io.File(filePath), chart, 800, 600);
    }
    
    public void generateWithdrawalsBarChart(String filePath) throws IOException, SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql = "SELECT EXTRACT(MONTH FROM timestamp) AS month, SUM(amount) AS total FROM transactions WHERE transaction_type = 'WITHDRAW' GROUP BY month";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String month = rs.getString("month");
                double total = rs.getDouble("total");
                dataset.addValue(total, "Withdrawals", month);
            }
        }
        JFreeChart chart = ChartFactory.createBarChart("Monthly Withdrawals", "Month", "Amount", dataset);
        ChartUtils.saveChartAsJPEG(new java.io.File(filePath), chart, 800, 600);
    }

    public void exportReportToPDF(String filePath) throws DocumentException, IOException, SQLException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        String sql = "SELECT * FROM transactions";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            PdfPTable table = new PdfPTable(5); 
            table.addCell("Account Number");
            table.addCell("Type");
            table.addCell("Amount");
            table.addCell("Description");
            table.addCell("Timestamp");

            while (rs.next()) {
                table.addCell(rs.getString("account_number"));
                table.addCell(rs.getString("transaction_type"));
                table.addCell(String.valueOf(rs.getDouble("amount")));
                table.addCell(rs.getString("description"));
                table.addCell(rs.getString("timestamp"));
            }
            document.add(new Paragraph("Transaction Report"));
            document.add(table);
        }
        document.close();
    }

    public void exportReportToExcel(String filePath) throws IOException, SQLException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Transaction Report");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Account Number");
        header.createCell(1).setCellValue("Type");
        header.createCell(2).setCellValue("Amount");
        header.createCell(3).setCellValue("Description");
        header.createCell(4).setCellValue("Timestamp");

        String sql = "SELECT * FROM transactions";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs.getString("account_number"));
                row.createCell(1).setCellValue(rs.getString("transaction_type"));
                row.createCell(2).setCellValue(rs.getDouble("amount"));
                row.createCell(3).setCellValue(rs.getString("description"));
                row.createCell(4).setCellValue(rs.getString("timestamp"));
            }
        }
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }
}