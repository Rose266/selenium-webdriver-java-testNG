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

public class Topic_24_Handle_Windows_Tab {
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
public void TC_01_naukri_I_Jobs(){
	driver.get("https://www.naukri.com/");
	String homePageWindowID = driver.getWindowHandle();
	System.out.println(homePageWindowID);
	
	driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
	sleepInSecond(3);
	System.out.println("Tab A : " + driver.getCurrentUrl());
	
	Set<String> allWindowIDs = driver.getWindowHandles();
	for(String id : allWindowIDs) {
		if(!id.equals(homePageWindowID)) {
			driver.switchTo().window(id);
		}
	}
	
	System.out.println("Tab B : " + driver.getCurrentUrl());
	
	String jobPageWindowID = driver.getWindowHandle();
	allWindowIDs = driver.getWindowHandles();
	System.out.println("Set size : " + allWindowIDs.size());
	for(String id : allWindowIDs) {
		if(!id.equals(jobPageWindowID)) {
			driver.switchTo().window(id);
		}
	}
	System.out.println("Tab A : " + driver.getCurrentUrl());
	
	
}
//@Test
public void TC_02_naukri_I_Companies() {
	
	driver.get("https://www.naukri.com/");
	String currentWindowID = driver.getWindowHandle();
	driver.findElement(By.xpath("//a[@title='Explore top companies hiring on Naukri']")).click();
	sleepInSecond(3);
	System.out.println("Tab A : " + driver.getCurrentUrl());
	Set<String> allWindowIDs = driver.getWindowHandles();
	for(String id : allWindowIDs) {
		if(!id.equals(currentWindowID)) {
			driver.switchTo().window(id);
		}
	}
	System.out.println("Tab B : " + driver.getCurrentUrl());
	String companiesPageID = driver.getWindowHandle();
	allWindowIDs = driver.getWindowHandles();
	for(String id : allWindowIDs) {
		if(!id.equals(companiesPageID)) {
			driver.switchTo().window(id);
		}
	}
	System.out.println("Tab A : " + driver.getCurrentUrl());
	
}
//@Test
public void TC_03_naukri_I_Register() {
	driver.get("https://www.naukri.com/");
	String currentWindowID =driver.getWindowHandle();
	driver.findElement(By.xpath("//div[text()='Register']")).click();
	sleepInSecond(3);
	System.out.println("Set A : " + driver.getCurrentUrl());
	Set<String> allWindowsID = driver.getWindowHandles();
	for(String id : allWindowsID) {
		if(!id.equals(currentWindowID)) {
			driver.switchTo().window(id);
		}
	}
	System.out.println("Tab B : " + driver.getCurrentUrl());
	String registerPageId = driver.getWindowHandle();
	allWindowsID = driver.getWindowHandles();
	for(String id : allWindowsID) {
		if(!id.equals(registerPageId)) {
			driver.switchTo().window(id);
		}
		}
	System.out.println("Tab A : " + driver.getCurrentUrl());
}
//@Test
public void TC_01_naukri_II_Jobs() {
	driver.get("https://www.naukri.com/");
	
	driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
	sleepInSecond(3);
	System.out.println("Tab A : " + driver.getCurrentUrl());
	Set<String> allWindowIDS = driver.getWindowHandles();
	for(String id : allWindowIDS) {
		driver.switchTo().window(id);
		String actuallink = driver.getCurrentUrl();
		if(actuallink.contains("browse-jobs")) {
			break;
		}
	}
	System.out.println("Tab B : " + driver.getCurrentUrl());
	
	allWindowIDS = driver.getWindowHandles();
	for(String id : allWindowIDS) {
		driver.switchTo().window(id);
		String actualTitle1 = driver.getTitle();
		
		if(actualTitle1.equals("Jobs - Recruitment - Job Search - Employment - Job Vacancies - Naukri.com")) {
			break;
		}
	}
	System.out.println("Set A : " + driver.getCurrentUrl());
}
	//@Test
	public void TC_01_Ex_Windows_Tab_automationfc() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		System.out.println("Tab A :" + driver.getCurrentUrl());
		switchToTitle("Google");
		System.out.println("Tab B :" + driver.getCurrentUrl());
		switchToTitle("SELENIUM WEBDRIVER FORM DEMO");
		System.out.println("Tab A :" + driver.getCurrentUrl());
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		switchToLink("https://www.facebook.com/");
		System.out.println("Tab C :" + driver.getCurrentUrl());
		
		Set<String> allWindowIDs = driver.getWindowHandles();
		for(String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if(actualTitle.equals("SELENIUM WEBDRIVER FORM DEMO")) {
				break;
				
			}else {
				driver.close();
			}
		}
		
