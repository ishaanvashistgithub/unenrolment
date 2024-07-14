package Event_commands;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import Event_commands.Common;

public class events {

	final WebDriver driver;
	Common c = new Common();
	public events(WebDriver driver) {
		this.driver = driver;
	}

	// Single Element Find
	public WebElement FindElement(String path)  {
		try
		{
			 WebDriverWait wait=new WebDriverWait(driver, 50);
			 WebElement element =wait.until(ExpectedConditions.elementToBeClickable(driver.findElement((By.xpath(c.getElementName(path)))))); 
		    // WebElement element = driver.findElement(By.xpath(c.getElementName(path)));
			return element; 
		}catch(Exception e)
		{
			Reporter.log("Message :"+e.getLocalizedMessage());
			
		}
		return null;
	}
	
	public WebElement Find_Element(String path)  {
 
		    WebElement element = driver.findElement(By.xpath(c.getElementName(path)));
			return element; 
	}
	public String StringValue(String value)
	{
		String val=c.Variablevalue(value);
		return val;
	}

	//Find Multiple Element
	public List<WebElement> FindElements(String path2)
	{
		List<WebElement> elements=driver.findElements(By.xpath(c.getElementName(path2)));
		return elements;

	}
	//Apply Click method
	public void Click_event(String path) throws InterruptedException {
		Common.waitElement(driver, 5);
		FindElement(path).click();
		Common.waitElement(driver, 10);
	}
	//Apply Click method on Element
	public void table_Click_event(String path, String templateName, String templateGrid) throws InterruptedException {
		String templateRow = FindTableRow(templateName, templateGrid);
		driver.findElement(By.xpath(tableRowActions(templateRow, path))).click();
		Common.waitElement(driver, 10);
	}
	// Apply table row action
	public String tableRowActions(String templateRow, String path) {
		String[] actionColmunXPath = templateRow.split("td");
		return actionColmunXPath[0] + c.getElementName(path);
	}

	public WebElement sourceColumnstableElement(String fieldName,String compSourceColumn) {
		String[] row=FindTableRow(compSourceColumn,"SourceColumnTable").split("td");
		WebElement sourceColumnRow=driver.findElement(By.xpath(row[0]+c.getElementName(fieldName)));
		return sourceColumnRow;	
	}
	// It will return enable disable field status
	public boolean CheckFieldStatus(String path) throws InterruptedException {
		return FindElement(path).isEnabled();
	}

	// Click Element by Class Name Attribute
	public void Click_eventBy_ClassName(String path) {
		driver.findElement(By.className(path)).click();
		Common.waitElement(driver, 10);
	}

	// IF id is dynemic or same in the page
	public void Check_box_JS(String id, String text) {
		Object bool = ((JavascriptExecutor) driver)
				.executeScript("var chkBox = document.getElementById(" + "'" + id + "'" + ");"
						+ "  if (chkBox.checked) {" + "  return true  ;" + "  }" + " else{" + " return false;" + "}");
		boolean checkboxStatus = (Boolean) bool;
		if (text.equals("Yes")) {

			if (checkboxStatus) {
				System.out.println("Checkbox is already Checked");
				Reporter.log("Checkbox is already Checked");
			} else {
				((JavascriptExecutor) driver).executeScript("document.getElementById(" + "'" + id + "'" + ").click();");
				Reporter.log("Checkbox is Checked Now");
			}
		} else {
			if (checkboxStatus) {
				((JavascriptExecutor) driver).executeScript("document.getElementById(" + "'" + id + "'" + ").click();");
			} else {
				System.out.println("Checkbox is unchecked already");
				Reporter.log("Checkbo ix is unchecked already");
			}
		}

	}
	//Click Element by javaScript 
	public void Click_event_javaScript(WebDriver driver, WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		Common.waitElement(driver, 3);
	}

	// Get Text
	public String GetText_event(String path) {
		String actuaTtext = FindElement(path).getText();
		Reporter.log(actuaTtext);
		return actuaTtext;
	}

	public void DestinationAction(String tpath, String compareDestinationName) {
		String getDestionation = findDestinationXpath(compareDestinationName);
		driver.findElement(By.xpath(getDestionation + c.getElementName(tpath))).click();
	}

