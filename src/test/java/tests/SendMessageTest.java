package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ListingDetailsPage;
import pages.LoginPage;

public class SendMessageTest extends TestBase {

    ListingDetailsPage listingDetailsObject ;
    LoginPage loginObject;
    HomePage homeObject ;
    Faker faker = new Faker();
    String  name = faker.name().firstName();
    String email = faker.internet().safeEmailAddress();
    String phoneNumber = "0111"+faker.phoneNumber().subscriberNumber(8);
    @Test
    public void newUserCanInitiateLead(){
        System.out.println("New user try to Send Message....");
        listingDetailsObject = mockListingDetailsPage();
        openRequestCallListing();
        listingDetailsObject.openQuickRegistrationPopUpChat();
        listingDetailsObject.fillQuickRegistration(name,email,phoneNumber);
        Assert.assertTrue(listingDetailsObject.isChatModalAppear());
        removeStorage();
        clearCookies();
    }



    private static ListingDetailsPage mockListingDetailsPage(){
        return  new ListingDetailsPage(driver);
    }
    private static LoginPage mockLoginPage(){
        return  new LoginPage(driver);
    }
    private static HomePage mockHomePage(){
        return  new HomePage(driver);
    }
}
