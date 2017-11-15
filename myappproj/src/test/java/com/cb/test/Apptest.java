package com.cb.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.cb.utility.ReadConfigFile;


public class Apptest  {
	
	Logger log=Logger.getLogger(Apptest.class);	
	static WebDriver driver=null;

	@Test
	public void test1() throws Exception
	{
		
		ReadConfigFile data=new ReadConfigFile();
		System.out.println("URL="+data.getUrl());	
		log.debug("Url Fetched="+data.getUrl());
		
		//launch chrome browser
		//String exePath = "C:\\Users\\niranjan.deka\\Sofware\\Browserdrivers\\chromedriver32.exe";
		String exePath = System.getProperty("user.dir")+"\\BrowserDrivers\\chromedriver32.exe";
		log.debug(exePath);
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.get(data.getUrl());
		log.debug("Title Fetched="+driver.getTitle());

	}
	
}
