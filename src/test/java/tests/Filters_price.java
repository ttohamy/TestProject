package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.SortAndFilters;

public class Filters_price extends TestBase {
	SortAndFilters sf;
  @Test(priority = 1)
  public void f() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
sf= new SortAndFilters(driver);
	  driver.navigate().to("https://aqarmap.com.eg/ar/for-sale/property-type/cairo/nasr-city/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));		
sf.openprice.click();

  }
}
