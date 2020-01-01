package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ListingDetailsPage;
import pages.LoginPage;
import tests.AdminPanelLeadsTest;
import tests.TestBase;
import utilities.PropertyManager;

public class RequestCallTest extends TestBase {
	ListingDetailsPage listingDetailsObject ; 
	LoginPage loginObject;
	HomePage homeObject ;


	AdminPanelLeadsTest leadsTest ;
	Faker faker = new Faker();
	String  name = "mohamed mahroos";
	String email = faker.internet().safeEmailAddress();
	String phoneNumber = "0111"+faker.phoneNumber().subscriberNumber(8);
	String username2 = PropertyManager.getInstance().getUsername2();
	String password2 = PropertyManager.getInstance().getPassword2();
	String username3 = PropertyManager.getInstance().getUsername3();
	String password3 = PropertyManager.getInstance().getPassword3();


	@Test(priority=0)
	public void newUserCanInitiatLead()  {
		System.out.println("New user try to initiate a lead ....");
		listingDetailsObject = mockListingDetailsPage();
		openRequestCallListing();
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		listingDetailsObject.fillQuickRegistration(name,phoneNumber);
		listingDetailsObject.waitUntilAlertAppears();
		driver.switchTo().alert().accept();
		Assert.assertTrue(listingDetailsObject.isRequestSent(driver));
		Assert.assertTrue(isMailDelivered(name));
		removeStorage();
		clearCookies();
	}
	@Test(priority=2)
	public void loggedInUserCanInitiatLeadFirstTime(){
		System.out.println("Logged in user try to initiate a lead for first time ....");
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		homeObject.openLoginPage();
		loginObject.login(username2,password2);
		listingDetailsObject.closeNotificationModal();
		openRequestCallListing();
		listingDetailsObject.closeNotificationModal();
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		listingDetailsObject.submitQuickRegistrationForm();
		boolean requestStatus = listingDetailsObject.isRequestSent(driver);
		Assert.assertTrue(requestStatus);
		Assert.assertTrue(isMailDelivered("aqarmap live"));
		removeStorage();
		clearCookies();
		refresh();
	}

	@Test(priority=3)
	public void loggedInUserCanInitiatLeadSecondtTime() {
		System.out.println("Logged in user try to initiate a lead for second time .....");
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		homeObject.openLoginPage();
		loginObject.login(username3,password3);
		openShowPhoneNumberListing();
		listingDetailsObject.closeNotificationModal();
		listingDetailsObject.openQuickRegistrationPopUp();
		listingDetailsObject.quickRegisterationLeadButton.click();
		openRequestCallListing();
		listingDetailsObject.closeNotificationModal();
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		Assert.assertTrue(isMailDelivered("md"));
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
