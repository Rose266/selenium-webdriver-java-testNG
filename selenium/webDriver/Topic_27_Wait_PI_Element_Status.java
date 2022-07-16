package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_PI_Element_Status {
WebDriver driver;
String projectPath = System.getProperty("user.dir");
WebDriverWait explicitWait;
@BeforeClass
public void beforeClass () {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	explicitWait = new WebDriverWait(driver, 15);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	driver.manage().window().maximize();
	driver.get("https://www.facebook.com/");
}
//@Test
public void TC_01_Visible() {
	//Visible có trên UI và có trong DOM/HTML
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
	Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
}
//@Test
public void TC_02_Invisible_In_Dom() {
	//Invisible: Không có trên UI và có trong DOM(không bắt buộc)
	// Indom và not in dom thời gian chạy khác nhau
	// Kết quả như nhau nhưng thời gian chạy của mỗi case khác nhau
	driver.findElement(By.xpath("//a[@data-testid['open-registration-form-button']]")).click();
	
	// Chạy chưa đầy 1s
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	
	// Không hiển thị->Pass->chưa đầy 1s
	Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
}
//@Test
public void TC_02_Invisible_Not_In_Dom() {
	//Invisible: Không có trên UI và không có trong DOM(không bắt buộc)
	// Indom và not in dom thời gian chạy khác nhau
	// Kết quả như nhau nhưng thời gian chạy của mỗi case khác nhau
	// Lí do cho việc time chạy khác nhau là :
	// Có trong Dom thì Selenium findElement chạy rất nhanh, không cần chờ hết timeout của từng loại wait
	// Element không có trong Dom thì Selenium findElement không có tìm thấy nên nó cứ chờ và tìm đi tìm lại cho đến khi hết timeout của từng loại wait
	// Đến khoảng time nhưng ko thỏa mãn đk -> đánh fail TC
	
	driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();
	sleepInSecond(2);
	// đợi tầm 15s
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	
	
	//Không hiển thị ->Fail->20s
	Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
}
//@Test
public void TC_03_Presence() {
	// Thực tế  rât ít dùng
	//Presence: Có trong DOM/HTML và có trên UI->Pass
	explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
	
	// Presence: Có trong DOM/HTML và không có trên UI->Pass
	driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
	explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
}
@Test
public void TC_04_Staleness() {
	// Bật registration form lên
	 driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
	 
	// Tại thời điểm này element này đang có trong DOM
	WebElement confirmationEmailCheckBox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
	 
	// Đóng Registration form lại
	driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();
	
	// Wait cho Confirmation Email Address textbox không còn trong DOM nữa
	// Vì 1 lý do nao đó cần wait cho nó không còn tồn tại trong Dom nữa
	explicitWait.until(ExpectedConditions.stalenessOf(confirmationEmailCheckBox));
}
@AfterClass
public void afterClass () {
	driver.quit();
}
public void sleepInSecond (long second) {
	try {
		Thread.sleep(second*1000);
	} catch(InterruptedException e) {
		e.printStackTrace();
	}
	
}
}
