package tests;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.EmailUtils;
import utilities.PropertyManager;

import javax.mail.Message;

public class TestBase {
	public static WebDriver driver ;
	public static EmailUtils emailUtils;
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
			driver = new ChromeDriver();
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
			driver = new ChromeDriver(option);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://aqarmap.com.eg/ar/");

	}
	@AfterClass
	public void closeDriver() {
//		driver.quit();
	}
	public void removeStorage(){
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.localStorage.clear();");
	}
	public static void refresh() {
		driver.navigate().refresh();
	}

	public static void openShowPhoneNumberListing() {
		driver.navigate().to("https://aqarmap.com.eg/ar/listing/2484708-for-rent-apartment-sharqia");
		waitForLoad(driver);
	}
	public static  void openLoginPage(){
		driver.navigate().to("https://aqarmap.com.eg/ar/login");
	}

	public static void openRequestCallListing() {
		driver.navigate().to("https://aqarmap.com.eg/ar/listing/2497109-for-rent-apartment-sharqia");
		waitForLoad(driver);
	}
	public static void openLeadsForListing() {
		driver.get("https://aqarmap.com.eg/en/admin/leads/?search=listing_id&q=2484708&start_date=&end_date=");
	}
	public void clearCookies() {
		driver.manage().deleteAllCookies();
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
	public boolean isMailDelivered(String mailContent){
		try {
			Thread.sleep(7000);
			Message msg = emailUtils.getLatestMessage();
			String msgContent = emailUtils.getMessageContent(msg).toLowerCase();
			if(msgContent.contains(mailContent.toLowerCase())) {
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

}
