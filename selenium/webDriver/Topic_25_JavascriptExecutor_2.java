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

public class Topic_25_JavascriptExecutor_2 {
WebDriver driver;
JavascriptExecutor jsExecutor;

String projectPath = System.getProperty("user.dir");
String emailAddress;
@BeforeClass
public void beforeClass () {
System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");	
driver = new FirefoxDriver();
// Nếu không có đoạn này-> báo null vì jsExecutor chưa dược khởi tạo
jsExecutor = (JavascriptExecutor) driver;
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
driver.manage().window().maximize();
emailAddress = "nguynnhung" + generateRandomNumber() + "@gmail.com";
}
//@Test
public void TC_01_TechPanda() {
	navigateToUrlByJS("http://live.techpanda.org/");
	sleepInSecond(5);
	String homePageUrl = (String) executeForBrowser("return document.URL");
	Assert.assertEquals(homePageUrl, "http://live.techpanda.org/");
	hightlightElement("//a[text()='Mobile']");
	
	clickToElementByJS("//a[text()='Mobile']");
	hightlightElement("//a[@title='Samsung Galaxy']/parent::li//button");
	clickToElementByJS("//a[@title='Samsung Galaxy']/parent::li//button");
	sleepInSecond(5);
	Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
	hightlightElement("//a[text()='Customer Service']");
	clickToElementByJS("//a[text()='Customer Service']");
	sleepInSecond(5);
	scrollToElementOnTop("//input[@id='newsletter']");
	hightlightElement("//input[@id='newsletter']");
	sendKeyToElementByJs("//input[@id='newsletter']", emailAddress);
	hightlightElement(" //button[@title='Subscribe']");
	clickToElementByJS(" //button[@title='Subscribe']");
	sleepInSecond(5);
	Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
	navigateToUrlByJS("http://demo.guru99.com/v4/");
	sleepInSecond(5);
	String demoGuruDomain = (String) executeForBrowser("return document.domain");
	Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
}
//@Test
public void TC_02() {
	driver.get("https://warranty.rode.com/");
	By registerButton = By.xpath("//button[contains(text(),'Register')]");
	driver.findElement(registerButton).click();
	sleepInSecond(2);
	By firstName = By.id("firstname");
	Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(firstName)), "Please fill out this field.");
	driver.findElement(firstName).sendKeys("Max");
	driver.findElement(registerButton).click();
	sleepInSecond(2);
	By surName = By.id("surname");
	Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;",driver.findElement(surName)), "Please fill out this field." );
	driver.findElement(surName).sendKeys("nguent");
	By emailAddress = By.xpath("//div[contains(text(),'Register')]/parent::div//input[@id='email']");
	driver.findElement(emailAddress).sendKeys("@#$%.@#$%^gmail");
	driver.findElement(registerButton).click();
	sleepInSecond(2);
	Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;",driver.findElement(emailAddress)), "Please enter an email address." );
	driver.findElement(emailAddress).clear();
	driver.findElement(emailAddress).sendKeys("nhung@gmail.com");
	driver.findElement(registerButton).click();
	sleepInSecond(2);
	By pass = By.xpath("//div[contains(text(),'Register')]/parent::div//input[@id='password']");
	Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage",driver.findElement(pass)), "Please fill out this field.");
	driver.findElement(pass).sendKeys("123456");
	driver.findElement(registerButton).click();
	sleepInSecond(2);
	By confirmPass = By.cssSelector("input#password-confirm");
	Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", driver.findElement(confirmPass)), "Please fill out this field.");
	
	
	
	
	
}
@AfterClass
public void afterClass () {
	
}
public int generateRandomNumber() {
	Random rand = new Random();
	return rand.nextInt(9999);
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

public Object executeForBrowser(String javaScript) {
	return jsExecutor.executeScript(javaScript);
}

public String getInnerText() {
	return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
}

public boolean areExpectedTextInInnerText(String textExpected) {
	String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
	return textActual.equals(textExpected);
}

public void scrollToBottomPage() {
	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}

public void navigateToUrlByJS(String url) {
	jsExecutor.executeScript("window.location = '" + url + "'");
}

public void hightlightElement(String locator) {
	WebElement element = getElement(locator);
	String originalStyle = element.getAttribute("style");
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
	sleepInSecond(1);
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
}

public void clickToElementByJS(String locator) {
	jsExecutor.executeScript("arguments[0].click();", getElement(locator));
}

public void scrollToElementOnTop(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
}

public void scrollToElementOnDown(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
}

public void sendkeyToElementByJS(String locator, String value) {
	jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
}

public void removeAttributeInDOM(String locator, String attributeRemove) {
	jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
}

public String getElementValidationMessage(String locator) {
	return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
}

public boolean isImageLoaded(String locator) {
	boolean status = (boolean) jsExecutor.executeScript(
			"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
			getElement(locator));
	return status;
}

public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
}

}
