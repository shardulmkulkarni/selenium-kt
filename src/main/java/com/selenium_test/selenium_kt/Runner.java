package com.selenium_test.selenium_kt;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium_test.selenium_kt.util.UIStore;
import com.selenium_test.selenium_kt.util.UIStoreNew;
import com.selenium_test.selenium_kt.util.WebDriverHelper;

public class Runner {
	
	public static WebDriver driver;
	public static WebDriverHelper wh;
	
	public static void main(String[] args) throws InterruptedException {
		/*System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/
		/*System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, "drivers/IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		WebDriver driver = new InternetExplorerDriver(capabilities);*/
		//System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		//WebDriver driver = new FirefoxDriver();
		//driver.get("https://www.homedepot.com/");
		//driver.manage().window().maximize();
		//driver.findElement(By.id("headerSearch")).sendKeys("hammer");
		
		
		
		/*WebElement searchElem = driver.findElement(By.id("headerSearch"));
		searchElem.sendKeys("hammer");
		driver.findElement(By.id("headerSearchButton")).click();	*/
		/*try {
			if(driver.findElement(By.id("hd_pip")).isDisplayed()) {
				System.out.println("PLP page is displayed");
				
			} else {
				System.out.println("PLP page is not displayed");
			}			
		} catch(NoSuchElementException nse) {
			System.out.println("In NoSuchElementException block");
			System.out.println("PLP page is not displayed");
			nse.printStackTrace();
		} catch(Exception ex) {
			System.out.println("in exception block");
			ex.printStackTrace();
		}*/
		
		
		/*WebDriverHelper wh = new WebDriverHelper(driver);
		wh.sendKeys(UIStore.txtSearch, "hammer");
		wh.click(UIStore.btnSearch);
		System.out.println("Current time : "+new Timestamp(System.currentTimeMillis()));
		if(wh.isElementPresent(UIStore.verifyPLP)) {
			System.out.println("PLP page is displayed");
		} else {
			System.out.println("PLP page is not displayed");
		}
		
		System.out.println("After time : "+new Timestamp(System.currentTimeMillis()));
		String plpPODDCss = UIStore.plpPODByIndex.replaceAll("##INDEX##", "3");
		wh.click(By.cssSelector(plpPODDCss));*/
		setWebDriverHelper();
		wedDriverWait();
		clear();
		selectSortOption();
		//searchStore();
		//isEnabled();
		//isSelected();
		//isDisplayed();
		//getCssValue();
		//getHeightWidth();
		//getLocation();
		//actions();
		
		driver.quit();
	}
	
	public static void setWebDriverHelper() {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "/Users/shardulkulkarni/Dev_Programs/eclipse_workspace/SeleniumKT/selenium-kt/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wh = new WebDriverHelper(driver);
		//driver.get("https://www.homedepot.com/");
		//driver.get("https://www.homedepot.com/s/hammer?NCNI-5");
	}
	
	public static void searchStore() throws InterruptedException {
		
		wh.click(UIStore.lnkStoreFinder);
		if(wh.isElementPresent(UIStore.txtSFSearch)) {
			System.out.println("Store Finder Page is displayed");
		} else {
			System.out.println("Store Finder Page is not displayed");
		}
		
		Thread.sleep(500);
		wh.sendKeys(UIStore.txtSFSearch, "121");
		wh.click(UIStore.btnStoreSearch);
		Thread.sleep(500);
		/* System.out.println(wh.getText(UIStore.storeName)); */
		/*
		 * if(wh.getText(UIStore.storeName).contains("121")) {
		 * System.out.println("Store list got updated with new search results"); }
		 */
		
		List<WebElement> stores = driver.findElements(UIStore.stores);
		String milesText;
		String storeNotInOrder = null;
		double prevMiles = -1.00;
		double currentMiles;
		boolean storesInOrder = true;
		for (WebElement store : stores) {
			milesText = store.findElement(UIStore.storeMileage).getText().replace("mi", "").trim();
			System.out.println("Current miles :" + milesText);
			currentMiles = Double.parseDouble(milesText);
			
			if(currentMiles < prevMiles)  {
				storesInOrder = false;
				storeNotInOrder = store.findElement(UIStore.storeName).getText();
				break;
			}			
			prevMiles = currentMiles;
		}
		
		if(storesInOrder) {
			System.out.println("Stores are in ascending distance");
		} else {
			System.out.println("Following Store has miles less than previous one:" +storeNotInOrder);
		}
		
	}

