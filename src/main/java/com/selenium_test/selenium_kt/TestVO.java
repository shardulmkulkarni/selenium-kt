package com.selenium_test.selenium_kt;

import org.openqa.selenium.WebDriver;

import com.selenium_test.selenium_kt.util.WebDriverHelper;

public class TestVO {

	private WebDriver driver = null;
	private WebDriverHelper wh = null;
	//Report
	//Data Helper
	public TestVO(WebDriver driver, WebDriverHelper wh) {
		this.driver = driver;
		this.wh = wh;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverHelper getWh() {
		return wh;
	}
	
}
