package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SortAndFilters extends PageBase {
	public SortAndFilters(WebDriver driver) {
		super(driver);

	
	
	
}

	
	@FindBy(xpath = "/html/body/div[4]/div[3]/div[4]/div[1]/div[2]/div/div/div/button")
	public WebElement sortopen;
	@FindBy(xpath="/html/body/div[4]/div[3]/div[4]/div[1]/div[2]/div/div/div/div/div/ul/li[4]/a")
	public WebElement hightolow;
	@FindBy(xpath="/html/body/div[4]/div[3]/div[4]/div[1]/div[2]/div/div/div/div/div/ul/li[3]/a")
	public WebElement lowtohigh;
	@FindBy(xpath = "/html/body/div[4]/div[3]/div[4]/div[1]/div[2]/div/div/div/div/div/ul/li[5]/a")
	public WebElement areasmall;

	@FindBy(xpath = "/html/body/div[4]/div[3]/div[4]/div[1]/div[2]/div/div/div/div/div/ul/li[6]/a")
	public WebElement arealarege;
	
	
}
