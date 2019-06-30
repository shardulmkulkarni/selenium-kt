package com.selenium_test.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.selenium_test.selenium_kt.TestVO;
import com.selenium_test.selenium_kt.util.UIStoreNew;
import com.selenium_test.selenium_kt.util.WebDriverHelper;

public class HomePage extends BasePO {
	
	public static final By txtSearch = By.cssSelector("#headerSearch");
	public static final By btnSearch = By.id("headerSearchButton");
	
	public HomePage(TestVO testVO) {
		super(testVO);
		
	}
	
	public HomePage openHomePage() {
		driver.get("https://www.homedepot.com/");
		return this;
	}
	
	public PLPPage searchKeyword(String keyword) {
		wh.sendKeys(txtSearch, "lamps");
		wh.click(btnSearch);
		return new PLPPage(testVO);
	}
	
}
