package ProjectTestcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UtilitiFunctions {
	public static WebDriver driver;

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

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}
	}

}

