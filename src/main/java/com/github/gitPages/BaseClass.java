package com.github.gitPages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents the Base Class
 * Common utils used
 * @author tmtinsi
 *
 */
public class BaseClass {
	private static Logger logger = Logger.getLogger(BaseClass.class);
	protected WebDriver driver;
	public BaseClass(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean isElementPresent(WebElement elm, By by) {
		try {
			elm.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * verify if webelement is present
	 * @param searchElement
	 * @return
	 */
	public boolean isWebElementPresent(WebElement searchElement){
		if(searchElement.getSize() != null){

			logger.info(searchElement.getText() + " Element Is Present On The Page\n");
	    	return searchElement.isDisplayed();
		}
	    else{
			logger.debug(searchElement.getText() + " Element Is Not Present On The Page\n");
		    return searchElement.isDisplayed();
	    }
	}
	
	/**
	 * getproperties from properties file
	 * @throws Exception
	 * @param property
	 */
		public static String getProperties(String property) throws FileNotFoundException, IOException{
		String getProperty = null;
		try(FileReader reader = new FileReader("tests.properties")){
			Properties properties = new Properties();
			properties.load(reader);
			getProperty = properties.getProperty(property);
		}
		
		catch(Exception e){
			logger.error("\n Failed to read the properties file error " + e);
		}
		return getProperty;
	}
	
	/**
	 * click on a weblement
	 * @param element
	 */
	public void clickOnElement(WebElement element){
		element.click();
	}
	
	/**
	 * Switch to new window opened
	 * @param driver
	 */
	public static void winHandle(WebDriver driver){
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
	}
}
