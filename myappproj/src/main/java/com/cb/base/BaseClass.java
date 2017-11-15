package com.cb.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.cb.utility.ExcelDataProvider;
import com.cb.utility.ReadConfigFile;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass extends ExcelDataProvider {

	private WebDriver driver;
	Logger log = Logger.getLogger(BaseClass.class);
	

	static ExtentReports extent;
	static ExtentTest Reportlogger;

	private String CallClassname;
	public String testname;
	
	protected BaseClass() {
		StackTraceElement[] trace = new Throwable().getStackTrace();
		this.CallClassname = trace[1].getFileName().trim();
	}

	@BeforeTest
	public void before() throws Exception {
		
		ReadConfigFile data = new ReadConfigFile();
		String browsertype = data.getBrowser().trim();
		
		//get the date_time
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formatter=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		
		// String testname=textcontext.getClass().getSimpleName().toString();
		testname = CallClassname.trim().toString().replace(".", "_").split("_")[0];
		extent = new ExtentReports(System.getProperty("user.dir") + "/Reports/" + "RunReport_"+testname +"_runon_"+browsertype+"_"+formatter.format(cal.getTime())+".html", true);
		extent.addSystemInfo("Host Name", "Central");
		extent.addSystemInfo("Environment", "Automation Testing");
		extent.addSystemInfo("BrowserSelect", browsertype);
		extent.addSystemInfo("User Name", "Niranjan Deka");
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
	}

	@AfterSuite
	public void tearDownSuite() {
		extent.flush();
		extent.close();
	}

	public static void reportLoginfo(String message) {
		Reportlogger.log(LogStatus.INFO, message);// For extentTest HTML report
	}

	public static void reportLogfail(String message) {
		Reportlogger.log(LogStatus.FAIL, message);// For extentTest HTML report
	}

	public static void reportLogpass(String message) {
		Reportlogger.log(LogStatus.PASS, message);// For extentTest HTML report
	}

	public static void reportTestName(String message) {
		Reportlogger = extent.startTest(message);
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			Reportlogger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			Reportlogger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			Reportlogger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}

		extent.endTest(Reportlogger);
	}

	@BeforeClass
	public void initializeTestBaseSetup() throws Exception {

		ReadConfigFile data = new ReadConfigFile();
		// System.out.println("URL=" + data.getBrowser());
		String browserType = data.getBrowser().trim();

		try {
			setDriver(browserType);

		} catch (Exception e) {
			System.out.println("Error....." + e.getMessage());
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriver setDriver(String browserType) throws Exception {

		String exePath = null;

		switch (browserType) {
		case "firefox":
			log.info("firefox browser launching..");
			ReadConfigFile data = new ReadConfigFile();
			String browsertype = data.getBrowser().trim();
			String firefoxpathexe=data.getFirepathexebinaries();
			//System.setProperty("webdriver.firefox.bin","C:\\Users\\niranjan.deka\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			System.setProperty("webdriver.firefox.bin",firefoxpathexe);
			//System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\Browserdrivers\\geckodriver64.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "chrome":
			log.info("Chrome browser launching..");
			exePath = System.getProperty("user.dir") + "\\Browserdrivers\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", exePath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "ie":
			log.info("IE browser launching..");
			//exePath = System.getProperty("user.dir") + "\\Browserdrivers\\IEDriverServer32.exe";
			exePath = System.getProperty("user.dir") + "C:\\Users\\niranjan.deka\\.m2\\repository\\org\\seleniumhq\\selenium\\selenium-ie-driver\\2.53.0\\selenium-ie-driver-2.53.0";
			System.setProperty("webdriver.ie.driver", exePath);
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			break;
		case "phantom":
			log.info("Phantom browser launching..");
			File file = new File(System.getProperty("user.dir") + "\\Browserdrivers\\phantomjs.exe");
			System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
			driver = new PhantomJSDriver();
			break;
		default:
			log.info("No browser is selected in the config file..");
			System.out.println("browser : " + browserType + " is invalid, Launching IE as browser of choice..");
			// driver = new InternetExplorerDriver();
		}

		return driver;

	}

	@AfterClass
	public void closeApplication() {

		log.info("Closing the browser");
		driver.close();
		driver.quit();

	}

}
