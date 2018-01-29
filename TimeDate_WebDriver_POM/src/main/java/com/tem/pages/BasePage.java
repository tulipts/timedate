package com.tem.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tem.constants.Constants;

public class BasePage {
	// Function for waiting until element is visible
	public void waitUntilVisibilityOf(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.explicitWaitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Function for waiting until element is clickable
	public void waitUntilElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.explicitWaitTime);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
