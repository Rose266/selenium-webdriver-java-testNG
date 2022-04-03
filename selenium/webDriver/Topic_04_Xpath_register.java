package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_04_Xpath_register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Regrister_With_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại");
		}
	
	@Test
	public void TC_02_Register_With_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Nhung");
		driver.findElement(By.id("txtEmail")).sendKeys("nguyenthihongnhung.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("nguyenthihongnhung.com");
		driver.findElement(By.id("txtPassword")).sendKeys("huy12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("Huy12345");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		}
	
	@Test
	public void TC_03_Register_With_Incorrect_Confirm_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Hong Nhung");
		driver.findElement(By.id("txtEmail")).sendKeys("nguyennhung@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("nguyennhung6@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Hoa12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("Hoa12345");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		}
	
	@Test
	public void TC_04_Register_With_Password_Less_Than_6_Words() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Hong Nhung");
		driver.findElement(By.id("txtEmail")).sendKeys("nguyennhung@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("nguyennhung@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12334");
		driver.findElement(By.id("txtCPassword")).sendKeys("12334");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		}
	
	@Test
	public void TC_05_Register_With_Incorrect_Confirm_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Hong Nhung");
		driver.findElement(By.id("txtEmail")).sendKeys("nguyennhung@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("nguyennhung@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Hoa12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("Hoa123");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
		
	}
	
	@Test
	public void TC_06_Register_With_Invalid_Phone_Number() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Hong Nhung");
		driver.findElement(By.id("txtEmail")).sendKeys("nguyennhung@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("nguyennhung@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Hoa12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("Hoa12345");
		driver.findElement(By.id("txtPhone")).sendKeys("1123456789");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
