package com.cb.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cb.base.BaseClass;
import com.cb.pages.googlehomepage;
import com.cb.utility.ReadConfigFile;

public class Test2 extends BaseClass {

	Logger log = Logger.getLogger(Test2.class);

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	@Test
	public void test2() throws Exception {

		ReadConfigFile data = new ReadConfigFile();
		String url = data.getUrl().trim();
		
		reportTestName("Test 1 start");
		
		log.debug("url=" + url);
		//driver.get("http://www.google.com");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.debug("Launched=" + driver.getTitle());
		reportLoginfo("Start the browser..="+driver.getTitle());
		
		String searchtext="Testng";
		
		googlehomepage googleobj=PageFactory.initElements(driver, googlehomepage.class);
		
		googleobj.entersearhtext(searchtext);
		reportLoginfo("Enter search text="+searchtext);
		googleobj.clicksearchbutton();
		Thread.sleep(5000);
		reportLoginfo("click on the google search button");
		List<WebElement> mylist=googleobj.getAllsearchlinks();
		reportLoginfo("Links search list seize="+mylist.size());
		for(int i=0;i<mylist.size();i++)
		{
			reportLoginfo("Links"+i+"="+mylist.get(i).getText().trim());
		}
		
		
		reportLogpass("Test step 1 passed");
		//reportLogfail("Test step 2 failed");
		reportLoginfo("End the browser..");
	}

}
