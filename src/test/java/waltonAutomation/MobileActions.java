package waltonAutomation;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.Dimension;
 
import java.time.Duration;
 
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
 
//**********************************************************************************************************
//Author: Sazzad Ahmmed Mohon
//Description: This is the main class of MobileActions such as swipe, tap, multitouch, etc.
//**********************************************************************************************************

public class MobileActions {
 
    private AndroidDriver driver;
 
    public MobileActions (AndroidDriver driver) {
        this.driver = driver;
    }
 
    //Tap to an element for 250 milliseconds
    public void tapByElement (AndroidElement androidElement) {
        new TouchAction(driver)
                .tap(tapOptions().withElement(element(androidElement)))
                .waitAction(waitOptions(Duration.ofMillis(0))).perform();
    }
 
    //Tap by coordinates
    public void tapByCoordinates (int x,  int y) {
        new TouchAction(driver)
                .tap(point(x,y))
                .waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }
 
    //Press by element
    public void pressByElement (AndroidElement element, long seconds) {
        new TouchAction(driver)
                .press(element(element))
                .waitAction(waitOptions(ofSeconds(seconds)))
                .release()
                .perform();
    }
 
    //Press by coordinates
    public void pressByCoordinates (int x, int y, long seconds) {
        new TouchAction(driver)
                .press(point(x,y))
                .waitAction(waitOptions(ofSeconds(seconds)))
                .release()
                .perform();
    }
 
    //Horizontal Swipe by percentages
    public void horizontalSwipeByPercentage (double startPercentage, double endPercentage, double anchorPercentage) {
//        Dimension size = driver.manage().window().getSize();
//        int anchor = (int) (size.height * anchorPercentage);
//        int startPoint = (int) (size.width * startPercentage);
//        int endPoint = (int) (size.width * endPercentage);
// 
//        new TouchAction(driver)
//                .longPress(point(startPoint, anchor))
//                .waitAction(waitOptions(ofMillis(500)))
//                .moveTo(point(endPoint, anchor))
//                .release().perform();
       // new TouchAction(driver).longPress(435, 1237).waitAction(Duration.ofMillis(313)).moveTo(453, 655).release().perform();

      //  new TouchAction(driver).press(391, 1171).waitAction(Duration.ofMillis(418)).moveTo(311, 293).release().perform();
 
        new TouchAction(driver).press(PointOption.point(715, 1781)).waitAction(waitOptions(ofMillis(1066))).moveTo(PointOption.point(681, 837)).release().perform();        
      //  new TouchAction(driver).press(PointOption.point(180, 200)).waitAction(waitOptions(ofMillis(1066))).moveTo(PointOption.point(180, 220)).release().perform();        

    }
 
    //Vertical Swipe by percentages
    public void verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);
 
        new TouchAction(driver)
                .press(point(anchor, startPoint))
                .waitAction(waitOptions(ofMillis(2000)))
                .moveTo(point(anchor, endPoint))
                .release().perform();
    }
 
    //Swipe by elements
    public void swipeByElements (AndroidElement startElement, AndroidElement endElement) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
 
        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
 
        new TouchAction(driver)
                .press(point(startX,startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endX, endY))
                .release().perform();
    }
    
 
    //Multitouch action by using an android element
    public void multiTouchByElement (AndroidElement androidElement) {
        TouchAction press = new TouchAction(driver)
                .press(element(androidElement))
                .waitAction(waitOptions(ofSeconds(1)))
                .release();
 
        new MultiTouchAction(driver)
                .add(press)
                .perform();
    }
}