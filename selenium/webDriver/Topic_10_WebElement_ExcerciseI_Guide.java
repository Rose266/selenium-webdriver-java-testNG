package webDriver;

import org.openqa.selenium.By;

public class Topic_10_WebElement_ExcerciseI_Guide {

	// Chạy BeforeClass trước để Open/ Create tương đương vs Pre-condition --> Sau đó chạy TC
	// Chạy TC 01, TC 02,...--> Xong hết --> AfterClass
	// Chạy AfterCLass đóng Browser/ Delete ... tương đương vs Postcondition
	// => Before chạy trước cho tất cả các TC và After chạy sau cho tất cả các TC
	// => Có hàm chạy trước cho từng TC
	// Ví dụ mở ra màn hình Đăng ký cho tất cả các TC. 
	//=> Mở ra vì Code đỡ phải mất công viết đi viết lại nhiều lần và trường hợp TC bị submit fail làm cho TC không hoạt động được
	// (cont) => Chạy tiếp bị sai => hàm BeforeMethod
	// BeforeMethod -> TestMethod-> every Lặp lại cho tât cả các TC/ Chạy trước cho tất cả các TC
	// BeforeClass -> TestClass-> one Chạy 1 lần đầu tiên cho tất cả các TC/ Chạy trước cho TC đầu tiên
	// NullPonterException chưa được khởi tạo đã lôi ra dùng khi sử dụng driver
	// Driver chưa được bật lên, trình duyệt chưa đc mở ra nên không find được=>> chỉ viết khi có thì ms viết
	// By không đi tìm mà chỉ lưu thành chuỗi,được lưu như 1 chuỗi nên sử dụng được tại class
	By phoneErrorMessage = By.id("txtPhone-error");
	// Muốn uppercase nhanh vào word vào change case
	// hard code= fix cứng dữ liệu
	// implicityWait Ảnh hưởng cho 2 hàm findElement/findElements
	// Nguyên tắc khi chạy 
	/** 
	 * Nếu như tìm thấy element thì không cần chờ hết timeout (20s)
	 * Nếu như chưa tìm thấy element thì 
	 * + Mỗi nửa giây sẽ tìm lại 1 lần 0.5s
	 * + Nếu như chưa tìm tìm thấy thì cứ tìm lại cho đến khi nào hết timeout 20s thì thôi
	 * + Sau 20 s nó sẽ bị fail TC
	 * */
}
