package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class LaunchBrowserAdminLoginCourses {
	public WebDriver driver;
	public Actions action;
	public ExtentTest eTest;
	public ExtentReports eReport;

	// final Logger logger = Logger.getLogger(LaunchBrowser.class);

	@Parameters({ "url", "Login", "Password" })
	//@BeforeMethod
	public void openBrowser(String url, String Login, String Password) throws InterruptedException {

		// Chrome path mentioned in project
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		// Open URL and enter username and password Fields
		driver.navigate().to(url);
		Thread.sleep(5000);
		driver.findElement(By.name("username")).sendKeys(Login);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.id("loginbtn")).click();

		// This WebElements use only for Production login
		// Select org=new
		// Select(driver.findElement(By.xpath("//select[@name='exampleFormControlSelect1']")));
		// org.selectByValue(Organization);
		// driver.findElement(By.id("loginbtn")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(10000);
		WebElement LoginTitle = driver.findElement(By.xpath("//span[@itemprop='title' and text()='Dashboard']"));
		if (LoginTitle.getText().contains("Dashboard")) {
			System.out.println("Admin Login successfully after enter valid login details ");
		} else {
			System.out.println("Admin login failed");
		}

	}

	//@AfterMethod
	//public void closebrowser() {
		//driver.quit();
	//}

}
