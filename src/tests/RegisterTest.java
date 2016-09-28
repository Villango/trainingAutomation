package tests;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.BasePage;

public class RegisterTest extends BaseTest{
	private String baseUrl;
	private BasePage base;
	// private boolean acceptNextAlert = true;
	
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
	public void testRegisterPage() throws Exception {
		//Testing comment
		String pageTitle = "";

		// Click the Register link
		
		assertTrue("Element not found.", base.clickOnElement("ctl00_LoginView_RegisterLink"));
		
		pageTitle = driver.getTitle();

		// Verification.
		assertTrue("Titles didn't match.", pageTitle.equals("Register"));
		assertTrue("FirstName field is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName"))
						.isDisplayed());
		assertTrue("LastName field is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName"))
						.isDisplayed());
		assertTrue("Email field is not displayed", driver
				.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email")).isDisplayed());
		assertTrue("UserName field is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName"))
						.isDisplayed());
		assertTrue("Password field is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password"))
						.isDisplayed());
		assertTrue("ConfirmPassword field is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword"))
						.isDisplayed());
		assertTrue("SecurityQuestion field is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question"))
						.isDisplayed());
		assertTrue("SecurityAnswer field is not displayed", driver
				.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Answer")).isDisplayed());
		assertTrue("Submit button is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton"))
						.isDisplayed());
	}

	@Test
	public void testRegisterFailMessages() throws Exception {
		
		// Click the Register link
		base.clickOnElement("ctl00_LoginView_RegisterLink");
		base.waitPresenceElement("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton");
		base.clickOnElement("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton");

		// Verification.
		assertTrue("FirstNameRequired message is not displayed",
				driver.findElement(
						By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstNameRequired")).getText()
						.contains("First name is required."));
		assertTrue("LastNameRequired message is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastNameRequired"))
						.getText().contains("Last name is required."));
		assertTrue("EmailRequired message is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_EmailRequired"))
						.getText().contains("Email is required."));
		assertTrue("UserNameRequired message is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserNameRequired"))
						.getText().contains("User name is required."));
		assertTrue("PasswordRequired message is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordRequired"))
						.getText().contains("Password is required."));
		assertTrue("ConfirmPasswordRequired message is not displayed",
				driver.findElement(
						By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPasswordRequired"))
						.getText().contains("Confirm password is required."));
		assertTrue("QuestionRequired message is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_QuestionRequired"))
						.getText().contains("Security question is required."));
		assertTrue("AnswerRequired message is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_AnswerRequired"))
						.getText().contains("Security answer is required."));
	}

	@Test
	public void testFailedRegistration() throws Exception {

		String alreadyAnUser = "User name already exists. Please enter a different user name";

		// Click the Register link
		base.clickOnElement("ctl00_LoginView_RegisterLink");
		base.waitPresenceElement("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton");
		// Send Data
		
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName", "Tracy");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName", "Villafuerte");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email", "rh1@avantica.net");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName", "tvillafuerte");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password", "FfVv456#");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword", "FfVv456#");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question", "Color favorito");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Answer", "azul");
		
		base.clickOnElement("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton");
		
		// Verification.
		assertTrue("Message is not displayed",
				driver.findElement(By.id("ctl00_Main_InfoLabel")).getText().contains(alreadyAnUser));

	}
	
	@Test
	public void testPasswordsNotMatching() throws Exception {

		String confirmationPass = "The password and confirmation password must match.";

		// Click the Register link
		
		base.clickOnElement("ctl00_LoginView_RegisterLink");
		base.waitPresenceElement("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton");
		
		// Send Data

		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName", "Tracy");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName", "Villafuerte");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email", "rh1@avantica.net");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName", "tvillafuerte");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password", "FfVv456#");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword", "FfVv456");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question", "Color favorito");
		base.typeOnElement("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Answer", "azul");
		
		base.clickOnElement("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton");
		
		// Verification.
		assertTrue("Message is not displayed",
				driver.findElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordCompare")).getText().contains(confirmationPass));

	}

}
