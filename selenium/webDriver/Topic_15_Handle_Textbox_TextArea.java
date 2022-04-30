package webDriver;

import static org.testng.Assert.assertTrue;

import java.util.Random;
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

import net.bytebuddy.asm.Advice.Argument;

public class Topic_15_Handle_Textbox_TextArea {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass () {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver ();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	public void TC_01() {
		driver.get("http://demo.guru99.com/v4");
		String customerName = "Nhung";
		String inputDateOfBirth = "06261996";
		String outputDateOfBirth = "1996-06-26";
		String inputAddress = "Home\nLazy Street";
		String outputAddress = "Home Lazy street";
		String city = "Ha Noi";
		String state = "Cau Giay";
		String pin = "123456";
		String mobileNumber = "0888888888";
		String email = "trankieuhuong" + generateRandomNumber() + "@gmail.com";
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
		String idNumber = driver.findElement(CustomerIDBy).getText();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		String userID= driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		String pass = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//")).sendKeys(pass);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("driver.findElement(By.name(\"btnLogin\")).click();")), "Manger Id : " + userID);
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(customerNameBy).sendKeys(customerName);
		driver.findElement(genderBy).click();
		WebElement dateOfBirthTextBox = driver.findElement(dateOfBirthBy);
		jsExecutor.executeScript("arguments[0] removeAttribute('disabled')", dateOfBirthTextBox);
		dateOfBirthTextBox.sendKeys(inputDateOfBirth);
		driver.findElement(addressBy).sendKeys(inputAddress);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(mobileNumberBy).sendKeys(mobileNumber);
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(passwordBy).sendKeys(passWord);
		driver.findElement(By.cssSelector("input[name='sub']")).click();
		driver.findElement(CustomerIDBy).getText();
		Assert.assertEquals(driver.findElement(CustomerIDBy).getText(), idNumber);
		Assert.assertEquals(driver.findElement(customerNameBy).getText(), customerName);
		Assert.assertTrue(driver.findElement(genderBy).isSelected());
		Assert.assertEquals(driver.findElement(dateOfBirthBy).getText(),outputDateOfBirth);
		Assert.assertEquals(driver.findElement(addressBy), outputAddress);
		Assert.assertEquals(driver.findElement(cityBy), city);
		Assert.assertEquals(driver.findElement(stateBy), state);
		Assert.assertEquals(driver.findElement(pinBy), pin);
		Assert.assertEquals(driver.findElement(mobileNumberBy), mobileNumber);
		Assert.assertEquals(driver.findElement(emailBy), email);
		
	}
	//@Test 
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
	
	@AfterClass
	public void afterClass () {
		driver.quit();
	}
	public int generateRandomNumber () {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	public void SleepInSecond (long timeInSecond) {
		try {
		Thread.sleep(timeInSecond * 1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}
}
