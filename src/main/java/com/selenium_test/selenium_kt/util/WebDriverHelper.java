package com.selenium_test.selenium_kt.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class WebDriverHelper {

	private WebDriver driver = null;
	
	public WebDriverHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isElementPresent(By by) {
		boolean elementPresent = false;
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		try {
			elementPresent = driver.findElement(by).isDisplayed();
			
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return elementPresent;
	}
	
	public void click(By by) {
		if(isElementPresent(by)) {
			driver.findElement(by).click();
		}
	}
	
	public void sendKeys(By by, String keys) {
		if(isElementPresent(by)) {
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(keys);
		}
	}
}
