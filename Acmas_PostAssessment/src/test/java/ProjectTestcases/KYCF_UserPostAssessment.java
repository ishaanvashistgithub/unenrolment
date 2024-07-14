package ProjectTestcases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.Multimap;

import jxl.write.biff.*;
import utilities.LaunchBrowser;
import utilities.UtilitiFunctionsd;

public class KYCF_UserPostAssessment extends LaunchBrowser {

	public XLUtilites xlutil;
	public UtilitiFunctionsd utility;
	SimpleDateFormat newDate;
	Date date;
	FileInputStream fis, fis2;
	XSSFWorkbook wb, wb2;
	XSSFSheet sheet;

	public File file, file2, output;
	public String projectpath = System.getProperty("user.dir");
	public String userlistWB = projectpath + "/Data/Userlist.xlsx";
	public String QuestionAnswerWB = projectpath + "/Data/All_Questions.xlsx";
	String SheetQA="KYCF_PostAssessment";
	String SheetUsers="KYCF_User";

	Map<String, Integer> rowmap = new HashMap<String, Integer>();

	@Test(dataProvider = "PostAssessment", description = "Complete the Post the Assessment process")
	public void CourseKYCF_PostAssessment(String Username, String Password, String CourseforPostAssessment) throws Exception{
		WebElement loginuserid = driver.findElement(By.id("username"));
		loginuserid.sendKeys(Username);
		System.out.println(" User ID : " + Username);

		WebElement enterPassword = driver.findElement(By.id("password"));
		enterPassword.sendKeys(Password);
		System.out.println(" User Password : " + Password);
		WebElement clickLoginBtn = driver.findElement(By.id("loginbtn"));
		clickLoginBtn.click();

		Thread.sleep(5000);

		action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//form[@id='search']"))).clickAndHold().perform();

		WebElement searchCourseFiled = driver.findElement(By.xpath("//input[@id='coursesearchbox']"));
		searchCourseFiled.sendKeys(CourseforPostAssessment);
		searchCourseFiled.sendKeys(Keys.ENTER);
		// Open Course on SLP
		WebElement clickCourse = driver.findElement(By.xpath("(//div[@class='course-btn'])[1]/p/a[1]"));
		clickCourse.click();

		// if course belong to single course (Foundational course )
		driver.findElement(By.xpath("//li[@class='activity quiz modtype_quiz ']/div/div/div[2]")).click();
		driver.findElement(By.xpath("(//button[@type='submit'])[last()]")).click();

		String questionSeries = driver.findElement(By.xpath("//span[@class='qno']")).getText();
		System.out.println("Print in string : " + questionSeries);
		int qNO = Integer.parseInt(questionSeries);
		System.out.println("Print in Ineger :" + qNO);
		// if question
		for (qNO = qNO; qNO <= 20; qNO++) {

			String paQUestion = driver.findElement(By.xpath("//div[@class='qtext']")).getText();

			System.out.println("Question " + qNO + " :" + paQUestion);

			String answer = XLUtilites.getAnswer(QuestionAnswerWB, SheetQA, paQUestion);

			try {

				UtilitiFunctions.postAssessmentAnswer(driver, "//div[@class='ablock']/div[2]", answer);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//input[@name='next']")).click();
				System.out.println(" Select write Answer : " + answer);
				Thread.sleep(3000);

			} catch (Exception e)

			{
				System.out.println("Not Select Answer " + qNO + ": " + answer);
				System.out.println(e.getMessage());
				driver.findElement(By.xpath("//input[@name='next']")).click();
				Thread.sleep(4000);
			}

		}

	}

	@DataProvider(name = "PostAssessment")
	public String[][] getData() throws Exception {

		int rownum = XLUtilites.getRowCount(userlistWB, SheetUsers);
		int colmnum = XLUtilites.getCellCount(userlistWB, SheetUsers, 1);

		String userData[][] = new String[rownum][colmnum];

		for (int i = 1; i <= rownum; i++) {

			for (int j = 0; j < colmnum; j++) {

				userData[i - 1][j] = XLUtilites.getCellData(userlistWB, SheetUsers, i, j);
				rowmap.put(userData[i - 1][j].toString(), i);
			}
		}
		return userData;
	}

}