package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Element_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	//@Test
	public void TC_01_WebElement_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.cssSelector("input#email"));
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("nguyennhung@gmail.com");
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element is not displayed");
		}
		WebElement ageRadioBtn = driver.findElement(By.cssSelector("input#under_18"));
		if (ageRadioBtn.isDisplayed()) {
			ageRadioBtn.click();
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element is not displayed");
		}
		WebElement eduTextarea = driver.findElement(By.cssSelector("textarea#edu"));
		if (eduTextarea.isDisplayed()) {
			eduTextarea.sendKeys("College Education");
			System.out.println("Element is displayed");
		} else {
			System.out.print("Element is not displayed");
		}
		WebElement infoText = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (infoText.isDisplayed()) {
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element is not displayed");
		}
	}
	//@Test
	public void TC_02_WebElement_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement mailTextbox = driver.findElement(By.cssSelector("input#mail"));
		if (mailTextbox.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
		WebElement under18RadioBtn = driver.findElement(By.cssSelector("input#under_18"));
		if (under18RadioBtn.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
		WebElement eduTextarea = driver.findElement(By.cssSelector("textarea#edu"));
		if (eduTextarea.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
		WebElement singleDropdown = driver.findElement(By.cssSelector("select#job1"));
		if (singleDropdown.isEnabled()) {
			System.out.println("Single dropdown list is enabled");
		} else {
			System.out.println("Single dropdown list  is disabled");
		}
		WebElement multiDropdown = driver.findElement(By.cssSelector("select#job2"));
		if (multiDropdown.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
		WebElement devCheckbox = driver.findElement(By.cssSelector(" input#development"));
		if (devCheckbox.isEnabled()) {
			System.out.print("Development checkbox is enabled");
		} else {
			System.out.println("Development checkbox is disabled");
		}
		
		WebElement slider01 = driver.findElement(By.cssSelector("input#slider-1"));
		if (slider01.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#disable_password"));
		if (passwordTextbox.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
		WebElement radioDisableButton = driver.findElement(By.cssSelector("input#radio-disabled"));
		if (radioDisableButton.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
		WebElement disableTextarea = driver.findElement(By.cssSelector("textarea#bio"));
		if (disableTextarea.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
		WebElement disableDropdown = driver.findElement(By.cssSelector("select#job3"));
		if (disableDropdown.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
		WebElement disableCheckbox = driver.findElement(By.cssSelector("input#check-disbaled"));
		if (disableCheckbox.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
		WebElement slider02 = driver.findElement(By.cssSelector("input#slider-2"));
		if (slider02.isEnabled()) {
			System.out.println("Slider02 is enabled");
		} else {
			System.out.println("Slider02 is disabled");
		}
		
	}
	//@Test
	public void TC_03() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//input[@id='under_18']")).click();
		driver.findElement(By.xpath("//input[@id='java']")).click();
		WebElement under18RadionBtn = driver.findElement(By.cssSelector("input#under_18"));
		if (under18RadionBtn.isSelected()) {
			System.out.println("ELement is selected");
		} else {
			System.out.println("Element is de-selected");
		}
		WebElement javaCheckbox = driver.findElement(By.cssSelector("input#java"));
		if (javaCheckbox.isSelected()) {
			System.out.println("Element is selected");
		} else {
			System.out.println("Element is de-selected");

		}
		driver.findElement(By.xpath("//input[@id='java']")).click();
		WebElement javaCheckboxDeSelected = driver.findElement(By.cssSelector("input#java"));
		if (javaCheckboxDeSelected.isSelected()) {
			System.out.println("Element is selected");
		} else {
			System.out.println("Element is de-selected");
		}
	}
	@Test
	public void TC_04() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("nguyenthihongnhung9966@gmail.com");
		driver.findElement(By.cssSelector("input#new_username")).sendKeys("HongNhung");
		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#new_password"));
		passwordTextbox.clear();
		passwordTextbox.sendKeys("rose");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		passwordTextbox.clear();
		passwordTextbox.sendKeys("ROSE");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		passwordTextbox.clear();
		passwordTextbox.sendKeys("@#$%");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345678");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		passwordTextbox.clear();
		passwordTextbox.sendKeys("Hnhung.266");
		Assert.assertTrue(driver.findElement(By.cssSelector("button#create-account")).isEnabled());
		driver.findElement(By.cssSelector("input#marketing_newsletter")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());
	}
	@AfterClass
	public void afterClass () {
		driver.quit();
	}
	// Dùng sleep để set cứng tránh tình trạng mạng chậm trình duyệt nhảy lung tung các bước trong TC dẫn đến TC fail
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
