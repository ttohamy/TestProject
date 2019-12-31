package pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPanelLeadsPage extends PageBase {

	public AdminPanelLeadsPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div[1]/p")
	WebElement numberOfResultLabel ; 
	
	public int getNumberOfLeads() {
		String resultLabel = numberOfResultLabel.getText();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(resultLabel);
		int numberOfLeadsOfTheListing = 0 ;
		while(m.find()) {
			numberOfLeadsOfTheListing = Integer.parseInt(m.group());
		}
		return numberOfLeadsOfTheListing;
	}
	

}
