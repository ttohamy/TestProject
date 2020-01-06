package pages ;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.PropertyManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminPanelLeadsPage extends PageBase {

	public AdminPanelLeadsPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div[1]/p")
	WebElement numberOfResultLabel ;
	@FindBy(className = "next")
	WebElement paginationNextButton ;
	WebDriver headlessDriver ;
	String adminUser = PropertyManager.getInstance().getAdminUserName();
	String adminPassword = PropertyManager.getInstance().getAdminPassword();
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



	public boolean isLeadAdded(String name , String phoneNumber, String listingId,WebDriver driver){
		LoginPage loginPage = new LoginPage(driver);
		String pattern = "yyyy-MM-dd";
		loginPage.login(adminUser,adminPassword);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String startDate = simpleDateFormat.format(new Date());
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(simpleDateFormat.parse(startDate));
			c.add(Calendar.DATE, 1);
		}catch (Exception e){
			System.out.println("issue");
		}
		String endDate = simpleDateFormat.format(c.getTime());
		driver.get("https://aqarmap.com.eg/en/admin/leads/?search=listing_id&q="
				+listingId+
				"&start_date="+startDate+"&end_date="+endDate);
		boolean leadAppears = false;
		try {

			while (paginationNextButton.isDisplayed()){
				if(driver.getPageSource().contains(name)&&driver.getPageSource().contains(phoneNumber)){
					System.out.println("Success...Lead is now on the DB");
					leadAppears =true;
					break;
				}else {
					click(paginationNextButton);
				}
			}
		}catch (Exception e){
			if(driver.getPageSource().contains(name)&&driver.getPageSource().contains(phoneNumber)){
				System.out.println("Success...Lead is now on the DB");
				leadAppears =true;
			}else{
			leadAppears = false;
			System.out.println("Failed....The leads is not added to the DB");
			}
		}
		return leadAppears;
	}
	

}
