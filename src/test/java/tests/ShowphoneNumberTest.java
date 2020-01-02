package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import pages.HomePage;
import pages.ListingDetailsPage;
import pages.LoginPage;
import utilities.PropertyManager;

public class ShowphoneNumberTest extends TestBase {
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
		System.out.println("New user try to Show Phone Number....");
		listingDetailsObject = mockListingDetailsPage();
		openShowPhoneNumberListing();
		listingDetailsObject.openQuickRegistrationPopUp();
		listingDetailsObject.fillQuickRegistration(name,phoneNumber);
		Assert.assertTrue(listingDetailsObject.isPhoneNumberAppear());
		Assert.assertTrue(isMailDelivered(name));
		removeStorage();
		clearCookies();
	}
//	@Test(priority=1)
//	public void existingUserCanInitiatLead(){
//		System.out.println("Existing user try to initiate a lead....");
//		listingDetailsObject = new ListingDetailsPage(driver);
//		openListingDetailsPage();
//		listingDetailsObject.fillQuickRegistration("my name", phoneNumber);
//		removeStorage();
//		clearCookies();
//		refresh();
//	}

	@Test(priority=2)
	public void loggedInUserCanInitiatLeadFirstTime(){
		System.out.println("Logged in user try to Show Phone Number for first time ....");
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		homeObject.openLoginPage();
		loginObject.login(username2,password2);
		listingDetailsObject.closeNotificationModal();
		openShowPhoneNumberListing();
		listingDetailsObject.closeNotificationModal();
		listingDetailsObject.openQuickRegistrationPopUp();
		listingDetailsObject.submitQuickRegistrationForm();
		Assert.assertTrue(listingDetailsObject.isPhoneNumberAppear());
		Assert.assertTrue(isMailDelivered("aqarmap live"));
		removeStorage();
		clearCookies();
		refresh();
	}

	@Test(priority=3)
	public void loggedInUserCanInitiatLeadSecondtTime() {
		System.out.println("Logged in user try to Show Phone Number for second time .....");
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		homeObject.openLoginPage();
		loginObject.login(username3,password3);
		openRequestCallListing();
		listingDetailsObject.closeNotificationModal();
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		listingDetailsObject.quickRegisterationLeadButton.click();
		openShowPhoneNumberListing();
		listingDetailsObject.closeNotificationModal();
		listingDetailsObject.openQuickRegistrationPopUp();
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
