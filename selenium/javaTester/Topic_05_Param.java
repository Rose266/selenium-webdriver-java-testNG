package javaTester;

import org.testng.annotations.Test;

public class Topic_05_Param {

	// Hàm không có tham số
	public void clicktoElement() {
		// void thường có () còn class thì không có mà có {}
	}
	
	// Hàm có 1 tham số
	// Tham số này có kiểu dữ liệu là string
	public void clicktoElement(String elementName) {
		
	}
	
	// Hàm có 2 tham số
	public void clicktoElement (String elementName, String locatorName) {
		
	}
	@Test // Test method/ Test case/ Test script
	public void TC_01_Login () {
		// Hàm có thể gọi qua hàm để dùng
		/**
		 * Truyền đúng số lượng tham số
		 * Truyền đúng kiểu dữ liệu của tham số đó
		 * Parameter= tham số
		 * Tạo ra nó thì là tham số, lôi nó ra dùng thì là đối số */
		clicktoElement("Textbox");
		
		
	}
}
