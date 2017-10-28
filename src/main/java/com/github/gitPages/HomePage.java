package com.github.gitPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
/**
 * Represents Hopemage and activities on Homepagen
 * @author tmtinsi
 */
public class HomePage extends BaseClass{	
	/**
	 * HomePage constructor
	 * @param driver
	 * @author tmtinsi
	 */
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * ReturnsHomePage
	 * @param driver
	 * @return homepage
	 * @author tmtinsi
	 */
	public  static HomePage getHomePage(WebDriver driver) throws Exception{
	    return PageFactory.initElements(driver,
	    		HomePage.class);	
	}
	
	/**
	 * verify Issues element present on a page
	 * @return
	 */
	public boolean isElementPresent(String issues){	
		 return driver.findElement(By.linkText(issues)).isDisplayed();
	 }
	
	public String isTextPresent(String textToFind, WebElement element) {
		return element.getText();
	}

}
