package Event_commands;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Common {
	String webElementXPath, Elementvalue;
	//Element get From Properties File 
	public String getElementName(String webElementKey){
		Properties props=null;
		FileInputStream fin =null;
		try{
			File f = new File("PropertiesFile");		
			fin = new FileInputStream(f.getAbsolutePath()+"/ImportManager.properties");
			props = new Properties();
			props.load(fin);
			webElementXPath = props.getProperty(webElementKey);
		}catch(Exception e){
			System.out.println(e.getMessage());
		} 
		return webElementXPath;
	}
	
	public String Variablevalue(String fieldvalue)
	{
		Properties props=null;
		FileInputStream fin =null;
		try{
			File f = new File("PropertiesFile");		
			fin = new FileInputStream(f.getAbsolutePath()+"/ImportManager.properties");
			props = new Properties();
			props.load(fin);
			Elementvalue = props.getProperty(fieldvalue);
		}catch(Exception e){
			System.out.println(e.getMessage());
		} 
		return Elementvalue;
		
	}
	// Apply Implicity Wait for Element
	public static void waitElement(WebDriver driver, int timeSeconds)
	{
		driver.manage().timeouts().implicitlyWait(timeSeconds, TimeUnit.SECONDS);
	}

	//Get random number
	public static int randomNumber(int number)
	{
		Random random = new Random(); 
		int randomNo = random.nextInt(number);
		return randomNo; 
	}

	//Set Fix Wait for some scenarios
	public static void fixWaitforVisible(int time) throws InterruptedException
	{
		Thread.sleep(time);
	}

	//Get Current Date and Time
	public static String GetCurrentDateTime(String DT_Format)
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(DT_Format);
		String FormateDate = formatter.format(date);
		return  FormateDate;
	}

	//Get page Title
	public static Boolean GetPageTitle(WebDriver driver, String URL)
	{
		boolean GetTitle=driver.getCurrentUrl().contains(URL);
		return GetTitle;
	}
	// Get random text
	public static String getrandomText(int limit)
	{
		String generatedString = RandomStringUtils.randomAlphanumeric(limit);
		return generatedString;
	}
	// Page Refresh method
	public static void refreshPage(WebDriver driver)
	{
		driver.navigate().refresh();
	}
	//Page go to back
	public static void backpage(WebDriver driver)
	{
		driver.navigate().back();
	}
	// page go to forward
	public static void forwordPage(WebDriver driver)
	{
		driver.navigate().forward();
	}
   





















}

