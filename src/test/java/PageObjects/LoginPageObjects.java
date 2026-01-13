package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageObjects {
	WebDriver driver = null;
	WebDriverWait wait = null;
	
	By username_Input = By.xpath("//input[@name='username']");
	By password_Input = By.xpath("//input[@name='password']");
	
	By login_Button = By.xpath("//button[@type='submit']");
	
	By required_Fields = By.xpath("//span");
	By invalid_Fields = By.className("oxd-alert-content-text");

	public LoginPageObjects(WebDriver driver, WebDriverWait wait) {
		super();
		this.driver = driver;
		this.wait = wait;
	}
	
	public void setUsernameInput(String username) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(username_Input));
		element.sendKeys(username);
	}
	
	public void setPasswordInput(String password) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(password_Input));
		element.sendKeys(password);
	}
	
	public void clickLogin() {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(login_Button));
		element.click();
	}
	
	public boolean checkRequired() {
		
		List<WebElement> list = driver.findElements(required_Fields);
		WebElement element;
		try {
			element = driver.findElement(invalid_Fields);
		}catch (NoSuchElementException e) {
			element = null;
		}
		
		if (list.size() > 0) {
			return true;
		}
		if (element != null && element.isDisplayed()) {
			return true;
		}
		
		return false;
	}
}
