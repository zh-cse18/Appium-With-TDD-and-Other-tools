package ExcelOperation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.Table.Cell;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class WriteInExcel {
	
	static XSSFWorkbook workBook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	
	Object empsheet[][] = {{"Name","Place","MobileNumber"},
						   {"Zahid", "Satkhira", "01746484619"},
						   {"Hassan", "Gazipur", "01678864115"}
						   };
		
	public static void main(String arg[]) throws InterruptedException, IOException {
		WriteInExcel createObj = new WriteInExcel();		
		 createObj.writeMultipleDataInExcel();
		//writeSingleDataIntoASpecificColumn();	
	}
	
	public void writeMultipleDataInExcel() throws FileNotFoundException, IOException {
		workBook = new XSSFWorkbook();
		sheet = workBook.createSheet("Sheet2");
		
		for(int r=0; r < empsheet.length; r++) {
			row = sheet.createRow(r);
			
			for(int c = 0; c < empsheet[0].length; c++) {
				
				Object value = empsheet[r][c];
				cell = row.createCell(c);
				cell.setCellValue((String)value);
				
			}
					
		}
		workBook.write(new FileOutputStream("./data/datasheet.xlsx"));
		workBook.close();
		
	}
		
	public static void writeSingleDataIntoASpecificColumn() throws IOException {
					
		try {
			System.out.println("1 ");
			
			workBook = new XSSFWorkbook();
			System.out.println("2 ");
			sheet = workBook.createSheet("Sheet2");
			System.out.println("3");
			
			row = sheet.createRow(0);
			System.out.println("4 ");
			cell =  row.createCell(1);
			cell.setCellValue("Hassan");
			workBook.write(new FileOutputStream("./data/datasheet.xlsx"));
			workBook.close();
			
			System.out.println("yes ");
				
		} 
		catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());			
		}
		
		
	}
	
}
