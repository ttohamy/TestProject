package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SortAndFilters;

import java.util.Random;

public class Filters_Compounds extends TestBase {
    SortAndFilters sf;

    //go to link and activate compounds only filters
    @Test(priority = 1)
    public void checksortcompounds() throws InterruptedException {
        sf = new SortAndFilters(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.navigate().to("https://aqarmap.com.eg/en/for-sale/property-type/cairo/nasr-city/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        Thread.sleep(2500);
        sf.filter_comp.click();

        sf.fil_search.click();
        String url = driver.getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));

        Assert.assertTrue(url.contains("unitOnly=1"), "sort activated");


    }

    //assert that 3 random listings on the page are  compunds
    @Test(priority = 2)
    public void assertcompounds() {
        Random rand = new Random();
        int x = 1 + rand.nextInt(3);
        int z = x + rand.nextInt(3);
        int c = z + rand.nextInt(3);
        //random 3 integers to check 3 random listings


        System.out.println(x + "rd listing");
        System.out.println(z + "th listing");
        System.out.println(c + "th listing");

        String assert1 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[" + x + "]/div/div/a/div/div[2]/div/label")).getText();
        System.out.println(assert1);
        String assert2 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[" + z + "]/div/div/a/div/div[2]/div/label")).getText();
        System.out.println(assert2);
        String assert3 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[" + c + "]/div/div/a/div/div[2]/div/label")).getText();
        System.out.println(assert3);
        Assert.assertEquals(assert1 = "Compound", "Compound");
        Assert.assertEquals(assert2 = "Compound", "Compound");
        Assert.assertEquals(assert3 = "Compound", "Compound");


    }
}
