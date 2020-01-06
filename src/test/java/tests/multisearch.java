package tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.HomePage;

public class multisearch extends TestBase{
HomePage hp;
	
	//multi search
	@Test(priority = 1)
	  public void multisearch() throws InterruptedException {
		hp = new HomePage(driver);
		driver.navigate().to("https://aqarmap.com.eg/en/");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		hp = new HomePage(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));		
hp.searchbar.sendKeys("abu hama");
Thread.sleep(2000);
hp.suggestloc1.click();
hp.searchbar.sendKeys("derb negm");
Thread.sleep(2000);
hp.suggestloc1.click();
hp.searchbtn.click();
assertTrue(driver.getPageSource().contains("abu"));
assertTrue(driver.getPageSource().contains("derb"));

}
}