	public static void selectSortOption() throws InterruptedException {
		//driver.findElement(By.className("drop-down__select js-reviews-sort-by"));
		
		
		Select select = new Select(driver.findElement(By.name("sortByAjax")));
		//select.selectByIndex(5);		
		select.selectByValue("lowestrating");
		//select.selectByVisibleText("Featured reviews");
		Thread.sleep(1000);
		
		List<WebElement> custReviews = driver.findElements(UIStore.custReviews);
		
		for (WebElement custReview : custReviews) {
			System.out.println(custReview.findElement(UIStore.custRatings).getAttribute("rel"));
		}
		
	}
	
	public static void clear() throws InterruptedException {
		wh.sendKeys(UIStore.txtSearch, "hammer");
		Thread.sleep(1000);
		driver.findElement(UIStore.txtSearch).clear();
		
		Thread.sleep(1000);
	}
	
	public static void isEnabled() {
		System.out.println("Before entering Price, Go Button is enables : " +driver.findElement(UIStore.btnPriceGo).isEnabled());
		wh.sendKeys(UIStore.txtLowerBound, "10");
		System.out.println("After entering Price, Go Button is enabled :" +driver.findElement(UIStore.btnPriceGo).isEnabled());
	}
	
	public static void isSelected() throws InterruptedException {
		
		// Thread.sleep(5000);
		
		System.out.println("Before selecting compare check box : " + driver.findElement(UIStore.chkCompare).isSelected());
		//wh.click(UIStore.chkCompare);
		//driver.findElement(UIStore.chkCompare).click();
		
		/*Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(UIStore.chkCompare)).click().build().perform();*/
		
		WebElement element =  driver.findElement(UIStore.chkCompare);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",element);
		
		System.out.println("After selecting compare check box : " + driver.findElement(UIStore.chkCompare).isSelected());
	}
	
	public static void isDisplayed() throws InterruptedException {
		driver.get("https://www.homedepot.com/s/lamps?NCNI-5");
		wh.click(UIStore.leftNavDept);
		System.out.println("Before clicking : " +driver.findElement(UIStore.deptFilters).isDisplayed());
		wh.click(UIStore.leftNavDept);
		Thread.sleep(500);
		System.out.println("After clicking : " +driver.findElement(UIStore.deptFilters).isDisplayed());
	}
	
	public static void  getCssValue() {
		driver.get("https://www.homedepot.com/s/hammer?NCNI-5");
		
		if(driver.findElement(By.id("messageBar")).getCssValue("background-color").contains("rgba(249, 99, 2, 1)")) {
			System.out.println("Message bar is displayed in orange");
		}
		//System.out.println("Backgroud color for messageBar : " + driver.findElement(By.id("messageBar")).getCssValue("background-color"));
	}
	
	public static void getHeightWidth() {
		driver.get("https://www.homedepot.com/s/hammer?NCNI-5");
		WebElement plpPOD = driver.findElement(By.cssSelector(".plp-pod "));
		System.out.println("Height : " +plpPOD.getSize().getHeight());
		System.out.println("Width : " +plpPOD.getSize().getWidth());
	}
	
	public static void getLocation() {
		
		driver.get("https://www.homedepot.com/s/hammer?NCNI-5");
		WebElement storeHeader = driver.findElement(By.className("storeinfo-header"));
		System.out.println("Y : " +storeHeader.getLocation().getX());
		System.out.println("X : " +storeHeader.getLocation().getY());		
	}
	
	public static void actions() throws InterruptedException {
		/*driver.get("https://www.homedepot.com/s/hammer?NCNI-5");
		Thread.sleep(1000);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.cssSelector(".sortby-section"))).click();
		a.moveToElement(driver.findElement(By.cssSelector("li[data-value='P_Topseller_Sort|1']"))).click();
		a.build().perform();*/
		
		driver.get("https://www.homedepot.com/");
		Actions a = new Actions(driver);
		//long duration = new Duration(50, TimeUnit.MILLISECONDS);
		/*a.moveToElement(driver.findElement(UIStore.txtSearch)).click().sendKeys("d").pause(50).sendKeys("r").pause(50).sendKeys("i").build().perform();;
		a.moveToElement(driver.findElement(By.cssSelector("a[tabindex='2']"))).click();
		a.build().perform();*/
		//"a[data-searchterm='drill in Drill/Drivers']"
		a.moveToElement(driver.findElement(UIStore.txtSearch)).click().keyDown(Keys.SHIFT).sendKeys("hammer").build().perform();
		Thread.sleep(1000);		
	}
	
	public static void wedDriverWait() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.homedepot.com/s/hammer?NCNI-5");
		System.out.println("Element present : "+ wh.isElementPresent(UIStoreNew.verifyPLP));
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(UIStoreNew.verifyPLP)));
		System.out.println("Element present after : "+ wh.isElementPresent(UIStoreNew.verifyPLP));
	}
}
