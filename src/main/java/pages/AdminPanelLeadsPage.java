package pages ;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import utilities.PropertyManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminPanelLeadsPage extends PageBase {

	public AdminPanelLeadsPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div[1]/p")
	WebElement numberOfResultLabel ;
	@FindBy(xpath = "/html/body/div[2]/div/div[2]/div[2]/span[8]/a")
	WebElement paginationNextButton ;
	WebDriver headlessDriver ;
	String adminUser = PropertyManager.getInstance().getUsername1();
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
	public boolean isLeadAdded(String name , String phoneNumber, String listingId){
		System.out.println("Headless is perfect");
		ChromeOptions option = new  ChromeOptions();
		option.addArguments("--headless");
		option.addArguments("window-size=2000,3000");
		headlessDriver = new ChromeDriver(option);
		LoginPage loginPage = new LoginPage(headlessDriver);
		String pattern = "yyyy-MM-dd";
//		loginPage.login();

		boolean leadAppears = false;
		try {
			while (paginationNextButton.isDisplayed()){
				if(headlessDriver.getPageSource().contains(name)&&headlessDriver.getPageSource().contains(phoneNumber)){
					leadAppears =true;
					break;
				}
					click(paginationNextButton);
			}
		}catch (Exception e){
			System.out.println("not found..."+e.getMessage());
			leadAppears = false;
		}
		return leadAppears;
	}
	

}
