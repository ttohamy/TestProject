package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageBase {
	protected WebDriver driver ;
	WebDriverWait wait ;

	public PageBase(WebDriver driver) {
		PageFactory.initElements(driver ,this);
		wait = new WebDriverWait(driver, 30);

	}
	protected void click(WebElement element) {
		element.click();
	}
	protected void addTextToElement(WebElement element , String value) {
		element.sendKeys(value);
	}

	

}
