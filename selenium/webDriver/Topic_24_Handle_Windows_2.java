package webDriver;

import java.util.List;
import java.util.Set;
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

public class Topic_24_Handle_Windows_2 {
WebDriver driver;
JavascriptExecutor  jsExecutor;
String projectPath = System.getProperty("user.dir");
@BeforeClass
public void beforeClass () {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver ();
	jsExecutor = (JavascriptExecutor) driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();

}
		//@Test
		public void TC_01_Basic_Form_Switch_By_ID(){
		
			
			// Cách này chỉ dùng duy nhất cho 2 Tab/ 2 WIndow-> nhiều hơn FAIL
			driver.get("https://automationfc.github.io/basic-form/index.html");
			sleepInSecond(2);
	//1. Switch to B
			
			//Vừa mở ra nó chỉ có duy nhất 1 tab
			// Lấy ra ID của driver đang đứng tại tab/window(Active)
			String formTabID = driver.getWindowHandle();
			System.out.println("Form Tab = " + formTabID);
			
			//A:998d3dfc-2258-467f-b798-21c70b001182
			driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
			sleepInSecond(3);
			
			// Lúc này nó đã có 2 tab rồi
			// A:
			// B:
			
			// Dùng vòng lặp để duyệt qua từng ID trong set
			// Lấy ra tất cả các ID của các tab/Window đang có
			Set<String> allTabID = driver.getWindowHandles();
			
			// DÙng vòng lặp để duyệt qua từng ID
			for (String id:allTabID) {
				//id là 1 biến tạm dùng để duyệt qua từng giá trị trong vòng lặp (allTabID)
				
				// Lần duyệt thứ 1
				// ID A
				// Form tab ID A
				
				// Lần duyệt thứ 2
				// ID B
				// Form tab ID B
				
				// Nếu như ID nào khác parents ID
				if(!id.equals(formTabID)) {
					
					// Switch vào 
					driver.switchTo().window(id);
					sleepInSecond(3);
				}
			}
			System.out.println(driver.getCurrentUrl());
			System.out.print(driver.getTitle());
			driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Rose");
			sleepInSecond(2);
	
			
	//2. Back to A
			String googleID = driver.getWindowHandle();
			for (String id : allTabID) {
				if (!id.equals(googleID)) {
					driver.switchTo().window(id);
				}
			}
			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.getTitle());
			
	}
	

		@Test
		public void TC_02_Basic_Form_Switch_By_Title() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			sleepInSecond(3);
			
			
			driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
			sleepInSecond(3);
			
			
			Set<String> allWindowID = driver.getWindowHandles();
			
			for(String id : allWindowID) {
				driver.switchTo().window(id);
				String googleTitle = driver.getTitle();
				if(googleTitle.equals("Google")) {
					break;
				}
			}
			sleepInSecond(3);
			System.out.println(driver.getCurrentUrl());
			sleepInSecond(3);
			
			for(String id:allWindowID) {
				driver.switchTo().window(id);
				String basicFormTitle = driver.getTitle();
				
				if(basicFormTitle.equals("SELENIUM WEBDRIVER FORM DEMO")) {
					break;
				}
				
			}
			sleepInSecond(3);
			System.out.println(driver.getCurrentUrl());
			sleepInSecond(3);
			
			driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
			sleepInSecond(5);
			
			for(String id:allWindowID) {
				driver.switchTo().window(id);
				String fbTitle = driver.getTitle();
				if(fbTitle.equals("Facebook – log in or sign up")) {
					driver.findElement(By.cssSelector("input#email")).sendKeys("nguyen@gmail.com");
					driver.findElement(By.className("input#pass")).sendKeys("12345");
					break;
					
				}
			}
			sleepInSecond(3);
			System.out.println(driver.getCurrentUrl());
			
			
			
			
			
			
	
	
}
		@Test
		public void TC_03_naukri_I_Register() {
	
}
		@Test
		public void TC_01_naukri_II_Jobs() {
			
}
		@Test
		public void TC_01_Ex_Windows_Tab_automationfc() {
		
		}
	
		
		@Test
		public void TC_02_Ex_Windows_Tab() {
			
		}
		
		@Test
		public void TC_02_Ex_Window_Tab_Panda(){
			
		}
	
		@Test
		public void TC_03_Ex_Window_Dictionary_II() {
		
		}
		
		@AfterClass
		public void afterClass(){
		driver.quit();
		}
	
		public void sleepInSecond(long second) {
			try {
				Thread.sleep(second*3000);
			} catch(InterruptedException e) {
				e.printStackTrace();
				}
			}
	
		
	
		
	
		}

