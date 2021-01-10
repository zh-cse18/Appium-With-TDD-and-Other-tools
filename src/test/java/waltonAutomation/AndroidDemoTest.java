 package waltonAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;


public class AndroidDemoTest extends BaseTest {
   	private static final Object[] String = null;
   	protected AndroidDriver<AndroidElement> driver = null;
	 private String testName = "Untitled";
	 public String getText="";
	 public String apkPath = "";
     JsonArray jReport = new JsonArray();
	 private int item_count = 0;
	 private int total_item = 0;
	 private boolean loopRunning = false; 
	 private int startLoopIndex = 0, endLoopIndex = 0;
	 int w=0;

	@BeforeMethod   
    public void setUp() throws Exception {
		 System.out.println("1");
		 FileInputStream file= new FileInputStream("./data/datasheet.xlsx");
		 System.out.println("2");
		 XSSFWorkbook workbook= new XSSFWorkbook(file);
	     XSSFSheet sheet= workbook.getSheetAt(0);
	     System.out.println("3");
	     XSSFRow row0= sheet.getRow(0);
	     XSSFRow row1= sheet.getRow(1);
	  
	     
	    String appPackage =    row0.getCell(1).toString();
	    System.out.println(appPackage);
	    String appActivity =    row1.getCell(1).toString();
	    System.out.println(appActivity);
	    String apkPath =    row1.getCell(2).toString();

	     
	     
        dc.setCapability("testName", testName);      
       // dc.setCapability(MobileCapabilityType.UDID, "QSUC6TV4DUUO6D8P");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
		 
	    dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
		
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		
		dc.setCapability(MobileCapabilityType.APP, apkPath);
		System.out.println(apkPath);
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        System.out.println("5");
        
        
        driver.setLogLevel(Level.INFO);  
        
        System.out.println("6");
    }
	
	
	@Test(priority=1)
	 public void  excelVaue() throws InterruptedException, IOException{
		
		 System.out.println("started xcel value");
		int newstate = 0;
		
		FileInputStream file= new FileInputStream("./data/datasheet.xlsx");
		 System.out.println("started xcel value");
        Thread.sleep(7500);
        
        JsonArray jsonArray = new JsonArray();
               
        XSSFWorkbook workbook= new XSSFWorkbook(file);
        XSSFSheet sheet= workbook.getSheetAt(0);
          int x= sheet.getLastRowNum();
          
          for(int i=3;i<=x;i++){
        	  XSSFRow row= sheet.getRow(i);
        	  
        	  
        	  JsonObject jsonObject = new JsonObject();
        		 
    		  jsonObject.addProperty("xpath", row.getCell(0).toString());
    		  jsonObject.addProperty("action", row.getCell(1).toString());
    		  jsonObject.addProperty("value", row.getCell(2).toString());
    		  jsonObject.addProperty("skip", row.getCell(3).toString());
    		  jsonObject.addProperty("screenshot", row.getCell(4).toString());
    		  jsonObject.addProperty("description", row.getCell(5).toString());
    		  jsonObject.addProperty("loop", row.getCell(6).toString());
    		  jsonObject.addProperty("counter", row.getCell(7).toString());
    		  jsonObject.addProperty("state", row.getCell(8).toString());
	          jsonArray.add(jsonObject);
	         
        	  
          }
             
          
          file.close();
         
          
          for(int i=0;i<jsonArray.size();i++){  //JsonElement jsonElement: jsonArray
        	 
        	  JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
        	  String xpath = jsonObject.get("xpath").getAsString();
        	  String action = jsonObject.get("action").getAsString();
        	  String value = jsonObject.get("value").getAsString();
        	  String skip = jsonObject.get("skip").getAsString(); 
        	  String screenshot = jsonObject.get("screenshot").getAsString();
        	  String description = jsonObject.get("description").getAsString();
        	  String loop = jsonObject.get("loop").getAsString();
        	  String counter = jsonObject.get("counter").getAsString();
        	  String state = jsonObject.get("state").getAsString();
        	  System.out.println(description);
        	
        	int startstate =Integer.parseInt(state);
        	if (startstate>1){
        		
        		 newstate = startstate;
        		 w=i;
        		
        	}

        	  if(performAction(xpath,action,value,skip,screenshot,description,loop,counter,state))
        	  {   
                   jsonObject.addProperty("status", true);

        	  }
        	  else
        		  jsonObject.addProperty("status", false);
        	  
        	  if(loop.equals("start")){
        		  startLoopIndex = i;
        		  loopRunning = true;
        	  }
        	  
        	  else if(loopRunning && loop.equals("end") && item_count<=total_item){
        		  
        		  int k=startLoopIndex;
        		  
        		  while(true){
        			  JsonObject jsonObjectInLoop = jsonArray.get(k).getAsJsonObject();
                	  xpath = jsonObjectInLoop.get("xpath").getAsString();
                	  action = jsonObjectInLoop.get("action").getAsString();
                	  value = jsonObjectInLoop.get("value").getAsString();
                	  skip = jsonObjectInLoop.get("skip").getAsString(); 
                	  screenshot = jsonObjectInLoop.get("screenshot").getAsString();
                	  description = jsonObjectInLoop.get("description").getAsString();
                	  loop = jsonObjectInLoop.get("loop").getAsString();
                	  counter = jsonObjectInLoop.get("counter").getAsString();
                	  state = jsonObjectInLoop.get("state").getAsString();
                	  
                	
        			  if(performAction(xpath,action,value,skip,screenshot,description,loop,counter,state))
                		  {
                		 
        				  jsonObject.addProperty("status", true);
                		  
                		  }
                	  else
                		  jsonObject.addProperty("status", false);
        			  
        			  
        			  k++;
        			  
        			  if(loop.equals("end")) k = startLoopIndex;
        			  
        			  if(item_count>=total_item && loop.equals("end")) {
        				  
        				  System.out.println("loop end");
        				  
        				  loopRunning = false;
        				  startLoopIndex = endLoopIndex = item_count = total_item = 0;
        				  break;
        			  }
        			  
        			  
        		  }
        	  }
        	  
        	if (startstate==0){
        	int	end=i;
        		
        		int endstate = startstate;
        		for (int t=0;t<newstate;t++){
        			System.out.println("total count"+": "+t);
        			 for(int l=w;l<=end;l++){  //JsonElement jsonElement: jsonArray
        	        	 
        	        	  JsonObject jsonObject1 = jsonArray.get(l).getAsJsonObject();
        	        	  String xpath1 = jsonObject1.get("xpath").getAsString();
        	        	  String action1 = jsonObject1.get("action").getAsString();
        	        	  String value1 = jsonObject1.get("value").getAsString();
        	        	  String skip1 = jsonObject1.get("skip").getAsString(); 
        	        	  String screenshot1 = jsonObject1.get("screenshot").getAsString();
        	        	  String description1 = jsonObject1.get("description").getAsString();
        	        	  String loop1 = jsonObject1.get("loop").getAsString();
        	        	  String counter1 = jsonObject1.get("counter").getAsString();
        	        	  String state1 = jsonObject1.get("state").getAsString();
        	        	  if(performAction(xpath1,action1,value1,skip1,screenshot1,description1,loop1,counter1,state1))
        	        	  {   
        	                   jsonObject.addProperty("status", true);

        	        	  }
        	        	  System.out.println(description);
        			 }
        			
        			
        		}
        		
        		
        	}  

        	  //reportScreenshot.word_Document(description);
        	  
        	  
          }
          
          //Report reportScreenshot = new Report(driver); 
          //reportScreenshot.word_Document(jsonArray);
          //System.out.println(jsonArray+"");
		
	}
	
	
	
