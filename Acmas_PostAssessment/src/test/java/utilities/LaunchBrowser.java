package utilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class LaunchBrowser {
	public WebDriver driver;
	public Actions action;
	public ExtentTest eTest;
	public ExtentReports eReport;
	public String downloadpath;

	@Parameters({ "url" })
	@BeforeMethod
	public void openBrowser(String url) throws InterruptedException {

		// Chrome path mentioned in project

		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "\\Drivers\\chromedriver.exe");
		downloadpath = path + "\\Data\\DownloadData\\";
		Map<String, Object> chromeprefs = new HashMap<String, Object>();
		// chromeprefs.put("profile.default_content_settings.popups", 0);
		chromeprefs.put("download.default_directory", downloadpath);

		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("prefs", chromeprefs);

		driver = new ChromeDriver(option);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		// Open URL and enter username and pasword Fields
		driver.navigate().to(url);
		Thread.sleep(5000);

	}

	@AfterMethod
	public void closebrowser() {
		driver.quit();

	}

}
