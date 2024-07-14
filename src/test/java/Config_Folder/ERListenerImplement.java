package Config_Folder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ERListenerImplement implements ITestListener {
	public ExtentSetup eSetup;
	public ExtentReports eReport;
	public ExtentTest eTest;

	@Override
	public void onTestStart(ITestResult result) {
		eTest = eReport.createTest(result.getMethod().getDescription());
		eTest.info("Admin Login successfully");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		eTest.log(Status.PASS, result.getMethod().getDescription() + " is successfully Done");
		eTest.info("User Unenrollment successfully");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		eTest.log(Status.FAIL, result.getMethod().getDescription() + " is Failed");
		eTest.log(Status.FAIL, result.getThrowable());

		/*
		 * WebDriver driver = WebDriverManager.getDriverInstance();
		 * 
		 * File src = ((TakesScreenshot)
		 * driver).getScreenshotAs(OutputType.FILE);
		 * 
		 * SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		 * Date date = new Date(); String actualDate = format.format(date);
		 * String screenshotPath = System.getProperty("user.dir") +
		 * "\\Screenshots\\" + actualDate + ".jpeg";
		 * 
		 * File destination = new File(screenshotPath); try {
		 * FileUtils.copyFile(src, destination); } catch (IOException e) {
		 * e.getStackTrace(); } try {
		 * eTest.addScreenCaptureFromPath(screenshotPath,
		 * " TestCase Failure Screenshot"); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * String imgpath = System.getProperty("user.dir") + "/Screenshots/" +
		 * result.getName() + ".png"; File fl = new File(imgpath); if
		 * (fl.exists()) { try { eTest.fail("Screenshot is below " +
		 * eTest.addScreenCaptureFromPath(imgpath)); } catch (IOException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } }
		 */
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		eTest.log(Status.SKIP, result.getMethod().getDescription() + " User Unenrollment Skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		eReport = ExtentSetup.setupExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		eReport.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}
}
