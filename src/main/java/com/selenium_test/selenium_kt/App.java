package com.selenium_test.selenium_kt;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium_test.po.HomePage;
import com.selenium_test.po.PLPPage;
import com.selenium_test.selenium_kt.util.UIStore;
import com.selenium_test.selenium_kt.util.UIStoreNew;
import com.selenium_test.selenium_kt.util.WebDriverHelper;

/**
 * Hello world!
 *
 */
public class App {
	
	public static WebDriver driver;
	public static WebDriverHelper wh;
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Hello World!");
		// searchKeyword();
		setWebDriverHelper();
		//selectDropdown();
		//webDriverWait();
		//isEnabled();
		//isSelected();
		//getCssValue();
		//getHeightWidth();
		//getLocation();
		//actions();
		//validateStoreDistance();
		//validateCertonaPriceAndATC();
		pageObjectExample();
		Thread.sleep(1000);
		tearDown();
	}

	private static void searchKeyword() throws InterruptedException {

//		Set System property for the chromeDriver path
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "Drivers/chromedriver");

//		Instantiate WebDriver for Chrome Driver
		WebDriver driver = new ChromeDriver();

//		Delete all cookies before using the browser 
		driver.manage().deleteAllCookies();

//		Call the desired URL
		driver.get("https://www.homedepot.com/");

//		Wrapper class for actions such as finding an element and performing actions
		WebDriverHelper wh = new WebDriverHelper(driver);

//		By is the locator used to identify elements 
//		By.id, By.name, By.className, By.cssSelector, By.xpath, By.linkText, By.partialLinkText, By.TagName

//         WebElement element = driver.findElement(By.id("headerSearch"));
//         element.sendKeys("hammer");
		// wh.sendKeys(By.id("headerSearch"), "lamps");

		wh.sendKeys(UIStoreNew.txtSearch, "lamps");

		// driver.findElement(By.id("headerSearchButton")).click();
		// wh.click(By.id("headerSearchButton"));
		wh.click(UIStoreNew.btnSearch);

		if (wh.isElementPresent(UIStoreNew.verifyPLP)) {
			System.out.println("PLP page displayed");
		} else {
			System.out.println("PLP page not displayed");
		}

