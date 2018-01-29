package com.tem.constants;

public class Constants {

	// Project Variables
	public static final String PropertiesPath = ".//src//main//resources//project.properties";
	public static final String Log4JPath = ".//src//main//resources//log4j.xml";

	// Excel variables
	public static final String TestDataPath = ".//src//test//resources//Test Cases - Data.xlsx";

	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";
	public static final String KEYWORD_SKIP = "SKIP";
	public static final Object RunMode_YES = "Y";
	public static final Object RunMode_NO = "N";

	// Data Excel sheets
	public static final String TestCasesSheet = "Test Cases";
	public static final String TestDataSheet = "Test Data";

	// Test Cases Column index
	public static final int Col_TestCaseID = 0;
	public static final int Col_RunMode = 1;

	// Test Steps Column index
	// public static final int Col_TestCaseID = 0;
	public static final int Col_Keyword = 2;
	public static final int Col_Object = 3;
	public static final int Col_Data = 4;

	// Wait
	public static final int pageLoadWaitTime = 10;
	public static final int implicitWaitTime = 10;
	public static final int explicitWaitTime = 6000;

	/* Locators */
	// HomePage
	public static final String calendarYear_Text = "//*[@id='boxyear']";
	public static final String viewCalendar_Button = "//*[@value='View Calendar']";
	public static final String calendarMonth_RadioButton = "//*[@value='1' and @name='typ']";
	public static final String calendarYear_RadioButton = "//*[@value='0' and @name='typ']";
	public static final String calendarMonthOption_Select = "//*[@id='month']";
	public static final String calendarHeader_Label = "//*[@id='ct1']/h1";

	
	
	

}
