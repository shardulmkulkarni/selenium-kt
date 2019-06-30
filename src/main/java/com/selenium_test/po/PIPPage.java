package com.selenium_test.po;

import org.openqa.selenium.By;

import com.selenium_test.selenium_kt.TestVO;

public class PIPPage extends BasePO{

	public static final By verifyPIP = By.id("hd-pip");
	
	public PIPPage(TestVO testVO) {
		super(testVO);
	}
	
	public PIPPage verifyPIPPage() {
		boolean isPIPPage = false;
		if (wh.isElementPresent(verifyPIP)) {
			System.out.println("PIP page is displayed");
		}  else  {
			System.out.println("PIP page is not displayed");
		}
		return this;
	}
}
