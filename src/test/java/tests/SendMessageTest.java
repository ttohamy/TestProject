package tests;

import com.github.javafaker.Faker;
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
    String username4 = PropertyManager.getInstance().getUsername4();
    String password4 = PropertyManager.getInstance().getPassword4();
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
        softAssert.assertTrue(listingDetailsObject.isChatModalAppear());
        clearData();
        openLoginPage();
        softAssert.assertTrue(adminPage.isLeadAdded(name,phoneNumber,"2497109",driver));
        softAssert.assertAll();
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
        softAssert.assertTrue(listingDetailsObject.isChatModalAppear());
        clearData();
        refresh();
        openLoginPage();
        softAssert.assertTrue(adminPage.isLeadAdded(name,phoneNumber,"2497109",driver));
        softAssert.assertAll();
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
        loginObject.login(username4, password4);
        listingDetailsObject.closeNotificationModal();
        openRequestCallListing();
        waitForLoad(driver);
        listingDetailsObject.closeNotificationModal();
        listingDetailsObject.openQuickRegistrationPopUpChat();
        listingDetailsObject.fillQuickRegistration(username4);
        softAssert.assertTrue(listingDetailsObject.isChatModalAppear());
        clearData();
        refresh();
        openLoginPage();
        softAssert.assertTrue(adminPage.isLeadAdded("Tohamy","126606622","2497109",driver));
        softAssert.assertAll();
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
        listingDetailsObject.openQuickRegistrationPopUpChat();
        softAssert.assertTrue(listingDetailsObject.isChatModalAppear());
        clearData();
        openLoginPage();
        softAssert.assertTrue(adminPage.isLeadAdded("md","1118608831","2497109",driver));
        softAssert.assertAll();
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
