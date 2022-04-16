package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_11_Elementex_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By fullNameBy = By.xpath("//input[@id='txtFirstname']");
	By emailAddressBy = By.xpath("//input[@id='txtEmail']");
	By confirmEmailAddressBy = By.xpath("//input[@id='txtCEmail']");
	By passwordBy = By.xpath("//input[@id='txtPassword']");
	By confirmPasswordBy = By.xpath("//input[@id='txtCPassword']");
	By telephoneNumberBy = By.xpath("//input[@id='txtPhone']");
	By fullNameErrorBy = By.xpath("//label[@id='txtFirstname-error']");
	By emailAddressErrorBy = By.xpath("//label[@id='txtEmail-error']");
	By confirmEmailAddressErrorBy = By.xpath("//label[@id='txtCEmail-error']");
	By passwordErrorBy = By.xpath("//label[@id='txtPassword-error']");
	By confirmPasswordErrorBy = By.xpath("//label[@id='txtCPassword-error']");
	By telephoneNumberErrorBy = By.xpath("//label[@id='txtPhone-error']");
	
	@BeforeClass 
	public void beforeClass () {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod () {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	@Test
	public void Register_01_With_Empty_Data () {
		
		Assert.assertEquals(driver.findElement(fullNameErrorBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailAddressErrorBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailAddressErrorBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(telephoneNumberErrorBy).getText(), "Vui lòng nhập số điện thoại. ");
	}
	@Test
	public void Register_02_With_Invalid_Email () {
		driver.findElement(fullNameBy).sendKeys("Nguyen Nhung");
		driver.findElement(emailAddressBy).sendKeys("123@345@789");
		driver.findElement(confirmEmailAddressBy).sendKeys("123@345@789");
		driver.findElement(passwordBy).sendKeys("123456");
		driver.findElement(confirmPasswordBy).sendKeys("123456");
		driver.findElement(telephoneNumberBy).sendKeys("0123456789");
		Assert.assertEquals(driver.findElement(emailAddressErrorBy).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailAddressErrorBy).getText(), "Vui lòng nhập email hợp lệ");
		
	}
	@Test
	public void Register_03_With_Incorrect_Confirm_Email() {
		driver.findElement(fullNameBy).sendKeys("Nguyen Nhung");
		driver.findElement(emailAddressBy).sendKeys("nguyenthinhung@gmail.com");
		driver.findElement(confirmEmailAddressBy).sendKeys("nghuyennhung@gmail.com");
		driver.findElement(passwordBy).sendKeys("Nhung123");
		driver.findElement(confirmPasswordBy).sendKeys("Nhung123");
		driver.findElement(telephoneNumberBy).sendKeys("0123456789");
		Assert.assertEquals(driver.findElement(confirmEmailAddressErrorBy).getText(), "Email nhập lại không đúng");
		
	}
	@Test
	public void Register_04_With_Password_Less_Than_6_Characters () {
		driver.findElement(fullNameBy).sendKeys("Nguyen Nhung");
		driver.findElement(emailAddressBy).sendKeys("nguyenthinhung@gmail.com");
		driver.findElement(confirmEmailAddressBy).sendKeys("nguyenthinhung@gmail.com");
		driver.findElement(passwordBy).sendKeys("12345");
		driver.findElement(confirmPasswordBy).sendKeys("12345");
		driver.findElement(telephoneNumberBy).sendKeys("0123456789");
		Assert.assertEquals(driver.findElement(passwordErrorBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	@Test
	public void Register_05_With_Incorrect_Confirm_Password () {
		driver.findElement(fullNameBy).sendKeys("Nguyen Nhung");
		driver.findElement(emailAddressBy).sendKeys("nguyenthinhung@gmail.com");
		driver.findElement(confirmEmailAddressBy).sendKeys("nguyenthinhung@gmail.com");
		driver.findElement(passwordBy).sendKeys("Nhung123");
		driver.findElement(confirmPasswordBy).sendKeys("Nhung456");
		driver.findElement(telephoneNumberBy).sendKeys("0123456789");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorBy).getText(), "Mật khẩu bạn nhập không khớp");
		
	}
	@Test
	public void Register_06_With_Invalid_Phone_Number () {
		driver.findElement(fullNameBy).sendKeys("Nguyen Nhung");
		driver.findElement(emailAddressBy).sendKeys("nguyenthinhung@gmail.com");
		driver.findElement(confirmEmailAddressBy).sendKeys("nguyenthinhung@gmail.com");
		driver.findElement(passwordBy).sendKeys("Nhung123");
		driver.findElement(confirmPasswordBy).sendKeys("Nhung123");
		driver.findElement(telephoneNumberBy).sendKeys("0123456");
		Assert.assertEquals(driver.findElement(telephoneNumberErrorBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
	}
	@AfterClass
	public void afterClass () {
		driver.quit();
	}
}
