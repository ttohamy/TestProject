package testsKSA;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.AdminPanelLeadsPage;
import pages.HomePage;
import pages.ListingDetailsPage;
import pages.LoginPage;
import tests.AdminPanelLeadsTest;
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
		listingDetailsObject.fillQuickRegistration(name,email,phoneNumber);
		softAssert.assertTrue(listingDetailsObject.isPhoneNumberAppear(driver));
		softAssert.assertTrue(isMailDelivered(name,"test show"));
		clearData();
		openLoginPage();
		softAssert.assertTrue(adminPage.isLeadAdded("ksa",name,phoneNumber,"159202",driver));
		softAssert.assertAll();
		clearData();
	}

	@Test(priority=1)
	public void loggedInUserCanInitiatLeadFirstTime(){
		System.out.println("Logged in user try to Show Phone Number for first time ....");
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		adminPage = new AdminPanelLeadsPage(driver);
		openLoginPage();
		loginObject.login("ksa",username2,password2);
		openShowPhoneNumberListing();
		listingDetailsObject.openQuickRegistrationPopUp();
		listingDetailsObject.submitQuickRegistrationForm();
		softAssert.assertTrue(listingDetailsObject.isPhoneNumberAppear(driver));
		softAssert.assertTrue(isMailDelivered("aqarmap live","test show"));
		clearData();
		openLoginPage();
		softAssert.assertTrue(adminPage.isLeadAdded("ksa","Aqarmap Live","9661004267878","159202",driver));
		softAssert.assertAll();
		refresh();
		clearData();
	}

	@Test(priority=2)
	public void loggedInUserCanInitiatLeadSecondtTime() {
		System.out.println("Logged in user try to Show Phone Number for second time .....");
		listingDetailsObject = mockListingDetailsPage();
		loginObject = mockLoginPage();
		homeObject = mockHomePage();
		adminPage = new AdminPanelLeadsPage(driver);
		openLoginPage();
		loginObject.login("ksa",username3,password3);
		openRequestCallListing();
		listingDetailsObject.openQuickRegistrationPopUpRequestCall();
		listingDetailsObject.submitQuickRegistrationForm();
		openShowPhoneNumberListing();
		listingDetailsObject.openQuickRegistrationPopUp();
		softAssert.assertTrue(listingDetailsObject.isPhoneNumberAppear(driver));
		softAssert.assertTrue(isMailDelivered("md","test show"));
		clearData();
		openLoginPage();
		softAssert.assertTrue(adminPage.isLeadAdded("ksa","md","9661111115458","159202",driver));
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
