package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListingDetailsPage extends PageBase {

	public ListingDetailsPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver,20);
	}

	WebDriverWait wait ;


	@FindBy(xpath = "/html/body/div[9]/div[1]/div[1]/div/section/div[2]/div[2]/div[1]/div[1]/div[1]/a")
	WebElement showPhoneNumberButton ;
	@FindBy(id = "lead_name")
	WebElement quickRegisterationUserNameTextBox ;
	@FindBy(id = "lead_phone")
	WebElement quickRegisterationNumebrTextBox;
	@FindBy(id = "quickLeadButton")
	public WebElement quickRegisterationLeadButton ;
	@FindBy(xpath = "//*[@id=\"notification-modal\"]/div/div/div[1]/button")
	WebElement notificationModalCloseButton;
	@FindBy(xpath = "/html/body/div[4]/div/div/div[3]/input[2]")
	WebElement notificationFrame ;
	@FindBy(xpath = "//*[@id=\"listingContactWrapper\"]/div[1]/div[1]/div[1]/form/button")
	WebElement requestCallButton ;
	@FindBy(xpath = "/html/body/div[9]/div[1]/div[1]/div/section/div[2]/div[2]/div[1]/div[1]/div[1]/div/a")
	WebElement numberAppears;
	@FindBy(linkText="سيتواصل معك المعلن في أقرب وقت.")
	WebElement alertSuccessMsgRequestCall;

	public void fillQuickRegistration(String userName , String phoneNumber){
		wait.until(ExpectedConditions.visibilityOf(quickRegisterationUserNameTextBox));
		addTextToElement(quickRegisterationUserNameTextBox, userName);
		addTextToElement(quickRegisterationNumebrTextBox, phoneNumber);
		submitQuickRegistrationForm();
	}
	public void submitQuickRegistrationForm(){
		wait.until(ExpectedConditions.elementToBeClickable(quickRegisterationLeadButton));
		click(quickRegisterationLeadButton);
	}
	public void openQuickRegistrationPopUp()  {
		wait.until(ExpectedConditions.visibilityOf(showPhoneNumberButton));
		click(showPhoneNumberButton);
	}
	public void openQuickRegistrationPopUpRequestCall(){
		wait.until(ExpectedConditions.elementToBeClickable(requestCallButton));
		click(requestCallButton);
	}

	public boolean isPhoneNumberAppear(){
		wait.until(ExpectedConditions.elementToBeClickable(numberAppears));
		if(numberAppears.isDisplayed()) {
			System.out.println("Success....Phone number appears");
			return true;
		}
		else {
			System.out.println("Failed....No Phone number appears");
			return false;
		}
	}
	public void closeNotificationModal() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(notificationModalCloseButton));
			notificationModalCloseButton.click();
		}catch (Exception e){
			System.out.println("No notification modal");
		}
	}
	public boolean isRequestSent(){
		wait.until(ExpectedConditions.visibilityOf(alertSuccessMsgRequestCall));
		if(alertSuccessMsgRequestCall.getText().contains("سيتواصل معك المعلن في أقرب وقت.")){
			System.out.println("Success...the request sent");
			return true;
		}else{
			System.out.println("Failed...the request not sent");
			return false;
		}
	}
	public void waitUnilAlertAppears(){

		try {
			wait.until(ExpectedConditions.alertIsPresent());
		}catch (Exception e){
			System.out.println("No Alerts..."+e.getMessage());
		}

	}




}
