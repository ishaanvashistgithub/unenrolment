package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UtilitiFunctions {
	WebDriver driver;
	
	
	public void test(String ePath, String ExcelData) {
		WebElement element = driver.findElement(By.xpath(ePath));
		List<WebElement> rows = element.findElements(By.tagName("li"));
		for (int rownum = 1; rownum < rows.size(); rownum++) {
			
		if(driver.findElement(By.xpath(ePath+"/li["+rownum+"]")).getText().contains(ExcelData)) {
			driver.findElement(By.xpath(ePath+"/li["+rownum+"]")).click();
			//wait
		}
			
		}
	}

}
