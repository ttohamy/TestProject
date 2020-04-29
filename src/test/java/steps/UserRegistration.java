package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.RegistrationPage;
import tests.TestBase;

public class UserRegistration extends TestBase {

    RegistrationPage registerObject;
    HomePage homePage ;

    @Given("the user is in the home page")
    public void the_user_is_in_the_home_page() {
        homePage = new HomePage(driver);
        registerObject = new RegistrationPage(driver);
        homePage.openRegistrationPage();
    }
    @When("I click on register button")
    public void i_click_on_register_button() {
        Assert.assertTrue(driver.getCurrentUrl().contains("register"));
    }
    @When("I entered the user data")
    public void i_entered_the_user_data() {
        registerObject.register();
    }

    @Then("The registration page displayed successfully")
    public void the_registration_page_displayed_successfully() {
        Assert.assertTrue(driver.getCurrentUrl().contains("aqarmap"));
    }
}
