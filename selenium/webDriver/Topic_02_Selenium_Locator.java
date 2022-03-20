package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	// Khai báo một biến driver đại diện cho Selenium webDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Set gecko driver : giao tiếp giữa browser và code
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// Bật trình duyệt Firefox lên
		driver = new FirefoxDriver();

		// Set thời gian đi tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Bật browser to lên
		driver.manage().window().maximize();

		// Mở app lên
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() {
		// Email Adress Textbox(HTML)
		// Element: HTML tagname + HTML attribute name + HTML attribute value
		// <input type="text" class="inputtext _55r1 _6luy" name="email"
		// id="email" data-testid="royal_email" placeholder="Email hoặc số điện thoại"
		// autofocus="1" aria-label="Email hoặc số điện thoại">

		// Selenium có 8 loại locator
		// Id
		driver.findElement(By.id("email")).sendKeys("heokojkoc@gmail.com");
		// Class
		// <img class="fb_logo _8ilh img"
		// src="https://static.xx.fbcdn.net/rsrc.php/y8/r/dF5SId3UHWd.svg"
		// alt="Facebook">

		// Name
		driver.findElement(By.className("fb_logo")).isDisplayed();

		// Cách viết code để không bị lỗi (error/compile error)
		// driver đại diện cho thư viện của Selenium
		// Tìm element để tương tác lên

		// Tagname: Tìm xem có bao nhiêu cái element cùng loại ở trên page HTML
		// driver.findElement(By.tagName("a")); // Ra 1 cái đầu tiên

		driver.findElements(By.tagName("a"));

		// Link Text: Truyền cả text vào
		driver.findElement(By.linkText("Tiếng Việt"));

		// Partial Link Text: Truyền 1 phần của text
		// Độ chính xác không cao
		driver.findElement(By.partialLinkText("Tiếng"));
		driver.findElement(By.partialLinkText("Tiếng Việt"));
		driver.findElement(By.partialLinkText("Tiếng Việ"));
		driver.findElement(By.partialLinkText("Việt"));

		// Css
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input#email"));
		driver.findElement(By.cssSelector("#email"));

		driver.findElement(By.cssSelector("img.fb_logo"));
		driver.findElement(By.cssSelector("img[class='fb_logo _8ilh img'"));
		
		// fb_logo _8ilh img
		driver.findElement(By.cssSelector(".fb_logo"));

		driver.findElement(By.cssSelector("input[name='email']"));

		driver.findElement(By.cssSelector("a"));

		// Css không làm việc với text (dùng thuộc tính khác của thẻ a để thao tác)
		// Link vs Partial Link dùng Xpath là nhiều
		driver.findElement(By.cssSelector("a[title='Vietnamese']"));
		driver.findElement(By.cssSelector("a[onclick*='vi_VN']"));

		driver.findElement(By.cssSelector("a[title*='Vietnam'"));

		// Xpath
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//img[@class='fb_logo _8ilh img']"));
		driver.findElement(By.xpath("//img[contains(@class,'fb_logo')]"));
		driver.findElement(By.xpath("//img[starts-with(@class,'fb_logo')]"));
		driver.findElement(By.xpath("//input[@name='email']"));
		driver.findElement(By.xpath("//a"));
		driver.findElement(By.xpath("//a[text()='Tiếng Việt']"));
		driver.findElement(By.xpath("//a[contains(text(),'Tiếng')]"));
		driver.findElement(By.xpath("//a[contains(text(),'Tiếng Việt')]"));
		driver.findElement(By.xpath("//a[contains(text(),'Việt')]"));
	}

	@Test
	public void TC_02_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
