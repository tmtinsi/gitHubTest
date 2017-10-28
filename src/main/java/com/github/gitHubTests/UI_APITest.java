package com.github.gitHubTests;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.github.gitPages.BaseClass;
import com.github.gitPages.HomePage;
import com.github.gitPages.ChallengingDomPage;

/**
 * Represents gitHUB UI and APItests Test Cases
 * @author tmtinsi
 */
public class UI_APITest {
	private static Logger logger = Logger.getLogger(UI_APITest.class);
	WebDriver driver;
	ChallengingDomPage challengingDomPage = null;
	HomePage homePage = null;
	String winHandleBefore = null;
	String url = null;
	String baseUrl = null;
	String repo = null;
	
	List<String> possibleButtonText = Arrays.asList("qux", "baz", "foo", "bar"); 
		
	/**
	 * setup run before tests have run
	 * setup and configuration before tests
	 * @author tmtinsi
	 */
	@BeforeClass
	public void classSetup(){
	}
	@AfterClass
	public void classTearDown(){
	}

	@BeforeTest
	public void setup() throws IOException{	
		try {
			baseUrl = BaseClass.getProperties("tests.BASEURL");
			logger.debug("setup() \n Accessed baseUrl from config " + baseUrl);
		} catch (FileNotFoundException e) {
			logger.debug("\n Failed to get url from config " + e);
			e.printStackTrace();

		}
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1240, 640));
		winHandleBefore = driver.getWindowHandle();
	}
	
	/**
	 * Teardown run after tests have run
	 * clean up after tests
	 * @author tmtinsi
	 */
	@AfterTest
	public void tearDown(){
	    driver.close();
	}
	
	/**
	 * @testsummary - Demonstrate GUI testing by Listing all issues by user
	 * 				Editing the issue and closing the issue@ http://www.github.com
	 * @testCase -Test Case 1 
	 * @testSteps
	 * 	 -1 launch https://the-internet.herokuapp.com homepage
	 *   -2 Append the path /challenging_dom
	 *   -3 Verify you are on the right page by checking for the webelement text of Challenging DOM
	 *    
	 * @testlink  n/a
	 * @testconfiguration n/a
	 * @testprerequisites n/a
	 * @author-cmeja	 
	 * 
	 * @throws Exception
	 */

	@Test(priority=1, groups = { "ui" })
	public void testChallengingDOM() throws Exception{
		logger.info("Start of Challenging DOM"); 
		String path =  "/challenging_dom";
		challengingDomPage = challengingDomPage.navigateTo(driver,baseUrl,path);
		homePage = HomePage.getHomePage(driver);
		
		//verify page title of challengingDomPage present
		Assert.assertTrue(challengingDomPage.getElementChallengingDomText().contains("Challenging DOM"));
	}
	
	/**
	 * Verify the blue button exists
	 * click on the blue button
	 * verify blue, red and green button text is contained in the possible text list
	 * @throws Exception
	 */
	@Test(priority=1, groups = { "ui" })
	public void testBlueButtonPresence() throws Exception{
		logger.info("Start of Challenging DOM"); 
		String path =  "/challenging_dom";
		challengingDomPage = challengingDomPage.navigateTo(driver,baseUrl,path);
		homePage = HomePage.getHomePage(driver);
		
		Assert.assertTrue(challengingDomPage.isBlueButtonPresent());	
		challengingDomPage.clickBlueButton();
		String blueButtonText = challengingDomPage.getBlueButtonText();
		String redButtonText = challengingDomPage.getRedButtonText();
		String greenButtonText = challengingDomPage.getGreenButtonText();
		
		logger.info("BLUE BUTTON TEXT ----> " + blueButtonText);
		logger.info("RED BUTTON TEXT ----> " + redButtonText);
		logger.info("RED BUTTON TEXT ----> " + greenButtonText);
		
		Assert.assertTrue(possibleButtonText.contains(blueButtonText));
		Assert.assertTrue(possibleButtonText.contains(redButtonText));
		Assert.assertTrue(possibleButtonText.contains(greenButtonText));
	}
	
	@Test(priority=1, groups = { "ui" })
	public void testRedButtonPresence() throws Exception{
		logger.info("Start of Challenging DOM"); 
		String path =  "/challenging_dom";
		challengingDomPage = challengingDomPage.navigateTo(driver,baseUrl,path);
		homePage = HomePage.getHomePage(driver);
		
		Assert.assertTrue(challengingDomPage.isRedButtonPresent());	
		challengingDomPage.clickRedButton();
		String blueButtonText = challengingDomPage.getBlueButtonText();
		String redButtonText = challengingDomPage.getRedButtonText();
		String greenButtonText = challengingDomPage.getGreenButtonText();
		
		logger.info("BLUE BUTTON TEXT ----> " + blueButtonText);
		logger.info("RED BUTTON TEXT ----> " + redButtonText);
		logger.info("RED BUTTON TEXT ----> " + greenButtonText);
		
		Assert.assertTrue(possibleButtonText.contains(blueButtonText));
		Assert.assertTrue(possibleButtonText.contains(redButtonText));
		Assert.assertTrue(possibleButtonText.contains(greenButtonText));
	}
	
	@Test(priority=1, groups = { "ui" })
	public void testGreenButtonPresence() throws Exception{
		logger.info("Start of Challenging DOM"); 
		String path =  "/challenging_dom";
		challengingDomPage = challengingDomPage.navigateTo(driver,baseUrl,path);
		homePage = HomePage.getHomePage(driver);
		

		Assert.assertTrue(challengingDomPage.isGreenButtonPresent());	
		challengingDomPage.clickGreenButton();
		String blueButtonText = challengingDomPage.getBlueButtonText();
		String redButtonText = challengingDomPage.getRedButtonText();
		String greenButtonText = challengingDomPage.getGreenButtonText();
		
		logger.info("BLUE BUTTON TEXT ----> " + blueButtonText);
		logger.info("RED BUTTON TEXT ----> " + redButtonText);
		logger.info("RED BUTTON TEXT ----> " + greenButtonText);
		
		String script = "return arguments[0].innerHTML";
		logger.info("SCRIPT   " + script);
		logger.info("CANVAS TEXT -----> "+ challengingDomPage.getcanvasContextText());
		Assert.assertTrue(possibleButtonText.contains(blueButtonText));
		Assert.assertTrue(possibleButtonText.contains(redButtonText));
		Assert.assertTrue(possibleButtonText.contains(greenButtonText));
	}

}
