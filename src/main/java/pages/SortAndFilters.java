package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SortAndFilters extends PageBase {
	public SortAndFilters(WebDriver driver) {
		super(driver);

	
	
	
}

	// sort options
	@FindBy(xpath = "/html/body/div[4]/div[3]/div[4]/div[1]/div[2]/div/div/div/button")
	public WebElement sortopen; //sort open drop down
	@FindBy(xpath="/html/body/div[4]/div[3]/div[4]/div[1]/div[2]/div/div/div/div/div/ul/li[4]/a")
	public WebElement hightolow; //choose from drop down high price to low
	@FindBy(xpath="/html/body/div[4]/div[3]/div[4]/div[1]/div[2]/div/div/div/div/div/ul/li[3]/a")
	public WebElement lowtohigh; //choose from drop down low price to high
	@FindBy(xpath = "/html/body/div[4]/div[3]/div[4]/div[1]/div[2]/div/div/div/div/div/ul/li[5]/a")
	public WebElement areasmall; //choose from drop down area small to large

	@FindBy(xpath = "/html/body/div[4]/div[3]/div[4]/div[1]/div[2]/div/div/div/div/div/ul/li[6]/a")
	public WebElement arealarege; //choose from drop down area large to small
	
	//Filters 
	//filters compounds only
	@FindBy(xpath = "/html/body/div[4]/div[3]/div[4]/div[2]/div[2]/div[1]/form/div[1]/div[1]/div/div/div/div/div/div[2]/label")
	public WebElement filter_comp;
	//search filters button
	@FindBy(id ="te-submit-filters")
	public WebElement fil_search;
	//open price filters
	@FindBy(id="te-prices-container")
	public WebElement openprice;
	
}
