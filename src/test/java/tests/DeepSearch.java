package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;

public class DeepSearch extends TestBase {
    HomePage hp;

    @Test(priority = 1)
    public void inputsearch_critria() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.navigate().to("https://aqarmap.com.eg/ar/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        hp = new HomePage(driver);
        hp.opentype.click();
        hp.apartments.click();
        hp.searchbar.sendKeys("مدينة نص");
        Thread.sleep(2500);
        hp.suggestloc1.click();
        hp.searchbtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
    }

    //  psdbComponent.clickDocumentLink();
//  ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
//  driver.switchTo().window(tabs2.get(1));
//  driver.close();
//  driver.switchTo().window(tabs2.get(0));
    @Test(priority = 2)
    public void opentab() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        hp = new HomePage(driver);
        hp.listing4.click();
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        assertTrue(driver.getPageSource().contains("مدينة نص"));
        driver.close();
        driver.switchTo().window(tabs2.get(0));
        assertTrue(driver.getPageSource().contains("مدينة نص"));

    }

    @Test(priority = 3)
    public void multi_shaleh() throws InterruptedException {
        driver.navigate().to("https://aqarmap.com.eg/ar/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        hp = new HomePage(driver);
        hp.opentype.click();
        hp.shalehat.click();
        hp.searchbar.sendKeys("الساحل الشمال");
        Thread.sleep(2500);
        hp.suggestloc1.click();
        hp.searchbar.sendKeys("شرم الش");
        Thread.sleep(2500);
        hp.suggestloc1.click();
        hp.searchbtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        hp.listing4.click();
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        if (driver.getPageSource().contains("الساحل الشمال") || driver.getPageSource().contains("شرم الش")) {
            System.out.println("Search success");
        } else System.out.println("ERROR ERROR ERROR ERROR ERROR ERROR");
        driver.close();
        driver.switchTo().window(tabs2.get(0));
        hp.listing7.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        if (driver.getPageSource().contains("الساحل الشمال") && driver.getPageSource().contains("شرم الش")) {
            System.out.println("Search success");
        } else System.out.println("ERROR ERROR ERROR ERROR ERROR ERROR");
        driver.switchTo().window(tabs2.get(0));
    }

    @Test(priority = 4)
    public void page2nd() {
        hp.nextsearchpage.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        hp.listing4.click();
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        if (driver.getPageSource().contains("الساحل الشمال") || driver.getPageSource().contains("شرم الش")) {
            System.out.println("Search success");
        } else System.out.println("ERROR ERROR ERROR ERROR ERROR ERROR");
        driver.close();
        driver.switchTo().window(tabs2.get(0));
        hp.listing7.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        if (driver.getPageSource().contains("الساحل الشمال") || driver.getPageSource().contains("شرم الش")) {
            System.out.println("Search success");
        } else System.out.println("ERROR ERROR ERROR ERROR ERROR ERROR");
//driver.close();
        driver.switchTo().window(tabs2.get(0));
    }

}
