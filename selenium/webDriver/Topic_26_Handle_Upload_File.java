package webDriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Handle_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	// Cách lấy đường dẫn tương đối
	// D:\\Automation\\01-Software\\WebDriver\\selenium-webdriver-java-testng
	//String doorFileName = "door.jpg";
	//String tableFileName = "table.jpg";
	//String windowFileName = "Window.jpg";
	
	//Chỉ support cho các máy Windows
	// Windows support \\
	// Mac,Linux support //
	//String uploadFileFolderPath = projectPath + "\\uploadImages\\";
	//String doorFilePath = uploadFileFolderPath + doorFileName;
	//String tableFilePath = uploadFileFolderPath + tableFileName;
	//String windowFilePath = uploadFileFolderPath + windowFileName;
	
	
	//Cách giải quyết là dùng thư viện java separator-> Tùy OS mà nó sẽ \\ hoặc // 
	//String uploadFolderPath = projectPath + File.separator +"uploadImages" + File.separator;
	//String doorFilePath = uploadFolderPath + doorFileName;
	//String windowFilePath = uploadFolderPath + windowFileName;
	//String tableFilePath = uploadFolderPath + tableFileName;
	
	String doorFileName = "door.jpg";
	String tableFileName = "table.jpg";
	String  windowFileName = "Window.jpg";
	
	String uploadFolderPath = projectPath + File.separator + "uploadImages" + File.separator;
	
	String doorFilePath = uploadFolderPath + doorFileName ;
	String tableFilePath = uploadFolderPath + tableFileName;
	String windowFilePath = uploadFolderPath + windowFileName;
	
	@BeforeClass
	public void beforeClass () {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// Chạy headless với Firefox
		//FirefoxOptions options = new FirefoxOptions();
		//options.addArguments("-headless"); 
		//options.addArguments("window-size=1920x1080");
		//driver = new FirefoxDriver(options);
		
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		
		// Dùng chạy headless
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("headless");
		//options.addArguments("window-size=1920x1080");
		//headless thì không cần khởi tạo vì có option rồi driver = new ChromeDriver(options);
		
		//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		//driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println(projectPath);
		System.out.println(uploadFolderPath);
		System.out.println(doorFilePath);
		System.out.println(windowFilePath);
		System.out.println(tableFilePath);
		
	}
	//@Test
	public void TC_01_One_File_One_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		// Selenium sendkeys method
		// Không click vào button AddFile để bật open file dialog lên
		// Cách này chỉ xử lý được trên máy này và upload 1 file. Vì sang máy window khác đường dẫn này sẽ sai
		/** Đây là đường dẫn tuyệt đối 
		 * D:\\Automation\\01-Software\\WebDriver\\selenium-webdriver-java-testng\\uploadImages\\door.jpg;
		 */
		// Cách 1 : Lưu ý không dùng vì khi upload 1 file-> vì element sẽ bị thay đổi trạng thái cập nhật lại
		// Sau khi truyền lần 1-> Cập nhật lại trạng thái-> truyền tiếp vs element cũ-> Fail
		//WebElement uploadFile =driver.findElement(By.xpath("//input[@type='file']"));
		//uploadFile.sendKeys(doorFilePath);
		
		
		// Cách 2 
		// Load File
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(doorFilePath);
		sleepInSecond(5);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(windowFilePath);
		sleepInSecond(5);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(tableFilePath);
		sleepInSecond(5);
		
		// Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() ='"+ doorFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+tableFileName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + windowFileName +"']")).isDisplayed());
		
		
		// Click to upload button at each file
		// Cho dừng 2s để click chậm-> ít lỗi-> như end user
		List<WebElement> uploadButton = driver.findElements(By.xpath("//table//span[text()='Start']"));
		for(WebElement button : uploadButton) {
			button.click();
			sleepInSecond(2);
		}
			
		//Verify Upload Success
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+doorFileName+"']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+tableFileName+"']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+windowFileName+"']")).isDisplayed());
		
	}
	//@Test
	public void TC_02_Mutiple_File_One_Time() {
				driver.get("https://blueimp.github.io/jQuery-File-Upload/");
				
				
		        // Load File
				// Truyền nhiều file thì thay vì truyền đường dẫn từng file thì h truyền đường dẫn 3 file 1 lúc
				// Khi truyền nhiều đường dẫn thực tế nó được phân tách bới xuống dòng-> thêm "\n" kí tự xuống dòng trong java
				driver.findElement(By.xpath("//input[@type='file']")).sendKeys(doorFilePath + "\n" + windowFilePath + "\n" + tableFilePath);
				
				// Verify file upload success
				Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() ='"+ doorFileName +"']")).isDisplayed());
				Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+tableFileName+"']")).isDisplayed());
				Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + windowFileName +"']")).isDisplayed());
				
				
				// Click to upload button at each file
				// Cho dừng 2s để click chậm-> ít lỗi-> như end user
				List<WebElement> uploadButton = driver.findElements(By.xpath("//table//span[text()='Start']"));
				for(WebElement button : uploadButton) {
					button.click();
					sleepInSecond(2);
				}
					
				//Verify Upload Success
					Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+doorFileName+"']")).isDisplayed());
					Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+tableFileName+"']")).isDisplayed());
					Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+windowFileName+"']")).isDisplayed());
					
	}
	//@Test
	public void TC_03_Redo_TC02() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(windowFilePath + "\n" + tableFilePath + "\n" + doorFilePath);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + doorFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + tableFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + windowFileName + "']")).isDisplayed());
		
		List<WebElement> uploadButton = driver.findElements(By.xpath("//table//span[text()='Start']"));
		for (WebElement button : uploadButton) {
			button.click();
			sleepInSecond(2);	
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='" + doorFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='" + tableFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='" + windowFileName +"']")).isDisplayed());
		
	}
	//@Test
	public void TC_04_Upload_Gofile() {
		driver.get("https://gofile.io/?t=uploadFiles");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(doorFilePath + "\n" + tableFilePath + "\n" + windowFilePath);
		sleepInSecond(10);
		Assert.assertEquals(driver.findElement(By.xpath("//h5")).getText(),"Your files have been successfully uploaded");
		String uploadLink = driver.findElement(By.cssSelector("table a")).getAttribute("href");
		driver.get(uploadLink);
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + windowFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + tableFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + doorFileName + "']")).isDisplayed());
		
	}
	@Test
	public void TC_05_Upload_File() {
		
	}
	@AfterClass
	public void afterClass() {
	 driver.quit();	
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
