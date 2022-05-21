package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Handle_Fixed_Popup {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	// Ở đây nên dùng là By [Online 24] - Topic 26 (Handle Fixed Popup) 49:18
	//@Test
	public void TC_01_Fixed_Popup_I() {
		driver.get("https://ngoaingu24h.vn/");
		By loginPopup = By.xpath("//div[@class='main-banner-panel']/preceding-sibling::div[@id='modal-login-v1']");
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("button.login_")).click();
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		By loginInputPopup = By.xpath("//div[@class='main-banner-panel']/preceding-sibling::div[@id='modal-login-v1']//input[@id='account-input']");
		By passwordInputPopup = By.xpath("//div[@class='main-banner-panel']/preceding-sibling::div[@id='modal-login-v1']//input[@id='password-input']");
		driver.findElement(loginInputPopup).sendKeys("automationfc");
		driver.findElement(passwordInputPopup).sendKeys("automationfc");
		By textErrorPopup = By.xpath("//div[@class='main-banner-panel']/preceding-sibling::div[@id='modal-login-v1']//div[@class='row error-login-panel']");
		By buttonLoginPopup = By.xpath("//div[@class='main-banner-panel']/preceding-sibling::div[@id='modal-login-v1']//button[@data-text='Đăng nhập']");
		driver.findElement(buttonLoginPopup).click();
		Assert.assertEquals(driver.findElement(textErrorPopup).getText(), "Tài khoản không tồn tại!");
		By closeBtnPopup = By.xpath("//div[@class='main-banner-panel']/preceding-sibling::div[@id='modal-login-v1']//button[@class='close']");
		driver.findElement(closeBtnPopup).click();
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	}
	//@Test
	public void TC_02_Fixed_Popup_II() {
		driver.get("https://jtexpress.vn/");
		Assert.assertTrue(driver.findElement(By.cssSelector("div#home-modal-slider")).isDisplayed());
		driver.findElement(By.cssSelector("button.close")).click();
		Assert.assertFalse(driver.findElement(By.cssSelector("div#home-modal-slider")).isDisplayed());
		String billNumber = "841000059844";
		driver.findElement(By.cssSelector("input#billcodes")).sendKeys(billNumber);
		driver.findElement(By.cssSelector("input#billcodes")).sendKeys(Keys.ENTER);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#billcodes")).getText(), billNumber);
		//Assert.assertEquals(driver.findElement(By.xpath("//button[@class='btn btn-link']/parent::div")).getText(), "Mã vận đơn: " + billNumber);
		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='btn btn-link']/parent::div")).getText().contains(billNumber));
	}
	//@Test Đợi đến phần Wait
	public void TC_03_Random_Popup_In_Dom_Test_Project() {
		driver.get("https://blog.testproject.io/");
		Assert.assertTrue(driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed());
		if (driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch svg")).click();
		}else 
		{
			
		}	}
	//@Test
	public void TC_04_Random_Popup_In_Dom_VNK() {
    // Nếu như popup hiển thị thì có thể thao tác với popup rồi close nó đi -> qua step tiếp theo
    // Nếu như popup không hiển thị thì qua step tiếp theo luôn
	/* Cách 1	driver.get("https://vnk.edu.vn/");
		if(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed()) {
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			driver.findElement(By.xpath("//a[@title='Liên hệ']")).click();
		} else
		{
			driver.findElement(By.xpath("//a[@title='Liên hệ']")).click();
		}
		Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content h1")).getText().contains("Liên Hệ")); */
	// Cách 2
		driver.get("https://vnk.edu.vn/");
		if (driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed()) {
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			Assert.assertFalse(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed());}
		driver.findElement(By.xpath("//a[@title='Liên hệ']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content h1")).isDisplayed());
	 
	}
	//@Test
	public void TC_05_Random_Popup_In_Dom_Kmplayer() {
		driver.get("https://www.kmplayer.com/home");
		if (driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed()) {
			// <map name = "support home" driver.findElement(By.cssSelector("area#btn-r")).click();
			//element này slenium không support map, chart,.. nên không dùng như thông thường đc
			// dùng jsexecutor để click
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
			sleepInSecond(2);
			Assert.assertFalse(driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed());
		} else {
			System.out.println("Case 2 - Nếu như popup không hiện thì qua step tiếp theo luôn - Passed");
		}
	
	    driver.findElement(By.xpath("//a[contains(text(),'MOVIEBLOC')]")).click();
	    Assert.assertEquals(driver.findElement(By.cssSelector("a.logo")).getText(), "MovieBloc");
			
	
	}
	// Fail case này @Test
	public void TC_06_Random_Popup_Not_In_Dom_DeHieu() {
		driver.get("https://dehieu.vn/");
		// Test để vào được case 1
		sleepInSecond(10);
		
		// Cố tình dùng cách ở 2 test case trên để làm
		if (driver.findElement(By.cssSelector("div.popup-content")).isDisplayed()) {
			System.out.println("Case 1: Có xuất hiện đóng popup đi");
			
			// Thao tác với popup 
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Nguyễn Tín Nghĩa");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("tinnghia520@gmail.com");
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0865888888");
			
			//Close popup đi 
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(2);
			// Không dùng cách này để verify popup không hiển thị nữa
			// Lý do khi nó không hiển thị close rồi thì element không còn trong DOM
			// Không còn trong DOM thì không findElement
			// Find element should not be used to look for non-present elements,
			// nosuchelement exception - ì no match elements are found
			// Assert.assertFalse(driver.findElement(By.cssSelector("button#close-popup")).isDisplayed());

		} else {
			
			// nếu như setting cho app vào ngày xxx nào đó sẽ hiện popup cho chạy để chạy chương trình marketing- có popup
			// sau thời gian này thì setting nó không hiển thị popup nữa mở page ra nó không render cái popup này nữa
			// ý trên suy rakhông trong dom
			System.out.println("Case 2 : không xuất hiện chuyển qua step 2");
			
		}
			driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
			Assert.assertTrue(driver.findElement(By.cssSelector("input#search-courses")).isDisplayed());
	}
	@Test
	public void TC_07_Random_Popup_Not_In_Dom_DeHieu_Cachgiai() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(20);
		List<WebElement>  popup = driver.findElements(By.cssSelector("div.popup-content"));
		if (popup.size() > 0) {
			System.out.println("Case 1: Có xuất hiện đóng popup đi");
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Liễu Như");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("lieunnhu96@gmail.com");
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0865862596");
			driver.findElement(By.cssSelector("button#close-popup")).click();
			popup = driver.findElements(By.cssSelector("div.popup-content"));
			Assert.assertEquals(popup.size(), 0);
		} else {
			System.out.println("Case 2 : không xuất hiện chuyển qua step 2");
		}
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("//a[text()='Tất cả khóa học']")).isDisplayed());
		
		
	}
	//@Test
	public void TC_08_Random_Popup_Not_In_Dom_Shopee() {
		driver.get("https://shopee.vn/");
		
	}
	
		

	@AfterClass
	public void afterClass() {
		
	}
	public void sleepInSecond (long second) {
		try {
			Thread.sleep(second*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