		System.out.println("Tab A : " + driver.getCurrentUrl());
		}
	
	//Tại sao nó lại nhảy sang youtube rồi lại nhảy sang fb tiếp?@Test
		public void TC_02_Ex_Windows_Tab() {
			driver.get("https://kyna.vn/");
			driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
			List<WebElement> popup = driver.findElements(By.cssSelector("iframe.cs_chat_open"));
			if(popup.size()>0) {
				driver.findElement(By.cssSelector("div.overlay")).click();
			} else {
				driver.switchTo().defaultContent();
				}
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//h4[text()='Thông tin Kyna']/parent::div/preceding-sibling::div//a[contains(@href,'facebook')]")));
			sleepInSecond(3);
			switchToTitle("Kyna.vn - Home | Facebook");
			System.out.println("Kyna B : " + driver.getCurrentUrl());
			switchToTitle("Kyna.vn - Học online cùng chuyên gia");
			System.out.println("Kyna A : " + driver.getCurrentUrl());
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//h4[text()='Thông tin Kyna']/parent::div/preceding-sibling::div//a[contains(@href,'youtube')]")));
			sleepInSecond(3);
			switchToLink("youtube.com/user/kynavn");
			sleepInSecond(3);
			System.out.println("Kyna C : " + driver.getCurrentUrl());
			if(!driver.getTitle().equals("Kyna.vn - Học online cùng chuyên gia")) {
				switchToTitle("Kyna.vn - Học online cùng chuyên gia");
				
			} else {
				driver.close();
			}
			System.out.println("Kyna A : " + driver.getCurrentUrl());
		}
		
		//@Test
		public void TC_02_Ex_Window_Tab_Panda(){
			driver.get("http://live.techpanda.org/");
			driver.findElement(By.xpath("//a[text()='Mobile']")).click();
			driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div//a[text()='Add to Compare']")).click();
			Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
			driver.findElement(By.xpath("//a[@title='IPhone']/following-sibling::div//a[text()='Add to Compare']")).click();
			Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product IPhone has been added to comparison list.");
			driver.findElement(By.xpath("//button[@title='Compare']")).click();
			sleepInSecond(3);
			Set<String> allTabIds = driver.getWindowHandles();
			for(String id : allTabIds ) {
				driver.switchTo().window(id);
				if(driver.getTitle().equals("Products Comparison List - Magento Commerce")) {
					break;
				}
			}
			System.out.println("Current url : " + driver.getCurrentUrl());

		}
		//@Test Tại sao làm vs link thì bị lỗi
		public void TC_03_Ex_Window_Dictionary() {
			driver.get("https://dictionary.cambridge.org/vi/");
			driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
			sleepInSecond(3);
			
			System.out.println("Curent Tab : " + driver.getCurrentUrl());
			Set<String> allTabsID = driver.getWindowHandles();
			for(String id : allTabsID ) {
				driver.switchTo().window(id);
				if(driver.getCurrentUrl().contains("cambridge.org/vi/auth/signin")) {
					break;
				}
				}
			System.out.println("Tab B : " + driver.getCurrentUrl());
		}
		// Case này không hiểu sao fail mấy lần
		@Test
		public void TC_03_Ex_Window_Dictionary_II() {
			driver.get("https://dictionary.cambridge.org/vi/");
			driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
			sleepInSecond(3);
			String currentWindowID = driver.getWindowHandle();
			System.out.println("Curent Tab : " + driver.getCurrentUrl());
			Set<String> allTabsID = driver.getWindowHandles();
			for(String id : allTabsID ) {
				if(!id.equals(currentWindowID)) {
					driver.switchTo().window(id);
				}}
			System.out.println("Tab B : " + driver.getCurrentUrl());
			driver.findElement(By.xpath("//input[@value='Log in']")).click();
			Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Email *']/following-sibling::span")).getText(), "This field is required");
			Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Password *']/following-sibling::span")).getText(), "This field is required");
			driver.findElement(By.xpath("//input[@placeholder='Email *']")).sendKeys("automationfc.com@gmail.com");
			driver.findElement(By.xpath("//input[@placeholder='Password *']")).sendKeys("Automation000***");
			driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		
		
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
		public void switchToTitle(String expectedTitle) {
			Set<String> allWindowIDs = driver.getWindowHandles();
			for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if(expectedTitle.equals(actualTitle)) {
				break;
			}
			}
		}
		
		public void switchToLink(String expectedRelativeLink) {
			Set<String> allWindowIDs = driver.getWindowHandles();
			for (String id : allWindowIDs) {
				driver.switchTo().window(id);
				String actualLink = driver.getCurrentUrl();
				if (expectedRelativeLink.contains(actualLink)) {
					break;
				}
			}
		}
		
		
		public void switchToCurrentWindowID (String currentWindowId ) {
			currentWindowId = driver.getWindowHandle();
			Set<String> allWindowIDs = driver.getWindowHandles();
			for (String id : allWindowIDs ) {
				if(!id.equals(currentWindowId)) {
					driver.switchTo().window(id);
				}
			}
		}
			
		}

