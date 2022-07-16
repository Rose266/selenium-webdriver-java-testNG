package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Handle_Frame_Iframe {
	WebDriver driver;
	Select select;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver ();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	//@Test
	public void TC_Iframe_I() {
		driver.get("https://kyna.vn/");
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'//www.facebook.com')]")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "166K likes");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Hong Nhung");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0912345677");
		select = new Select(driver.findElement(By.cssSelector("select[id='serviceSelect']")));
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea.meshim_widget_widgets_TextArea")).sendKeys("hoahong");
		driver.findElement(By.cssSelector("input.submit")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(Keys.ENTER);
		List<WebElement> items = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement item : items) {
			Assert.assertTrue(item.getText().contains("Excel"));
		}
		}
	@Test
	public void TC_08_Frame_II() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("12699686");
		driver.findElement(By.cssSelector("a.btn-primary")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