	private boolean performAction(String xpath, String action, String value, String skip,String screenshot,String description,String loop,String counter,String state ) throws InterruptedException, IOException{
 
		//List list1=driver.findElements(By.xpath("//*[@id='modes_and_settings_level2_recycler']"));			 
		 boolean status = false; 
		// int k=Integer.parseInt(counter);  
	
	
		 
		new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		  if(action.equals("click")){	
		
			   getText= (driver.findElement(By.xpath(xpath)).getText()).isEmpty()?"Not Found":driver.findElement(By.xpath(xpath)).getText();
			   driver.findElement(By.xpath(xpath)).click();
			 
		     	  System.out.println("our text"+getText);
                  if(screenshot.equals("yes")){
				 
             	  Report reportScreenshot = new Report(driver); 
 	        	  String imageName=  reportScreenshot.screen_shot();
 	        	  System.out.println(description);
 	        	  reportJson(imageName,getText,description);
				 				 
			 }
		
			 Thread.sleep(2000);	 
			 status = true;
		}
		
		else if(action.equals("back")){
			Thread.sleep(500);	
			driver.navigate().back();
			Thread.sleep(500);	
		}
		
//		else if(action.equals("text")){
//			 text = driver.findElement(By.xpath(xpath)).getText();
//			 System.out.println(text);
//			 System.out.println("Testhhhhhhhhhh");
//			
//			
//			
//		}
		
		else if(action.equals("sendkeys") & skip.equals("no") ){
			 String getText= (driver.findElement(By.xpath(xpath)).getText()).isEmpty()?"Not Found":driver.findElement(By.xpath(xpath)).getText();
			  driver.findElement(By.xpath(xpath)).sendKeys(value);	
			 
			  System.out.println("our text"+getText);
			  if(screenshot.equals("yes")){
				  System.out.println(description);
              	  Report reportScreenshot = new Report(driver); 
  	        	  String imageName=  reportScreenshot.screen_shot();
  	        	  reportJson(imageName,getText,description);
				 				 
			 }
		      status = true;
		}
		
		
		else if (loop.equals("start") & action.equals("click")){
			 String getText= (driver.findElement(By.xpath(xpath)).getText()).isEmpty()?"Not Found":driver.findElement(By.xpath(xpath)).getText();
			 driver.findElement(By.xpath(xpath)).click();
			
			
			 if(screenshot.equals("yes")){
				       System.out.println(description);
                	  Report reportScreenshot = new Report(driver); 
    	        	  String imageName=  reportScreenshot.screen_shot();
    	        	  reportJson(imageName,getText,description);
				 				 
			 }
		
			 
			
		}
		
		else if (action.equals("container")& skip.equals("no")){
            if(item_count>=5){
			 MobileActions scroll = new MobileActions(driver);
		     scroll.horizontalSwipeByPercentage(0.9, 0.7, 0.5);
		     WebElement containerList =  driver.findElement(By.xpath(xpath));
			   	List<WebElement> containerListItems = containerList.findElements(By.className(value));
				total_item = containerListItems.size();
				
				System.out.println(total_item);
            }
			 
			WebElement containerList =  driver.findElement(By.xpath(xpath));
		   	List<WebElement> containerListItems = containerList.findElements(By.className(value));
		

			containerListItems.get(item_count).click();
		    String Text=containerListItems.get(item_count).getText();
		    String getText= (Text).isEmpty()? "Not Found":Text;   
		    System.out.println("our text"+getText);
		    
		 
		   
		    
			if(screenshot.equals("yes")){
				 System.out.println(description);
				  Report reportScreenshot = new Report(driver); 
	        	  String imageName=  reportScreenshot.screen_shot();
	        	  reportJson(imageName,getText,description);
	        	 
				 				 
			 }
			total_item = containerListItems.size();
			
			System.out.println(total_item);
			
			item_count++;
			
			
			System.out.println("Container Size :"+containerListItems.size()+"");
			 

		}
		
		
		return status;

	}
	
	public void reportJson(String imageName,String getText, String description){
		  
 		 System.out.println(imageName);
 		 System.out.println(getText);
 		 System.out.println(description);
 		 
 		 JsonObject jObject = new JsonObject();
 		 jObject.addProperty("imageName", imageName);
 		 jObject.addProperty("getText", getText);
 		 jObject.addProperty("description", description);
 		 jReport.add(jObject);

	}
	

	
	@AfterMethod
	public void tearDown() throws IOException{
		Report reportScreenshot = new Report(driver); 
        reportScreenshot.word_Document(jReport);
		driver.quit();
	}
	
}
