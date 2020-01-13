package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ListingDetailsPage extends PageBase {

	public ListingDetailsPage(WebDriver driver) {
		super(driver);
	}


	@FindBy(id = "te-phone-number")
	WebElement showPhoneNumberButton ;
	@FindBy(id = "lead_name")
	WebElement quickRegisterationUserNameTextBox ;
	@FindBy(id = "lead_phone")
	WebElement quickRegisterationNumebrTextBox;
	@FindBy(id="lead_email")
	WebElement quickRegisterationEmailTextBox;
	@FindBy(id = "quickLeadButton")
	public WebElement quickRegisterationLeadButton ;
	@FindBy(xpath = "//*[@id=\"notification-modal\"]/div/div/div[3]/input[2]")
	WebElement notificationModalCloseButton;
	@FindBy(id = "te-call-request")
	WebElement requestCallButton ;
	@FindBy(xpath = "/html/body/div[9]/div[1]/div[1]/div/section/div[2]/div[2]/div[1]/div[1]/div[1]/div/a")
	WebElement numberAppears;
	@FindBy(xpath="//*[@id=\"listingContactWrapper\"]/div[1]/div[1]/div[1]/div")
	WebElement alertSuccessMsgRequestCall;
	@FindBy(id= "te-open-chat")
	WebElement openSendMsgButton;
	@FindBy(id="te-chat-textarea")
	WebElement chatModalTextArea ;
	@FindBy(id="search_btn")
	WebElement searchButton;

	public void fillQuickRegistration(String userName , String phoneNumber){
		wait.until(ExpectedConditions.visibilityOf(quickRegisterationUserNameTextBox));
		addTextToElement(quickRegisterationUserNameTextBox, userName);
		addTextToElement(quickRegisterationNumebrTextBox, phoneNumber);
		submitQuickRegistrationForm();
	}
	public void fillQuickRegistration(String userName ,  String email ,String phoneNumber ){
		wait.until(ExpectedConditions.visibilityOf(quickRegisterationUserNameTextBox));
		addTextToElement(quickRegisterationUserNameTextBox, userName);
		addTextToElement(quickRegisterationEmailTextBox,email);
		addTextToElement(quickRegisterationNumebrTextBox, phoneNumber);
		submitQuickRegistrationForm();
	}
	public void fillQuickRegistration(String email ){
		wait.until(ExpectedConditions.visibilityOf(quickRegisterationUserNameTextBox));
		addTextToElement(quickRegisterationEmailTextBox,email);
		submitQuickRegistrationForm();
	}

	public void submitQuickRegistrationForm(){
		wait.until(ExpectedConditions.elementToBeClickable(quickRegisterationLeadButton));
		click(quickRegisterationLeadButton);
	}
	public void openQuickRegistrationPopUp()  {
		
		wait.until(ExpectedConditions.elementToBeClickable(showPhoneNumberButton));
		click(showPhoneNumberButton);
	}
	public void openQuickRegistrationPopUpRequestCall(){

		wait.until(ExpectedConditions.elementToBeClickable(requestCallButton));
		click(requestCallButton);
	}
	public void openQuickRegistrationPopUpChat(){
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		wait.until(ExpectedConditions.elementToBeClickable(openSendMsgButton));
		click(openSendMsgButton);
	}

	public boolean isPhoneNumberAppear(WebDriver driver){
		if(driver.getPageSource().contains("01283122213")) {
			System.out.println("Success....Phone number appears");
			return true;
		}
		else {
			System.out.println("Failed....No Phone number appears");
			return false;
		}
	}
	public boolean isRequestSent(WebDriver driver){
		if(driver.getPageSource().contains("سيتواصل معك المعلن في أقرب")){
			System.out.println("Success...the request sent");
			return true;
		}else{
			System.out.println("Failed...the request not sent");
			return false;
		}
	}
	public boolean isChatModalAppear(){
		wait.until(ExpectedConditions.elementToBeClickable(chatModalTextArea));
		if(chatModalTextArea.isDisplayed()) {
			System.out.println("Success...Chat modal appears");
			return true;
		}
		else{
			System.out.println("Success...Chat modal not appear");
			return false;
		}
	}
	public void closeNotificationModal() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(notificationModalCloseButton));
			notificationModalCloseButton.click();
			wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		}catch (Exception e){
			System.out.println("No notification modal");
		}
	}

	public void waitUntilAlertAppears(){

		try {
			wait.until(ExpectedConditions.alertIsPresent());
		}catch (Exception e){
			System.out.println("No Alerts..."+e.getMessage());
		}

	}




}
