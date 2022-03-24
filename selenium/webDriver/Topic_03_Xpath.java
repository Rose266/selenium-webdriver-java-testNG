package webDriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath {
	WebDriver driver;
	String projectPath= System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass () {
		// Mở browser lên
		System.setProperty("webdriver.gecko.driver", projectPath+"\\browserDrivers\\geckodriver.exe");
		driver= new FirefoxDriver();
	
		
		// Hàm này sẽ áp dụng cho việc tìm element (findElement/findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Phóng to browser lên
		driver.manage().window().maximize();
		
		// Mở app lên
		driver.get("");
	}
	@Test
	public void TC_01_() {
		// Tìm (Find)- số ít trả về 1 cái
		// Thao tác trực tiếp không khai báo biến - sử dùng 1 lần/ không dùng lại element này
		driver.findElement(By.id("")).click();
		
		// Khai báo biến - dùng lại Element nhiều lần
		// Khai báo tên của Element + kiểu của Element
		WebElement loginButton = driver.findElement(By.id(""));
		loginButton.click();
		loginButton.isDisplayed();
		
		// Tìm (Find)- số nhiều- trả về 1 hoặc >1
		driver.findElements(By.id("")).size();
		
		// Lạp lại nhiều lần
		List<WebElement> loginCheckBoxes =driver.findElements(By.id(""));
		 
		// Có thể dùng vòng lặp để duyệt qua
		// Lấy tổng số lương, duyệt qua và click hết tất cả vào các checkbox
		/**for (int i=0; i< loginCheckBoxes.size();i++) {
			loginCheckBoxes.get(i).click();
		}*/
		
		// Thao tác (Action): Click/ type/ select/ hover/..
		
		// Kiểm tra dữ liêu (Verify/Assert): getText/ getAttribute/ getCss/..
		
		// Thao tác với Email textbox: 
		
		// Thao tác với Password textbox
		
		// Thao tác Login
		
		
	}
	@Test
	public void TC_02_() {
		
	}
	@AfterClass
	public void afterClass () {
		driver.quit();
	}
}