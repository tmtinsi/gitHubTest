package com.github.gitPages;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
	
	@FindBy(xpath = "//*[@id='content']/div/div/div/div[2]/table")
	protected WebElement elementTable;
	
	public WebElement findWebElementOnTable(String text) {
		if (isElementPresent(elementTable, By.tagName("tbody"))) {
			WebElement tableBody = elementTable.findElement(By.tagName("tbody"));
			if (isElementPresent(tableBody, By.tagName("tr"))) {
				List<WebElement> listOfRows = tableBody.findElements(By.tagName("tr"));
				for (int i = listOfRows.size() - 1; i >= 0; i--) {
					try {
						if (listOfRows.get(i).findElement(By.linkText("edit")).getText().equals(text)) {
							logger.info("FOUND TEXT " + text + "On ROW " + listOfRows.get(i));
							List<WebElement> td = listOfRows.get(i).findElements(By.tagName("td"));
							return listOfRows.get(i);
						}
					}
					catch (Exception e) {
						logger.info("NO Webelements on row " + e.getMessage());
					}
				}
			}
		}
		return null;
		}
	
	public void checkLinkText(String text){
		String url = null;
		WebElement tableBody = elementTable.findElement(By.tagName("tbody"));
		List<WebElement> listOfRows = tableBody.findElements(By.tagName("tr"));
		for (int i = listOfRows.size() - 1; i > 0; i--) {
			String editXpath = "//*[@id='content']/div/div/div/div[2]/table/tbody/tr["+ i + "]/td[7]/a[1]";
			if(driver.findElement(By.xpath(editXpath)).isDisplayed()){
				driver.findElement(By.xpath(editXpath)).click();
				logger.info("Checking " + text +" TEXT on ROW " + i);
				url = driver.getCurrentUrl();
				logger.info("Response URL  = " + url);
				Assert.assertEquals("https://the-internet.herokuapp.com/challenging_dom#edit",url);
			}
		}		
	}
		
		
	
	public boolean isCanvasPresent(){
		return canvasContext.isDisplayed();
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
