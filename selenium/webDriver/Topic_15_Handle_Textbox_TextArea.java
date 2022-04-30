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
		driver.findElement(By.xpath("//a[text()='here']")).click();
		SleepInSecond(5);
		String email = "trankieuhuong" + generateRandomNumber() + "@gmail.com";
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		SleepInSecond(5);
		String userID= driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		String pass = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		driver.get("http://demo.guru99.com/v4");
		SleepInSecond(5);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		driver.findElement(By.name("btnLogin")).click();
		SleepInSecond(5);
		driver.findElement(By.cssSelector("tr.heading3>td")).getText();
		Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id : " + userID);
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		SleepInSecond(30);
		String customerName = "Nhung";
		String inputDateOfBirth = "06261996";
		String inputAddress = "Home\nLazy Street";
		String city = "Ha Noi";
		String state = "Cau Giay";
		String pin = "123456";
		String mobileNumber = "0888888888";
		String passWord = "Huo666";
	
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='f']")).click();
		WebElement dateOfBirthTextBox = driver.findElement(By.xpath("//input[@id='dob']"));
		jsExecutor.executeScript("arguments[0] removeAttribute('disabled')", dateOfBirthTextBox);
		dateOfBirthTextBox.sendKeys(inputDateOfBirth);
		driver.findElement(By.cssSelector("textarea[name='addr']")).sendKeys(inputAddress);
		
		driver.findElement(By.cssSelector("input[name='city']")).sendKeys(city);
		
		driver.findElement(By.cssSelector("input[name='state']")).sendKeys(state);
		
		driver.findElement(By.cssSelector("input[name='pinno']")).sendKeys(pin);
		driver.findElement(By.cssSelector("input[name='telephoneno']")).sendKeys(mobileNumber);
		driver.findElement(By.cssSelector("input[name='emailid']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys(passWord);
		driver.findElement(By.cssSelector("input[name='sub']")).click();
		driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		String idNumber = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), idNumber);
		By customerNameBy = By.xpath("//input[@name='name']");
		By genderBy = By.xpath("//input[@value='f']");
		By dateOfBirthBy = By.xpath("//input[@id='dob']");
		By addressBy = By.cssSelector("textarea[name='addr']");
		By cityBy = By.cssSelector("input[name='city']");
		By stateBy = By.cssSelector("input[name='state']");
		By pinBy = By.cssSelector("input[name='pinno']");
		By mobileNumberBy = By.cssSelector("input[name='telephoneno']");
		By emailBy = By.cssSelector("input[name='emailid']");
		By CustomerIDBy = By.xpath("//td[text()='Customer ID']/following-sibling::td");


		Assert.assertEquals(driver.findElement(customerNameBy).getText(), customerName);
		Assert.assertTrue(driver.findElement(genderBy).isSelected());
		String outputDateOfBirth = "1996-06-26";
		Assert.assertEquals(driver.findElement(dateOfBirthBy).getText(),outputDateOfBirth);
		String outputAddress = "Home Lazy street";
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
