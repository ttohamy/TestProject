package testsKSA;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.EmailUtils;
import utilities.PropertyManager;

import javax.mail.Message;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBase {
	public static WebDriver driver ;
	public static EmailUtils emailUtils;
	public SoftAssert softAssert ;
	@BeforeClass
	protected void getSoftAssert(){
		softAssert = new SoftAssert();
	}
	@BeforeClass
	public static void connectToEmail() {
		try {
			String username = PropertyManager.getInstance().getUsername1();
			String password = PropertyManager.getInstance().getPassword1();
			emailUtils = new EmailUtils(username, password, "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	@BeforeClass
	@Parameters({"browser"})
	public void startDriver(@Optional("chrome") String browserName) {
		if(System.getProperty("os.name").toLowerCase().contains("windows"))
		{
		  	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
		}
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			driver = new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if(browserName.equalsIgnoreCase("mobileView")){
			Map<String, String> mobileEmulation = new HashMap<>();
			mobileEmulation.put("deviceName", "Nexus 5");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			driver = new ChromeDriver(chromeOptions);
		}
		else if(browserName.equalsIgnoreCase("chrome-headless")) {
			System.out.println("Headless is perfect");
			ChromeOptions option = new  ChromeOptions();
			option.addArguments("--headless");
			option.addArguments("window-size=2000,3000");
			option.setPageLoadStrategy(PageLoadStrategy.NONE);
			driver = new ChromeDriver(option);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("http://ksa.aqarmap.com/ar/");

	}
	@AfterClass
	public void closeDriver() {
		driver.quit(); //
	}
	public void removeStorage(){
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.localStorage.clear();");
	}
	public static void refresh() {
		driver.navigate().refresh();
	}

	public static void openShowPhoneNumberListing() {
		driver.navigate().to("http://ksa.aqarmap.com/ar/listing/159202-for-rent-other-arar");
		waitForLoad(driver);
	}
	public static  void openLoginPage(){
		driver.navigate().to("http://ksa.aqarmap.com/ar/login");
	}

	public static void openRequestCallListing() {
		driver.navigate().to("http://ksa.aqarmap.com/ar/listing/159203-for-rent-other-arar");
		waitForLoad(driver);
	}

	public static void openLeadsForListing() {
		driver.get("https://aqarmap.com.eg/en/admin/leads/?search=listing_id&q=2484708&start_date=&end_date=");
	}
	public void clearCookies() {
		driver.manage().deleteAllCookies();
	}
	public void clearData(){
		clearCookies();
		removeStorage();
	}
	public static void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
					}
				};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}
	public boolean isMailDelivered(String leadCreator , String listingTitle){
		try {
			Thread.sleep(7000);
			Message msg = emailUtils.getLatestMessage();
			String msgContent = emailUtils.getMessageContent(msg).toLowerCase();
			if(msgContent.contains(leadCreator.toLowerCase())&&msgContent.contains(listingTitle.toLowerCase())) {
				System.out.println("success.....Mail sent");
				return true;
			}else{
				System.out.println("Failed.....Mail Not Delivered");
				System.out.println("Mail Subject is : "+msgContent);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public void openAddListingPage(){
		driver.get("https://aqarmap.com.eg/ar/listing/initialize/init");
	}
	public void openReviewPage(String listingID){
		driver.get("https://aqarmap.com.eg/en/admin/listing/"+listingID+"/review/");
	}
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String currentDir = System.getProperty("user.dir")+"/screenshots/" ;
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(currentDir+ testResult.getName() + "-"+ ".jpg"));
		}
	}
}
