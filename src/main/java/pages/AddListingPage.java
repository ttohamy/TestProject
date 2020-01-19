package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddListingPage extends PageBase {
    public AddListingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div/div/div/div[2]")
    WebElement propertyTypeDropdown;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div/div/div/div[3]/ul/li[3]")
    WebElement chaletLi;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[2]/div/div[2]")
    WebElement propertySectionDropdown;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[2]/div/div[3]/ul/li[2]")
    WebElement forRentLi;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[3]/div/div[2]")
    WebElement viewDropdown;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[3]/div/div[3]/ul/li[1]")
    WebElement gardenLi;
    @FindBy(id = "area")
    WebElement areaText;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[2]/button")
    WebElement nextButton;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div/input")
    WebElement roomsText;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[2]/div/input")
    WebElement bathsText;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[3]/div/input")
    WebElement floorsText;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div/div/div/div[2]")
    WebElement locationDropdown;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div/div/div/div[2]/input")
    WebElement locationInput;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div/div/div/div[3]/ul/li[1]")
    WebElement firstLocationLi;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div[2]")
    WebElement subLocationDropdown;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div[3]/ul/li[1]")
    WebElement firsSubLocationLi;
    @FindBy(id = "title")
    WebElement titleTextArea;
    @FindBy(id = "descriptionText")
    WebElement descriptionTextArea;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div/div[2]")
    WebElement paymentMethodDropdown;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div/div[3]/ul/li[1]")
    WebElement cashLi;
    @FindBy(id = "price")
    WebElement priceTextArea;
    @FindBy(xpath = "/html/body/input")
    WebElement uploadPhotosElement;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[1]")
    WebElement loadingSpinner;
    @FindBy(xpath = "/html/body/div[4]/section/div/div/div[2]")
    WebElement warning;

    public void selectItemFromDropDown(WebElement dropdownLocator, WebElement dropdownItem) {
        try {
            if (!loadingSpinner.isDisplayed()) {

                wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
                click(dropdownLocator);
                wait.until(ExpectedConditions.elementToBeClickable(dropdownItem));
                click(dropdownItem);
            }
        } catch (Exception e) {
            System.out.println("Failed...");
        }
    }

    public void selectItemFromDropDown(WebElement dropdownLocator, WebElement dropdownItem, String text) {
        try {
            if (!loadingSpinner.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(locationDropdown));
                click(dropdownLocator);
                addTextToElement(locationInput, text);
                wait.until(ExpectedConditions.elementToBeClickable(dropdownItem));
                click(dropdownItem);
            }
        } catch (Exception e) {
            System.out.println("failed...");
        }
    }

    public void fillFirstPage() {
        selectItemFromDropDown(propertyTypeDropdown, chaletLi);
        selectItemFromDropDown(propertySectionDropdown, forRentLi);
        selectItemFromDropDown(viewDropdown, gardenLi);
        addTextToElement(areaText, "120");
        clickNextButton();
    }

    public void fillSecondPage() {
        wait.until(ExpectedConditions.elementToBeClickable(roomsText));
        addTextToElement(roomsText, "4");
        addTextToElement(floorsText, "5");
        addTextToElement(bathsText, "2");
        clickNextButton();
    }

    public void fillLocationsPage() {
        selectItemFromDropDown(locationDropdown, firstLocationLi, "ديرب نجم");
        selectItemFromDropDown(subLocationDropdown, firsSubLocationLi);
        clickNextButton();
    }

    public void fillTitleAndDescription() {
        wait.until(ExpectedConditions.elementToBeClickable(titleTextArea));
        addTextToElement(titleTextArea, "شقة فاخرة تشطيب رائع");
        addTextToElement(descriptionTextArea, "شقة تحتوي علي 4 غرف و عمارة فيها اسانسير");
        clickNextButton();
        clickNextButton();
    }

    public void fillPaymentMethodPage() {
        selectItemFromDropDown(paymentMethodDropdown, cashLi);
        wait.until(ExpectedConditions.textToBePresentInElement(paymentMethodDropdown,"كاش"));
        wait.until(ExpectedConditions.elementToBeClickable(priceTextArea));
        addTextToElement(priceTextArea, "120000");
        clickNextButton();
    }

    public void uploadPhotos() {
        uploadPhotosElement.sendKeys("/home/tohamy/Pictures/add listing/add1.jpg");
        clickNextButton();
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        click(nextButton);
    }

    public int getListingID(WebDriver driver) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        wait.until(ExpectedConditions.elementToBeClickable(warning));
        String resultLabel = driver.getCurrentUrl();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(resultLabel);
        int numberOfLeadsOfTheListing = 0;
        while (m.find()) {
            numberOfLeadsOfTheListing = Integer.parseInt(m.group());
        }
        return numberOfLeadsOfTheListing;
    }


}
