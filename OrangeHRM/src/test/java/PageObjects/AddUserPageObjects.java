package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUserPageObjects {
	WebDriver driver;
	WebDriverWait wait;
	
	By role_Dropbox = By.xpath("//div[contains(@class,'oxd-grid-item')][1]//div[@class='oxd-select-wrapper']");
	By status_Dropbox = By.xpath("//div[contains(@class,'oxd-grid-item')][3]//div[@class='oxd-select-wrapper']");
	
	By employee_Input = By.xpath("//div[@class='oxd-autocomplete-wrapper']//input");
	By username_Input = By.xpath("//div[@class='oxd-form-row']//input[contains(@class, 'oxd-input')]");
	By password_Input = By.cssSelector("input[type='password']");
	
	By input_Error = By.className("oxd-input--error");
	By autocomplete_Error = By.className("oxd-autocomplete-text-input--error");
	By select_Error = By.className("oxd-select-text--error");
	
	By save_Button = By.xpath("//div[@class='oxd-form-actions']//button[2]");
	By cancel_Button = By.xpath("//div[@class='oxd-form-actions']//button[1]");
	
	
	public AddUserPageObjects(WebDriver driver, WebDriverWait wait) {
		super();
		this.driver = driver;
		this.wait = wait;
	}
	
	public void setUsernameInput(String username) {
		if(username != null) {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(username_Input));
			element.sendKeys(username);
		}
	}
	
	public void setPasswordInput(String password, String confirmPassword) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(password_Input));
		List<WebElement> inputElements = driver.findElements(password_Input);
		if(password != null) {
			WebElement pass = inputElements.get(0);
			pass.sendKeys(password);
		}
		if(confirmPassword != null) {
			WebElement confirm = inputElements.get(1);
			confirm.sendKeys(confirmPassword);
		}
	}
	
	public void setEmployeeInput(String employee) throws InterruptedException {
		if(employee != null) {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(employee_Input));
			element.sendKeys(employee);
			
			By xpath = By.xpath("//div[@role='option']//span[text()='" + employee + "']");
			Thread.sleep(3000);
			if(driver.findElements(xpath).size() != 0) {
				WebElement select = driver.findElement(xpath);
				select.click();
			}
		}
	}
	
	public void selectRoleDropbox(String role) {
		if(role != null) {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(role_Dropbox));
			element.click();
			
			By xpath = By.xpath("//div[@role='option']//span[text()='" + role + "']");
			WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
			
			select.click();
		}
	}
	
	public void selectStatusDropbox(String status) {
		if(status != null) {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(status_Dropbox));
			element.click();
			
			By xpath = By.xpath("//div[@role='option']//span[text()='" + status + "']");
			WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
			
			select.click();
		}
	}
	
	public void clickCancel() {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cancel_Button));
		element.click();
	}
	
	public void clickSave() {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(save_Button));
		element.click();
	}
	
	public boolean checkError() {
		List<WebElement> inputs = driver.findElements(input_Error);
		List<WebElement> selects = driver.findElements(select_Error);
		List<WebElement> autos = driver.findElements(autocomplete_Error);
		
		if(inputs.size() > 0 || selects.size() > 0 || autos.size() > 0) {
			return true;
		}
		
		return false;
	}
}
