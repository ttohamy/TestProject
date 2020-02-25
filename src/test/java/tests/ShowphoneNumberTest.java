package tests;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
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
		softAssert.assertTrue(listingDetailsObject.isPhoneNumberAppear(driver));
		softAssert.assertTrue(isMailDelivered(name));
		clearData();
		openLoginPage();
		softAssert.assertTrue(adminPage.isLeadAdded(name,phoneNumber,"2484708",driver));
		softAssert.assertAll();
		clearData();
	}

	@Test(priority=1)
	public void loggedInUserCanInitiatLeadFirstTime() throws InterruptedException {
		System.out.println("Logged in user try to Show Phone Number for first time ....");
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		adminPage = new AdminPanelLeadsPage(driver);
		openLoginPage();
		loginObject.login(username2,password2);
		openShowPhoneNumberListing();
		Thread.sleep(2000);
		listingDetailsObject.closeNotificationModal();
		listingDetailsObject.openQuickRegistrationPopUp();
		listingDetailsObject.submitQuickRegistrationForm();
		softAssert.assertTrue(listingDetailsObject.isPhoneNumberAppear(driver));
		softAssert.assertTrue(isMailDelivered("aqarmap live"));
		clearData();
		openLoginPage();
		softAssert.assertTrue(adminPage.isLeadAdded("Aqarmap Live","100077895","2484708",driver));
		softAssert.assertAll();
		refresh();
		clearData();
	}

	@Test(priority=2)
	public void loggedInUserCanInitiatLeadSecondtTime() throws InterruptedException {
		System.out.println("Logged in user try to Show Phone Number for second time .....");
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		adminPage = new AdminPanelLeadsPage(driver);
		openLoginPage();
		loginObject.login(username3,password3);
		openRequestCallListing();
		listingDetailsObject.closeNotificationModal();
		refresh();
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		listingDetailsObject.submitQuickRegistrationForm();
		openShowPhoneNumberListing();
		listingDetailsObject.openQuickRegistrationPopUp();
		softAssert.assertTrue(listingDetailsObject.isPhoneNumberAppear(driver));
		softAssert.assertTrue(isMailDelivered("md"));
		clearData();
		openLoginPage();
		softAssert.assertTrue(adminPage.isLeadAdded("md","1118608831","2484708",driver));
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
