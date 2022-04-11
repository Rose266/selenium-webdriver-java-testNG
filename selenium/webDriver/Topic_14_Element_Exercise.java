package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Element_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mail']/preceding-sibling::label")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Age:']//following-sibling::label[text()='Under 18']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//textarea[@id='edu']/preceding-sibling::label")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class='figcaption']/h5")).isDisplayed());
	}
	@Test
	public void TC_02() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mail']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//textarea[@id='edu']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//select[@id='job1']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//select[@id='job2']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='development']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='disable_password']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='radio-disabled']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//textarea[@id='bio']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//select[@id='job3']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='check-disbaled']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled());
	}
	@Test
	public void TC_03() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//input[@id='under_18']")).click();
		driver.findElement(By.xpath("//input[@id='java']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='java']")).isSelected());
		driver.findElement(By.xpath("//input[@id='java']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='java']")).isSelected());
	}
	@Test
	public void TC_04() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("nguyenthihongnhung9966@gmail.com");
		
	}

}
