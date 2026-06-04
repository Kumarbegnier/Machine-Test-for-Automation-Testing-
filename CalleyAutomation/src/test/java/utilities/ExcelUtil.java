package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    /**
     * Reads an XLSX file and returns data as Object[][] for TestNG DataProvider.
     *
     * Rules:
     * - First row is considered header row (skipped).
     * - Empty cells are returned as empty string.
     */
    public static Object[][] readExcel(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            int rowCount = sheet.getPhysicalNumberOfRows();
            if (rowCount <= 1) {
                return new Object[0][0];
            }

            // Determine max column count based on rows
            int maxCols = 0;
            for (int i = 0; i < rowCount; i++) {
                Row r = sheet.getRow(i);
                if (r != null) {
                    maxCols = Math.max(maxCols, r.getLastCellNum());
                }
            }

            List<Object[]> rows = new ArrayList<>();

            // start from 1 to skip header row
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                Object[] data = new Object[maxCols];
                boolean hasAnyValue = false;

                for (int c = 0; c < maxCols; c++) {
                    Cell cell = row.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String value = "";
                    if (cell != null) {
                        value = switch (cell.getCellType()) {
                            case STRING -> cell.getStringCellValue();
                            case NUMERIC -> {
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    yield cell.getDateCellValue().toString();
                                }
                                double d = cell.getNumericCellValue();
                                // avoid 1.0 style for integers
                                if (d == Math.rint(d)) {
                                    yield String.valueOf((long) d);
                                }
                                yield String.valueOf(d);
                            }
                            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
                            case FORMULA -> {
                                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                                CellValue cv = evaluator.evaluate(cell);
                                yield cv == null ? "" : (cv.getCellType() == CellType.NUMERIC ? String.valueOf(cv.getNumberValue()) : cv.getStringValue());
                            }
                            case BLANK -> "";
                            default -> cell.toString();
                        };
                    }
                    if (!value.trim().isEmpty()) {
                        hasAnyValue = true;
                    }
                    data[c] = value;
                }

                // Skip completely empty rows
                if (hasAnyValue) {
                    rows.add(data);
                }
            }

            Object[][] result = new Object[rows.size()][maxCols];
            for (int i = 0; i < rows.size(); i++) {
                result[i] = rows.get(i);
            }
            return result;

        } catch (IOException e) {
            throw new RuntimeException("Failed to read excel: " + filePath + " sheet=" + sheetName, e);
        }
    }
}

