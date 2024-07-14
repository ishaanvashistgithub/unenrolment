package ProjectTestcases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.Multimap;

import utilities.LaunchBrowser;

public class UserEnrollment extends LaunchBrowser {

	public XLUtilites xlutil;
	String projectpath = System.getProperty("user.dir");
	String path = projectpath + "/Data/CollectData/collectdataforUnenrolement.xlsx";
	String coursecount;
	Map<String, Integer> rowmap = new HashMap<String, Integer>();

	@Test(dataProvider = "UserEnrollment", description = " User Unenrollment Process")
	public void UserCheck_and_UnenrollMent(String recordID, String corprateKey, String courseName, String Email,
			String UnenrolledFromSLP, String RemovedFromInvoicing, String Comment, String EnrollmentStart_Date,
			String Unenrollment_Date, String CourseNameRemovedFrom, String ErrorMessage)
					throws InterruptedException, IOException {

		// String recordID,
		action = new Actions(driver);
		System.out.println(
				"******************************START UN-ENROLLMENT PROCESS****************************************");
		System.out.println("************  Un-enrollment start with record number : " + recordID);
		Thread.sleep(10000);
		// Show More Link on Users page
		action.moveToElement(driver.findElement(By.xpath("//a[@class='moreless-toggler']"))).click().perform();
		Thread.sleep(5000);

		// Search Corporate Key in USer name
		WebElement UserNameSearch = driver.findElement(By.name("username"));

		// Trims Corporate Keys after remove spaces
		String cp = corprateKey.trim();

		// Enter User name in Username Field on Users page
		UserNameSearch.sendKeys(cp);
		UserNameSearch.sendKeys(Keys.ENTER);
		System.out.println("************  Corporate Key : " + cp);

		// Apply If else condition user exist or not

		try {
			if (driver.findElement(By.xpath("//a[normalize-space()='First name']")).isDisplayed()) {

				Assert.assertTrue(true);
				System.out.println("************  User exist Continue Next process >>>>>");

				String getFirdstLastname = driver
						.findElement(By.xpath("(//table[@class='admintable generaltable']//following::tr)[2]/td[1]/a"))
						.getText();

				System.out.println("************  Get Name of User in searched Record :  " + getFirdstLastname);

				String getEmail = driver
						.findElement(By.xpath("(//table[@class='admintable generaltable']//following::tr)[2]/td[2]"))
						.getText();
				Email = getEmail;

				for (Map.Entry<String, Integer> m : rowmap.entrySet()) {
					// System.out.println(m.getKey() + " " +
					// m.getValue());

					// if (m.getKey().equals(corprateKey)) {

					if (m.getKey().equals(recordID)) {
						XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 3, Email);
					}
				}

				System.out.println("************  Get Email of User in searched Record :" + Email);

				// open user by click First name and last name link
				driver.findElement(By.xpath("(//table[@class='admintable generaltable']//following::tr)[2]/td[1]/a"))
				.click();
				System.out.println(
						"************  Get User Profile URL after click User name : " + driver.getCurrentUrl());

				String getCourseFromexcel = courseName.trim();

				System.out.println("************  Course Avilable for Unenrollment " + getCourseFromexcel);

				// Apply if else condition to check course is visible in users
				// profile or not
				Thread.sleep(10000);
				try {
					if (driver.findElement(By.xpath("//*[contains(text(),'" + getCourseFromexcel + "')]"))
							.isDisplayed()) {
						System.out.println("************  Course Exist in User profile  ************");
						Assert.assertTrue(true);

						// if course match with list then continue else course
						// getting CourseNameRemovedFrom

						List<WebElement> coursecount = driver
								.findElements(By.xpath("//*[contains(text(),'" + getCourseFromexcel + "')]"));
						System.out.println("************  Course count : " + coursecount.size());
						// changes in 115 line and 118 line change xpath and 
						if (coursecount.size() == 2) {

							CourseNameRemovedFrom = driver
									.findElement(By.xpath("(//*[contains(text(),'" + getCourseFromexcel + "')])[1]"))
									.getText();
							System.out.println("************  Course name Remove From : " + CourseNameRemovedFrom);
							for (Map.Entry<String, Integer> m : rowmap.entrySet()) {
								// System.out.println(m.getKey() + " " +
								// m.getValue());

								// if (m.getKey().equals(corprateKey)) {
								if (m.getKey().equals(recordID)) {
									XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 9, CourseNameRemovedFrom);
								}
							}

							// click on the Existing course
							action.moveToElement(
									driver.findElement(By.xpath("//*[contains(text(),'" + getCourseFromexcel + "')]")))
							.click().perform();

							System.out.println(
									"************  Get Course URL after click course name : " + driver.getCurrentUrl());
							Thread.sleep(15000);

							// Click on Course Administration option under
							// user

							action.moveToElement(
									driver.findElement(By.xpath("//span[contains(text(), 'Course administration')]")))
							.click().perform();
							// Click on Users option under Course
							// Administration
							// page.
							action.moveToElement(driver.findElement(By.xpath("//*[text()='Users']"))).click().perform();

							// Click on Enrolled Users option under Course
							// Administration page.
							action.moveToElement(driver.findElement(By.xpath("//*[text()='Enrolled users']"))).click()
							.perform();

							Thread.sleep(15000);
							// Search user in course page by email which is
							// getting
							// from the user record
							WebElement searchUserinCourse = driver.findElement(By.xpath(
									"//input[@placeholder='Search keyword or select filter' and @role='combobox']"));
							// searchUserinCourse.sendKeys(getEmail);
							searchUserinCourse.sendKeys(Email);
							searchUserinCourse.sendKeys(Keys.ENTER);
							Thread.sleep(5000);

							// Get Last Access is Never and not.
							String LastAccess = driver
									.findElement(
											By.xpath("(//table[@id='participants']//following::tr)[1]/td[last()-1]"))
									.getText();
							System.out.println("************  Print the status of the user  " + LastAccess);

							// print messages with applied condition as per
							// below
							// statement

							if (LastAccess.equals("Never")) {

								UnenrolledFromSLP = "Yes";
								RemovedFromInvoicing = "Yes";
								Comment = "Never accessed course";

								System.out.println("************  Last Status :" + LastAccess);

								System.out.println(
										"************  UNEnrollment from SLP plate form Status : " + UnenrolledFromSLP);

								System.out.println(
										"************  Remove from Invoicing status  : " + RemovedFromInvoicing);

								System.out.println("************  Comment : " + Comment);

								for (Map.Entry<String, Integer> m : rowmap.entrySet()) {
									// System.out.println(m.getKey() + " " +
									// m.getValue());
									// if (m.getKey().equals(corprateKey)) {
									if (m.getKey().equals(recordID)) {
										// XLUtilites.SetCellData(path,
										// "Sheet1",
										// m.getValue(), 2, Email);
										XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 4, UnenrolledFromSLP);
										XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 5, RemovedFromInvoicing);
										XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 6, Comment);
									}
								}

							} else {
								UnenrolledFromSLP = "Yes";
								RemovedFromInvoicing = "No";
								Comment = "User accessed course";

								System.out.println("ELSE Last Access = CorpKey :" + corprateKey + "; "
										+ " UnenrolledFronSLP Status :" + UnenrolledFromSLP + "; "
										+ " RemovedFromInvoicing status :" + RemovedFromInvoicing + "; "
										+ " Write comment:::" + Comment + ";");

								System.out.println("************  Last Status :" + LastAccess);

								System.out.println(
										"************  UNEnrollment from SLP plate form Status : " + UnenrolledFromSLP);

								System.out.println(
										"************  Remove from Invoicing status  : " + RemovedFromInvoicing);

								System.out.println("************  Comment : " + Comment);

								for (Map.Entry<String, Integer> m : rowmap.entrySet()) {
									// System.out.println(m.getKey() + " " +
									// m.getValue());

									// if (m.getKey().equals(corprateKey)) {
									if (m.getKey().equals(recordID)) {
										// XLUtilites.SetCellData(path,
										// "Sheet1",
										// m.getValue(), 2, Email);
										XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 4, UnenrolledFromSLP);
										XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 5, RemovedFromInvoicing);
										XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 6, Comment);
									}
								}
							}

							// ::::::::::::::: UnEnroll
							// User::::::::::::::::::::::::::::::::::::://

							// Click on the Edit Enrollment icon
							Thread.sleep(5000);
							driver.findElement(By.xpath("(//img[@title='Edit enrolment'])[1]")).click();
							Thread.sleep(5000);
							String date = driver.findElement(By.xpath("//select[@id='id_timestart_day']"))
									.getAttribute("value");
							String month = driver.findElement(By.xpath("//select[@id='id_timestart_month']"))
									.getAttribute("value");
							String year = driver.findElement(By.xpath("//select[@id='id_timestart_year']"))
									.getAttribute("value");

							String EnrollDate = date + "-" + month + "-" + year;
							EnrollmentStart_Date = EnrollDate;

							/*
							 * WebElement searchUserinCourse3 =
							 * driver.findElement(By
							 * .xpath("//input[@placeholder='Search keyword or select filter' and @role='combobox']"
							 * )); //searchUserinCourse.sendKeys(getEmail);
							 * searchUserinCourse3.sendKeys(Email);
							 * searchUserinCourse3.sendKeys(Keys.ENTER);
							 */
							Thread.sleep(5000);

							System.out.println("************  Print Enrollment Start Date :" + EnrollmentStart_Date);

							for (Map.Entry<String, Integer> m : rowmap.entrySet()) {
								// System.out.println(m.getKey() + " " + //
								// m.getValue());

								// if (m.getKey().equals(corprateKey)) {
								if (m.getKey().equals(recordID)) {
									XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 7, EnrollmentStart_Date);
								}
							}

							// close Enrollment Edit popup
							driver.findElement(
									By.xpath("//button[@class='btn btn-secondary' and  @data-action='cancel']"))
							.click();

							Thread.sleep(5000);
							action.moveToElement(driver.findElement(By.xpath("(//img[@title='Unenrol'])[1]"))).click()
							.perform();

							Thread.sleep(5000);
							String getUnenrolluser = driver
									.findElement(By.xpath("//div[@class='modal-content']/div[2]")).getText();

							Assert.assertTrue(getUnenrolluser.contains(getFirdstLastname));
							Assert.assertTrue(getUnenrolluser.contains(getCourseFromexcel));

							action.moveToElement(driver.findElement(By.xpath("//button[contains( text(), 'Unenrol')]")))
							.click().perform();
							Thread.sleep(10000);
							String usernotfound = driver.findElement(By.xpath("//h2[text()='Nothing to display']"))
									.getText();
							Assert.assertEquals("Nothing to display", usernotfound);

							driver.navigate().refresh();

							Thread.sleep(20000);
							WebElement searchUserinCourse2 = driver.findElement(By.xpath(
									"//input[@placeholder='Search keyword or select filter' and @role='combobox']"));
							// searchUserinCourse2.sendKeys(getEmail);
							searchUserinCourse2.sendKeys(Email);
							searchUserinCourse2.sendKeys(Keys.ENTER);

							String usernotfound2 = driver.findElement(By.xpath("//h2[text()='Nothing to display']"))
									.getText();
							Assert.assertEquals("Nothing to display", usernotfound2);

							// SimpleDateFormat newDate = new
							// SimpleDateFormat("dd/MMM/yyyy hh:mm:ss a");
							SimpleDateFormat newDate = new SimpleDateFormat("dd/MMM/yyyy");
							Date date1 = new Date();
							Unenrollment_Date = newDate.format(date1);

							System.out.println("************ " + corprateKey + "  User Unenrollment from " + courseName
									+ " course at " + Unenrollment_Date + " time.");

							// UnenrollmentDate

							for (Map.Entry<String, Integer> m : rowmap.entrySet()) {
								// System.out.println(m.getKey() + " " +
								// m.getValue());

								// if (m.getKey().equals(corprateKey)) {
								if (m.getKey().equals(recordID)) {
									XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 8, Unenrollment_Date);
								}
							}

							System.out.println(
									"******************************END UN-ENROLLMENT PROCESS with Successfully completed ****************************************");
						} else {

							System.out.println(
									"************  Course count is :" + coursecount.size() + " and more than 1 ");

							ErrorMessage = "Multiple same base-course avilable";
							System.out.println(
									"******************************END UN-ENROLLMENT PROCESS Due to multiple course with same name ****************************************");

							for (Map.Entry<String, Integer> m : rowmap.entrySet()) {
								// System.out.println(m.getKey() + " " +
								// m.getValue());

								// if (m.getKey().equals(corprateKey)) {
								if (m.getKey().equals(recordID)) {
									XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 10, ErrorMessage);
								}
							}
						}
					}
				} catch (Exception e) {
					ErrorMessage = "Course not Found";

					System.out
					.println("************  Course is already UNenrolled or course is not exist in the course");
					System.out.println(
							"******************************END UN-ENROLLMENT PROCESS Due to course is Course not Found ****************************************");

					for (Map.Entry<String, Integer> m : rowmap.entrySet()) {
						// System.out.println(m.getKey() + " " + m.getValue());

						// if (m.getKey().equals(corprateKey)) {
						if (m.getKey().equals(recordID)) {
							XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 10, ErrorMessage);
						}
					}
				}
			}
		} catch (Exception e) {
			ErrorMessage = "User is not found/exists";
			System.out.println("************  " + cp
					+ " Corporate Key is not found in slp or may be this user is not exist in the list :");

			System.out.println(
					"******************************END UN-ENROLLMENT PROCESS Due to User not Exist ****************************************");

			for (Map.Entry<String, Integer> m : rowmap.entrySet()) {
				// System.out.println(m.getKey() + " " + m.getValue());

				// if (m.getKey().equals(corprateKey)) {
				if (m.getKey().equals(recordID)) {
					XLUtilites.SetCellData(path, "Sheet1", m.getValue(), 10, ErrorMessage);
				}
			}
		}
	}

	@DataProvider(name = "UserEnrollment")
	public String[][] getData() throws Exception {

		int rownum = XLUtilites.getRowCount(path, "Sheet1");
		int colmnum = XLUtilites.getCellCount(path, "Sheet1", 0);

		String userData[][] = new String[rownum][colmnum];

		for (int i = 1; i <= rownum; i++) {

			for (int j = 0; j < colmnum; j++) {

				userData[i - 1][j] = XLUtilites.getCellData(path, "Sheet1", i, j);
				rowmap.put(userData[i - 1][j].toString(), i);
			}
		}
		return userData;
	}
}
