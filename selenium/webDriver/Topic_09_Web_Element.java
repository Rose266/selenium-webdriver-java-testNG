package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Web_Element {
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
	public void TC_01_Define_Element() {
		// Muốn thao tác được với Element thì phải tìm Elemet trước
		// Sau đó mới áp dụng hành vi vào cho Element đó
		// Hành vi tương tác vs element bắt đầu bằng get thì luôn trả về kiểu dữ liệu
		// Hành vi tương tác với element bắt đầu bằng "is" thì luôn trả về boolean
		// Hàm trả về void thì luôn là action
		// Get và Is thì thường dùng để verify dữ liệu
		// Action thì dùng để thao tác lên element
		
		
		// 1- Tìm Element
		// 2- Tìm với loại locator gì
		// 3- Tương tác lên/Kiểm tra nó
		// Lưu ý trong khi viết code dấu đóng ngoặc và mở ngoặc phải đầy đủ
		driver.findElement(By.className(""));
		
		// Muốn thao tác trực tiếp lên element thì không cần khai báo biến
		driver.findElement(By.id("send2")).click();
		
		// Thao tác từ 2 lần trở lên thì cần khai báo biến tránh việc lặp lại
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("email")).isDisplayed();
		
		//Kiểu dữ liệu + tên biến
		// Cách viết như này tìm element xong gán vào biến này 
		WebElement emailTextbox = driver.findElement(By.id("id"));
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		emailTextbox.isDisplayed();// check display
		
	}
	@Test
	public void TC_02_Method_Element() {
		WebElement element = driver.findElement(By.id(""));
		
		// Xóa dữ liệu trong những field cho phép nhập
		// Luôn clear hết dữ liệu trước khi thao tác lên field đó
		// Textbox/ TextArea/ Editable Dropdown
		element.clear();//*****// dùng nhiều
		
		
		// Nhập dữ liệu trong những field cho phép nhập
		element.sendKeys("");//*****// dùng nhiều
		element.sendKeys(Keys.ENTER);
		
		// Element cha và Element con
		// Thực tế không dùng như này
		driver.findElement(By.className("footer")).findElement(By.cssSelector("a[title='My Account']"));
		// Thực tế dùng như này:
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		
		// Lấy giá trị trong thuộc tính
		element.getAttribute("");//*****// dùng nhiều
		driver.findElement(By.id("search")).getAttribute("placeholder");
		// Lấy ra đoạn text là Search entire store here...
		
		// Các hàm action lên Element/Browser thì không có kiểu trả về
		// Các hàm để verify/lấy dữ liệu thì nó luôn có kiểu trả về
		
		// Verify 1 step
		// Không khai báo biến và verify trực tiếp
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"),"Search entire store here...");
		// Khai báo biến để dùng nhiều lần 
		String searchTextboxPlaceholderValue = driver.findElement(By.id("search")).getAttribute("Search entire store here...");
		Assert.assertEquals(searchTextboxPlaceholderValue, "Search entire store here...");
		// Sau đó là hành động gì đó, dùng 1 lần thì ko cần khai báo biến
		// Assert không phải của testNG
		
		// Thường dùng để kiểm tra về UI
		// GUI: Font type/ Font Size/ Color/ Pixel/...
		// Ví dụ kiểm tra button login có màu gì
		element.getCssValue("background-color");
		// Sau khi get nó sẽ ra rgb(51, 204, 93) và là string 
		element.getCssValue("font-size");//*****// dùng nhiều
		// ra 13px
		
		// getLocation getRect và getSize bỏ qua vì thực tế không dùng
		// GUI: position, location, size của element
		element.getLocation(); //lấy ra vị trí của element trong màn hình nào đó
		element.getSize();//Lấy ra kích thước
		element.getRect();// Lấy ra vị trí và kích thước
		
		// Học trong framework Attach screenshots to Report HTML
		element.getScreenshotAs(OutputType.BASE64);//*****// dùng nhiều
		element.getScreenshotAs(OutputType.BYTES);//*****// dùng nhiều
		element.getScreenshotAs(OutputType.FILE);//*****// dùng nhiều
		
		element = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"));
		element = driver.findElement(By.cssSelector("#advice-validate-email-email"));
		String emailTextboxTagname = element. getTagName();
		// dùng trong trường hợp không ghi/có tên thẻ
		// dùng trong trường họp output của element này sẽ là input của element khác-- ít khi xảy ra
		
		// Truyền 1 biến vào trong 1 chuỗi thì tách chuỗi thành 2 chuỗi mới và truyền biến vào giữa
		// Chuỗi T1 + biến + chuỗi T2
		element = driver.findElement(By.xpath("//" + emailTextboxTagname  + "[@id='advice-validate-password']"));
		
		// Lấy ra text của element hiện tại và text của element con bên trong
		element.getText();//*****// dùng nhiều
		
		// Mong muốn 1 element hiển thị hoặc không hiển thị
		// Hiển thị là người dùng nhìn thấy được/ thao tác được/ có kích thước cụ thể(chiều rộng/cao)
		// Áp dụng cho tất cả các loại element
		element.isDisplayed();//*****// dùng nhiều
		
		// Mong muốn 1 element có thể thao tác được lên hoặc không 
		// Ngược lại với disable
		// Thao tác được thì gọi là enable
		// Không thao tác được là disable
		// Áp dụng cho tất cả các loại element
		element.isEnabled();//*****// dùng nhiều
		
		// Mong muốn 1 element đã được chọn hay chưa
		// Checkbox chọn và bỏ chọn trên chính nó được + hình vuông
		// Radio button Chọn/ không được bỏ chọn + hình tròn
		// Áp dụng cho 1 vài loại element: Checkbox/ Radio button/ Dropdown (Default)
		element.isSelected();//*****// dùng nhiều
		
		// Click vào 1 element
		// Button/ Link/ Checkbox/ Radio/ Image/ Icon/...
		element.click();//*****// dùng nhiều
		
		
		// Giống hành vi ENTER ở cac form
		// Chỉ dùng cho tagname: form (element con bên trong)
		
		// Tất cả các đoạn driver đều phải nằm dưới đoạn khởi tạo
		// NullPonterException chưa được khởi tạo đã lôi ra dùng
		
		
	
	
	}
	@AfterClass
	public void afterClass() {
		
	}
}
