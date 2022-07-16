package webDriver;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic29_Wait_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	//Wait rõ ràng: với các điều kiện/ status cụ thể 
	
	String projectPath = System.getProperty("user.dir");
	By loadingButton = By.cssSelector("div#loading img");
	By helloWordText = By.xpath("//div[@id='finish']//h4");
	String doorFileName = "door.jpg";
	String tableFileName ="table.jpg";
	String windowFileName = "Window.jpg";
	String uploadImagesPath = projectPath + File.separator + "uploadImages" + File.separator;
	String doorFilePath = uploadImagesPath + doorFileName;
	String windowFilePath = uploadImagesPath + windowFileName;
	String tableFilePath = uploadImagesPath + tableFileName;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Implicit- Wait ngầm định: không có 1 element điều kiện nào rõ ràng
		// Ngầm định cho việc tìm element thôi
		
		driver.manage().window().maximize();
		
	}
	//@Test
	public void TC_0_Invisible_Equal() {
		
		// 5s này là cho cái loading explicit wait còn không phải của tất cả các step TC
		// chỉ ảnh hưởng cho các step dùng các hàm của explicit wait
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Loading icon biến mất sau 5s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingButton));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']//h4")).getText(), "Hello World!");
	}
	//@Test
	public void TC_1_Invisible_Less() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		// 
		explicitWait = new WebDriverWait(driver, 3);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Loading icon biến mất sau 3s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingButton));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']//h4")).getText(), "Hello World!");
		
	}
	//@Test
	public void TC_2_Invisible_More() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		// 
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Loading icon biến mất sau 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingButton));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']//h4")).getText(), "Hello World!");
	}
	//@Test
	public void TC_3_Visible() {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(helloWordText));
		Assert.assertEquals(driver.findElement(helloWordText).getText(), "Hello World!");
		
	}
	
	// Cách này không hay bằng 2 cách trên
	// Số lượng element hiển thị =1 -> xuất hiện
	//@Test
	public void TC_04_Number() {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start button")).click();
		
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(helloWordText, 1));
		Assert.assertEquals(driver.findElement(helloWordText).getText(), "Hello World!");
	}
	//@Test
	public void TC_05_Ajaxloading() {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		// Wait cho Date Picker xuất hiện 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		
		// Element này nó tìm được tại thời diểm chưa click lên ngày 11
		WebElement msgText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(msgText.getText(), "No Selected Dates to display.");
		
		// Wait cho ngày 11 có thể click lên và click lên nó
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']"))).click();
		
		// Wait loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1'] div.raDiv")));
		
		// Sau khi click vào ngày 11 thì element có text được cập nhật lại
		// Nếu như dùng element đã được find ở trên rồi -> Fail
		
		// Verify ngày được update
		assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Monday, July 11, 2022");
		
		// Wait cho ngày được selected thành công (visible)
		WebElement todaySelected= explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()='11']")));
		
		// Verify ngày được chọn 
		Assert.assertTrue(todaySelected.isDisplayed());
	}
	@Test
	public void TC_06_Upload_File() {
		explicitWait = new WebDriverWait(driver, 90);
		driver.get("https://gofile.io/?t=uploadFiles");
		// Load File + Uploading
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(tableFilePath + "\n" + windowFilePath + "\n" + doorFilePath);
		
		// Wait cho File được upload thành công trong vòng 90s
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("span[id*='progressString']"))));
		
		// Wait cho text được visible
		WebElement successText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Your files have been successfully uploaded')]")));
		Assert.assertTrue(successText.isDisplayed());
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a#rowUploadSuccess-downloadPage"))).click();
		
		
		
		
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
}
