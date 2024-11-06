package Base;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReportListener extends TestListenerAdapter {
    private Workbook workbook;
    private Sheet sheet;
    private int rowNum = 0;

    @Override
    public void onStart(ITestContext testContext) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Test Results");

        // Create header row
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Test Case Name");
        headerRow.createCell(1).setCellValue("Status");
        headerRow.createCell(2).setCellValue("Execution Time");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        writeTestResult(tr, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        writeTestResult(tr, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        writeTestResult(tr, "SKIPPED");
    }

    private void writeTestResult(ITestResult result, String status) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(result.getName());
        row.createCell(1).setCellValue(status);
        row.createCell(2).setCellValue(result.getEndMillis() - result.getStartMillis());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        try (FileOutputStream outputStream = new FileOutputStream("TestResults.xlsx")) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
