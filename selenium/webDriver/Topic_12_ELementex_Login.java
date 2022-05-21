package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_12_ELementex_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextboxBy = By.xpath("//input[@id='email']");
	By passwordTextboxBy = By.xpath("//input[@id='pass']");
	By emailTextBoxErrorBy = By.xpath("//div[@id='advice-required-entry-email']");
	By passwordTextboxErrorBy = By.xpath("//div[@id='advice-required-entry-pass']");
	
	String firstName, lastName, fullName, emailAddress, password, confirmPassword;
	@BeforeClass
	public void beforeClass () {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstName = "Nhung";
		lastName = "Nguyen";
		fullName = firstName + " " + lastName;
		emailAddress = "nguyenthihongnhung" + generateRandomNumber() + "@gmail.com";
		password = "123456";
	}
	@BeforeMethod
	public void beforeMethod () {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}
	@Test
	public void Login_01_With_Empty_Email_And_Password() {
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(emailTextBoxErrorBy).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(passwordTextboxErrorBy).getText(), "This is a required field.");
		
	}
	@Test
	public void Login_02_With_Invalid_Email() {
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys("1234512345@1234.1234");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	@Test
	public void Login_03_With_Password_Less_Than_6_Characters() {
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys("nguyenthinhung@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("1234");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	@Test
	public void Login_04_Create_A_New_Account() {
		driver.findElement(By.xpath("//span[text() = 'Create an Account']")).click();
		driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
		driver.findElement(By.cssSelector("#email_address")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.cssSelector("#confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//span[text()='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName +"!");
		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div[@class='box-content']")).getText();
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		
		
	}
	@Test
	public void Login_05_With_Incorrect_Email() {
		driver.findElement(emailTextboxBy).sendKeys("nguyenmai@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".error-msg span")).getText(),"Invalid login or password.");
		
	}
	@Test
	public void Login_06_With_Incorrect_Password() {
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("123458900");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".error-msg span")).getText(), "Invalid login or password.");
	}
	@Test
	public void Login_07_With_Valid_Email_And_Password() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		sleepInSecond(15);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(),"MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName +"!");
		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
	}
	@AfterClass
	public void afterClass () {
		driver.quit();
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
