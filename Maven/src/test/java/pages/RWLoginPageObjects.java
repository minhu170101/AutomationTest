package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RWLoginPageObjects {
	WebDriver driver = null;
	WebDriverWait wait = null;

	By email_Input = By.cssSelector("input[type=text]");
	By pass_Input = By.cssSelector("input[type=password]");
	By code_Input = By.cssSelector("input[name=code]");
	By error_Input = By.className("ant-input-affix-wrapper-status-error");
	By error_Message = By.className("messengerError");

	By login_Button = By.className("bntLogin");
	By verify_Button = By.className("bnt-verifi");

	int index = 0;

	public RWLoginPageObjects(WebDriver driver, WebDriverWait wait) {
		super();
		this.driver = driver;
		this.wait = wait;
	}

	// Input Method
	public void SetEmailInput(String text) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(email_Input));
		element.sendKeys(text);
	}

	public void SetPassInput(String text) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(pass_Input));
		element.sendKeys(text);
	}

	public void SetCodeInput() {
		wait.until(ExpectedConditions.urlToBe("https://gwy.qa.marketnode.com/login/verify-code"));

		List<WebElement> inputFields = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(code_Input));

		for (WebElement inputField : inputFields) {
			index++;
			inputField.sendKeys(Integer.toString(index));
		}
	}

	// Button Method
	public void ClickLogin() {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(login_Button));
		element.click();

		//driver.findElement(login_Button).sendKeys(Keys.RETURN);
	}

	public void ClickVerify() {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(verify_Button));
		element.click();
		//driver.findElement(verify_Button).sendKeys(Keys.RETURN);
	}
	
	public boolean CheckError() {
		if(driver.findElements(error_Input).size() != 0 || driver.findElements(error_Message).size() != 0) {
			return true;
		}
		return false;
	}
}
