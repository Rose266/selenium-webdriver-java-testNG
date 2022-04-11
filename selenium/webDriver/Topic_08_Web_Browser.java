package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Web_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass () {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_01_Method () {
		//Browser
		/** 1. Tương tác với Browser thông qua hàm driver
		 *  Gọi 1 thư viện để dùng qua dấu chấm
		 *  Driver đang có kiểu WebDriver thì hậu tố của nó thằng nào là webdriver sẽ là do nó quản lý
		  */ 
		/** 2. Kiểu trả về của hàm là gì void/String/Class/Interface
		 * Void là action tương tác lên browser
		 */
		/** 3. Tên hàm
		 * Nhìn tên hàm biết nó dùng để làm gì, chứa ý nghĩa trong đó luôn
		 * Hàm lấy dữ liệu ra thì thường có tiền tố là get
		 * Tên hàm hàm chứa cách dùng, tính năng của hàm
		 * */
		/** 4. Sau tên hàm nằm trong dấu ()
		 * Nếu không có gì thì hàm này không cần truyền tham số
		 * Nếu có thì cần truyền đúng kiểu dữ liệu mà nó định dạng định nghĩa bên trong(quan tâm thêm số lượng tham số là bao nhiê)
		 * 
		 * */
		// Action lên browser
				// Open browser
				// Open URL
				// Refresh/ Back/ Foward
				// Maximize/ Minimize/ Fullist
				
				// Lấy dữ liệu ra từ Browser
				// Get Url/ Get title/ Get source page/ Get position/ Get Location...
		
		//Element Học trong bài **Wait**
		driver.findElement(null);// *** Dùng nhiều*** //
		driver.findElements(null);// *** Dùng nhiều*** //
		
		// WebBrowser Method
		// Dùng để close browser hoặc tab;
		// Nếu như chỉ có 1 tab : close browser
		// Nếu như có nhiều tab: close tab đang active
			driver.close();
			
		// Dùng để đóng browser luôn
		// Không quan tâm có bao nhiêu tab
			driver.quit();// *** Dùng nhiều*** //
			
		// Mở 1 url ra
			driver.get("");// *** Dùng nhiều*** //
		
		// Lấy ra Url của page hiện tại/page đang đưng 
			driver.getCurrentUrl();
			
		// Lấy ra scope (HTML/JS/CSS) của page hiện tại
			driver.getPageSource();
			
		// Lấy ra title của page hiện tại, đại diện cho page
			driver.getTitle();
		
		// Học trong bài **window/tab** sau
		// Dùng để xử lý window và tab
		// Lấy ra ID của window/tab đang activer
			driver.getWindowHandle();// *** Dùng nhiều*** //
			
		// Lấy ra ID của tất cả các tab/window đang có	
			driver.getWindowHandles();// *** Dùng nhiều*** //
			
		// Manage không trả về kiểu dữ liệu mà trả về option interface kiểu 
		// **Frame work- cookie** sẽ có bài về cookie sau
		// Cookie lưu lại phiên đăng nhập/ session/ dữ liệu duyệt web/... 
			driver.manage().deleteAllCookies();
			
		// **Framework-logs** sẽ có bài về log sau
			driver.manage().logs();
		
		// Chờ cho find element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);// *** Dùng nhiều*** //
		
		//Chờ cho page load thành công
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		// Chờ cho 1 đoạn code Javascript được thực thi thành công
		//****javascriprt executor*****
		// asynchronous script bắt đồng bộ
		// synchronous scriprt đồng bộ
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		 
		// Giả lập full hết toàn màn hình
		driver.manage().window().fullscreen();
		
		// Mở rộng hết cửa sổ
		driver.manage().window().maximize();// *** Dùng nhiều*** //
		
		// Thực tế không dùng test GUI/Reponsive (Font/Size/Color/Position/Location/..) 
		// Set vị trí của browser so với độ phân giải màn hình
		driver.manage().window().setPosition(new Point(100, 250));
		driver.manage().window().getPosition();
		
		// Mở browser với kích thước là bao nhiêu
		// Test responsive
		driver.manage().window().setSize(new Dimension(1920, 1000));
		driver.manage().window().getSize();
		
		// Tracking history tốt hơn/ thực tế không dùng
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://kenh14.vn/");
		driver.get("https://kenh14.vn/");
		
		//****Alert*****
		// Alert
		driver.switchTo().alert();// *** Dùng nhiều*** //
		
		//****Frame/Iframe*****
		// Frame/Iframe
		driver.switchTo().frame(0);// *** Dùng nhiều*** //
		 
		//****Window/Tab*****
		//Window/Tab
		driver.switchTo().window("");// *** Dùng nhiều*** //
		
	}
	@Test
	public void TC_02 () {
		
	}

}
