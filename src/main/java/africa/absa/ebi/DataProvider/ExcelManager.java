package africa.absa.ebi.DataProvider;

import africa.absa.ebi.Logger.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelManager {

    private String[][] readFile(String sheetName) throws IOException {
        String[][] excelData = null;
        try {
            FileInputStream excelFile =
                    new FileInputStream(new File("src/main/java/Utilities/TestData/Users.xlsx"));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet mySheet = workbook.getSheet(sheetName);
            int numRows = mySheet.getLastRowNum() + 1;
            int numCols = mySheet.getRow(0).getLastCellNum();
            excelData = new String[numRows][numCols];

            for (int i = 1; i < numRows; i++) {
                Row row = mySheet.getRow(i);
                for (int j = 0; j < numCols; j++) {
                    Cell cell = row.getCell(j);
                    String value = cellToString(cell);
                    excelData[i][j] = value;
                }
            }
            excelFile.close();
            return excelData;
        } catch (Exception exception) {
            Log.fatal(exception.getMessage());
        }
        return excelData;
    }

    private String cellToString(Cell cell) {
        String results = "";
        switch (cell.getCellType()) {
            case NUMERIC:
                results = cell.getNumericCellValue() + "";
                break;
            case STRING:
                results = cell.getStringCellValue();
                break;
            case BOOLEAN:
                results = cell.getBooleanCellValue() + "";
                break;
            default:
                Log.error("Invalid type");
                break;
        }
        return results;
    }
}
