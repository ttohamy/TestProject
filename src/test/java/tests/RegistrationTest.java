package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

public class RegistrationTest extends TestBase {
    HomePage homePage;
    RegistrationPage registrationPage ;
    String confirmationURL ;
    @Test
    public void userCanRegisterSuccessfully(){
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage.openRegistrationPage();
        registrationPage.register();
        softAssert.assertEquals(driver.getCurrentUrl(), confirmationURL);
    }
}
