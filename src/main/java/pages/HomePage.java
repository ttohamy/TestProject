package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
//open type list
@FindBy(xpath="//*[@id=\"search-box\"]/div[1]/div[2]/div[1]/div[1]/div/button")
public WebElement opentype;
//aparts 
@FindBy(xpath="/html/body/div[4]/div[3]/div/div/div/div/div/form[1]/div[1]/div[2]/div[1]/div[1]/div/div/div/ul/li[2]/a")
public WebElement apartments;
//open first listing
@FindBy(id ="te-listing-card-4")
public WebElement listing4;
@FindBy(xpath="/html/body/div[4]/div[4]/div[7]/div/div/div/div[1]/button")
public WebElement closesug;
@FindBy(xpath="/html/body/div[4]/div[3]/div/div/div/div/div/form[1]/div[1]/div[2]/div[1]/div[1]/div/div/div/ul/li[4]/a")
public WebElement shalehat;

@FindBy(id ="te-listing-card-7")
public WebElement listing7;
@FindBy(id="te-next-search-result-page")
public WebElement nextsearchpage;
}

