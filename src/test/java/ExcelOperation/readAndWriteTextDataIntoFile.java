package ExcelOperation;

import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class readAndWriteTextDataIntoFile {

	public static void main(String[] args) throws IOException {
		
		//create a directory
		File file = new File("Zahid");
		file.mkdir();
		
		//delete a Directory
		//file.delete();
		
		//create a text file into zahid Directory
		
		File file1= new File("./Zahid/simple.txt");
		file1.createNewFile();
		
		Formatter formatter = new Formatter("./Zahid/simple.txt");
		Object i = formatter.format("%s","Zahid");
		
		Object name  = 13254;
		System.out.println(i);
		System.out.println(name);
		formatter.close();
		
		Scanner scanner = new Scanner(file1);
		System.out.println(scanner.next());

	}

}