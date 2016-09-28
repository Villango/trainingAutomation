package tests;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.BasePage;

public class LoginTest extends BaseTest{
	private String baseUrl;
	private BasePage base;
	
	@BeforeMethod
	// Setting Up everything we will use.
	public void setUp() throws Exception {
		System.out.println("Creating Driver");
		// New instance of a driver
		driver = this.createLocalDriver("firefox");
		// baseURL given to test
		baseUrl = "http://192.168.0.103:86/";
		// Set the timeout time
		driver.get(baseUrl + "/");
		
		this.base = new BasePage(driver);
		base.implicitWait();
	}

	// Running the test. This time tried to make it fail as a loginFailed,
	// because I don't know how to verify text with Selenium IDE.
	@Test
	public void testLoginPage() throws Exception {
		
		String pageTitle= "";
		
		// Click the Login link
		
		base.clickOnElement("ctl00_LoginView_LoginLink");
		pageTitle = driver.getTitle();
		// Verification.
		assertTrue("Titles didn't match.", pageTitle.equals("Login"));
		assertTrue("UserName field is not displayed", driver.findElement(By.id("ctl00_Main_LoginConrol_UserName")).isDisplayed());
		assertTrue("Password field is not displayed", driver.findElement(By.id("ctl00_Main_LoginConrol_Password")).isDisplayed());
		assertTrue("Login Button is not displayed", driver.findElement(By.id("ctl00_Main_LoginConrol_LoginButton")).isDisplayed());
	}

	// Running the test, making it fail.
	@Test
	public void testLoginFailed() throws Exception {
		String pageTitle = "";
		// Clear and send valid text to the UserName Field
		
		// Click the Login link
		base.clickOnElement("ctl00_LoginView_LoginLink");
		
		base.typeOnElement("ctl00_Main_LoginConrol_UserName", "testuser1");
		
		// Clear and send invalid text to the Password field.
		base.typeOnElement("ctl00_Main_LoginConrol_Password", "1111");
		
		// Click the Login button
		base.clickOnElement("ctl00_Main_LoginConrol_LoginButton");
		
		pageTitle = driver.getTitle();

		// Verification.
		assertTrue("Titles didn't match.", pageTitle.equals("Login"));
		assertTrue("LoginFailedMessage is not displayed",
				driver.findElement(By
						.xpath("//tbody/tr/td[contains(text(),'Your login attempt was not successful. Please try again.')]"))
						.isDisplayed());
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
}
