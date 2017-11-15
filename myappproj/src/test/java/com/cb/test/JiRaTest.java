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
import com.cb.pages.jiraloginpage;
import com.cb.utility.ReadConfigFile;

public class JiRaTest extends BaseClass {

	Logger log = Logger.getLogger(JiRaTest.class);
	private WebDriver driver;
	int iterationcount = 1;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	@Test(dataProvider = "ExcelData")
	public void TestJira(HashMap mapdata) throws Exception {

		ReadConfigFile data = new ReadConfigFile();
		String url = data.getUrl().trim();
		log.info("Launched test url=" + url);
		
		//get the test data from excel sheet
		String username = mapdata.get("Username").toString();
		String password = mapdata.get("Password").toString();

		reportTestName("JIRA LOGIN TEST - Iteration - " + iterationcount);
		reportLoginfo("##################Test-Start##########################");
		log.info("Test Data Iteration = " + iterationcount);
		reportLoginfo("Test Data Iteration = " + iterationcount);

		//launch the test website 
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Browser capture title =" + driver.getTitle());
		reportLoginfo("Start the browser..=" + driver.getTitle());

		// get the page object for the login page and start the action
		jiraloginpage Jiraloginobj = PageFactory.initElements(driver, jiraloginpage.class);

		// enter username and password
		Jiraloginobj.enterusername(username);
		log.info("Username selected=" + username);
		reportLoginfo("Username entered=" + username);
		Jiraloginobj.enterpassword(password);
		log.info("Password selected=" + password);
		reportLoginfo("Password entered=" + password);

		// Click on the login button
		Jiraloginobj.clicklogin();
		Thread.sleep(2000);

		// get the title of page
		String logintitle = driver.getTitle();
		log.info("Login capture title=" + logintitle);
		reportLoginfo("Captured title after login (actual)=" + logintitle);

		// validate if the correct title is displayed or not if login successful
		if (logintitle.contains("System") == true) {
			reportLoginfo("Expected string after login (expected)=System");
			log.info("Jira Login is successful");
			reportLogpass("Jira Login is successful");
		} else {
			reportLogfail("Jira Login is NOT successfull");
			log.info("Jira Login is NOT successful");
			iterationcount=iterationcount+1;
			Assert.assertTrue(false);
		}

		// click on the top right login icon
		Jiraloginobj.clickLogouticon();
		// click on the drop down list of logout label
		Jiraloginobj.clickLogoutlabel();

		// get the title of page
		String logouttitle = driver.getTitle();
		log.info("Logout capture title=" + logouttitle);
		reportLoginfo("Captured title after logout (actual)=" + logouttitle);
		// validate if the correct title is displayed or not if logout
		// successful
		if (logouttitle.contains("Logout") == true) {
			reportLoginfo("Expected string after logout (expected)=Logout");
			log.info("Jira logout is NOT successful");
			reportLogpass("Jira logout is successfull");
		} else {
			log.info("Jira logout is NOT successful");
			reportLogfail("Jira logout is NOT successfull");
		}

		reportLogpass("Jira Login and Logout testing completed.");
		iterationcount = iterationcount + 1;
		reportLoginfo("##################Test-End##########################");

		// WebElement loginusername =
		// driver.findElement(By.xpath("*//li[@id='user-options']//a/@title"));
		// String labl=loginusername.getText();
		// Thread.sleep(3000);

	}
	
	
}
