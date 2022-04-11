package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Assert {
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
	public void TC_01() {
		// 3 hàm assert hay dùng
		// assert sinh ra để kiểm tra tính đúng đắn của dữ liệu
		
		// 1. Kiểm tra dữ liệu mà mình mong muốn là ĐÚNG
		// mong muốn Email textbox hiển thị 
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		
		// 2. Kiểm tra dữ liệu mà mình mong muốn là SAI
		// Mong muốn email textbox không hiển thị
		Assert.assertFalse(driver.findElement(By.id("email")).isDisplayed());
		
		// 3. Kiểm tra dữ liệu mà mình mong muốn với dữ liệu thực tế là BẰNG NHAU
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='search']//a[@title='My Account']")).getAttribute("placeholder"), "Search entire store here...");;
		// Truyền đúng cái mình cần tìm vào thì kiểu dữ liệu mình tự match
		
		// Tuyệt đối 2 cái bằng nhau
		Assert.assertEquals(driver.findElement(By.id("email")).getText(), "This is a required field.");
		
		// Tương đối 
		String benefitsText = driver.findElement(By.cssSelector("ul.benefits")).getText();
		Assert.assertTrue(benefitsText.contains("Faster checkout"));
		Assert.assertTrue(benefitsText.contains("Save multiple shipping addresses"));
		Assert.assertTrue(benefitsText.contains("View and track orders and more"));
	}
	

}
