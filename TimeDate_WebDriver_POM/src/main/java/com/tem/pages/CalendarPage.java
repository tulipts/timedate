package com.tem.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tem.constants.Constants;

public class CalendarPage extends BasePage {
	WebDriver driver;

	// Constructor 
	public CalendarPage(WebDriver driver) {
		this.driver = driver;
		//the driver is passed in and also "this" meaning Object page = this web page
		PageFactory.initElements(this.driver, this);
	}

	// Repository
	@FindBy(xpath = Constants.calendarHeader_Label)
	public WebElement calendarHeader_Label;

	// POM Methods
	public String getCalendarHeader() {
		waitUntilVisibilityOf(driver, calendarHeader_Label);
		return calendarHeader_Label.getText();
	}

}
