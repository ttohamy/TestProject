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
	@FindBy(name = "_submit")
	WebElement loginButton  ;
	public void login(String username , String password) {
		wait.until(ExpectedConditions.elementToBeClickable(usernameTextBox));
		addTextToElement(usernameTextBox,username);
		addTextToElement(passwordTextBox, password);
		submitLogin();
		try {
			wait.until(ExpectedConditions.urlToBe("https://aqarmap.com.eg/ar/"));
		}catch (Exception e){
			System.out.println("Failed...i can't log in");
		}
	}
	public void submitLogin(){
		clickEnter();
	}
	

}
