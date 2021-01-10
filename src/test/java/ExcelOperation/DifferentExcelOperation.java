package ExcelOperation;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DifferentExcelOperation {
	
	
	public static void main(String arg[]) throws IOException {
		getRowCount();
		getRowCountByFileStream();
	}

	public static void getRowCount() throws IOException {
		
			
		try {
			
			String dir = System.getProperty("user.dir");
			String excelPath = dir + "./data/datasheet.xlsx";
			XSSFWorkbook workBook = new XSSFWorkbook(excelPath);
			XSSFSheet sheet = workBook.getSheet("Sheet1");
			int rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("row number = "+  rowCount);
			
		}
		catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());			
		}
		
		
	}
	
	public static void getRowCountByFileStream() throws IOException {	
		
		try {
		
			String excelPath ="./data/datasheet.xlsx";
			FileInputStream fis = new FileInputStream(excelPath);
			Workbook wb = WorkbookFactory.create(fis);
			
			Sheet sh = (Sheet) wb.getSheet("Sheet1");
			int rowCount = ((XSSFSheet) sh).getLastRowNum();
			System.out.println( "Row num = " + rowCount);
			
		}
		catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());			
		}
		
		
	}
}
