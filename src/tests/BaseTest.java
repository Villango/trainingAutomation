package tests;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.BasePage;

public class BaseTest {
	private WebDriver driver;
	private String baseUrl;
	private BasePage base;
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Starting Execution");
	}
	
	@BeforeClass
	public void beforeClass(){
		System.out.println("Executing LogInTest Class");
	}
	
	@BeforeMethod
	// Setting Up everything we will use.
	public void setUp() throws Exception {
		System.out.println("Creating Driver");
		// New instance of a driver
		driver = this.createLocalDriver("chrome");
		// baseURL given to test
		baseUrl = "http://192.168.0.103:86/";
		// Set the timeout time
		driver.get(baseUrl + "/");
		
		this.base = new BasePage(driver);
		base.implicitWait();
	}
	
	public WebDriver createLocalDriver(String browserType){
		WebDriver localDriver = null;
		
		if("firefox" == browserType){
			localDriver = new FirefoxDriver();
		}else if("chrome" == browserType){
			localDriver = new ChromeDriver();
		}
		
		return localDriver;
	}
	
	public WebDriver createRemoteDriver(String browserType){
		WebDriver remoteDriver = null;
		
		return remoteDriver;
	}
	
	
	@Test
	public void testSuccessfulLogin() throws Exception {
		String pageTitle="";
		String userName = "testuser1";
		// Click the Login link
		base.clickOnElement("ctl00_LoginView_LoginLink");
		
		// Clear and send valid text to the UserName Field
		base.typeOnElement("ctl00_Main_LoginConrol_UserName", userName);
		
		// Clear and send valid text to the Password field.
		base.typeOnElement("ctl00_Main_LoginConrol_Password", "test123#");
		
		// Click the login button
		base.clickOnElement("ctl00_Main_LoginConrol_LoginButton");		
		
		pageTitle = driver.getTitle();

		// Verification.
		assertTrue("Titles didn't match.", pageTitle.equals("Welcome"));
		assertTrue("User is not logged", driver.findElement(By.id("ctl00_LoginView_MemberName")).getText().contains(userName));
		
	}
	
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("Deleting Browser");
		driver.quit();
		//Delete the driver
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("Completing execution of LogInTest Class");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("Completing Execution");
	}
	
}
