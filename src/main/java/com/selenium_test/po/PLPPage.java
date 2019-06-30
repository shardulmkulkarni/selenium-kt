package com.selenium_test.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.selenium_test.selenium_kt.TestVO;
import com.selenium_test.selenium_kt.util.UIStoreNew;
import com.selenium_test.selenium_kt.util.WebDriverHelper;

public class PLPPage extends BasePO {
	
	public static final By verifyPLP = By.id("hd_plp");
	public static final By firstProdDescLink = By.cssSelector(".pod-plp__description a");
	
	public PLPPage(TestVO testVO) {
		super(testVO);
	}
	
	public PLPPage verifyPLPPage() {
		boolean isPLPPage = false;
		if (wh.isElementPresent(verifyPLP)) {
			System.out.println("PLP page is displayed");
		}  else  {
			System.out.println("PLP page is not displayed");
		}
		return this;
	}
	
	public PIPPage clickFirstProduct() {
		wh.click(firstProdDescLink);
		return new PIPPage(testVO);
	}
}
