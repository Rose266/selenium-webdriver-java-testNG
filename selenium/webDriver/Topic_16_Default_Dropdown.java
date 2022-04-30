package webDriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	Select select;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver ();
		
		// Khởi tạo sau khi driver này (driver = new FirefoxDriver ();) được sinh ra
		// Nếu khởi tạo trước driver=> driver chưa sinh ra/có dữ liệu=> sai
		// Chúng cần driver
		// JavascriptExecutor/ WebDriverWait/ Actions/...
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver,30);
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	public void TC_01_Rode() {
		/**
		 * driver.findElement(By.cssSelector("")).click();
		 * Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(),"");
		 * Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		 * List<WebElement> storeName = driver.findElements(By.className(""));
		 * Assert.assertEquals(storeName.size(), 32);
		 * for (WebElement store: storeName) {
		 * System.out.println(store.getText());
		 * }
		 * */
		// Khởi tạo khi sử dụng (element xuât hiện)
		// Khởi tạo select để thao tác với element (country dropdown)
		// deselectAll() - deselect bỏ trọn: hỗ trợ khi dropdown phải chọn nhiều element cùng 1 lúc
		// getfirstselected dùng để verify
		// get options xem dropdown đó có bn item
		// is Multiple kiểm tra dropdown có phải là Multiple hay không
		// selectByIndex- index là thứ tự của hàm option  
		driver.get("https://rode.com/en/support/where-to-buy");
		select = new Select(driver.findElement(By.cssSelector("select[id='country']")));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Vietnam");
		}
	@Test	
	public void TC_02_Nopcommerce() {
		String dateOfBirthDay, dateOfBirthMonth, dateOfBirthYear, email,firstName,lastName,pass;
		firstName ="MinhMinh";
		lastName = "Nguyen";
		dateOfBirthDay = "26";
		dateOfBirthMonth = "June";
		dateOfBirthYear = "1996";
		pass = "123456";
		email = "minhminh" + generateRandomNumber() + "@gmail.com";
		By firstNameBy=By.cssSelector("input#FirstName");
		By lastNameBy = By.cssSelector("input#LastName");
		By emailBy = By.cssSelector("input#Email");
		By passBy = By.cssSelector("input#Password");
		By confirmationBy = By.cssSelector("input#ConfirmPassword");
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("input#gender-female")).click();
		driver.findElement(firstNameBy).sendKeys(firstName);
		driver.findElement(lastNameBy).sendKeys(lastName);
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		select.selectByVisibleText(dateOfBirthDay);
		List<WebElement> dateOfBirthDayValue = select.getOptions();
		Assert.assertEquals(dateOfBirthDayValue.size(), 32);
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText(dateOfBirthMonth);
		List<WebElement> dateOfBirthMonthValue = select.getOptions();
		Assert.assertEquals(dateOfBirthMonthValue.size(), 13);
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText(dateOfBirthMonth);
		List<WebElement> dateOfBirthYearValue = select.getOptions();
		Assert.assertEquals(dateOfBirthYearValue.size(), 112);
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(passBy).sendKeys(pass);
		driver.findElement(confirmationBy).sendKeys(pass);
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector("a.ico-account")).click();
		Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthDay);
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthMonth);
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthYear);
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);
		
		
	}
	@AfterClass
	public void afterClass () {
		driver.quit();
	}
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond *1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public int generateRandomNumber () {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
	