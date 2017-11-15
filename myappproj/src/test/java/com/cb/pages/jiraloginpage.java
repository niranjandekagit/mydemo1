package com.cb.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class jiraloginpage {

	WebDriver driver;

	public jiraloginpage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	@FindBy(name = "os_username")
	WebElement username;

	@FindBy(name = "os_password")
	WebElement password;

	@FindBy(name = "login")
	WebElement login;

	@FindBy(xpath = "//span[@class='aui-avatar-inner']")
	WebElement logouticon;

	@FindBy(xpath = "//*[@id='log_out']")
	WebElement logoutlabel;

	public void enterusername(String usename) throws InterruptedException {
		username.sendKeys(usename);
		Thread.sleep(2000);

	}

	public void enterpassword(String pasword) throws InterruptedException {
		password.sendKeys(pasword);
		Thread.sleep(2000);

	}

	public void clickLogouticon() throws InterruptedException {
		logouticon.click();
		Thread.sleep(2000);

	}

	public void clickLogoutlabel() throws InterruptedException {
		logoutlabel.click();
		Thread.sleep(3000);

	}

	public void clicklogin() throws InterruptedException {
		login.click();
		Thread.sleep(2000);

	}
}
