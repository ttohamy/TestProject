package testsKSA;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.PropertyManager;

public class LoginTest extends TestBase {
	LoginPage loginObject ;
	HomePage homeObject ; 
	String username = PropertyManager.getInstance().getUsername1();
	String password = PropertyManager.getInstance().getPassword1();
	String homePageUrl = "https://aqarmap.com.eg/ar/";
	@Test
	public void userCanLoginSuccessfully() throws InterruptedException {
		homeObject = new HomePage(driver);
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.login("ksa",username, password);
		Assert.assertEquals(driver.getCurrentUrl(), homePageUrl);
	}

}
