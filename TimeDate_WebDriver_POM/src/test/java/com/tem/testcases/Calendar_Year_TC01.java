package com.tem.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tem.base.BaseTest;
import com.tem.pages.CalendarPage;
import com.tem.pages.HomePage;
import com.tem.utility.Log;

public class Calendar_Year_TC01 extends BaseTest {

	@Test(dataProvider = "getData")
	public void testCalendarYear(Hashtable<String, String> data) {
		try {
			navigateToApp();
			Log.info("Changing year");
			System.out.println("Start Test: Calendar_Year_TC01. Change and validate year in calendar");
			// Change year - enter year
			HomePage home = new HomePage(driver);
			home.changeYear(data.get("Year"));
			
			// View calendar
			home.viewCalendar();			
			// Validate year on calendar page
			CalendarPage calendar = new CalendarPage(driver);
			//String actual = calendar.getCalendarHeader();
			System.out.println("Expected Year:" +  data.get("Year"));
			System.out.println("Actual:" +  calendar.getCalendarHeader());
			Log.info("Asserting year");
			Assert.assertTrue(calendar.getCalendarHeader().contains(data.get("Year")));
			Log.info("TEST PASSED");
		}catch(Exception e) {
			Log.fatal("TEST FAILED");
			Assert.fail(e.getMessage());
		}catch(AssertionError e) {
			Log.error("TEST FAILED");
			Assert.fail(e.getMessage());
		}
		System.out.println("End Test: Calendar_Year_TC01.");
		
	}
}
