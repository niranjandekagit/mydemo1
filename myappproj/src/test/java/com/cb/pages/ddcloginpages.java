package com.cb.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ddcloginpages {

	WebDriver driver;

	public ddcloginpages(WebDriver ldriver) {
		this.driver = ldriver;
	}

	@FindBy(id = "C2__USER_NAME")
	WebElement username;

	@FindBy(name = "C2__LOGIN[1].PASSWORD")
	WebElement password;

	// @FindBy(xpath = "//a[contains(@onclick, 'return button')]")
	@FindBy(id = "C2__BUT_21AA8B8279AB849B2246")
	WebElement nextbutton;

	@FindBy(xpath = ".//*[@id='C2__BUT_E08B195153315344131925']/span")
	WebElement SignIn;

	@FindBy(xpath = ".//*[@id='C1__BUT_F6A61BDCCE0BE222609605']/span")
	WebElement Logoutbtn;

	@FindBy(xpath = ".//*[@id='C2__HEAD_D8D291AF3353DBBC3408775']")
	WebElement LoginLabel;

	public String getLoginlabel() {

		try {
			String loginLabel = LoginLabel.getText().trim();
			return loginLabel;
		}

		catch (Exception ex) {
			return "";
		}

	}

	public void enterusername(String usename) throws InterruptedException {

		// username.sendKeys(Keys.F5);
		// Thread.sleep(1000);
		username.sendKeys(usename);
		Thread.sleep(2000);

	}

	public String getLogoutbutton() {

		try {
			String logoutlabel = Logoutbtn.getText();
			return logoutlabel;
		}

		catch (Exception ex) {
			return "";
		}

	}

	public void enterpassword(String pasword) throws InterruptedException {

		password.sendKeys(pasword);
		Thread.sleep(2000);

	}

	public void clickNext() throws InterruptedException {

		// JavascriptExecutor executor = (JavascriptExecutor)driver;
		// executor.executeScript("arguments[0].click();", nextbutton);
		nextbutton.click();
		Thread.sleep(2000);

	}

	public void clickSignIn() throws InterruptedException {
		SignIn.click();
		Thread.sleep(2000);

	}

	public void clickLogout() throws InterruptedException {

		Logoutbtn.click();
		Thread.sleep(2000);

	}

}
