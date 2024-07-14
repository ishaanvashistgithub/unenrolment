package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class UtilitiFunctionsd {
	WebDriver driver;
	public Actions action;

	public void dynamicXPath(String ePath, String ExcelData) {

		WebElement element = driver.findElement(By.xpath(ePath));
		List<WebElement> rows = element.findElements(By.tagName("span"));
		for (int rownum = 1; rownum < rows.size(); rownum++) {

			if (driver.findElement(By.xpath(ePath + "/span[" + rownum + "]")).getText().contains(ExcelData)) {
				try {
					Thread.sleep(2000);
					driver.findElement(By.xpath(ePath + "/span[" + rownum + "]")).click();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// wait
			}

		}
	}

	public static void postAssessmentAnswer(WebDriver driver,String ePath, String ExcelData) {
		WebElement element = driver.findElement(By.xpath(ePath));
		List<WebElement> rows = element.findElements(By.tagName("div"));
		for (int row = 1; row < rows.size(); row++) {

			if (driver.findElement(By.xpath(ePath + "/div[" + row + "]/label")).getText().contains(ExcelData))

			{
				try {
					Thread.sleep(2000);
					 driver.findElement(By.xpath(ePath + "/div[" + row + "]/label/../input")).click();
					 System.out.println("Option select");

				} catch (InterruptedException e) {
					System.out.println("No Option available");
					e.printStackTrace();
				}

				// wait
			}

		}
	}

}
