package webDriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Alert {

	WebDriver driver;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osname = System.getProperty("os.name");
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	//@Test
	public void TC_00_Login_With_No_Data() {
		driver.get("https://demo.guru99.com/v4/index.php");
		driver.findElement(By.name("btnLogin")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		alert.accept();
	}
	
	//@Test
	public void TC_01_Acept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button [@onclick='jsAlert()']")).click();
		alert = driver.switchTo().alert();
		assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
	}
	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}
	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		String textToSendKey = "Rose";
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(textToSendKey);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " +  textToSendKey);
	}
	//@Test
	public void TC_04_Authentication_Alert() {
		String email = "admin";
		String pass = "admin";
		driver.get("http://" + email + ":" + pass +  "@" + "the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	@Test
	public void TC_05_Authentication_Alert_I() {
		String username = "admin";
		String pass ="admin";
		driver.get("http://the-internet.herokuapp.com/");
		String basicAuthLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		String[] basicAuth = basicAuthLink.split("//");
		basicAuthLink = basicAuth[0] + "//" + username + ":" + pass + "@" + basicAuth[1];
		driver.get(basicAuthLink);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	public String getAuthenticateLink (String url, String username, String pass) {
		String[] link = url.split("//");
		url = link[0] + "//" + username + ":" + pass + "@" + link[1];
		return url;
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
