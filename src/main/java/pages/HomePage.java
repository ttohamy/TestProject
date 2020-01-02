package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends PageBase  {

	public HomePage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver,20);
	}
	WebDriverWait wait ;
	@FindBy(id = "te-login-button")
	WebElement loginButton ;
	
	public void openLoginPage() {
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		click(loginButton);
	}

}
