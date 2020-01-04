package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 20);

	}
	WebDriverWait wait ;
	@FindBy(id = "username")
	WebElement usernameTextBox ; 
	@FindBy(name = "password")
	WebElement passwordTextBox ; 
	@FindBy(xpath = "//*[@id=\"vueApp\"]/section/div/div/div[2]/form/div/button")
	WebElement loginButton  ;
	public void login(String username , String password) {
		wait.until(ExpectedConditions.elementToBeClickable(usernameTextBox));
		addTextToElement(usernameTextBox,username);
		addTextToElement(passwordTextBox, password);
		submitLogin();
		try {
			wait.until(ExpectedConditions.urlToBe("https://aqarmap.com.eg/ar/"));
			System.out.println("Success...Now the user is logged in");
		}catch (Exception e){
			System.out.println("Failed...i can't log in");
		}
	}
	public void submitLogin(){
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		click(loginButton);
//		clickEnter();
	}
	

}
