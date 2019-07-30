import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class dataDriven {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("ExcelDriven/src/main/resources/datademo.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
                XSSFSheet xssfSheet = workbook.getSheetAt(i);
            }
        }
    }
}
