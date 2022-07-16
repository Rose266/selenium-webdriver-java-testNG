package webDriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Implicit_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Case 1
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Case 2 
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Case 1 và Case 2 giống nhau vì Implicit Wait chỉ ảnh hưởng tới Element
	}
	
	//@Test
	public void TC_03_Implicit_Wait_0s(){
		
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		//  timeout cuả implicit  là 0s-> không có cơ chế tìm lại-> Fail
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	//@Test
	public void TC_04_Implicit_Wait_10s(){
	
		//  timeout cuả implicit  là 10s-> Pass
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	@Test
	public void TC_05_Implicit_Wait_2Implicit(){
	
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	@Test
	public void TC_06_Implicit_Wait_2Implicit(){
	
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	@Test
	public void TC_07() {
		// Tc này pass vì nó nhận timeout từ TC trước
		// Dùng implicit được set ở step gần nhất=> Chỉ cần xét duy nhất 1 lần ở before
		
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	@AfterClass
	public void afterClass(){
		driver.quit();
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Hàm đo thời gian xem trả về thời gian  bao nhiêu
	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}
	
}
