package PageElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class pageFunctions {
	


	public static void LoginUser(WebDriver driver, String user1, String pass1) throws InterruptedException {
		WebElement loginuserid = driver.findElement(By.id("username"));
		loginuserid.sendKeys(user1);
		System.out.println(" User ID : " + user1);

		WebElement enterPassword = driver.findElement(By.id("password"));
		enterPassword.sendKeys(pass1);
		System.out.println(" User Password : " + pass1);
		WebElement clickLoginBtn = driver.findElement(By.id("loginbtn"));
		clickLoginBtn.click();

		Thread.sleep(5000);
	}

	public static WebElement search(WebDriver driver) {
		return driver.findElement(By.xpath("//form[@id='search']"));
	}

	public static void searchCourse(WebDriver driver, String course) {
		WebElement searchCourseFiled = driver.findElement(By.xpath("//input[@id='coursesearchbox']"));
		searchCourseFiled.sendKeys(course);
		searchCourseFiled.sendKeys(Keys.ENTER);
	}

	public static void clickCoursefromSearchList(WebDriver driver) throws InterruptedException {
		WebElement clickCourse = driver.findElement(By.xpath("(//div[@class='course-btn'])[1]/p/a[1]"));
		clickCourse.click();
		Thread.sleep(3000);
	}

	public static void ClickFoundationPostAssessmentfor_NON_TTA(WebDriver driver) {
		driver.findElement(By.xpath("//li[@class='activity quiz modtype_quiz ']/div/div/div[2]")).click();
	}

	public static void StartorInprogressPostAssessment(WebDriver driver) {
		driver.findElement(By.xpath("(//button[@type='submit'])[last()]")).click();
	}

	public static int getPostAssessmentQNo(WebDriver driver) {
		String questionSeries = driver.findElement(By.xpath("//span[@class='qno']")).getText();
		System.out.println("Print in string : " + questionSeries);
		int question = Integer.parseInt(questionSeries);
		return question;
	}

	public static String Questiontext(WebDriver driver) {
		String PAQues = driver.findElement(By.xpath("//div[@class='qtext']")).getText();
		return PAQues;

	}

	public static void questionNextbutton(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='next']")).click();
		Thread.sleep(3000);
	}

	public static void postAssessmentAnswer(WebDriver driver, String ePath, String ExcelData) {
		WebElement element = driver.findElement(By.xpath(ePath));
		List<WebElement> rows = element.findElements(By.tagName("div"));
		// System.out.println("Count : "+rows.size());
		for (int row = 1; row <= rows.size(); row++) {

			if (driver.findElement(By.xpath(ePath + "/div[" + row + "]/label")).getText().contains(ExcelData))

			{

				try {
					Thread.sleep(2000);
					driver.findElement(By.xpath(ePath + "/div[" + row + "]/label/../input")).click();
					Thread.sleep(2000);
					if ((driver.findElement(By.xpath(ePath + "/div[" + row + "]/label/../input")).isEnabled()))

					{
						System.out.println("Option select");
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Option not select");
				}

			}

		}
	}

	public static void ClickFoundationPostAssessment_1_for_nonFoundational(WebDriver driver)
			throws InterruptedException {
		driver.findElement(By.xpath("(//li[@class='activity quiz modtype_quiz ']/div/div)[1]/div[2]/div/a/span")).click();
		Thread.sleep(3000);
	}

	public static void ClickIntermediatePostAssessment_2_for_nonFoundational(WebDriver driver)
			throws InterruptedException {
		driver.findElement(By.xpath("(//li[@class='activity quiz modtype_quiz ']/div/div)[2]/div[2]/div[1]/div/span")).click();
		Thread.sleep(3000);
	}

	public static void ClickonScromFilefirstFOR_KYCFI(WebDriver driver) throws InterruptedException {
		WebElement scromfileClick=driver.findElement(By.xpath("(//ul[@class='topics']/li[3]/div[last()]/ul/li[1]//following::span)[1]"));
		scromfileClick.click();
		Thread.sleep(3000);
	}

	public static void ClickonScromFile2ndFOR_KYCFI(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("(//ul[@class='topics']/li[3]/div[last()]/ul/li[2]//following::span)[1]")).click();
		Thread.sleep(3000);
	}
	
	
	public static int checkrecord(WebDriver driver)
	{
		List<WebElement> list =driver.findElements(By.xpath("//a[@class='qnbutton answersaved free btn btn-secondary']"));
		int totalselect=list.size();
		return totalselect;
	 
		//a[@id='quiznavbutton1']//following::span[@class='trafficlight']
	}

	public static void finishpostAssessment(WebDriver driver)
	{
		driver.findElement(By.xpath("//a[@class='endtestlink']")).click();
	}
	
	public static WebElement submitPostAssessmentExam(WebDriver driver)
	{
		WebElement submintPA=driver.findElement(By.xpath("(//div[@class='controls'])[last()]/div/form/button"));
		return submintPA;
	}
	
	public static WebElement ConfirmPostAssessmentConfirm(WebDriver driver)
	{
		WebElement confirma=driver.findElement(By.xpath("(//div[@class='confirmation-message']//following::input)[1]"));
		return confirma;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
