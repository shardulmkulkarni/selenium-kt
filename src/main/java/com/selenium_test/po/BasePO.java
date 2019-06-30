package com.selenium_test.po;

import org.openqa.selenium.WebDriver;

import com.selenium_test.selenium_kt.TestVO;
import com.selenium_test.selenium_kt.util.WebDriverHelper;

public class BasePO {

	protected WebDriver driver = null;
	protected WebDriverHelper wh = null;
	protected TestVO testVO =null;
	
	public BasePO(TestVO testVO) {
		this.testVO = testVO;
		this.driver = testVO.getDriver();
		this.wh = testVO.getWh();
	}
}
