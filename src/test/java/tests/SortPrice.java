package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.SortAndFilters;

import static org.testng.Assert.assertTrue;

public class SortPrice extends TestBase {
    SortAndFilters sf;

    //open abbass el akkad search page and change sort
    @Test(priority = 1)
    public void sortclick() {
        driver.navigate().to("https://aqarmap.com.eg/en/for-sale/property-type/cairo/nasr-city/abbas-el-akkad/");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        sf = new SortAndFilters(driver);
        sf.sortopen.click();
        sf.hightolow.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        System.out.println("success searching sorted = high to low prices");

    }

    //getting 3 listing prices and then comparing. the 1st listing is i, 5th listing is i2, 7th listing is i3 then comparing them in form of assertion
    @Test(priority = 2)
    public void sorthigh() {

        String assert1 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[1]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i = Integer.parseInt(assert1);
        String assert2 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[3]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i2 = Integer.parseInt(assert2);
        String assert3 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[14]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i3 = Integer.parseInt(assert3);
        System.out.println(i + " first listing price "  + i2 + " 3rd listing price  " + i3+"  14th listing price ");
        assertTrue(i > i2, "Failure one");
        assertTrue(i2 > i3, "failure two");
        assertTrue(i > i3, "fail three");
        System.out.println("Success, first listing > 3rd,  3rd listing > 14th");
    }

    @Test(priority = 3)
    public void sortlowtohigh() {
        sf = new SortAndFilters(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        sf.sortopen.click();
        sf.lowtohigh.click();
        System.out.println("switch from high to low>> low to high");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        String assert1 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[1]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i = Integer.parseInt(assert1);
        String assert2 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[3]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i2 = Integer.parseInt(assert2);
        String assert3 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[14]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i3 = Integer.parseInt(assert3);
        System.out.println(i + " " + i2 + " " + i3);
        assertTrue(i3 > i2, "Failure one");
        assertTrue(i2 > i, "failure two");
        assertTrue(i3 > i, "fail three");
        System.out.println("first listing < 3rd, 3rd<14th Success");
    }


}
