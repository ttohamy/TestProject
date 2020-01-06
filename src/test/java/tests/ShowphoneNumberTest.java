package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import pages.AdminPanelLeadsPage;
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
	AdminPanelLeadsPage adminPage ;

	@Test(priority=0)
	public void newUserCanInitiatLead()  {
		System.out.println("New user try to Show Phone Number....");
		listingDetailsObject = mockListingDetailsPage();
		adminPage = new AdminPanelLeadsPage(driver);
		openShowPhoneNumberListing();
		listingDetailsObject.openQuickRegistrationPopUp();
		listingDetailsObject.fillQuickRegistration(name,phoneNumber);
		Assert.assertTrue(listingDetailsObject.isPhoneNumberAppear(driver));
		Assert.assertTrue(isMailDelivered(name));
		clearData();
//		openLoginPage();
//		Assert.assertTrue(adminPage.isLeadAdded(name,phoneNumber,"2484708",driver));
		clearData();
	}

	@Test(priority=1)
	public void loggedInUserCanInitiatLeadFirstTime(){
		System.out.println("Logged in user try to Show Phone Number for first time ....");
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		openLoginPage();
		loginObject.login(username2,password2);
		openShowPhoneNumberListing();
		listingDetailsObject.closeNotificationModal();
		listingDetailsObject.openQuickRegistrationPopUp();
		listingDetailsObject.submitQuickRegistrationForm();
		Assert.assertTrue(listingDetailsObject.isPhoneNumberAppear(driver));
		Assert.assertTrue(isMailDelivered("aqarmap live"));
		clearData();
		refresh();
	}

	@Test(priority=2)
	public void loggedInUserCanInitiatLeadSecondtTime() {
		System.out.println("Logged in user try to Show Phone Number for second time .....");
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		openLoginPage();
		loginObject.login(username3,password3);
		openRequestCallListing();
		listingDetailsObject.closeNotificationModal();
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		listingDetailsObject.submitQuickRegistrationForm();
		openShowPhoneNumberListing();
		listingDetailsObject.openQuickRegistrationPopUp();
		Assert.assertTrue(listingDetailsObject.isPhoneNumberAppear(driver));
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
