package tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.HomePage;

public class search extends TestBase{
 HomePage hp;
	//single search assert on results as well assert on recent searches
	@Test(priority = 1)
  public void search() throws InterruptedException {
		driver.navigate().to("https://aqarmap.com.eg/en/");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		hp = new HomePage(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));		
hp.searchbar.sendKeys("Abbas El Akka");
Thread.sleep(2000);
hp.suggestloc1.click();
//select2-search_location-results
hp.searchbtn.click();
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("te-listing-card-2")));
String assert1 =  driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[2]/div/div/div[2]/h2/a/span")).getText();
System.out.println(assert1);
assertTrue(assert1.contains("Abbas"));
  }
	//test recent searches that it got triggered
	@Test(priority = 2)
	public void recentsearch(){
		hp = new HomePage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.navigate().to("https://aqarmap.com.eg/en/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
		hp.searchbar.click();
		assertTrue(driver.getPageSource().contains("Abbas"));
	}
	
	
	
}
