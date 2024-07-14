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
import utilities.LaunchBrowserAdminLoginCourses;

public class AcamsCourses extends LaunchBrowserAdminLoginCourses {

	public XLUtilites xlutil;
	String projectpath = System.getProperty("user.dir");
	
	@Test(priority = 0, description = "Clickonfaqdemo")
	public void Clickontheusers() {	
	driver.findElement(By.xpath("//a[@title='FAQ']")).click();
	}
	
	
	@Test(priority = 1, description = "Clickonfaqdemo")
	public void Clickontheuserss() {	
	driver.findElement(By.xpath("//a[normalize-space()='HANDBOOKS']")).click();
	
	

	
	}




}
