package webDriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Find_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}
	//@Test
	public void TC_01_Find_Element() {
		// - Có duy nhất 1 Element
		// Nếu như xuất hiện ngay-> Thì trả về element đó không cần chờ hết timeout
		// Nếu như Element chưa xuất hiện -> Thì sau 0.5s sẽ tìm lại cho đến khi hết timeout thì thôi
		// Trả về duy nhất 1 Element
		// 		System.out.println(" Start Time = " + getCurrentTime());
		//Trường hợp xuất hiện ngay
		// 		driver.findElement(By.xpath("//input[@id='email']"));
		// Trường hợp chưa xuất hiện phải tìm lại
		// Đang tìm->Click regrister->thấy
		// 		driver.findElement(By.xpath("//input[@name='firstname']"));
		// 		System.out.println(" End Time = " + getCurrentTime());

		
		
		// - Không có Element nào hết
		// Nó sẽ tìm đi tìm lại cho đến khi hết timeout
		// Sau khi hết timeout thì đánh fail cả testcase
		// Không có chạy các step còn lại
		// Throw/Log ra 1 Exception(ngoại lệ)- Thông báo lỗi: NoSuchElement - không tìm thấy element
		//			System.out.println(" Start Time = " + getCurrentTime());
		//			driver.findElement(By.xpath("//input[@name='nhung']"));
		//			System.out.println(" End Time = " + getCurrentTime());
		
		
		
		// - Có nhiều hơn 1 Element
		// Điều duy nhất khác tìm duy nhất 1 Element là
		// 	+ Lấy ra cái đầu tiên để thao tác, không quan tâm đến cái con lại
		System.out.println(" StartTime = " + getCurrentTime());
		driver.findElement(By.xpath("//div[@id='pageFooterChildren']//a[text()]"));
		System.out.println(" EndTime = " + getCurrentTime());
	}
	//@Test
	public void TC_02_Find_Elements() {
		int elementNumber = 0;
		// - Có duy nhất 1 Element
		// - Có nhiều hơn 1 Element
		// Nếu như xuất hiện ngay-> Thì trả về element đó không cần chờ hết timeout
		// Nếu như Element chưa xuất hiện -> Thì sau 0.5s sẽ tìm lại cho đến khi hết timeout thì thôi
		//		elementNumber = driver.findElements(By.xpath(" //input[@name='email']")).size();
		//		System.out.println(" 1 element = " + elementNumber);
		//		
		//		elementNumber = driver.findElements(By.xpath(" //div[@id='pageFooterChildren']//a[text()]")).size();
		//		System.out.println(" n element = " + elementNumber);
		// - Không có Element nào
		// Element và Elemnts khach nhau ở step này
		// Nó sẽ tìm đi tìm lại cho khi hết timeout
		// Sau khi hết timeout thì không đánh fail step này
		// Vẫn chạy tiếp các step tiếp theo
		// Vì nó tìm và trả về 1 list nên không tìm thấy nó sẽ trả về list rỗng
		System.out.println("StartTime = " + getCurrentTime());
		elementNumber = driver.findElements(By.xpath("//input[@name='Nhung']")).size();
		System.out.println(" 0 element = " + elementNumber);
		System.out.println("EndTime = " + getCurrentTime());
		
		
	}
	
	@AfterClass
	public void afterClass(){
		driver.quit();
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Hàm đo thời gian xem trả về thời gian  bao nhiêu
	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}
	
}
