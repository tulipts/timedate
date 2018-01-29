# timedate
# Project Name: TimeDate_WebDriver_POM
# This project runs automated tests against: https://www.timeanddate.com/
# The project is built using Java, Selenium Webdriver, Maven, TestNG and ReportNG
# Apache POI is used to access the test cases to run and test data from an excel sheet
# Automation Framework: Page Object Model using PageFactory
# Test 1: Calendar_Year_TC01 (enters a year into the calendar year selection and validates the calendar is displayed for the correct year)
# Test 2: Calendar_Month_TC01 (enters a month into the calendar month selection & validates the calendar is displayed for the correct month)
# Test 3: Calendar_Year_POMOptimised (as per Test 1 but utilises a more efficient Assert statement, to test and validate in one line of code. This is achieved by returning the page object in each method, thus allowing access to to all methods for the page)
# E.g. Assert.assertTrue(home.changeYear(data.get("Year")).viewCalendar().getCalendarHeader().contains(data.get("Year")));
