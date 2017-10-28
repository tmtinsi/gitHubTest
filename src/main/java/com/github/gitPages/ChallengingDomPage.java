package com.github.gitPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * represents the SignInPage and its activities
 * @author tmtinsi
 */
public class ChallengingDomPage extends BaseClass{
	private static Logger logger = Logger.getLogger(ChallengingDomPage.class);
	public ChallengingDomPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[@id='content']/div/h3")
	private WebElement elementChallengingDom;
	
	@FindBy(className = "button")
	private WebElement elementBlueButton;
	
	@FindBy(css = ".button.alert")
	private WebElement elementRedButton;
	
	@FindBy(css = ".button.success")
	private WebElement elementGreenButton;
	
	//@FindBy(xpath = "//*[@id='content']/script/text()")
	@FindBy(id = "canvas")
	private WebElement canvasContext;
	
	public String getcanvasContextText(){
		return canvasContext.getText();
	}
	
	public boolean isBlueButtonPresent(){
		return elementBlueButton.isDisplayed();
		
	}
	
	public boolean isRedButtonPresent(){
		return elementRedButton.isDisplayed();
		
	}
	
	public boolean isGreenButtonPresent(){
		return elementGreenButton.isDisplayed();
		
	}
	
	public String getBlueButtonText(){
		return elementBlueButton.getText();
		
	}
	
	public String getRedButtonText(){
		return elementRedButton.getText();
		
	}
	
	public String getGreenButtonText(){
		return elementGreenButton.getText();
		
	}
	public String getElementChallengingDomText(){
		return elementChallengingDom.getText();	
	}
	/**
	 * get the SignInPage
	 * @param driver, baseUrl, issue
	 * @return
	 */
	public static ChallengingDomPage navigateTo(WebDriver driver, String baseUrl,String path) throws Exception{	
		logger.info("Navigating  to login page"); 
		driver.get(baseUrl + path);
	    return PageFactory.initElements(driver,
	    		ChallengingDomPage.class);	
	}

	public void clickBlueButton() {
		elementBlueButton.click();
	}
	
	public void clickRedButton() {
		elementRedButton.click();
	}
	
	public void clickGreenButton() {
		elementGreenButton.click();
	}
}
