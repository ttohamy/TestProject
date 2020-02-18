package testsKSA;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.AdminPanelLeadsPage;
import pages.HomePage;
import pages.ListingDetailsPage;
import pages.LoginPage;
import tests.AdminPanelLeadsTest;
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
		listingDetailsObject.fillQuickRegistration(name,email,phoneNumber);
		listingDetailsObject.waitUntilAlertAppears();
		driver.switchTo().alert().accept();
		softAssert.assertTrue(listingDetailsObject.isRequestSent(driver));
		softAssert.assertTrue(isMailDelivered(name,"test request"));
		clearData();
		openLoginPage();
		softAssert.assertTrue(adminPage.isLeadAdded("ksa",name,phoneNumber,"159203",driver));
		softAssert.assertAll();
		clearData();
	}

	@Test(priority=1)
	public void loggedInUserCanInitiatLeadFirstTime(){
		System.out.println("Logged in user try to Request a Call for first time ....");
		clearData();
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		adminPage = new AdminPanelLeadsPage(driver);
		openLoginPage();
		loginObject.login("ksa",username4,password4);
		openRequestCallListing();
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		listingDetailsObject.submitQuickRegistrationForm();
		boolean requestStatus = listingDetailsObject.isRequestSent(driver);
		softAssert.assertTrue(requestStatus);
		softAssert.assertTrue(isMailDelivered("tohamy","test request"));
		clearData();
		refresh();
		clearData();
		openLoginPage();
		softAssert.assertTrue(adminPage.isLeadAdded("ksa","tohamy","9661266066222","159203",driver));
		softAssert.assertAll();
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
		loginObject.login("ksa",username5,password5);
		openShowPhoneNumberListing();
		listingDetailsObject.openQuickRegistrationPopUp();
		listingDetailsObject.submitQuickRegistrationForm();
		openRequestCallListing();
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		boolean requestStatus = listingDetailsObject.isRequestSent(driver);
		softAssert.assertTrue(requestStatus);
		softAssert.assertTrue(isMailDelivered("ahmed nagy","test request"));
		clearData();
		openLoginPage();
		softAssert.assertTrue(adminPage.isLeadAdded("ksa","ahmed nagy","9661061679853","159203",driver));
		softAssert.assertAll();
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
