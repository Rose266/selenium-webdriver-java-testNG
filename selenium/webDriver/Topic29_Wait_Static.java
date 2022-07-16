package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic29_Wait_Static {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	// Static không linh động
	// Static wait hay còn gọi là dead wait
	// Wait cứng nếu như không đủ-> fail + Nếu như thỏa mãn điều kiện rồi vẫn chờ hết timeout
	
	// Implicit/Explicit/Fluent là loại wait linh động nếu như thỏa mãn đk thì ko cần chờ hết timeout
	
	@Test
	public void TC_0_Equal() throws InterruptedException {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//loading icon biên mất
		Thread.sleep(5000);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
	}
	@Test
	public void TC_1_Less() throws InterruptedException {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		// 3s không đủ để loading icon biến mất
		Thread.sleep(3000);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
	}
	@Test
	public void TC_2_Greater() throws InterruptedException {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//10s dư để loading icon biến mất
		Thread.sleep(10000);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
	}
	@Test
	public void TC_3() {
		
	}
	@AfterClass
	public void afterClass(){
		driver.quit();
	}
	
}
