package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddListingPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.PropertyManager;

public class AddListingTest extends TestBase {
    HomePage homePage;
    AddListingPage addListingPage;
    LoginPage loginPage ;
    String username = PropertyManager.getInstance().getUsername3();
    String password = PropertyManager.getInstance().getPassword3();
    String adminUsername = PropertyManager.getInstance().getAdminUserName();
    String adminPassword = PropertyManager.getInstance().getAdminPassword();
    @Test
    public void userCanAddListing(){
        homePage = new HomePage(driver);
        addListingPage = new AddListingPage(driver);
        loginPage = new LoginPage(driver);
        openLoginPage();
        loginPage.login(username,password);
        openAddListingPage();
        waitForLoad(driver);
        addListingPage.fillFirstPage();
        addListingPage.fillSecondPage();
        addListingPage.fillLocationsPage();
        addListingPage.fillTitleAndDescription();
        addListingPage.fillPaymentMethodPage();
        addListingPage.uploadPhotos();
        String listingID = Integer.toString(addListingPage.getListingID(driver));
        System.out.println(listingID);
        openLoginPage();
        loginPage.login(adminUsername,adminPassword);
        openReviewPage(listingID);
        Assert.assertTrue(driver.getPageSource().contains("Status: Pending Payment"));
        Assert.assertTrue(driver.getPageSource().contains("For Rent"));
        Assert.assertTrue(driver.getPageSource().contains("Chalet"));
        Assert.assertTrue(driver.getPageSource().contains("Abo Eid"));
        System.out.println("Success...You listing Status is Pending Payment");
    }
}
