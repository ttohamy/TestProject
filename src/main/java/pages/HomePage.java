package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends PageBase  {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	@FindBy(id = "te-login-button")
	WebElement loginButton ;
	
	public void openLoginPage() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		click(loginButton);
	}
	
	@FindBy(xpath="//*[@id=\"search-box\"]/div[1]/div[2]/div[3]/span/span[1]/span/ul/li/input")
	public WebElement searchbar;
	@FindBy(id= "search_btn")
public WebElement searchbtn;
	//suggested search results
	@FindBy(xpath="/html/body/div[4]/div[3]/div/div/div/div/div/form[1]/div[1]/div[2]/div[3]/span[2]/span/span/ul/li")
public WebElement suggestloc1;
	

public void suggestsearches() {
	
	WebElement suggest = driver.findElement(By.id("select2-search_location-results"));
	Select option = new Select(suggest);
	option.selectByVisibleText("Abbas El Akkad");
	
}

}
