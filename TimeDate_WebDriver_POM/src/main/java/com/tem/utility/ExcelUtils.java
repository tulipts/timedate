package com.tem.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.tem.constants.Constants;

public class ExcelUtils {
	private static XSSFSheet sheet;
	private static XSSFWorkbook wBook;
	private static org.apache.poi.ss.usermodel.Cell cell;
	private static XSSFRow row;

	// Initialise work book
	public static void setExcelPath(String Path) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			wBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			Log.error("Class Utils | Method setExcelFile | Exception desc : " + e.getMessage());
			// DriverScript.result = false;
		}
	}

	// Close work book
	public static void closeWorkbook() throws Exception {
		try {
			wBook.close();
		} catch (Exception e) {
			Log.error("Class Utils | Method setExcelFile | Exception desc : " + e.getMessage());
			// DriverScript.result = false;
		}
	}

	// Get cell value
	public static String getCellData(String sheetName, int rownum, int columnnum) throws Exception {
		try {
			return wBook.getSheet(sheetName).getRow(rownum).getCell(columnnum).getStringCellValue();
		} catch (Exception e) {
			e.getMessage();
			return "";
		}
	}

	// Get row count
	public static int getRowCount(String SheetName) {
		int rowCount = 0;
		try {
			sheet = wBook.getSheet(SheetName);
			rowCount = sheet.getLastRowNum() + 1;
		} catch (Exception e) {
			Log.error("Class Utils | Method getRowCount | Exception desc : " + e.getMessage());
			// DriverScript.result = false;
		}
		return rowCount;
	}
	
	/* Check test run mode */
	public static boolean checkTestRunMode(String sheetName, String testCaseName) throws Exception {
		int rows = getRowCount(sheetName);
		// Iterate all rows in Test Cases sheet
		for (int rowNum = 1; rowNum <= rows; rowNum++) {
			// match test case name with every test case (TC ID)
			if (getCellData(sheetName, rowNum, Constants.Col_TestCaseID).equals(testCaseName)) {
				// matched
				if (getCellData(sheetName, rowNum, Constants.Col_RunMode).equals("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	/* Get data from excel in an Object array / Hashtable */
	public static Object[][] getTableArray(String sheetName, String testCaseName) throws Exception {
		// Find rows
		int rowCount = getRowCount(sheetName);
		System.out.println("Running ExcelUtils. Show current test case info.");
		System.out.println("Total rows: " + rowCount);
		// Find the test Case Row Start number
		int testCaseRowStart = 0;
		while (!getCellData(sheetName, testCaseRowStart, 0).equals(testCaseName)) {
			testCaseRowStart++;
		}
		System.out.println("Test case starts from row: " + testCaseRowStart);
		// Find column start
		int testCaseColStart = testCaseRowStart + 1;
		System.out.println("column start: " + testCaseColStart);
		// Find data start
		int testCaseDataStart = testCaseRowStart + 2;
		System.out.println("data start: " + testCaseDataStart);
		// Count rows - Iterate through all rows until blank row is hit
		int rows = 0;
		while (!(getCellData(sheetName, testCaseDataStart + rows, 0).equals(""))) {
			rows++;
		}
		System.out.println("Number of rows for test case: " + rows);
		// Count cols - Iterate through all cols until blank col is hit
		int cols = 0;
		while (!(getCellData(sheetName, testCaseColStart, cols).equals(""))) {
			cols++;
		}
		System.out.println("Number of cols for test case: " + cols);
		// Class Object - array to hold multiple values
		// 2 dimensional object array will only have one column and multiple
		// rows
		Object[][] data = new Object[rows][1];
		int index = 0;
		Hashtable<String, String> table = null;
		// Print
		for (int rowNum = testCaseDataStart; rowNum < (testCaseDataStart + rows); rowNum++) {
			// Every row will have new hashtable
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				// Hashtbale Key = ColStart (col name); Value = celldata
				String key = getCellData(sheetName, testCaseColStart, colNum);
				String value = getCellData(sheetName, rowNum, colNum);
				table.put(key, value);
			}
			// Put hashtable into Object array for a row
			data[index][0] = table;
			// System.out.println(data[index][0] );
			index++;
			// System.out.println();
		}
		return data;
	}
}