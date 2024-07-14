package ProjectTestcases;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import AcamsPages.PageElements;
import Event_commands.Common;
import PageElements.pageFunctions;
import utilities.LaunchBrowser;
import utilities.UtilitiFunctionsd;

public class AML_UserPostAssessment extends LaunchBrowser {

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
	String SheetQA = "AML_PostAssessment";
	String SheetUsers = "AML_User";

	Map<String, Integer> rowmap = new HashMap<String, Integer>();

	@Test(dataProvider = "PostAssessment", description = "Complete the Post the Assessment process")
	public void CourseAML_PostAssessment(String Username, String Password, String CourseforPostAssessment)
			throws Exception {
        pageFunctions.LoginUser(driver, Username, Password);
		
		action = new Actions(driver);
		action.moveToElement(pageFunctions.search(driver)).clickAndHold().perform();
        pageFunctions.searchCourse(driver, CourseforPostAssessment);
		
		// Open Course on SLP
        pageFunctions.clickCoursefromSearchList(driver);
		
		// if course belong to single course (Foundational course )
        pageFunctions.ClickFoundationPostAssessmentfor_NON_TTA(driver);
        pageFunctions.StartorInprogressPostAssessment(driver);
       int qNO= pageFunctions.getPostAssessmentQNo(driver);
		
		// if question
		for (qNO = qNO; qNO <= 25; qNO++) {

			String paQUestion = pageFunctions.Questiontext(driver);

			System.out.println("Question " + qNO + " :" + paQUestion);

			String answer = XLUtilites.getAnswer(QuestionAnswerWB, SheetQA, paQUestion);

			try {
                String epath="//div[@class='ablock']/div[2]";
				pageFunctions.postAssessmentAnswer(driver, epath, answer);
				System.out.println("Answer : " + answer);
				Thread.sleep(2000);
				pageFunctions.questionNextbutton(driver);


			} catch (Exception e) {
				System.out.println("Answer not select " + qNO + ": " + answer);
				System.out.println(e.getMessage());
				pageFunctions.questionNextbutton(driver);
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