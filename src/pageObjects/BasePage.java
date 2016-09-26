package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected  WebDriver driver;
	
	public BasePage(WebDriver driver){
		this.driver = driver;
	}
	
	public void clickOnElement(String locator){
		driver.findElement(By.id(locator)).click();
	}
	public void typeOnElement(String locator, String text){
		driver.findElement(By.id(locator)).clear();
		driver.findElement(By.id(locator)).sendKeys(text);
	}
	public void implicitWait(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public void waitElementClickable(String element){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
	}
	public void waitPresenceElement(String element){
		WebDriverWait wait = new WebDriverWait (driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(element)));
	}
}
