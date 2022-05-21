package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Button_RadioButton_Checkbox {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass () {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	//@Test
	public void TC_01_JsExecutor() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInSecond(2);
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("nguyenhang@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("HoaHong1234");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		String buttonBackgroundColorRbg = driver.findElement(loginButtonBy).getCssValue("background-color");
		Assert.assertEquals(buttonBackgroundColorRbg, "rgb(201, 33, 39)");
		System.out.println("RGB Color = " + buttonBackgroundColorRbg);
		String buttonBackgroundcolorhexa = Color.fromString(buttonBackgroundColorRbg).asHex();
		Assert.assertEquals(buttonBackgroundcolorhexa.toUpperCase(), "#C92127");
		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",driver.findElement(loginButtonBy));
		driver.findElement(By.cssSelector("input#login_username")).clear();
		driver.findElement(By.cssSelector("input#login_password")).clear();
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
	}
	//@Test
	public void TC_02_Defaut_Checkbox_Or_RadioButton() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By radioBtn = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		driver.findElement(radioBtn).click();
		Assert.assertTrue(driver.findElement(radioBtn).isSelected());
		
		
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By leatherTrimCheckbox = By.xpath("//label[text()='Leather trim']/preceding-sibling::input");
		By luggageCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		By heatedCheckbox = By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input");
		By towbarCheckbox = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::input");
		checkToCheckbox(luggageCheckbox);
		checkToCheckbox(heatedCheckbox);
		Assert.assertTrue(isElementSelected(luggageCheckbox));
		Assert.assertTrue(isElementSelected(heatedCheckbox));
		Assert.assertFalse(isElementSelected(towbarCheckbox));
		Assert.assertFalse(isElementEnable(leatherTrimCheckbox));
		Assert.assertFalse(isElementEnable(towbarCheckbox));
		
		uncheckToCheckbox(heatedCheckbox);
		uncheckToCheckbox(luggageCheckbox);
		Assert.assertFalse(isElementSelected(heatedCheckbox));
		Assert.assertFalse(isElementSelected(luggageCheckbox));
	}
	public void checkToCheckbox (By by) {
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	public void uncheckToCheckbox (By by) {
		if(driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			return true;
		} else {
			return false;
			
		}
	}
	public boolean isElementEnable (By by) {
		if (driver.findElement(by).isEnabled()) {
		return true;	
		} else {
			return false;
		}
	}
	
	// Chọn tất cả cac checkbox
	//@Test
	public void TC_03_Multiple_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for(WebElement checkbox : checkboxes) {
			if(!checkbox.isSelected()) {
				checkbox.click();
			}}
		for(WebElement checkbox :checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				checkbox.click();
				
			}}
		for(WebElement checkbox:checkboxes) {
			Assert.assertFalse(checkbox.isSelected());
		}
	}
	//@Test
	public void TC_04_Custom_Radio() {
		// Case 1: - Click thẻ input=> không click được
		//		   - Verify thẻ input=> verify được
		
		// Case 2: - Click thẻ khác(span)=> click được
		//         - Verify thẻ khác(span)=> không verify được
		
		// Case 3: - Click thẻ khác (span)=> click được
		//         - Verify thẻ input=> Verify
		// => Case 3 dùng được nhưng không dùng vì xấu code và cần nhiều locator
		
		// Case 4: - Dùng jsExecutor để click và vẫn dùng thẻ input chứa checkbox/radio btn để verify
		driver.get("https://material.angular.io/components/radio/examples");
		By winterButton = By.xpath("//input[@value='Winter']");
		By summerButton = By.xpath("//input[@value='Summer']");
		jsExecutorClick(winterButton);
		Assert.assertTrue(driver.findElement(winterButton).isSelected());
		jsExecutorClick(summerButton);
		Assert.assertTrue(driver.findElement(summerButton).isSelected());
	}
	
	//@Test
	public void TC_05_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
		if(!driver.findElement(indeterminateCheckbox).isSelected()) {
			jsExecutorClick(indeterminateCheckbox);
		
		}
		Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
		if(!driver.findElement(checkedCheckbox).isSelected()) {
			jsExecutorClick(checkedCheckbox);
		}
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		if (driver.findElement(checkedCheckbox).isSelected()) {
			jsExecutorClick(checkedCheckbox);
		}
			Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		if(driver.findElement(indeterminateCheckbox).isSelected()) {
			jsExecutorClick(indeterminateCheckbox);
		}
			Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
		}
		

	//@Test
	public void TC_06_Custom_Radio_Covid() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		By dangKyBanThanBtn = By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div/input");
		By dangKyNguoiThanBtn = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		
		jsExecutorClick(dangKyNguoiThanBtn);
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(dangKyNguoiThanBtn).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerFullname']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).isDisplayed());
		
		
		jsExecutorClick(dangKyBanThanBtn);
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(dangKyBanThanBtn).isSelected());
		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerFullname']")).size(), 0);
		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).size(), 0);}
		
	@Test
	public void TC_07_Custom_Radio_Checkbox_Google_Doc() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By ctRadioBtn = By.xpath("//div[@data-value='Cần Thơ']");
		By dnRadioBtn = By.xpath("//div[@data-value='Đà Nẵng']");
		driver.findElement(dnRadioBtn).click();
		Assert.assertEquals(driver.findElement(dnRadioBtn).getAttribute("aria-checked"), "true");
		driver.findElement(ctRadioBtn).click();
		Assert.assertEquals(driver.findElement(ctRadioBtn).getAttribute("aria-checked"), "true");
		List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@role='checkbox']"));
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
		}
		for(WebElement checkbox : checkboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
		}
	}
	@Test
	public void TC_08_Accept_Alert() {
		
	}
	@Test
	public void TC_09_Confirm_Alert() {
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond (long second) {
		try {
			Thread.sleep(second*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void jsExecutorClick (By by) {
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
	}
	
}
