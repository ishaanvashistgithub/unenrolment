package ProjectTestcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

//from here 
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

//till here 

public class XLUtilites {

	public static FileInputStream fi;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle cs;

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

	public static void SetCellData(String xlfile, String xlsheet, int rownum, int column, String data) throws IOException {

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
	
	
	public static void writereport(int a, int b, String text) {
	    try {
	    	File file = new File("Data");
	        File excelFile = new File(file.getAbsolutePath()+"/CollectData/collectdataDemoo.xls");
	        System.out.println(excelFile);
	        WritableWorkbook book;
	        WritableSheet sheet;
	        Workbook existingBook = null;
	        if (!excelFile.exists()) {
	            book = Workbook.createWorkbook(excelFile);
	            sheet = book.createSheet("Records", 0);
	        } else {
	            existingBook = Workbook.getWorkbook(excelFile);
	            book = Workbook.createWorkbook(excelFile, existingBook);
	            sheet = book.getSheet("Records");
	        }
	        Label i = new Label(a, b, text);
	        sheet.addCell(i);
	        book.write();
	        book.close();
	        if (existingBook != null)
	            existingBook.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    }
	}

}
