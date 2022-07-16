package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
// mới xem đến 34:56
public class Topic_31_Fluent_Wait {
 WebDriver driver;
 WebDriverWait explicitWait;
 FluentWait <WebDriver> fluentWait;
 String projectPath = System.getProperty("user.dir");
 @BeforeClass
 public void beforeClass() {
	 System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\gecko.driver.exe");
	 driver = new FirefoxDriver ();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
 }
 @Test
 public void TC_01() {
	 
 }
 @Test
 public void TC_02() {
	 
 }
 @Test
 public void TC_03() {
	 
 }
 @Test
 public void TC_04() {
	 
 }
 @Test
 public void TC_05() {
	 
 }
@AfterClass
public void afterClass () {
	
}
}
