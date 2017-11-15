package com.cb.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class googlehomepage {
	
	WebDriver driver;
	
	public googlehomepage(WebDriver ldriver)
	{
		this.driver=ldriver;
	}
	
	@FindBy(name="q")
	WebElement searchtext;
	
	@FindBy(name="btnK")
	WebElement searchbutton;
	
	//@FindBy(css="ol li h3>a")
	@FindBy(xpath=("//h3[@class='r']/a"))
	List<WebElement> searchlist;
	
	
	public void entersearhtext(String text) throws InterruptedException
	{
		searchtext.sendKeys(text);
		Thread.sleep(3000);
		searchtext.sendKeys(Keys.ESCAPE);
		Thread.sleep(3000);
	}
	
	public void clicksearchbutton() throws InterruptedException
	{
		searchbutton.click();
		Thread.sleep(5000);
	}
	
	public List<WebElement> getAllsearchlinks() throws InterruptedException
	{
			return searchlist;
	}

}
