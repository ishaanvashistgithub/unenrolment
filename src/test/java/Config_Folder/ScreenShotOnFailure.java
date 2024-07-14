/*package Config_Folder;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import Event_commands.events;

public class ScreenShotOnFailure extends TestListenerAdapter {
	Common c = new Common();
	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = WebDriverManager.getDriverInstance();
		if (driver != null) {
			System.out.println("Snapshot for: "	+ result.getMethod().getMethodName());
			File file = new File("testng-xslt");
			//File file=new File("Screenshot");
			if (!result.isSuccess()) { 
				File scrFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				// Needs Commons IO library
				try {
					FileUtils.copyFile(scrFile, new File(file.getAbsolutePath()	+ "/Screenshot/shot_"+ result.getMethod().getMethodName() + "()"+ ".jpg"));
					//Notification(driver);
					//Render_Notifications(driver);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Reporter.setCurrentTestResult(result);
			}
		}
	}

	public void Notification(WebDriver driver) {
		events eve = new events(driver);
		if (driver.findElement(By.xpath(c.getElementName("Save_changes_notification_Message"))).isDisplayed()) {
			Reporter.log("Notifications ---------------");
			eve.GetText_event("Save_changes_notification_Message");
			try {
				eve.Click_event("Save_changes_notification_OK_button");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Reporter.log("--------------------------------");
		} else {
			System.out.println("No notification Found");
		}
	}
	public void Render_Notifications(WebDriver driver) {
		events eve = new events(driver);
		if(driver.findElement(By.xpath(c.getElementName("TC_Notification_Message"))).isDisplayed()){
			Reporter.log("Notificatio: ----------------------------------------");
			eve.GetText_event("TC_Notification_Message");
			try {
				eve.Click_event("TC_Notification_No_button");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Reporter.log("-----------------------------------------------------");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
*/