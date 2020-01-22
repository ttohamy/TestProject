package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SortAndFilters;

import java.util.Random;

public class Filters_price extends TestBase {
    SortAndFilters sf;

    Random rand = new Random();
    int x = 3 + rand.nextInt(3);
    int z = x + rand.nextInt(3);
    int c = z + rand.nextInt(3);

    @Test(priority = 1)
    public void f() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        sf = new SortAndFilters(driver);
        driver.navigate().to("https://aqarmap.com.eg/ar/for-sale/property-type/cairo/nasr-city/");
        System.out.println("set price range min and max");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        sf.openprice.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/div[2]/div[1]/form/div[1]/div[2]/div/div/div/div/div/div[1]/div/button")));
        sf.min.click();
        sf.minPrice_400.click();
        sf.max.click();
        sf.maxprice_900.click();
        sf.fil_search.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        System.out.println("price is now set to 400k min and 900k max, SUCCESS");
    }

    @Test(priority = 2)
    public void compareresults() {
    	System.out.println("check 3 listings if they are within the price range");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        String assert1 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[" + x + "]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i = Integer.parseInt(assert1);
        String assert2 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[" + z + "]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i2 = Integer.parseInt(assert2);
        String assert3 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[" + c + "]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i3 = Integer.parseInt(assert3);
        System.out.println(i);
        Assert.assertTrue(i > 400000, "assert 1. not lower than the min price");
        Assert.assertTrue(i < 1000000, "assert 1. not higher than the max price");
        Assert.assertTrue(i2 > 400000, "assert 2. not lower than the min price");
        Assert.assertTrue(i2 < 1000000, "assert 2. not higher than the max price");
        Assert.assertTrue(i3 > 400000, "assert 3. not lower than the min price");
        Assert.assertTrue(i3 < 1000000, "assert 3. not higher than the max price");
        System.out.println("Success all the 3 listings are within the min and max");
        driver.navigate().to("https://aqarmap.com.eg/ar/for-sale/property-type/cairo/nasr-city/?minPrice=2000000&maxPrice=3000000&location=34%2C30%2C3&propertyType=83&section=1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        System.out.println("max 3m price and min 2m.");
    }

    @Test(priority = 3)
    public void compare2() {
    	System.out.println("testing if the 3 listings are within the range");
        String assert1 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[" + x + "]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i = Integer.parseInt(assert1);
        String assert2 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[" + z + "]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i2 = Integer.parseInt(assert2);
        String assert3 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[" + c + "]/div/div/div[2]/div/a/div[2]/span[1]")).getAttribute("content");
        int i3 = Integer.parseInt(assert3);
        System.out.println(i);
        Assert.assertTrue(i > 2000000, "assert 1. not lower than the min price");
        Assert.assertTrue(i < 3000000, "assert 1. not higher than the max price");
        Assert.assertTrue(i2 > 2000000, "assert 2. not lower than the min price");
        Assert.assertTrue(i2 < 3000000, "assert 2. not higher than the max price");
        Assert.assertTrue(i3 > 2000000, "assert 3. not lower than the min price");
        Assert.assertTrue(i3 < 3000000, "assert 3. not higher than the max price");
        System.out.println("success, all listings are within the price range");
    }

}
