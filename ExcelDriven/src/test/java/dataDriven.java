import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class dataDriven {

    public static void main(String[] args) throws IOException {

//  File input stream argument
        FileInputStream fileInputStream = new FileInputStream("ExcelDriven/src/main/resources/datademo.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        int sheets = workbook.getNumberOfSheets();

        for (int i = 0; i < sheets; i++) {

            if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
                XSSFSheet xssfSheet = workbook.getSheetAt(i);
//  Identify testcases coloumn by scanning the entire 1st row

                Iterator<Row> rows = xssfSheet.iterator(); //sheet is collection of rows
                Row firstrow = rows.next();
                Iterator<Cell> cell = firstrow.cellIterator(); //row is collections of cells
                int k = 0;
                int coloumn = 0;

                while (cell.hasNext()) {
                    Cell value = cell.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
//  desired coloumn
                        coloumn = k;
                    }
                    k++;
                }
                System.out.println(coloumn);
                //    once coloumn is identifired then scan entire testcase coloumn to identify purchase testcase raw
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase("Purchase")) {
//  after grab purchase testcase row=pull all the data of that row and feed into test
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            System.out.println(cv.next().getStringCellValue());
                        }
                    }
                }
            }
        }
    }
}
