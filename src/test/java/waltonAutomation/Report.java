package waltonAutomation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.io.Files;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.appium.java_client.android.AndroidDriver;

public class Report {
	  private AndroidDriver driver;
	  
    public Report(AndroidDriver driver){
    	
    	this.driver = driver;
    	
    	
    }
 public void word_Document(JsonArray alldata) throws IOException{
	 
	  XWPFDocument document = new XWPFDocument();
      //Write the Document in file system
      FileOutputStream out = new FileOutputStream(new File("tablecreatedWord" + "_" + "ccc" + ".docx"));

     
      
//     for(JsonElement jsonElement: alldata){
//   	  String imagePath="";
//   	//create Paragraph
//
//      XWPFParagraph paragraph = document.createParagraph();
//          XWPFRun run = paragraph.createRun();
//    	  
//    	  
//    	  
//    	  JsonObject jsonObject = jsonElement.getAsJsonObject();
//    	  String desc = jsonObject.get("description").getAsString();
//    	  String getText = jsonObject.get("getText").getAsString();
// 	       imagePath = jsonObject.get("imageName").getAsString();
// 	       
//  	       System.out.println("i love description" + desc);
//    	   System.out.println("i love image" + imagePath);
//           run.setText("Description: " + desc +" Screen Shot Path: "+imagePath+ "Text: "  +getText + " \n");
//   	  
//      }
      
     // XWPFTable table = document.createTable();
      //XWPFTableRow row = table.getRow(0); // First row  
      // Columns  
      System.out.println(alldata.size());
      
         XWPFTable table = document.createTable();
         XWPFTableRow tableRowOne = table.getRow(0);
         tableRowOne.getCell(0).setText("Description");
         tableRowOne.addNewTableCell().setText("Get Text");
         tableRowOne.addNewTableCell().setText("Image Path");
      
         for(int i=0;i<alldata.size()-1;i++){
    	  String imagePath="";

    	  JsonObject jsonObject = alldata.get(i).getAsJsonObject();
    	  String desc = jsonObject.get("description").getAsString();
    	  String getText = jsonObject.get("getText").getAsString();
 	       imagePath = jsonObject.get("imageName").getAsString();
 	       
 	       System.out.println(desc);
 	       System.out.println(getText);
 	       System.out.println(imagePath);

 	      XWPFTableRow tableRowTwo = table.createRow();
 	      
 	      tableRowTwo.getCell(0).setText(desc);
 	      tableRowTwo.getCell(1).setText(getText);
 	      tableRowTwo.getCell(2).setText(imagePath);
  	      
	  
    	  
      } 
      

      
     document.write(out);
     //Close document
     // out.close();
     System.out.println("createdWord" + "_" + "Tasnim Hosen" + ".docx" + " written successfully");
	 
	 
	 
 }	
 
 public String  screen_shot() throws IOException {
     String route = "";
     Date date=new Date();
     
     DateFormat dateOnly = new SimpleDateFormat();
     
      int dd=date.getDate();
     int mm=date.getMonth();
     int yy=date.getYear();
     
     int hh=date.getHours();
     int min=date.getMinutes();
     int ss=date.getSeconds();

     String DateTime=dd+mm+yy+"_"+hh+min+ss;
    // C:\Users\Tasnim\Desktop\selenium
    // C:\Users\39309\Desktop\ScreenShort
    // String Path="C:\\Users\\39309\\Desktop\\ScreenShort"+DateTime;
     String Path="C:\\Users\\39309\\Desktop\\ScreenShort\\image"+DateTime;
    
   //  String Path="D:\\image"+DateTime;
     Path=Path+".png";
     
      route = Path;
     
   

//Take ScreenShot
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
   //FileUtils.copyFile(scrFile, new File(Path), true);
    Files.copy(scrFile, new File(Path));
    return route;
}

}
