package webDriver;

import static org.testng.Assert.assertTrue;

import java.awt.image.PixelGrabber;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Handle_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass () {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	public void TC_01() {
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr399297");
		driver.findElement(By.xpath("//")).sendKeys("hujYtEj");
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		String customerName = "Nhung";
		String dateOfBirth = "06261996";
		String address = "Home\nLazy Street";
		String city = "Ha Noi";
		String state = "Cau Giay";
		String pin = "123456";
		String mobileNumber = "0888888888";
		String email = "nguyenkieuhuong@gmail.com";
		String passWord = "Huo666";
		By customerNameBy = By.xpath("//input[@name='name']");
		By genderBy = By.xpath("//input[@value='f']");
		By dateOfBirthBy = By.xpath("//input[@id='dob']");
		By addressBy = By.cssSelector("textarea[name='addr']");
		By cityBy = By.cssSelector("input[name='city']");
		By stateBy = By.cssSelector("input[name='city']");
		By pinBy = By.cssSelector("input[name='pinno']");
		By mobileNumberBy = By.cssSelector("input[name='telephoneno']");
		By emailBy = By.cssSelector("input[name='emailid']");
		By passwordBy = By.cssSelector("input[name='password']");
		By CustomerIDBy = By.xpath("//td[text()='Customer ID']/following-sibling::td");
		driver.findElement(customerNameBy).sendKeys(customerName);
		driver.findElement(genderBy).click();
		driver.findElement(dateOfBirthBy).sendKeys(dateOfBirth);
		driver.findElement(addressBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(mobileNumberBy).sendKeys(mobileNumber);
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(passwordBy).sendKeys(passWord);
		driver.findElement(By.cssSelector("input[name='sub']")).click();
		driver.findElement(CustomerIDBy).getText();
		Assert.assertEquals(driver.findElement(CustomerIDBy).getText(), "18228");
		Assert.assertEquals(driver.findElement(customerNameBy).getText(), customerName);
		Assert.assertTrue(driver.findElement(genderBy).isSelected());
		Assert.assertEquals(driver.findElement(dateOfBirthBy).getText(),dateOfBirth);
		Assert.assertEquals(driver.findElement(addressBy), address);
		Assert.assertEquals(driver.findElement(cityBy), city);
		Assert.assertEquals(driver.findElement(stateBy), state);
		Assert.assertEquals(driver.findElement(pinBy), pin);
		Assert.assertEquals(driver.findElement(mobileNumberBy), mobileNumber);
		Assert.assertEquals(driver.findElement(emailBy), email);
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.cssSelector("input[name='cusid']")).sendKeys("18228");
		
		
		
;
		
	}
	@Test 
	public void TC_02 () {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		SleepInSecond(5);
		Assert.assertFalse(driver.findElement(By.xpath("//a[@id='menu_pim_addEmployee']")).isDisplayed());
		driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[@id='menu_pim_addEmployee']")).isDisplayed());
		driver.findElement(By.xpath("//a[@id='menu_pim_addEmployee']")).click();
		String firstName = "Rose";
		String lastName = "Nguyen";
		String editFirstName = "RRose";
		String editLastName = "Nguyenn";
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lastName);
		String employeeId = driver.findElement(By.xpath("//input[@id='employeeId']")).getAttribute("value");
		driver.findElement(By.cssSelector("input[id='btnSave']")).click();
		By personalFirstNameBy = By.xpath("//input[@id='personal_txtEmpFirstName']");
		By personalLastNameBy = By.xpath("//input[@id='personal_txtEmpLastName']");
		By personalEmployeeIdBy = By.xpath("//input[@id='personal_txtEmployeeId']");
		Assert.assertEquals(driver.findElement(personalFirstNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(personalLastNameBy).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(personalEmployeeIdBy).getAttribute("value"), employeeId);
		Assert.assertFalse(driver.findElement(personalFirstNameBy).isEnabled());
		Assert.assertFalse(driver.findElement(personalLastNameBy).isEnabled());
		Assert.assertFalse(driver.findElement(personalEmployeeIdBy).isEnabled());
		driver.findElement(By.xpath("//input[@id='btnSave'and @value ='Edit']")).click();
		
		driver.findElement(personalFirstNameBy).clear();
		driver.findElement(personalLastNameBy).clear();
		driver.findElement(personalFirstNameBy).sendKeys(editFirstName);
		driver.findElement(personalLastNameBy).sendKeys(editLastName);
		assertTrue(driver.findElement(personalFirstNameBy).isEnabled());
		assertTrue(driver.findElement(personalLastNameBy).isEnabled());
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(personalFirstNameBy).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(personalLastNameBy).getAttribute("value"), editLastName);
		Assert.assertFalse(driver.findElement(personalFirstNameBy).isEnabled());
		Assert.assertFalse(driver.findElement(personalLastNameBy).isEnabled());
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		driver.findElement(By.cssSelector("input[id='btnAdd']")).click();
		driver.findElement(By.cssSelector("input[id='immigration_number']")).sendKeys("00000473");
		driver.findElement(By.cssSelector("textarea[id='immigration_comments']")).sendKeys("Rose\nPassport\nEmployeeID");
		driver.findElement(By.cssSelector("input[id='btnSave']")).click();
		SleepInSecond(5);
		driver.findElement(By.xpath("//a[text()='Passport']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("input[id='immigration_number']")).getAttribute("value"), "00000473");
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea[id='immigration_comments']")).getAttribute("value"), "Rose\nPassport\nEmployeeID");
		
		
	}
	@Test
	public void TC_03() {
		
	}
	@Test
	public void TC_04 () {
		
	}
	@AfterClass
	public void afterClass () {
		driver.quit();
	}
	public void SleepInSecond (long second) {
		try {
		Thread.sleep(second * 1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}
}
