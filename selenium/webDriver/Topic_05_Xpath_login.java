package webDriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_05_Xpath_login{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullName, emailAddress, passWord;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS );
		driver.manage().window().maximize();
		
		firstName= "Nhung";
		lastName= "Nguyen";
		fullName= firstName + " " + lastName;
		emailAddress="nguyennhung" + generateRandomNumber() + "@gmail.com";
		passWord= "123456";
		
	}
	
	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field." );
	}
	@Test
	public void TC_02_Login_With_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("1234@1234.1234");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");	
	}
	@Test
	public void TC_03_Login_With_Password_Less_Than_6_Words() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("nguyennhung@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12345");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC_04_Login_With_Incorrect_Email_And_Passwords() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("nguyenthihongnhung9966@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("fgh1234");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}
	@Test
	public void TC_05_Create_A_New_Account() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(passWord);
		driver.findElement(By.id("confirmation")).sendKeys(passWord);
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		String contactInforText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInforText.contains(fullName));
		Assert.assertTrue(contactInforText.contains(emailAddress));
		
	}
	@Test
	public void TC_06_Login_With_Valid_Email_And_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(passWord);
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		String contactInforText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInforText.contains(fullName));
		Assert.assertTrue(contactInforText.contains(emailAddress));
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	
	}
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
		
	}

}
