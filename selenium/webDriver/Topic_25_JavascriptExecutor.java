package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_JavascriptExecutor {
WebDriver driver;
JavascriptExecutor jsExecutor;

String projectPath = System.getProperty("user.dir");
String emailAddress;
@BeforeClass
public void beforeClass () {
System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");	
driver = new FirefoxDriver();
jsExecutor = (JavascriptExecutor) driver;
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
driver.manage().window().maximize();
emailAddress = "nguynnhung" + generateRandomNumber() + "@gmail.com";
}
//@Test
public void TC_01_JE() {
	jsExecutor.executeScript("window.location = 'http://live.techpanda.org/' ");
	sleepInSecond(5);
	String domain = (String)jsExecutor.executeScript("return document.domain");
	Assert.assertEquals(domain, "live.techpanda.org");
	String pageUrl = (String)jsExecutor.executeScript("return document.URL");
	Assert.assertEquals(pageUrl, "http://live.techpanda.org/");
	WebElement element = driver.findElement(By.xpath("//a[text()='Mobile']"));
	jsExecutor.executeScript("arguments[0].click();",element);
	WebElement atcButton = driver.findElement(By.xpath("//img[@alt='Samsung Galaxy']/parent::a/following-sibling::div//button"));
	jsExecutor.executeScript("arguments[0].click()", atcButton);
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Samsung Galaxy was added to your shopping cart.");
	WebElement customerService = driver.findElement(By.xpath("//a[text()='Customer Service']"));
	jsExecutor.executeScript("arguments[0].click()", customerService);
	sleepInSecond(5);
	String pageTitle = jsExecutor.executeScript("return document.title").toString();
	Assert.assertEquals(pageTitle, "Customer Service");
	jsExecutor.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector("input#newsletter")));
	sleepInSecond(5);
	sendKeyToElementByJs("//input[@id='newsletter']", emailAddress);
	jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[@title='Subscribe']")));
	sleepInSecond(5);
	//Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "Thank you for your subscription.");
	String sText = jsExecutor.executeScript("return document.documentElement.innerText.match('" + "Thank you for your subscription." + "')[0];").toString();
	Assert.assertEquals(sText, "Thank you for your subscription.");
	sleepInSecond(5);
	jsExecutor.executeScript("window.location = 'http://demo.guru99.com/v4/'");
	sleepInSecond(5);
	String domain1 = (String)jsExecutor.executeScript("return document.domain");
	Assert.assertEquals(domain1,"demo.guru99.com");
}
//@Test
public void TC_02() {
	driver.get("https://automationfc.github.io/html5/index.html");
	jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//input[@type='submit']")));
	String msg1 = jsExecutor.executeScript("return arguments[0].validationMessage", driver.findElement(By.cssSelector("input#fname"))).toString();
	sleepInSecond(5);
	Assert.assertEquals(msg1, "Please fill out this field.");
}
@AfterClass
public void afterClass () {
	
}
public  int generateRandomNumber() {
	Random rand = new Random();
	return rand.nextInt(9999);
}
public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
}
public void sendKeyToElementByJs(String locator, String value) {
	jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
}

public void sleepInSecond (long second) {
	
	try {
		Thread.sleep(second * 1000);
	
	}catch(InterruptedException e) {
		e.printStackTrace();
	}
}

}
