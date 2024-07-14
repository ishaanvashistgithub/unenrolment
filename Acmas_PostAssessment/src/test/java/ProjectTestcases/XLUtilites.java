package ProjectTestcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.common.base.Function;

public class XLUtilites {

	public static File file;
	public static FileInputStream fi;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws, Answersheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle cs;
	public Actions action;

	public static int getRowCount(String xlfile, String xlsheet) throws Exception {
		int rowcount;

		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);

		rowcount = ws.getLastRowNum();

		wb.close();
		fi.close();

		return rowcount;
	}

	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws Exception {

		int cellCount;

		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);

		row = ws.getRow(rownum);

		cellCount = row.getLastCellNum();

		wb.close();
		fi.close();

		return cellCount;

	}

	public static String getCellData(String xlfile, String xlsheet, int rownum, int cellNumber) throws Exception {

		String data;

		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(cellNumber);

		try {
			data = cell.getStringCellValue();
			return data;
		}

		catch (Exception e) {
			data = "";
			return data;

		}
	}

	public static void SetCellData(String xlfile, String xlsheet, int rownum, int column, String data)
			throws IOException {

		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(column);
		cell.setCellValue(data);
		fos = new FileOutputStream(xlfile);

		wb.write(fos);
		wb.close();
		fi.close();
		fos.close();

	}

	public void fillGreenColor(String xlfile, String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);

		row = ws.getRow(rownum);
		cell = row.getCell(colnum);

		cs = wb.createCellStyle();

		cs.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(cs);
		wb.write(fos);
		wb.close();
		fi.close();
		fos.close();
	}

	public void fillRedColor(String xlfile, String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);

		cs = wb.createCellStyle();

		cs.setFillForegroundColor(IndexedColors.RED.getIndex());
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(cs);
		wb.write(fos);
		wb.close();
		fi.close();
		fos.close();
	}

	public static XSSFSheet getFileData() throws Exception {

		return ws;
	}

	public static String getAnswer(String xlfile, String xlsheet, String quest) throws Exception {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		Answersheet = wb.getSheet(xlsheet);
		String answer = null;
		int rowCount = Answersheet.getLastRowNum();
		for (int i = 1; i <= rowCount; i++) {
			row = Answersheet.getRow(i);
			cell = row.getCell(1);
			if (cell.getStringCellValue().toString().equals(quest)) {
				answer = Answersheet.getRow(i).getCell(2).toString();
			}
		}
		return answer;
	}

}
