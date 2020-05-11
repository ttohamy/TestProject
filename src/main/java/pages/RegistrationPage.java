package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends PageBase {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    Faker faker = new Faker();
    String username = faker.name().firstName();
    String email = faker.internet().safeEmailAddress();
    String password = "123456";
    String phoneNumber = "0111"+faker.phoneNumber().subscriberNumber(8);
    @FindBy(id="fos_user_registration_form_fullName")
    WebElement usernameText ;
    @FindBy(id = "fos_user_registration_form_phoneNumber")
    WebElement phoneNumberText;
    @FindBy(id="fos_user_registration_form_email")
    WebElement emailText ;
    @FindBy(id= "fos_user_registration_form_plainPassword_first")
    WebElement passwordText ;
    @FindBy(id="fos_user_registration_form_plainPassword_second")
    WebElement confirmPasswordText ;
    @FindBy(xpath="/html/body/div[4]/section/div/div/div[2]/form/button")
    WebElement nextButton ;
    @FindBy(xpath="(//div[@class='alert alert-success alert-dismissable'])[2]")
    WebElement successMessage;
    public void register(){
        wait.until(ExpectedConditions.elementToBeClickable(usernameText));
        addTextToElement(usernameText,username);
        addTextToElement(phoneNumberText,phoneNumber);
        addTextToElement(emailText,email);
        addTextToElement(passwordText,password);
        addTextToElement(confirmPasswordText,password);
        click(nextButton);
        wait.until(ExpectedConditions.urlToBe("https://aqarmap.com.eg/ar/register/confirmed"));
        if(successMessage.isDisplayed())
            System.out.println("Success...The user registered successfully");
        else
            System.out.println("Failed...The user doesn't registered");
        System.out.println(successMessage.getText());
    }
    public void register(String username , String phoneNumber , String email , String password){
        wait.until(ExpectedConditions.elementToBeClickable(usernameText));
        addTextToElement(usernameText,username);
        addTextToElement(phoneNumberText,phoneNumber);
        addTextToElement(emailText,email);
        addTextToElement(passwordText,password);
        addTextToElement(confirmPasswordText,password);
        click(nextButton);
        wait.until(ExpectedConditions.urlToBe("https://aqarmap.com.eg/ar/register/confirmed"));
        if(successMessage.isDisplayed())
            System.out.println("Success...The user registered successfully");
        else
            System.out.println("Failed...The user doesn't registered");
        System.out.println(successMessage.getText());
    }

}
