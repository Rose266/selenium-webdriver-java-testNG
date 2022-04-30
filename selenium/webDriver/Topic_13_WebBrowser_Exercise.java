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

public class Topic_13_WebBrowser_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass () {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver () ;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@BeforeMethod
	public void beforeMethod () {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();	}
	@Test
	public void Verify_01_URL() {
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
	}
	@Test
	public void Verify_02_Title() {
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	@Test
	public void Navigate_03_Function() {
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	@Test
	public void Get_04_Page_Source_code() {
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	@AfterClass
	public void afterClass () {
		driver.quit();
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
		
}
