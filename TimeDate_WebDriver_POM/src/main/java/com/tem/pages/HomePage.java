package com.tem.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.tem.constants.Constants;

public class HomePage extends BasePage {
	WebDriver driver;
	
	// Constructor 
	public HomePage(WebDriver driver) {
		this.driver = driver;
		//PageFactory initializes all web elements on this page. 
		//the last option is used here: Webdriver driver and Object page from the available parameters
		//the driver is passed in and also "this" meaning Object page = this web page
		PageFactory.initElements(this.driver, this);		
	}
	
	// Repository
	// String CalendarYear_Text = "//*[@id='boxyear']";
	// driver.findElement(By.xpath("//*[@id='boxyear']"));
	
	//@FindBy selenium annotation - which is a combination of "driver.findelement"
	//Rather than annotating a method, this is annotating an element.Hence declare the WebElement, which gets
	//passed the value of the constant - so the element is found by @FindBy
	
	@FindBy(xpath = Constants.calendarYear_Text) public WebElement calendarYear_Text;
	@FindBy(xpath = Constants.viewCalendar_Button)  public WebElement viewCalendar_Button;
	@FindBy(xpath = Constants.calendarMonth_RadioButton) public WebElement calendarMonth_RadioButton;
	@FindBy(xpath = Constants.calendarYear_RadioButton) public WebElement calendarYear_RadioButton;
	@FindBy(xpath = Constants.calendarMonthOption_Select) public WebElement calendarMonthOption_Select;
	
	// POM Methods
	// Change year ---- returning HomePage page object instead of void.
	public HomePage changeYear(String year) {		
		waitUntilVisibilityOf(driver, calendarYear_Text);
		if(!calendarYear_RadioButton.isSelected()) {
			calendarYear_RadioButton.click();
		}		
		calendarYear_Text.clear();
		calendarYear_Text.sendKeys(year);
		System.out.println("changeyr " + this);
		//Return to the same page object
		return this;	
	}
	
	//Change Month - returning HomePage instead of void.
	public HomePage changeMonth(String month) {		
		waitUntilVisibilityOf(driver, calendarMonth_RadioButton);
		if(!calendarMonth_RadioButton.isSelected()) {
			calendarMonth_RadioButton.click();
		}		
		Select selector = new Select(calendarMonthOption_Select);
		selector.selectByVisibleText(month);
		//Return to the same page object
		return this;
	}
	
	//View the Calendar - returning CalendarPage instead of void	
	public CalendarPage viewCalendar() {		
		viewCalendar_Button.click();
		//The Calendar Page is returned as a Page Object and is passed the driver object
		return new CalendarPage(driver);
	}
	
		
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
