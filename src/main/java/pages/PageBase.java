package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import sun.awt.X11.XKeyEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PageBase {
	protected WebDriver driver ; 
	
	public PageBase(WebDriver driver) {
		PageFactory.initElements(driver ,this);
	}
	protected static void click(WebElement element) {
		element.click();
	}
	protected static void addTextToElement(WebElement element , String value) {
		element.sendKeys(value);
	}
	public void clickEnter(){
		Robot rb = null;
		try {
			rb = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

	}
	

}
