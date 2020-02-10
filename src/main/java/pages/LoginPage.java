package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver) {
		super(driver);

	}
	@FindBy(id = "username")
	WebElement usernameTextBox ; 
	@FindBy(name = "password")
	WebElement passwordTextBox ; 
	@FindBy(xpath = "/html/body/div[4]/section/div/div/div[2]/form/div/button")
	WebElement loginButton  ;
	@FindBy(xpath="/html/body/div[4]/section/div/div/div[2]/form/input[3]")
	WebElement passwordTextBoxKsa;
	@FindBy(xpath = "/html/body/div[4]/section/div/div/div[2]/form/button")
	WebElement loginButtonKsa;
	public void login(String username , String password) {
		wait.until(ExpectedConditions.elementToBeClickable(usernameTextBox));
		addTextToElement(usernameTextBox,username);
		wait.until(ExpectedConditions.elementToBeClickable(passwordTextBox));
		addTextToElement(passwordTextBox, password);
		submitLogin();
		try {
			wait.until(ExpectedConditions.urlToBe("https://aqarmap.com.eg/ar/"));
			System.out.println("Success...Now the user is logged in");
		}catch (Exception e){
			System.out.println("Failed...i can't log in");
		}
	}
	public void login(String local , String username , String password) {
		wait.until(ExpectedConditions.elementToBeClickable(usernameTextBox));
		addTextToElement(usernameTextBox,username);
		if(local.equalsIgnoreCase("ksa")){
		wait.until(ExpectedConditions.visibilityOf(passwordTextBoxKsa));
		addTextToElement(passwordTextBoxKsa, password);
		click(loginButtonKsa);
		}
		try {
			wait.until(ExpectedConditions.urlToBe("https://ksa.aqarmap.com/ar/"));
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
