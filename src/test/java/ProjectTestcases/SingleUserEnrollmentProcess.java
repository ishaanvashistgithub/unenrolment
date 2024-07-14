package ProjectTestcases;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.LaunchBrowser;

public class SingleUserEnrollmentProcess extends LaunchBrowser {

	public JavascriptExecutor js;
	public Actions action;
	ExcelUtilities excel;
	// row no 1 to till avilable record
	int userData;
	// row no 1 to till avilable record
	int emailData;

	@Test(priority=1)
	public void UserCheck_and_UnenrollMent1() throws InterruptedException, IOException {

		String projectpath = System.getProperty("user.dir");
		String ExcellPath = projectpath + "/Data/CollectData/collectdataforUnenrolement-2.xlsx";
		String SheetName = "Sheet1";

		excel = new ExcelUtilities(ExcellPath, SheetName);

		action = new Actions(driver);

		Thread.sleep(10000);
		action.moveToElement(driver.findElement(By.xpath("//a[@class='moreless-toggler']"))).click().perform();
		Thread.sleep(5000);

		// searched user from 'First Name' column in
		// 'collectdataforUnenrolement' sheet
		WebElement UserNameSearch = driver.findElement(By.name("username"));

		// FirstnameSearch.sendKeys(Username);
		
		UserNameSearch.sendKeys(excel.getcelldata(1, 2).toString());
		UserNameSearch.sendKeys(Keys.ENTER);

		String getFirdstLastname = driver
				.findElement(By.xpath("//table[@class='admintable generaltable']//following::tr[last()]/td[1]/a"))
				.getText();
		String getEmail = driver
				.findElement(By.xpath("//table[@class='admintable generaltable']//following::tr[last()]/td[2]"))
				.getText();
		// ADD 'getEmail' email address value in 'Email address' column in
		// 'updateUnenrollment' sheet
		System.out.println("----------------------------------------START - Detail of the " + getFirdstLastname
				+ " User-------------------------------------------------------------");
		System.out.println("List search user:" + "FIRST NAME =" + getFirdstLastname + "EMAIL =" + getEmail);

		String LastAccess = driver
				.findElement(By.xpath("//table[@class='admintable generaltable']//following::tr[last()]/td[last()-2]"))
				.getText();

		System.out.println("Print the status of the user  " + LastAccess);

		if (LastAccess.equals("Never")) {

			String UnenrolledFromSLP = "YES";
			String RemovedFromInvoicing = "YES";
			String Comment = "USER NEVER ACCESSED COURSE";

			System.out.println("IF PART: " + " UnenrolledFronSLP Status:::" + UnenrolledFromSLP
					+ " RemovedFromInvoicing status:::" + RemovedFromInvoicing + " Write comment:::" + Comment);

			/*
			 * ADD data in 1. 'Unenrolled from course - ET' column with
			 * 'UnenrolledFromSLP' value, 2. 'Removed from invoicing - ET'
			 * column with 'RemovedFromInvoicing' value, 3 'Comments' column
			 * with 'Comment' value. in 'updateUnenrollment' sheet
			 */

		} else {
			String UnenrolledFromSLP = "YES";
			String RemovedFromInvoicing = "NO";
			String Comment = "USER ACCESSED COURSE";

			System.out.println("ELSE PART: " + " UnenrolledFronSLP Status:::" + UnenrolledFromSLP
					+ " RemovedFromInvoicing status:::" + RemovedFromInvoicing + " Write comment:::" + Comment);

			/*
			 * ADD data in 1. 'Unenrolled from course - ET' column with
			 * 'UnenrolledFromSLP' value, 2. 'Removed from invoicing - ET'
			 * column with 'RemovedFromInvoicing' value, 3 'Comments' column
			 * with 'Comment' value. in 'updateUnenrollment' sheet
			 */
		}

		driver.findElement(By.xpath("//table[@class='admintable generaltable']//following::tr[last()]/td[1]/a"))
				.click();

		// GET Course name from 'Reason for withdrawal ' column in
		// 'collectdataforUnenrolement' sheet

		String getCourseFromexcel = excel.getcelldata(1, 6).toString();
		System.out.println("GET DATA FROM SHEET AND VALUE IS : " + getCourseFromexcel);

		String ePath = "//li[@class='contentnode']";
		WebElement element = driver.findElement(By.xpath(ePath));

		List<WebElement> rows = element.findElements(By.tagName("li"));
		for (int rownum = 1; rownum <= rows.size(); rownum++) {

			if (driver.findElement(By.xpath(ePath + "/li[" + rownum + "]")).getText().contains(getCourseFromexcel)) {
				String coursefound = driver.findElement(By.xpath(ePath + "/li[" + rownum + "]")).getText();
				Thread.sleep(5000);
				System.out.println("Get COURSE NAME : " + coursefound);
				Assert.assertEquals(getCourseFromexcel, coursefound);
			}

		}
		action.moveToElement(driver.findElement(By.xpath("//form[@id='search']"))).clickAndHold().perform();
		WebElement Search = driver.findElement(By.xpath("//input[@id='coursesearchbox']"));

		// Search.sendKeys(getCourseFromexcel);
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

		String EntercourseName = excel.getcelldata(1, 6).toString();
		Search.sendKeys(EntercourseName);
		Search.sendKeys(Keys.ENTER);

		// Open Course in Admin
		Thread.sleep(5000);
		// driver.findElement(By.xpath("//a[text()='Click to enter this
		// course'])[1])")).click();
		action.moveToElement(driver.findElement(By.xpath("(//a[contains(text(),'Click to enter this course')])[1]")))
				.click().perform();

		// SELECT User Option
		action.moveToElement(driver.findElement(By.xpath("//*[text()='Users']"))).click().perform();

		action.moveToElement(driver.findElement(By.xpath("//*[text()='Enrolled users']"))).click().perform();

		Thread.sleep(15000);

		WebElement searchUserinCourse = driver
				.findElement(By.xpath("//input[@placeholder='Search keyword or select filter' and @role='combobox']"));
		searchUserinCourse.sendKeys(getEmail);
		// searcUserinCourse.sendKeys(getEmail);
		searchUserinCourse.sendKeys(Keys.ENTER);
		Thread.sleep(5000);

		String searchedEmailIDinList = driver
				.findElement(By.xpath("(//table[@id='participants']//following::tr)[1]/td[3]")).getText();
		Assert.assertEquals(getEmail, searchedEmailIDinList);

		// ::::::::::::::: UnEnroll User::::::::::::::::::::::::::::::::::::://
		Thread.sleep(5000);
		action.moveToElement(driver.findElement(By.xpath("(//img[@title='Unenrol'])[1]"))).click().perform();

		Thread.sleep(5000);
		String getUnenrolluser = driver.findElement(By.xpath("//div[@class='modal-content']/div[2]")).getText();

		Assert.assertTrue(getUnenrolluser.contains(getFirdstLastname));
		Assert.assertTrue(getUnenrolluser.contains(getCourseFromexcel));
		// Assert.assertTrue(getUnenrolluser.contains("AML Foundations
		// Europe"));

		action.moveToElement(driver.findElement(By.xpath("//button[contains( text(), 'Unenrol')]"))).click().perform();
		Thread.sleep(10000);
		String usernotfound = driver.findElement(By.xpath("//h2[text()='Nothing to display']")).getText();
		Assert.assertEquals("Nothing to display", usernotfound);

		driver.navigate().refresh();

		Thread.sleep(20000);
		WebElement searchUserinCourse2 = driver
				.findElement(By.xpath("//input[@placeholder='Search keyword or select filter' and @role='combobox']"));
		searchUserinCourse2.sendKeys(getEmail);
		// searcUserinCourse.sendKeys(getEmail);
		searchUserinCourse2.sendKeys(Keys.ENTER);
		String usernotfound2 = driver.findElement(By.xpath("//h2[text()='Nothing to display']")).getText();
		Assert.assertEquals("Nothing to display", usernotfound2);
		System.out.println("----------------------------------------End - Detail of the " + getFirdstLastname
				+ " User-------------------------------------------------------------");

	}

}
