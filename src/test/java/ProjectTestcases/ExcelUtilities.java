package ProjectTestcases;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	static XSSFWorkbook WrkBook;
	static XSSFSheet sheet;

	public ExcelUtilities(String ExcellPath, String SheetName) {
		try {
			WrkBook = new XSSFWorkbook(ExcellPath);
			sheet = WrkBook.getSheet(SheetName);
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public Object getcelldata(int rowNumber, int columnNumber) throws IOException {
	
		DataFormatter formatter = new DataFormatter();
		
		Object cellvalue = sheet.getRow(rowNumber).getCell(columnNumber).getStringCellValue();
		System.out.println(cellvalue);
	
	  return cellvalue;

	}

	public int getRowCount() throws IOException {

		int rowcount = sheet.getPhysicalNumberOfRows()-1;
		System.out.println("Print Row count: " + rowcount);
		return rowcount;
	}
	
	public void writeExcel(String sheetName, String cellvalue, int row, int column)
	{
		
	}

}
