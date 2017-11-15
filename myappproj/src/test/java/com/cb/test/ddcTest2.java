package com.cb.test;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cb.base.BaseClass;
import com.cb.pages.ddcloginpages;
import com.cb.utility.ReadConfigFile;

public class ddcTest2 extends BaseClass  {
	
	Logger log = Logger.getLogger(JiRaTest.class);
	private WebDriver driver;
	int iterationcount = 1;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	@Test(dataProvider = "ExcelData")
	public void Testddclogin(HashMap mapdata) throws Exception {

		ReadConfigFile data = new ReadConfigFile();
		String url = data.getUrl().trim();
		log.info("Launched test url=" + url);

		// get the test data from excel sheet
		String username = mapdata.get("Username").toString().trim();
		String password = mapdata.get("Password").toString().trim();

		reportTestName("DDC AccountCal Check - Iteration - " + iterationcount);
		reportLoginfo("##################Test-Start##########################");
		log.info("Test Data Iteration = " + iterationcount);
		reportLoginfo("Test Data Iteration = " + iterationcount);

		// launch the test website
		// driver.get(url);
		driver.navigate().to(url);

		if (iterationcount > 1) {
			driver.navigate().refresh();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Browser capture title =" + driver.getTitle());
		reportLoginfo("Start the browser..=" + driver.getTitle());

		// get the page object for the login page and start the action
		ddcloginpages ddcloginobj = PageFactory.initElements(driver, ddcloginpages.class);


		// Click on the logout button if login successful
		ddcloginobj.clickLogout();
		String loginLabel = ddcloginobj.getLoginlabel();
		log.info("Login label=" + loginLabel);

		// Validate the logout is successful
		if (loginLabel.equalsIgnoreCase("login")) {
			log.info("Logout is succesful");
			reportLogpass("Logout is succesful");
		} else {
			log.info("Logout is NOT succesful");
			reportLogpass("Logout is NOT succesful");
			iterationcount = iterationcount + 1;
			Assert.assertTrue(false);

		}

		reportLogpass("ddc Login and Logout testing completed.");
		iterationcount = iterationcount + 1;
		reportLoginfo("##################Test-End##########################");

	}

}
