package com.selenium_test.selenium_kt.util;

import org.openqa.selenium.By;

public class UIStore {
	public static final By txtSearch = By.cssSelector("#headerSearch");
	public static final By btnSearch = By.id("headerSearchButton");
	public static final By verifyPLP = By.id("hd_pip");	
	public static final String plpPODByIndex = ".plp-pod:nth-last-of-type(3)";
	public static final By lnkStoreFinder = By.linkText("Store Finder");
	public static final By txtSFSearch = By.id("storeSearchBox");
	public static final By btnStoreSearch = By.cssSelector(".sfSearchbox__button button"); 
	public static final By storeName = By.className("sfstorename");
	public static final By stores = By.cssSelector("#SFControl >section > div[class*='store']");
	public static final By storeMileage = By.className("sfstoremilage");
	public static final By custReviews = By.cssSelector("#customer_reviews .review");
	public static final By custRatings = By.cssSelector(".star-rating-container__stars");
	public static final By btnPriceGo = By.cssSelector(".js-customPriceGo");
	public static final By txtLowerBound = By.name("lowerBound");
	public static final By chkCompare = By.name("product");	
	public static final By leftNavDept = By.xpath("//div[@class='otherfilters']//a[text()='Department']");
	public static final By deptFilters = By.cssSelector("ul[data-refinement='Department']");
}
