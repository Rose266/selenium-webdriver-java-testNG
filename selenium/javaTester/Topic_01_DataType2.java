package javaTester;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_DataType2 {
	// Hàm khởi tạo, cùng tên với class
	public Topic_01_DataType2 () {}
	
	// Biến static thuộc class, chỉ có 1, mình có thể truy cập biển thông qua class
	static int studentNumber;
	static boolean statusValue;
	
	// Trong java nếu kết hợp 2 từ khóa với nhau sẽ coi đây là 1 hằng số-> hắng số phải viết hoa và phân tách nhau bởi dấu gạch nối
	static final String BROWSER_NAME="Chrome";// constant
	
	static int studentPrice;
	
	// Biến này thuộc đối tượng của class
	String studentName="Automation PC";
	
	public static void main(String[] args) {
		int studentPrice = 5;
		
		System.out.println(studentPrice);
	
		System.out.println(Topic_01_DataType2.BROWSER_NAME );
		
	System.out.println(statusValue);
		
		Topic_01_DataType2 topic_1 = new Topic_01_DataType2();
		Topic_01_DataType2 topic_2 = new Topic_01_DataType2();
		Topic_01_DataType2 topic_3 = new Topic_01_DataType2();
		
		System.out.println(topic_1.studentName);
		System.out.println(topic_2.studentName);
		System.out.println(topic_3.studentName);
		
		
	}
	
	// Biến toàn cục
	String homePageUrl="";
	@BeforeClass
	public void beforeClass(){}
	
	@Test
	public void TC_01 () {
		// Biến cục bộ: Sử dụng trong phạm vi của TC/hàm
		String homePageUrl="";
		
		System.out.println(homePageUrl);
		
		// Block code
		if (3<5) {
			// Cục bộ- dùng trong khối lệnh này thôi
			String homePageTitle ="";
			
			System.out.println(homePageTitle);
			
		}
	}
	
	@Test
	public void TC_02 () {}
	
	@AfterClass
	public void afterClass () {
		
	}
}