//         try {
//         	if(driver.findElement(By.id("hd_pip")).isDisplayed()) {
//        		  System.out.println("PLP page displayed"); } 
//         } catch(NoSuchElementException nse) {
//         	// nse.printStackTrace();
//         	System.out.println("PLP page not displayed");
//         }

		Thread.sleep(1000);
		driver.quit();
	}
	
	public static void setWebDriverHelper() {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wh = new WebDriverHelper(driver);
		//driver.get("https://www.homedepot.com/");
		//driver.get("https://www.homedepot.com/s/hammer?NCNI-5");
	}
	
	public static void tearDown() {
		driver.quit();
	}
	
	public static void selectDropdown() throws InterruptedException {
		driver.get("https://www.homedepot.com/l/search/6839/full/");
		driver.findElement(By.className("accordion__item")).click();
		Select select = new Select(driver.findElement(By.id("radius")));
		//select.selectByIndex(4);
		//select.selectByValue("20");
		select.selectByVisibleText("Search Within 100 Miles");
		Thread.sleep(3000);
	}
	
	public static void webDriverWait() {
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.homedepot.com/s/hammer?NCNI-5");
		//System.out.println("Element present : "+ wh.isElementPresent(UIStoreNew.verifyPLP));
		
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(UIStoreNew.questionBody));
		
		
		System.out.println("Element present after : "+ wh.isElementPresent(UIStoreNew.questionBody));
	}
	
	public static void isEnabled() {
		System.out.println("Before entering Price, Go Button is enabled : " +driver.findElement(UIStore.btnPriceGo).isEnabled());
		wh.sendKeys(UIStore.txtLowerBound, "10");
		System.out.println("After entering Price, Go Button is enabled :" +driver.findElement(UIStore.btnPriceGo).isEnabled());
	}
	
	public static void isSelected() throws InterruptedException {
		
		Thread.sleep(5000);
		
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
	
	public static void  getCssValue() {
		
		if(driver.findElement(By.id("messageBar")).getCssValue("background-color").contains("rgba(249, 99, 2, 1)")) {
			System.out.println("Message bar is displayed in orange");
		}
		
		System.out.println(driver.findElement(By.id("allProdCount")).getCssValue("font-family"));
		//System.out.println("Backgroud color for messageBar : " + driver.findElement(By.id("messageBar")).getCssValue("background-color"));
	}
	
	public static void getHeightWidth() {
		//driver.get("https://www.homedepot.com/s/hammer?NCNI-5");
		WebElement plpPOD = driver.findElement(By.cssSelector(".plp-pod "));
		System.out.println("Height : " +plpPOD.getSize().getHeight());
		System.out.println("Width : " +plpPOD.getSize().getWidth());
	}
	
	public static void getLocation() {
		WebElement storeHeader = driver.findElement(By.className("storeinfo-header"));
		System.out.println("Y : " +storeHeader.getLocation().getX());
		System.out.println("X : " +storeHeader.getLocation().getY());		
	}
	
	public static void actions() throws InterruptedException {
		/*
		 * Actions a = new Actions(driver);
		 * a.moveToElement(driver.findElement(By.cssSelector(".sortby-section"))).click(
		 * ); a.moveToElement(driver.findElement(By.
		 * cssSelector("div[class^='sortByListGrid'] li[data-value='P_Topseller_Sort|1']"
		 * ))).click(); a.build().perform();
		 */
		
		driver.get("https://www.homedepot.com/");
		Actions a = new Actions(driver);
		//long duration = new Duration(50, TimeUnit.MILLISECONDS);
		a.moveToElement(driver.findElement(UIStore.txtSearch)).click().sendKeys("d").pause(50).sendKeys("r").pause(50).sendKeys("i").build().perform();;
		a.moveToElement(driver.findElement(By.cssSelector("a[tabindex='2']"))).click();
		a.build().perform();
		//"a[data-searchterm='drill in Drill/Drivers']"
		//a.moveToElement(driver.findElement(UIStore.txtSearch)).click().keyDown(Keys.SHIFT).sendKeys("hammer").build().perform();
		
//		driver.findElement(By.id(""))
		Thread.sleep(10000);		
	}
	
	public static void validateStoreDistance() {
		driver.get("https://www.homedepot.com/l/search/6839/full/");
		List<WebElement> storesDistn = driver.findElements(By.className("sfstoremilage"));
		double prevMile = 0.0;
		boolean sorted = true;
		for (WebElement storeDistn : storesDistn) {
			System.out.println(storeDistn.getText());
			double currentMile = Double.valueOf(storeDistn.getText().replace("mi", "").trim());
			if(currentMile >= prevMile) {
				prevMile = currentMile;
			} else  {
				sorted = false;
				break;
			}
		}
		
		if(sorted) {
			System.out.println("Stores are sorted");
		} else {
			System.out.println("Store are not sorted");
		}
		
	}
	
	public static void validateCertonaPriceAndATC() {
		driver.get("https://www.homedepot.com/");
		List<WebElement> certonaPods = driver.findElements(By.cssSelector("#hp_sponsored div[class='owl-item active']"));
		
		for (WebElement certonaPod : certonaPods) {
			System.out.println("Price is present : " +certonaPod.findElement(By.className("price")).isDisplayed());
			System.out.println("ATC is present : " +certonaPod.findElement(By.className("bttn__content")).isDisplayed());

		}
	}
	
	public static void pageObjectExample() {
		TestVO testVo = new TestVO(driver, wh);
		HomePage homePage = new HomePage(testVo);
		PLPPage plpPage = homePage.openHomePage().searchKeyword("hammer").verifyPLPPage();
		plpPage.clickFirstProduct().verifyPIPPage();
	}
	
	
}