	public String GetText_By_Attribute(String path, String action) {
		String text = null;
		Select droplist = new Select(FindElement(path));
		List<WebElement> op = droplist.getOptions();
		for (WebElement el : op) {
			try {
				if (el.getAttribute("value").equals(action)) {
					text = el.getText();
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return text;
	}
	public String getTextByJS_Event(String path) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return document.getElementById(\"" + c.getElementName(path) + "\").innerText";
		System.out.println(script);
		System.out.println((String) js.executeScript(script));
		String elementValue = (String) js.executeScript(script);
		return elementValue;
	}

	public void SendKey_event(String path, String text) {
		WebElement textfield = FindElement(path);
		textfield.clear();
		textfield.sendKeys(text);
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}

	public void Clear_InputField(String path) {
		FindElement(path).clear();
	}

	public void SendKey_JS(String id, String text) {
		((JavascriptExecutor) driver).executeScript("document.getElementById(" + "'" + id + "'" + ").value='  ';");
		((JavascriptExecutor) driver)
		.executeScript("document.getElementById(" + "'" + id + "'" + ").value=" + "'" + text + "'" + ";");
		Common.waitElement(driver, 3);
	}

	public void Upload_File_event(String Scriptpath, String uploadpath) {
		Set<String> codeprojectWindowID = driver.getWindowHandles();
		System.out.println("Main Window Handle ----------------- " + codeprojectWindowID);
		String[] dialog = new String[] { Scriptpath, uploadpath };
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(dialog);
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		driver.switchTo().activeElement();
	}

	public void DropDown_list_event(String path, String text) {
		Select droplist = new Select(FindElement(path));
		List<WebElement> op = droplist.getOptions();
		for (WebElement el : op) {
			if (el.getText().trim().equals(text)) {
				droplist.selectByVisibleText(text);
				Reporter.log(text + " : is selected from DDlist");
				break;
			} else {
				droplist.selectByIndex(1);
			}
		}
	}

	public void DropDown_list(WebElement dropDownName, String text) {
		Select droplist = new Select(dropDownName);
		List<WebElement> op = droplist.getOptions();
		for (WebElement el : op) {
			try {
				if (el.getText().trim().equals(text)) {
					droplist.selectByVisibleText(text);
					Reporter.log(text + " : is selected from DDlist");
					break;
				}

			} catch (Exception e) {
				System.out.println(text + "Not Found in DDlist");
				// TODO: handle exception
			}
		}
	}

	public void Dropdow_by_attribute(String path, String action) {
		Select droplist = new Select(FindElement(path));
		List<WebElement> op = droplist.getOptions();
		for (WebElement el : op) {
			try {
				if (el.getAttribute("value").equals(action)) {
					el.click();
					Reporter.log(action + " : is selected from DDlist");
					break;
				}
			} catch (Exception e) {
				System.out.println(action + "Not Found in DDlist");
				// TODO: handle exception
			}
		}
	}

	public void DropDown_list_eventBy_index(String path, String text) {
		int index = Integer.parseInt(text);
		Select droplist = new Select(FindElement(path));
		droplist.selectByIndex(index);
	}

	public void Checkbox(WebElement element, String state) throws IOException {
		if (state.equals("Check")) {
			if (element.isSelected()) {
				System.out.println("Checkbox is already checked");
			} else {
				element.click();
				System.out.println("Checkbox is checked Now");
				Reporter.log("Checkbox is checked Now");
			}
		} else {
			if (!element.isSelected()) {
				element.click();
				System.out.println("Checkbox is Unchecked now");
				Reporter.log("Checkbox is Unchecked now");
			} else {
				System.out.println("Checkbox is Unchecked already");
				Reporter.log("Checkbox is Unchecked already");
			}
		}
	}

	public void Table_event(String compTest, String tPath) throws IOException { 																				
		String rowPath = FindTableRow(compTest, tPath);
		if (rowPath != null) {
			driver.findElement(By.xpath(rowPath)).click();
		} else {
			System.out.println(compTest + " :Text not found in the table");
		}
		Common.waitElement(driver, 3);

	}
	public  void mappingScreen() {
		WebElement mappingtable= FindElement("MappingTable");
		List<WebElement> headerRow=mappingtable.findElement(By.tagName("tbody")).findElements(By.tagName("th")); 
		System.out.println(headerRow);
		List<WebElement> dropbox=driver.findElements(By.xpath("//*[@class='table mb-0 mapping-table']/tbody/tr/td[3]/select"));
		Iterator<WebElement> itr= dropbox.iterator();
		Select droplist = new Select( driver.findElement(By.xpath("//*[@class='table mb-0 mapping-table']/tbody/tr/td[3]/select")));
		List<WebElement> op= droplist.getOptions();
		for(WebElement el:op)
		{
			if(!el.getText().contains("<")) {
				WebElement drop=itr.next();
				Select droplist1=new Select(drop);
				droplist1.selectByVisibleText((el.getText()));
			}
		}
	}
	public String FindTableRow(String compTest, String tPath) {
		String selectedRow = null;
		WebElement selectTableRow = FindElement(tPath);
		List<WebElement> rows = selectTableRow.findElements(By.tagName("tr"));
		List<WebElement> columns = rows.get(0).findElements(By.tagName("td"));
		labrow: for (int rownum = 1; rownum < rows.size(); rownum++) {
			for (int colnum = 1; colnum < columns.size(); colnum++) {
				WebElement tableColumn = driver
						.findElement(By.xpath(c.getElementName(tPath) + "/tr[" + rownum + "]/td[" + colnum + "]"));
				if (tableColumn.getText().equals(compTest)) {
					System.out.println(tableColumn.getText() + " is selected");
					Reporter.log(tableColumn.getText() + " is selected from table");
					selectedRow = c.getElementName(tPath) + "/tr[" + rownum + "]/td[" + colnum + "]";
					break labrow;
				} else {
					if (rows.size() + 1 == rownum + 1) {
						System.out.println(compTest + " :Text not found in the table");
					}
				}
			}
		}
		return selectedRow;
	}

	public String findDestinationXpath(String compareDestinationName) {
		String destination = null;
		WebElement destinations = FindElement("Destinations");
		List<WebElement> addedDestinations = destinations.findElements(By.className("destination-group"));
		for (int i = 1; i <= addedDestinations.size(); i++) {
			WebElement destinationName = driver.findElement(By
					.xpath(c.getElementName("Destinations") + "/div[" + i + "]" + c.getElementName("DestinationName")));
			if (destinationName.getText().equals(compareDestinationName)) {
				destination = c.getElementName("Destinations") + "/div[" + i + "]";
			} else {
				System.out.println("Destination does not exist");
			}
		}
		return destination;
	}

	public void ActionButton(String compTest, String tPath, String action) throws IOException { // Will get the table
		// cells data and click
		// on perticular cell
		String rowPath = FindTableRow(compTest, tPath);
		if (rowPath != null) {
			driver.findElement(By.xpath(rowPath + c.getElementName(action))).click();
		} else {
			System.out.println(compTest + " :Text not found in the table");
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void Table_GetData_event(String ePath) throws IOException { // Will get the table cells data and click on
		// perticular cell

		WebElement element = driver.findElement(By.xpath(c.getElementName(ePath)));
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		for (int rownum = 0; rownum < rows.size(); rownum++) {
			List<WebElement> columns = rows.get(rownum).findElements(By.tagName("td"));
			// System.out.println("Number of columns:"+columns.size());
			for (int colnum = 0; colnum < columns.size(); colnum++) {
				System.out.println(columns.get(colnum).getText());
				int rowno = rownum + 1;
				int colNo = colnum + 1;
				System.out.println(
						driver.findElement(By.xpath(c.getElementName(ePath) + "/tr[" + rowno + "]/td[" + colNo + "]"))
						.getText() + " is selected");
				Reporter.log(
						driver.findElement(By.xpath(c.getElementName(ePath) + "/tr[" + rowno + "]/td[" + colNo + "]"))
						.getText() + " is selected from table");
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
		}
	}
	// Will get the table cells data and click on perticular cell
	public String Table_event_Click_First(String ePath) throws IOException { 

		String t = driver.findElement(By.xpath(c.getElementName(ePath) + "/tr[1]/td[2]")).getText();
		driver.findElement(By.xpath(c.getElementName(ePath) + "/tr[1]/td[2]")).click();
		return t;
	}

	public void CheckItem_AfterDeleting_In_Table_event(String compTest, String tPath) throws IOException { // Will get
		// the table
		// cells
		// data
		// and click on particular cell
		String rowPath = FindTableRow(compTest, tPath);
		if (rowPath != null) {
			throw new ElementNotVisibleException(compTest + "Exists in the table");
		} else {
			System.out.println("Record get deleted");
		}
	}

	public void CheckItem_AfterAdding_In_Table_event(String compTest, String tPath) throws IOException { // Will get the
		// table
		// cells
		// data
		String rowPath = FindTableRow(compTest, tPath);
		if (rowPath != null) {
			System.out.println("Record is get added in grid");
		} else {

			throw new ElementNotVisibleException(compTest + "Not Exists,Still not added in the table");
		}
	}

	public void Sort_From_Table(String ePath, String compTest, String sortNo) throws IOException {
		WebElement element = driver.findElement(By.xpath(c.getElementName(ePath)));
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		labrow: for (int rownum = 0; rownum < rows.size(); rownum++) {
			List<WebElement> columns = rows.get(rownum).findElements(By.tagName("td"));
			// System.out.println("Number of columns:"+columns.size());
			for (int colnum = 0; colnum < columns.size(); colnum++) {
				System.out.println(columns.get(colnum).getText());
				if (columns.get(colnum).getText().equals(compTest)) {
					int rowno = rownum + 1;
					driver.findElement(By.xpath(c.getElementName(ePath) + "/tr[" + rowno + "]/td[1]")).click();
					System.out.println(
							driver.findElement(By.xpath(c.getElementName(ePath) + "/tr[" + rowno + "]/td[1]")).getText()
							+ "-     Sort No: "
							+ driver.findElement(By.xpath(c.getElementName(ePath) + "/tr[" + rowno + "]/td[2]"))
							.getText());
					WebElement source = driver
							.findElement(By.xpath(c.getElementName(ePath) + "/tr[" + rowno + "]/td[1]"));
					WebElement target = driver.findElement(By.xpath(c.getElementName(ePath) + "/tr[" + sortNo + "]"));
					Actions builder = new Actions(driver);
					builder.dragAndDrop(source, target);
					builder.build().perform();
					break labrow;
				}
			}
		}
	}

	public void Drag_and_Drop(String ePath) throws IOException {
		WebElement element = FindElement(ePath);
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		if (rows.size() > 0) {
			int sortNo = 0;
			if (rows.size() > 5) {
				sortNo = 4;
			} else if (rows.size() > 0) {
				sortNo = rows.size();
			}
			driver.findElement(By.xpath(c.getElementName(ePath) + "/tr[1]")).click();
			WebElement source = driver.findElement(By.xpath(c.getElementName(ePath) + "/tr[1]"));
			WebElement target = driver.findElement(By.xpath(c.getElementName(ePath) + "/tr[" + sortNo + "]"));
			Actions builder = new Actions(driver);
			builder.dragAndDrop(source, target);
			builder.build().perform();
		} else {
			System.out.println("No records exists");
		}

	}

	public void handle_browser_tab(String path, String id) throws InterruptedException {
		Common.waitElement(driver, 10);
		WebElement link = FindElement(path);
		;
		Actions newTab = new Actions(driver);
		newTab.keyDown(Keys.SHIFT).click(link).keyUp(Keys.SHIFT).build().perform();
		getWebElement(driver, id);
		// handle windows change
		String base = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();

		set.remove(base);
		assert set.size() == 1;
		driver.switchTo().window((String) set.toArray()[0]);

		// close the window
		driver.close();
		driver.switchTo().window(base);

		// handle windows change and switch back to the main window
		Thread.sleep(1500);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}

	public static WebElement getWebElement(WebDriver driver, String id) {
		WebElement myDynamicElement = null;
		try {
			myDynamicElement = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			return myDynamicElement;
		} catch (TimeoutException ex) {
			return null;
		}
	}

	public String getLocale(String locale, String status) throws Exception {
		String myque = null;
		switch (locale) {

		case ("EN"):
			myque = "select string_text from language_strings where string_id in (select string_id from language_strings where string_text = '"
					+ status + "' and language_code= 130) and language_code=130";
		break;
		case ("NL"):
			myque = "select string_text from language_strings where string_id in (select string_id from language_strings where string_text = '"
					+ status + "' and language_code= 130) and language_code=100";
		break;
		case ("FR"):
			myque = "select string_text from language_strings where string_id in (select string_id from language_strings where string_text = '"
					+ status + "' and language_code= 130) and language_code=110";
		break;
		case ("DE"):
			myque = "select string_text from language_strings where string_id in (select string_id from language_strings where string_text = '"
					+ status + "' and language_code= 130) and language_code=120";
		break;
		case ("ES"):
			myque = "select string_text from language_strings where string_id in (select string_id from language_strings where string_text = '"
					+ status + "' and language_code= 130) and language_code=140";
		break;
		case ("PT"):
			myque = "select string_text from language_strings where string_id in (select string_id from language_strings where string_text = '"
					+ status + "' and language_code= 130) and language_code=150";
		break;
		case ("IT"):
			myque = "select string_text from language_strings where string_id in (select string_id from language_strings where string_text = '"
					+ status + "' and language_code= 130) and language_code=160";
		break;
		case ("PO"):
			myque = "select string_text from language_strings where string_id in (select string_id from language_strings where string_text = '"
					+ status + "' and language_code= 130) and language_code=170";
		break;
		case ("RUS"):
			myque = "select string_text from language_strings where string_id in (select string_id from language_strings where string_text = '"
					+ status + "' and language_code= 130) and language_code=130";
		break;
		case ("TUR"):
			myque = "select string_text from language_strings where string_id in (select string_id from language_strings where string_text = '"
					+ status + "' and language_code= 130) and language_code=130";
		break;
		}
		return myque;

	}

	public void date_time_Picker(String date) throws InterruptedException {
		WebElement calIcon = driver.findElement(By.cssSelector(".k-icon.k-i-calendar"));
		calIcon.click();
		Thread.sleep(500);
		WebElement table = driver.findElement(By.className("k-content"));
		System.out.println("Kendo Calendar");
		List<WebElement> tableRows = table.findElements(By.xpath("//tr"));
		for (WebElement row : tableRows) {
			List<WebElement> cells = row.findElements(By.xpath("td"));
			DateFormat dateFormat = new SimpleDateFormat("dd");
			Date date1 = new Date();
			String cdate = dateFormat.format(date1);
			if (Integer.parseInt(cdate) > Integer.parseInt(date)) {

				for (WebElement cell : cells) {
					if (cell.getText().equals(cdate)) {
						driver.findElement(By.linkText(cdate)).click();
						break;
					}
				}
			} else {
				for (WebElement cell : cells) {
					if (cell.getText().equals(date)) {
						driver.findElement(By.linkText(date)).click();
						break;
					}

				}
			}
		}
		Thread.sleep(1000);
	}

	public void RemoveReplace() {
		List<WebElement> DynamicControls=driver.findElements(By.xpath(c.getElementName("TransFormation_RemoveTraillingBlank_DynamicControls")));
		for(int i=0;i<DynamicControls.size();i++)
		{
			//	DynamicControls
		}
	}
	public String actions(String action, WebElement element, String value) throws IOException {
		String returnValue = null;
		switch (action) {
		case ("Click"):
			element.click();
		break;
		case ("Clear"):
			element.clear();
		break;
		case ("IsenableOrDisable"):
			returnValue = Boolean.toString(element.isEnabled());
		break;
		case ("GetText"):
			returnValue = element.getText();
		break;
		case ("EnterValue"):
			element.sendKeys(value);
		break;
		case ("selectDropDown"):
			DropDown_list(element,value);
		break;
		case ("CheckBox"):
			Checkbox(element,value);
		break;
		}
		return returnValue;
	}
}
