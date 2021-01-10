package ExcelOperation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.junit.After;
import org.junit.Before;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class GoogleChromeUsingAppiumStudio {

  private String reportDirectory = "reports";
  private String reportFormat = "xml";
  private String testName = "Untitled";
  protected AndroidDriver<AndroidElement> driver = null;

  DesiredCapabilities dc = new DesiredCapabilities();
  
  @Before
  public void setUp() throws MalformedURLException {
      dc.setCapability("reportDirectory", reportDirectory);
      dc.setCapability("reportFormat", reportFormat);
      dc.setCapability("testName", testName);
      dc.setCapability(MobileCapabilityType.UDID, "69469037");
      dc.setBrowserName(MobileBrowserType.CHROME);
      driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
      driver.setLogLevel(Level.INFO);
  }

  @Test
  public void testUntitled() {
      driver.get("http://");
      driver.context("NATIVE_APP");
      driver.findElement(By.xpath("//*[@contentDescription='Web View']")).click();
      driver.findElement(By.xpath("//*[@id='url_bar']")).click();
      driver.context("WEBVIEW_1");
  
      driver.executeScript("seetest:client.deviceAction(\"Enter\")");
      driver.findElement(By.linkText("Images - Google")).click();
      new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='q']")));
      driver.findElement(By.xpath("//*[@name='q']")).sendKeys("youtube");
      driver.executeScript("seetest:client.deviceAction(\"Enter\")");
    
      driver.findElement(By.xpath("//*[@class='android.widget.FrameLayout' and ./*[@text='yyoutube - Google Search']]")).click();
      driver.findElement(By.xpath("(((((//*[@class='android.view.View' and ./parent::*[@class='android.view.View' and ./parent::*[@class='android.view.View' and (./preceding-sibling::* | ./following-sibling::*)[./*[@text='Close']] and ./parent::*[@class='android.view.View'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.View']]]]/*[@class='android.view.View'])[3]/*[@class='android.view.View'])[4]/*[@class='android.view.View'])[3]/*[@class='android.widget.ListView'])[1]/*/*/*/*[@text='YouTube | Google Developers' and @class='android.widget.Image' and ./parent::*[./parent::*[@text='YouTube | Google Developers' and (./preceding-sibling::* | ./following-sibling::*)[@contentDescription='YouTube | Google Developers developers.google.com']]]])[1]")).click();
      driver.findElement(By.xpath("//div[@class=\"tvh9oe BIB1wf\"]//c-wiz[@class=\"Y6heUd\"]//div[@class=\"OUZ5W\"]//div[@class=\"zjoqD\"]//div[@class=\"qdnLaf isv-id\"]//div[@class=\"v4dQwb\"]//img[@class=\"n3VNCb\"]")).click();
      driver.findElement(By.xpath("//*[@text='YouTube | Google Developers' and @class='android.widget.Image' and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.View']]")).click();

      driver.findElement(By.linkText("YouTube for Pressblog.youtube")).click();

      driver.findElement(By.xpath("//*[@text='YouTube | Google Developers' and @class='android.widget.Image']")).click();

      driver.findElement(By.linkText("OK")).click();
  }

  @After
  public void tearDown() {
      driver.quit();
  }
}
