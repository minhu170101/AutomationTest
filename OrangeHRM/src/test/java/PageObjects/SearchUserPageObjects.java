package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchUserPageObjects {
	
	WebDriver driver;
	WebDriverWait wait;
	
	By username_Input = By.xpath("//div[@class='oxd-form-row']//input[contains(@class, 'oxd-input')]");
	By employee_Input = By.xpath("//div[@class='oxd-autocomplete-wrapper']//input");
	
	By role_Dropbox = By.xpath("//div[contains(@class,'oxd-grid-item')][2]//div[@class='oxd-select-wrapper']");
	By role_DropDown = By.xpath("//div[@role='option']//span[text()='']");
	By status_Dropbox = By.xpath("//div[contains(@class,'oxd-grid-item')][4]//div[@class='oxd-select-wrapper']");
	
	By table_Cards = By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card']");
	
	By error_Message = By.className("oxd-input-field-error-message");
	By message_Popup = By.xpath("//div[@id='oxd-toaster_1']//*");
	
	By reset_Button = By.xpath("//div[@class='oxd-form-actions']//button[1]");
	By search_Button = By.xpath("//div[@class='oxd-form-actions']//button[2]");
	
	public SearchUserPageObjects(WebDriver driver, WebDriverWait wait) {
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
			/*
			 * else { element.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE)); }
			 */
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
	
	public void clickReset() {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(reset_Button));
		element.click();
	}
	
	public void clickSearch() {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(search_Button));
		element.click();
	}
	
	public boolean checkDataFound() {
		List<WebElement> list = driver.findElements(table_Cards);
		return list.size() > 0 ? true : false;
	}
	
	public boolean checkMessagePopup() {
		List<WebElement> list = driver.findElements(message_Popup);
		
		return list.size() > 0 ? true : false;
	}
	
	public boolean checkInvalid() {
		List<WebElement> list = driver.findElements(error_Message);
		
		return list.size() > 0 ? true : false;
	}
	
}
