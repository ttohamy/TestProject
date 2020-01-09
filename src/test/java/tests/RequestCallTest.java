package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPanelLeadsPage;
import pages.HomePage;
import pages.ListingDetailsPage;
import pages.LoginPage;
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
	String username4 = PropertyManager.getInstance().getUsername4();
	String password4 = PropertyManager.getInstance().getPassword4();
	String username5 = PropertyManager.getInstance().getUsername5();
	String password5 = PropertyManager.getInstance().getPassword5();
	AdminPanelLeadsPage adminPage ;

	@Test(priority=0)
	public void newUserCanInitiatLead()  {
		System.out.println("New user try to Request a Call ....");
		listingDetailsObject = mockListingDetailsPage();
		openRequestCallListing();
		adminPage = new AdminPanelLeadsPage(driver);
		waitForLoad(driver);
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		listingDetailsObject.fillQuickRegistration(name,phoneNumber);
		listingDetailsObject.waitUntilAlertAppears();
		driver.switchTo().alert().accept();
		Assert.assertTrue(listingDetailsObject.isRequestSent(driver));
		Assert.assertTrue(isMailDelivered(name));
		clearData();
		openLoginPage();
		Assert.assertTrue(adminPage.isLeadAdded(name,phoneNumber,"2497109",driver));
		clearData();

	}

	@Test(priority=1)
	public void loggedInUserCanInitiatLeadFirstTime(){
		System.out.println("Logged in user try to Request a Call for first time ....");
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		adminPage = new AdminPanelLeadsPage(driver);
		openLoginPage();
		loginObject.login(username4,password4);
		listingDetailsObject.closeNotificationModal();
		openRequestCallListing();
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		listingDetailsObject.submitQuickRegistrationForm();
		boolean requestStatus = listingDetailsObject.isRequestSent(driver);
		Assert.assertTrue(requestStatus);
		Assert.assertTrue(isMailDelivered("tohamy"));
		clearData();
		refresh();
		clearData();
		openLoginPage();
		Assert.assertTrue(adminPage.isLeadAdded("Tohamy","126606622","2497109",driver));
		clearData();
	}

	@Test(priority=2)
	public void loggedInUserCanInitiatLeadSecondtTime() {
		System.out.println("Logged in user try to Request a Call for second time .....");
		removeStorage();
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		adminPage = new AdminPanelLeadsPage(driver);
		openLoginPage();
		loginObject.login(username5,password5);
		openShowPhoneNumberListing();
		listingDetailsObject.closeNotificationModal();
		listingDetailsObject.openQuickRegistrationPopUp();
		listingDetailsObject.submitQuickRegistrationForm();
		openRequestCallListing();
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		boolean requestStatus = listingDetailsObject.isRequestSent(driver);
		Assert.assertTrue(requestStatus);
		Assert.assertTrue(isMailDelivered("testing"));
		clearData();
		openLoginPage();
		Assert.assertTrue(adminPage.isLeadAdded("Testing","1250552003","2497109",driver));
		clearData();
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
