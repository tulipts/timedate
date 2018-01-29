package com.tem.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tem.base.BaseTest;
import com.tem.pages.CalendarPage;
import com.tem.pages.HomePage;
import com.tem.utility.Log;

public class Calendar_Year_POMOptimised extends BaseTest {

	@Test(dataProvider = "getData")
	public void testCalendarYear(Hashtable<String, String> data) {
		try {
			navigateToApp();
			Log.info("Changing year");
			// Change year - enter year
			HomePage home = new HomePage(driver);
			// home.changeYear(data.get("Year"));			
			// View calendar
			// CalendarPage calendar = home.viewCalendar();			
			// Validate year on calendar page
			
			Log.info("Asserting year");
//Run all the tests as one line of code in Assert statement. Because the page object is returned in each method
//in the HomePage class, that allows access to other methods in the same class. So as viewCalendar returns
//the CalendarPage as the page object, that allows access to its method getCalendarHeader		
			Assert.assertTrue(home.changeYear(data.get("Year")).viewCalendar().getCalendarHeader().contains(data.get("Year")));
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
