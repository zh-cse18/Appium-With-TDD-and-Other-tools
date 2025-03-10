package ExcelOperation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.io.Files;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ChaldaTestingWithAppiumScript {
	public static void main(String arg[]) throws InterruptedException, MalformedURLException{
	   
	   desireCapabilitiesForAppium();
				
	}
		
	public static void desireCapabilitiesForAppium() throws MalformedURLException, InterruptedException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		dc.setCapability(MobileCapabilityType.APP, "D:\\Different Apk\\chaldal.apk");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(url,dc);
		Thread.sleep(15000);
		try {
			File file  = driver.getScreenshotAs(OutputType.FILE);
			String fileName = UUID.randomUUID().toString();
			File targetFile = new File(fileName + ".jpg");
			Files.copy(file, targetFile);
			
		}catch(Exception e) {}
		
		try {
			WebElement menu = driver.findElementByXPath("hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.support.v4.widget.l/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView\r\n");
			menu.click();
			
		}catch(Exception e) {
			System.out.println( "1" +e.getCause());
			
		}
		
		Thread.sleep(10000);
		try {
			WebElement loginMenu = driver.findElementByXPath("hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.support.v4.widget.l/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup//android.widget.TextView[1]\r\n");
			loginMenu.click();
			
		}catch(Exception e) {
			System.out.println("2" +e.getCause());
			System.out.println("2" +e.getMessage());
			
		}
		try {
			 System.out.println(driver.findElement(By.xpath("//android.widget.EditText[@bounds='[83,552][998,690]']")).isDisplayed());
			
		}catch(Exception e) {
			
			System.out.println("3" +e.getCause());
			System.out.println(e.getMessage());
			
		}
		
		try {
			driver.findElement(By.xpath("//android.widget.EditText[@bounds='[83,552][998,690]']")).sendKeys("01678864115");
			Thread.sleep(10000);
			
			try {
				File file  = driver.getScreenshotAs(OutputType.FILE);
				String fileName = UUID.randomUUID().toString();
				File targetFile = new File(fileName + ".jpg");
				Files.copy(file, targetFile);
				
			}catch(Exception e) {}
			
			
		}catch(Exception e) {
			
			System.out.println("4" +e.getCause());
			System.out.println(e.getMessage());
			
			
		}
		
	
}}
