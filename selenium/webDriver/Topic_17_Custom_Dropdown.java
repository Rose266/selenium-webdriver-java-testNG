package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		// Wait cho các trạng thái của element: visible/ presence/invisible/staleness
		// Visible phải thấy được trên giao diện 
		// Presence phải có trong HTML
		// Visible bao gồm cả presence nhưng presence không bao gồm visibles
		// Nên chọn presence 
		
		// Wait cho việc tìm element (findElement)
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	//@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemCustomDropdownList("span#number-button>span.ui-selectmenu-icon ", "ul#number-menu div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "5");
		
		selectItemCustomDropdownList("span#number-button>span.ui-selectmenu-icon ", "ul#number-menu div", "15");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "15");
		
		selectItemCustomDropdownList("span#number-button>span.ui-selectmenu-icon ", "ul#number-menu div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "19");
		
		selectItemCustomDropdownList("span#number-button>span.ui-selectmenu-icon ", "ul#number-menu div", "3");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "3");
		
	}
	
	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemCustomDropdownList("i.dropdown", "div.item span.text", "Christian");
		Assert.assertEquals(driver.findElement(By.cssSelector(" div.divider")).getText(), "Christian");
	}
	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemCustomDropdownList("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
	}
	@Test
	public void TC_04_Angular() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		//C1: Text khong nam o HTML
		selectItemCustomDropdownList("ng-select[bindvalue='provinceCode']","div[role='option']>span.ng-option-label", "Tỉnh Tuyên Quang");
		String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText");
		Assert.assertEquals(actualText, "Tỉnh Tuyên Quang");
		// C2: 
		selectItemCustomDropdownList("ng-select[bindvalue='provinceCode']","div[role='option']>span.ng-option-label", "Tỉnh Tuyên Quang");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText(), "Tỉnh Tuyên Quang");
		// C3: 
		selectItemCustomDropdownList("ng-select[bindvalue='provinceCode']","div[role='option']>span.ng-option-label", "Tỉnh Tuyên Quang");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getAttribute("innerText"), "Tỉnh Tuyên Quang");
		
		
		//C1:Text khong nam o HTML
		selectItemCustomDropdownList("ng-select[bindvalue='districtCode']", "div[role='option'] span", "Huyện Yên Sơn");
		String actualText1 = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='districtCode'] span.ng-value-label\").innerText");
		Assert.assertEquals(actualText1, "Huyện Yên Sơn");
		//C2:
		selectItemCustomDropdownList("ng-select[bindvalue='districtCode']", "div[role='option'] span", "Huyện Yên Sơn");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='districtCode'] span.ng-value-label")).getText(), "Huyện Yên Sơn");
		//C3
		selectItemCustomDropdownList("ng-select[bindvalue='districtCode']", "div[role='option'] span", "Huyện Yên Sơn");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='districtCode'] span.ng-value-label")).getAttribute("innerText"), "Huyện Yên Sơn");
		
		
	

	}
	
	@Test
	public void TC_05_Editdable() {
		
	}

		
		
	
	@Test
	public void TC_06_Multiple_Select() {
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void  selectItemCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem){
		// parent: thường là toggle để khi ấn vào hiện dropdownlist
		// child: thẻ chứa text
		// expected item: sau khi chọn text thẻ chứa giá trị hiển thị sau khi chọn tại dropdown
		//- Step 1: Click vào 1 element cho nó sổ ra hết các item
			driver.findElement(By.cssSelector(parentLocator)).click();
			sleepInSecond(5);
		//- Step 2: Chờ các item load hết ra thành công
		// Lưu ý 1: Locator chứa hêt tất cả các item
		// Lưu ý 2: Locator phải đến node cuối cùng chứa text
		// Wait nhận tham số là By 
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		//- Step 3: Tìm item càn chọn
		// Lấy hết tất cả các element (item) ra sau đó duyệt qua từng item
			List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
			
		// Duyệt qua từng item getText của item ra
		// Nếu Text = item mình mong muốn (item cần click vào)	
		//  + B1: Nếu item cần chọn nằm trong vùng nhìn thây thì không cần scroll tới element tìm tiếp 
		//	+ B2: Nếu item cần chọn nằm ở dưới thì scroll tới  item đó
		// Click vào item đó
		// Thoát khỏi vòng lặp không có kiểm tra item tiếp theo nữa
			for(WebElement item : allItems) {
				String actualText = item.getText();
				System.out.println("Actual Text = " + actualText);
				if(actualText.equals(expectedTextItem)) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
					sleepInSecond(5);
					item.click();
					sleepInSecond(5);
					break;
				}
			}
		
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		}
}
