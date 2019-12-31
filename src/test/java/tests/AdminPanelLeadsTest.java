package tests;


import pages.AdminPanelLeadsPage;
import pages.HomePage;
import pages.LoginPage;

public class AdminPanelLeadsTest extends TestBase {
	LoginPage loginObject ; 
	HomePage homeObject ; 
	AdminPanelLeadsPage adminObject ; 

	public int numberOfLeads() throws InterruptedException {
		adminObject = new AdminPanelLeadsPage(driver);
		loginObject = new LoginPage(driver);
		homeObject = new HomePage(driver); 
		homeObject.openLoginPage();
		Thread.sleep(2000);
		loginObject.login("mohamed.eltohamy@aqarmap.com", "2468123");
		Thread.sleep(2000);
		openLeadsForListing();
		Thread.sleep(3000);
		int leadsNumber = adminObject.getNumberOfLeads();
		driver.manage().deleteAllCookies();
		return leadsNumber ;
	}

}
