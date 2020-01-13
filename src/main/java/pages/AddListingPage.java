package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddListingPage extends PageBase {
    public AddListingPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div/div/div/div[2]")
    WebElement propertyTypeDropdown ;
    @FindBy(xpath = "/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/form/div[1]/div[1]/div/div/div/div[3]/ul/li[4]")
    WebElement apartmentsLi;

    public void selectPropertyType(){

        wait.until(ExpectedConditions.elementToBeClickable(propertyTypeDropdown));
        click(propertyTypeDropdown);
        wait.until(ExpectedConditions.elementToBeClickable(apartmentsLi));
        click(apartmentsLi);



    }

}
