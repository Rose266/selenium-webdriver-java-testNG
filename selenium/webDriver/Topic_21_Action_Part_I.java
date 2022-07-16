package webDriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Action_Part_I {
	WebDriver driver;
	Alert alert;
	JavascriptExecutor jsExecutor;
	Actions action;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name").toLowerCase();
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver ();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
		//System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver ();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	//@Test
	public void TC_00_Knowledge() {
		//Hàm build có nhiệm vụ là kết hợp nhiều hàm lại với nhau nhưng thực ra không cần, vì chả cần build vẫn chạy đc
		// Build=> bỏ qua
		// action.clickAndHold().moveToElement(driver.findElement(By.id(""))).build();
		
		// click()Actions dùng cho trình duyệt=> bỏ(không cần)
		
		// click(WebElementtarget)Actions: Dùng/tương tác vs element=> cần
		
		// clickAndHold(WebElementtarget)Actions nhấn và giữ chuột
		
		// contextClick(WebElementtarget)Actions click chuột phải
		
		// doubleClick(WebElementtarget)Actions click đúp, click trái hai lần 
		
		// keyDown(WebElementtarget.charSequence key) nhấn xuống và truyền phím vào cho element
		
		// keyUp (WebElementtarget.charSequence key) nhả ra
		
		// moveToElement(WebElementtarget) hover đến 1 element nào đó
		
		// perform() hàm bắt buộc sau mấy hàm trên, phải gọi ra ms sử dụng đc/ thực thi hành động ở trên 
		
		// release() nhả chuột trái ra dùng vs clickAndHold()
		
		//sendKeys(WebElementtarget.charSequence key) send phim cho 1 element nào đó(ví dụ enter)
			
	}
	//@Test
	public void TC_01_Hover_To_Element_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement ageTextBox = driver.findElement(By.cssSelector("input#age"));
		action.moveToElement(ageTextBox).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}
	// fail @Test
	public void TC_02_Hover_To_Element_I() {
		driver.get("https://www.myntra.com/");
		WebElement kidsNavLink = driver.findElement(By.xpath("//header//a[text()='Kids']"));
		action.moveToElement(kidsNavLink).perform();
		WebElement homeBathNavLink = driver.findElement(By.xpath("//a[text()='Home & Bath']"));
		action.click(homeBathNavLink).perform();
		String homeBathText = driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText();
		Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(), homeBathText);
	}
	// chưa học popup để sau @Test
	public void TC_03_Hover_To_Element_II() {
		driver.get("https://www.fahasa.com/");
		action.moveToElement(driver.findElement(By.cssSelector("a#NC_CLOSE_ICON"))).click().perform();
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//li[@class='parent dropdown aligned-left active']//span[text()='FOREIGN BOOKS']"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='dropdown-menu']//span[text()='FICTION']")).isDisplayed());
	}
	//@Test
	public void TC_04_Click_And_Hold_Element_Select_Multiple_Item() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> numberList = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		action.clickAndHold(numberList.get(0)).moveToElement(numberList.get(3)).release().perform();
		List<WebElement> selectedNumberList = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(selectedNumberList.size(), 4);
		
	}
	//@Test
	public void TC_05_Click_And_Select_Element_Select_Random_Item() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> numberList = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		Keys controlKey;
		if(osName.contains("win") || osName.contains("nux")) {
			controlKey = Keys.CONTROL;
		} else {
			controlKey =Keys.COMMAND;
		}
		action.keyDown(controlKey).perform();
		action.click(numberList.get(0)).click(numberList.get(4)).click(numberList.get(10)).perform();
		action.keyUp(controlKey).perform();
		List<WebElement> selectedNumberList = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(selectedNumberList.size(), 3);
	}
	//@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(5);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)",driver.findElement(By.xpath("//button[text()='Double click me']")));
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}
	//@Test
	
	public void TC_07_Right_Click_To_Element() {
		
		//Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Quit']")).isSelected());
		//action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
		//Assert.assertTrue(driver.findElement(By.xpath("li[class='context-menu-item context-menu-icon context-menu-icon-quit context-menu-visible']")).isDisplayed());
		//action.click(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
		//alert = driver.switchTo().alert();
		//alert.accept();
		//Assert.assertFalse(driver.findElement(By.cssSelector("span.context-menu-one")).isDisplayed());
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible")).isDisplayed());
		action.click(driver.findElement(By.cssSelector("li.context-menu-visible"))).perform();
		explicitWait.until(ExpectedConditions.alertIsPresent()).accept();

	}
	//@Test
	public void TC_08_Drag_And_Drop_Element_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallCircle = driver.findElement(By.cssSelector("div #draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("div #droptarget"));
		action.clickAndHold(smallCircle).perform();
		action.dragAndDrop(smallCircle, bigCircle).perform();
		String bigCirleColorRbg = bigCircle.getCssValue("background-color");
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		Assert.assertEquals(Color.fromString(bigCirleColorRbg).asHex().toLowerCase(), "#03a9f4");
	}
	//@Test
	public void Tc_09_Drag_And_Drop_HTML5 () throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		// Hàm import vào chỉ sử dụng css vì có jquery
		// drag_and_drop_helper.js Hàm để giả lập hành vi kéo thả trên web dùng code jquery
		// jquery_load_helper.js nếu như web app đang sử dụng chưa dùng thư viện jquery thì nó sẽ inject vào app này được
		String squareA = "#column-a";
		String squareB = "#column-b";
		String dragAndDropHelperContent = getContentFile(projectPath + "\\dragAndDrop\\drag_and_drop_helper.js");
		dragAndDropHelperContent = dragAndDropHelperContent + "$(\"" + squareA + "\").simulateDragDrop({dropTarget: \"" + squareB + "\"});";
		jsExecutor.executeScript(dragAndDropHelperContent);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		
		jsExecutor.executeScript(dragAndDropHelperContent);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());

		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
}
