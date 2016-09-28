package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {
	protected WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Starting Execution");
	}
	
	@BeforeClass
	public void beforeClass(){
		System.out.println("Executing LogInTest Class");
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
	
	@BeforeMethod
	public abstract void setUp() throws Exception;
	
	public WebDriver createRemoteDriver(String browserType){
		WebDriver remoteDriver = null;
		
		return remoteDriver;
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
