package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPanelLeadsPage;
import pages.HomePage;
import pages.ListingDetailsPage;
import pages.LoginPage;
import utilities.PropertyManager;

public class SendMessageTest extends TestBase {

    ListingDetailsPage listingDetailsObject;
    LoginPage loginObject;
    HomePage homeObject;
    Faker faker = new Faker();
    String name = faker.name().firstName();
    String email = faker.internet().safeEmailAddress();
    String phoneNumber = "0111" + faker.phoneNumber().subscriberNumber(8);
    String username2 = PropertyManager.getInstance().getUsername2();
    String password2 = PropertyManager.getInstance().getPassword2();
    String username3 = PropertyManager.getInstance().getUsername3();
    String password3 = PropertyManager.getInstance().getPassword3();
    AdminPanelLeadsPage adminPage ;

    @Test(priority = 0)
    public void newUserCanInitiateLead() {
        System.out.println("New user try to Send Message....");
        listingDetailsObject = mockListingDetailsPage();
        adminPage = new AdminPanelLeadsPage(driver);
        openRequestCallListing();
        waitForLoad(driver);
        listingDetailsObject.openQuickRegistrationPopUpChat();
        listingDetailsObject.fillQuickRegistration(name, email, phoneNumber);
        Assert.assertTrue(listingDetailsObject.isChatModalAppear());
        clearData();
        openLoginPage();
        Assert.assertTrue(adminPage.isLeadAdded(name,phoneNumber,"2497109",driver));
        clearData();

    }

    //not logged in existing user
    @Test(priority = 1)
    public void existingUserCanInitiateLead() {
        System.out.println("Existing user try to Send Message....");
        adminPage = new AdminPanelLeadsPage(driver);
        listingDetailsObject = mockListingDetailsPage();
        openRequestCallListing();
        waitForLoad(driver);
        listingDetailsObject.openQuickRegistrationPopUpChat();
        listingDetailsObject.fillQuickRegistration(name, username2, phoneNumber);
        Assert.assertTrue(listingDetailsObject.isChatModalAppear());
        clearData();
        refresh();
        openLoginPage();
        Assert.assertTrue(adminPage.isLeadAdded(name,phoneNumber,"2497109",driver));
        clearData();
    }

    @Test(priority = 2)
    public void loggedInUserCanInitiateLeadForFirstTime() {
        System.out.println("Logged in  user try to Send Message for First Time....");
        clearData();
        listingDetailsObject = mockListingDetailsPage();
        adminPage = new AdminPanelLeadsPage(driver);
        loginObject = mockLoginPage();
        homeObject = mockHomePage();
        waitForLoad(driver);
        openLoginPage();
        loginObject.login(username2, password2);
        listingDetailsObject.closeNotificationModal();
        openRequestCallListing();
        waitForLoad(driver);
        listingDetailsObject.closeNotificationModal();
        listingDetailsObject.openQuickRegistrationPopUpChat();
        listingDetailsObject.fillQuickRegistration("mohamed.mohsen@aqarmap.com");
        Assert.assertTrue(listingDetailsObject.isChatModalAppear());
        clearData();
        refresh();
        openLoginPage();
        Assert.assertTrue(adminPage.isLeadAdded("mohamed.mohsen@aqarmap.com","100077895","2497109",driver));
        clearData();
    }

    @Test(priority = 3)
    public void loggedInUserCanInitiateLeadForSecondTime() {
        System.out.println("Logged in user try to Request a Call for second time .....");
        clearData();
        adminPage = new AdminPanelLeadsPage(driver);
        listingDetailsObject = mockListingDetailsPage();
        loginObject = mockLoginPage();
        homeObject = mockHomePage();
        openLoginPage();
        waitForLoad(driver);
        loginObject.login(username3, password3);
        openShowPhoneNumberListing();
        listingDetailsObject.closeNotificationModal();
        listingDetailsObject.openQuickRegistrationPopUpChat();
        listingDetailsObject.fillQuickRegistration(username3);
        openRequestCallListing();
        listingDetailsObject.closeNotificationModal();
        listingDetailsObject.openQuickRegistrationPopUpChat();
        Assert.assertTrue(listingDetailsObject.isChatModalAppear());
        clearData();
        openLoginPage();
        Assert.assertTrue(adminPage.isLeadAdded("md","1118608831","2497109",driver));
        clearData();
    }


    private static ListingDetailsPage mockListingDetailsPage() {
        return new ListingDetailsPage(driver);
    }

    private static LoginPage mockLoginPage() {
        return new LoginPage(driver);
    }

    private static HomePage mockHomePage() {
        return new HomePage(driver);
    }
}
