package webDriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_Wait_Mix {
WebDriver driver;
String projectPath = System.getProperty("user.dir");
WebDriverWait explicitWait;
@BeforeClass
public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	
	driver.manage().window().maximize();
}
//@Test
public void TC_Element_Found() {
	// Element tìm thấy-> trộn 2 cái sẽ không bị ảnh hưởng
	driver.get("https://www.facebook.com/");
	
	By idEmailBy = By.xpath(" //input[@id='email']");
	
		
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	System.out.println("Implicit Begin = " + getTimeNow());	
	driver.findElement(idEmailBy).isDisplayed();
	System.out.println("Implicit End = " + getTimeNow());
	
	
	explicitWait = new WebDriverWait(driver, 15);
	System.out.println("Explicit Begin = " + getTimeNow());
	explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(idEmailBy));
	System.out.print("Explicit End = " + getTimeNow());
}
	
	//@Test
	public void TC_Element_Not_Found_Only_Implicit() {
		driver.get("https://www.facebook.com/");
		
		By idEmailBy = By.xpath(" //input[@id='VietNam']");
		
			
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Implicit Begin = " + getTimeNow());	
		
		try {
			driver.findElement(idEmailBy).isDisplayed();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Implicit End = " + getTimeNow());
	}
	//@Test
	public void TC_Element_Not_Found_Only_Explicit_By() {
		driver.get("https://www.facebook.com/");
		
		By idEmailBy = By.xpath(" //input[@id='VietNam']");
		
		// Implicit = 0	
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Explicit Begin = " + getTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(idEmailBy));;
		} catch (Exception e) {
			
			e.printStackTrace();}
		System.out.print("Explicit End = " + getTimeNow());
	}
	//@Test
	public void TC_04_Element_Not_Found_Mix() {
		//Implicit sẽ không bị ảnh hưởng bởi 1 loại Wait nào khác lên nó
		// Explicit đang bị Implicit ảnh hương nhưng số thời gian Implicit đang nhỏ hơn nên chưa thấy

	
		driver.get("https://www.facebook.com/");
		By idEmailBy = By.xpath(" //input[@id='VietNam']");
		// 1- Implicit < Explicit
		// 2- Implicit = Explicit
		// 3- Implict > Explicit
			
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Implicit Begin = " + getTimeNow());	
		try {
			driver.findElement(idEmailBy).isDisplayed();
		} catch (Exception e) {
			
		}
		System.out.println("Implicit End = " + getTimeNow());
		
		
		explicitWait = new WebDriverWait(driver, 5);
		System.out.println("Explicit Begin = " + getTimeNow());
		try {
			// Warapper việc findElement trong chính hàm visibilityofElementLocated
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(idEmailBy));
		} catch (Exception e) {
			
		}
		System.out.print("Explicit End = " + getTimeNow());
	}
	@Test
	public void TC_05_Element_Not_Found_Web_Element() {
driver.get("https://www.facebook.com/");
		
		WebElement  idEmailTextBox = driver.findElement(By.xpath(" //input[@id='VietNam']"));
		
		// Implicit = 0	
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Explicit Begin = " + getTimeNow());
		try {
			// find element là 1 tham số riêng, nó sẽ chạy riêng độc lập trước
			// Pass thì mới vào hàm : visibilityOf để chạy tiếp
			// Fail thì không  có vào 
			explicitWait.until(ExpectedConditions.visibilityOf(idEmailTextBox));;
		} catch (Exception e) {
			
			e.printStackTrace();}
		System.out.print("Explicit End = " + getTimeNow());
	}
	
	
@AfterClass
public void afterClass() {
	
}
	
	public String getTimeNow () {
		Date date = new Date();
		return date.toString();
	}
}
