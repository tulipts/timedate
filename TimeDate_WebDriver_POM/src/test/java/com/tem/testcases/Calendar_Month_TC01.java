package com.tem.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tem.base.BaseTest;
import com.tem.pages.CalendarPage;
import com.tem.pages.HomePage;
import com.tem.utility.Log;

public class Calendar_Month_TC01 extends BaseTest {

	@Test(dataProvider = "getData")
	public void testCalendarYear(Hashtable<String, String> data) {
		try {
			navigateToApp();
			Log.info("Changing month");
			// Change month - select month
			HomePage home = new HomePage(driver);
			home.changeMonth(data.get("Month"));
			
			// View calendar
			home.viewCalendar();	
			// View calendar - home.viewCalendar is returning a page object of Calendar Page
			//CalendarPage calendar = home.viewCalendar();
			CalendarPage calendar = new CalendarPage(driver);
			System.out.println("Expected:" +  data.get("Month"));
			System.out.println("Actual:" +  calendar.getCalendarHeader());
			// Validate month on calendar page
						
			Log.info("Asserting month");
			Assert.assertTrue(calendar.getCalendarHeader().contains(data.get("Month")));
			Log.info("TEST PASSED");
		}catch(Exception e) {
			Log.fatal("TEST FAILED");
			Assert.fail(e.getMessage());
		}catch(AssertionError e) {
			Log.error("TEST FAILED");
			Assert.fail(e.getMessage());
		}
		
	}
}
