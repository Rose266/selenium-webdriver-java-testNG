package javaTester;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;

public class Topic_01_DataType {
	static int studentNumber;

	public static void main(String[] args) {
		System.out.println("Testing");
		System.out.println("studentNumber");
		// Khai báo trước xong khởi tạo
		// Khai báo biến: Kiểu dữ liệu + tên biến
		int studentNumber;
		
		// Khởi tạo dữ liệu 
		studentNumber = 100;
		
		// Vừa khai báo vừa khởi tạo
		int teacherNumber = 20;
		// Kiểu dữ liệu
		// I- Nguyên thủy (Primitive)
		//boolean: luận lý/logic- 2 giá trị (true/false)
		boolean studentSex = true; 
		// Sô nguyên - không có phần thập phân - theo thứ tự phạm vi từ nhỏ đến lớn
		// byte
		 byte bEmployee = 10;
		// short
		 short sEmployee = 10;
		// int
		 int iEmployee = 10;
		// long 
		 long lEmloyee = 10;
		// Số thực - có phần thập phân
		// float 
		 float fEmpolyee = 7.5f;
		// double
		double dEmployee= 7.5f;
		// Ký tự
		// char : 1 kí tự
		// char a='B';
		
		// II- Tham chiếu (Reference)
		// 1 Array: mã- tập hợp nhiều giá trị của 1 kiểu dữ liệu nào đó, 
		// Array: Enables storing collection of multiple items under a single variable name
		// int [] studentNumber = { 15,20,-7,50 };
		// 2 String [] studentName = {"Nguyễn Thu Linh","Nguyễn Minh Thúy"};
		
		// 3 Interface/Class
		// WebDriver driver = new ChromeDriver();//Interface
		
		// Actions action = new Actions(driver);// Class
		
		// 4 Collection: List(ArrayList/ LinkedList)/ Set/ Queue
		// ArrayList<String> studentCountry = new ArrayList<String>();
		
		// 5 Object - có thể ép qua những kiểu dữ liệu khác được
		Object phone;
		
		
		// String: Chuỗi kí tự: Số/ Chữ/ Kí tự đặc biệt
		// String studentNickName ="B";
		// String companyName = "Hoang Gia An 2022 @Copyright";*/
		String a ="Hoang";
		System.out.println(a);// Dùng để ktra kết quả chạy như nào
		
		String b= a;
		System.out.println(a);
		System.out.println(b);
		
		 b="An";
		 a=b;
		System.out.println(a);
		System.out.println(b);
		
		
		
	}

}
