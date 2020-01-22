package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.SortAndFilters;

import static org.testng.Assert.assertTrue;

public class SortSize extends TestBase {
    SortAndFilters sf;

    //open abbass el akkad search page and change sort
    @Test(priority = 1)
    public void sortclick() {
        driver.navigate().to("https://aqarmap.com.eg/en/for-sale/property-type/cairo/nasr-city/abbas-el-akkad/");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
        sf = new SortAndFilters(driver);
        sf.sortopen.click();
        sf.arealarege.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));
    }

    //getting 3 listing prices and then comparing. the 1st listing is i, 5th listing is i2, 7th listing is i3 then comparing them in form of assertion
    @Test(priority = 2)
    public void sorthigh() {
        String assert1 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[1]/div/div/div[2]/div/a/div[1]	")).getText();
        System.out.println(assert1 + " first listing details ");
        String[] outputtest = assert1.split(" M²");
        int i = Integer.parseInt(outputtest[0]);
        System.out.println(i);
        String assert2 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[6]/div/div/div[2]/div/a/div[1]")).getText();
        System.out.println(assert2 + "  3rd listing details ");
        String[] outputtest2 = assert2.split(" M²");
        int i2 = Integer.parseInt(outputtest2[0]);
        System.out.println(i2);
        String assert3 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[14]/div/div/div[2]/div/a/div[1]")).getText();
        System.out.println(assert3 + "  14th listing details ");
        String[] outputtest3 = assert3.split(" M²");
        int i3 = Integer.parseInt(outputtest3[0]);
        System.out.println(i3);
        assertTrue(i > i2, "failure size 1");
        assertTrue(i2 > i3, "failure size 2");
        assertTrue(i > i3, "failure size 3");
    }

    // open sort and then switch it from large to small > small to large
    @Test(priority = 3)
    public void sortchange() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        sf = new SortAndFilters(driver);
        sf.sortopen.click();
        sf.areasmall.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_btn")));

    }

    //assertions comparisons
    @Test(priority = 4)

    public void sortsmall() {
        String assert1 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[1]/div/div/div[2]/div/a/div[1]	")).getText();
        System.out.println(assert1 + " first listing details ");
        String[] outputtest = assert1.split(" M²");
        int i = Integer.parseInt(outputtest[0]);
        System.out.println(i);
        String assert2 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[6]/div/div/div[2]/div/a/div[1]")).getText();
        System.out.println(assert2 + " 3rd listing details ");
        String[] outputtest2 = assert2.split(" M²");
        int i2 = Integer.parseInt(outputtest2[0]);
        System.out.println(i2);
        String assert3 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/div[2]/section/div/div[1]/ul/div[14]/div/div/div[2]/div/a/div[1]")).getText();
        System.out.println(assert3 + " 14th listing details");
        String[] outputtest3 = assert3.split(" M²");
        int i3 = Integer.parseInt(outputtest3[0]);
        System.out.println(i3);
        assertTrue(i < i2, "failure size 1");
        assertTrue(i2 < i3, "failure size 2");
        assertTrue(i < i3, "failure size 3");
    }


}
