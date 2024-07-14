package Config_Folder;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import Event_commands.Common;

public class WebDriverManager {
	private static WebDriver d;
	static Common c = new Common();

	public static WebDriver getDriverInstance() {
		return d;
	}

	public static WebDriver startDriver(String browser, String URL) throws InterruptedException, IOException {
		File file=new File("Drivers");
		String browerPath;
		 switch (browser)
		{
		case "FF":
			try {
				browerPath=file.getAbsolutePath()+"//geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", browerPath);	
				d=new FirefoxDriver();

			} 
			catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
			break;
		case "CH":
			try {
				browerPath=file.getAbsolutePath()+"//chromedriver.exe";
				ChromeOptions options = new ChromeOptions();
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				options.addArguments("test-type");
				options.addArguments("disable-infobars");
				options.addArguments("--start-maximized");
				
				options.addArguments("ignore-certificate-errors");
				System.setProperty("webdriver.chrome.driver",browerPath);
				capabilities.setCapability("acceptSslCerts", "true");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			}
					catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		 d.get(URL);Thread.sleep(2000);
		 return d;

	}

	public static void stopDriver() {
		// d.manage().deleteAllCookies();
		d.quit();
	}

	



	public static void highlightElement(WebElement element) {
		for (int i = 0; i < 10; i++) {
			JavascriptExecutor js = (JavascriptExecutor) d;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: red; border: 2px solid red;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
	}


}
