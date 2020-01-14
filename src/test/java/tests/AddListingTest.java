package tests;

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
    }
}
