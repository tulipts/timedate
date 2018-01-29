package com.tem.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.tem.constants.Constants;
import com.tem.utility.ExcelUtils;
import com.tem.utility.Log;

//All Test Cases inherits 'BaseTest' class
public class BaseTest {

	public WebDriver driver;
	public Properties project;
	FileInputStream fs;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		// Initialise configuration - properties
		initialiseConfig();
		// Set excel path
		ExcelUtils.setExcelPath(Constants.TestDataPath);		
		// Check test run mode
		if (!ExcelUtils.checkTestRunMode(Constants.TestCasesSheet, this.getClass().getSimpleName())) {
			Log.info("Skipping the test as Test Case runmode is 'No'");
			// test.log(LogStatus.SKIP, "Skipping the test as Test Case runmode is 'No'");
			throw new SkipException("Skipping the test as Test Case runmode is 'No'");
		}
		// Initialise WebDriver
		initialiseDriver();
	}

	public void initialiseConfig() {
		// Initialise Project properties
		try {
			fs = new FileInputStream(Constants.PropertiesPath);
			project = new Properties();
			project.load(fs);
		} catch (Exception e) {
			e.getMessage();
		}
		Log.startTestCase(this.getClass().getSimpleName());
		Log.info("Config initialised");
	}

	public void initialiseDriver() {
		if (driver == null) {
			// Log.info("Initialising Driver");
			if (project.getProperty("browserType").equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", ".//drivers//geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (project.getProperty("browserType").equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", ".//drivers//IEDriverServer.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability("ignoreZoomSetting", true);						
				driver = new InternetExplorerDriver();
			} else if (project.getProperty("browserType").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
				driver = new ChromeDriver();
			}
		}
		Log.info("Driver initialised");
		driver.manage().window().maximize();
	}

	public void navigateToApp() {
		// Navigate to home page
		Log.info("Navigating to app");
		driver.get(project.getProperty("testUrl"));
		driver.manage().timeouts().pageLoadTimeout(Constants.pageLoadWaitTime, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.implicitWaitTime, TimeUnit.SECONDS);
	}

	@DataProvider
	public Object[][] getData() throws Exception {		
		return ExcelUtils.getTableArray("Test Data", this.getClass().getSimpleName());	
	}

	@AfterTest
	public void tearDown() throws Exception {
		Log.endTestCase(this.getClass().getSimpleName());
		ExcelUtils.closeWorkbook();
		if (driver != null)
			driver.quit();
	}
}
