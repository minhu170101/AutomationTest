package StepDefinitions.SearchUser;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PageObjects.LoginPageObjects;
import PageObjects.SearchUserPageObjects;
import io.cucumber.java.en.*;

public class SearchUserSteps {
	WebDriver driver;
	WebDriverWait wait;

	@Given("user is on search page")
	public void user_is_on_search_page() throws InterruptedException {
		driver = new ChromeDriver();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    
		driver.manage().window().maximize();
	    driver.get("https://opensource-demo.orangehrmlive.com/");
	    
	    LoginPageObjects loginPageObj = new LoginPageObjects(driver, wait);
	    loginPageObj.setUsernameInput("Admin");
		loginPageObj.setPasswordInput("admin123");
		loginPageObj.clickLogin();
		
		Thread.sleep(3000);
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
	}
	
	@When("user entering {string}, {string}, {string} and {string}")
	public void user_entering_and(String username, String userRole, String employee, String status) throws InterruptedException {
		
		SearchUserPageObjects obj = new SearchUserPageObjects(driver, wait);
		obj.setUsernameInput(username);
		obj.setEmployeeInput(employee);
		obj.selectRoleDropbox(userRole);
		obj.selectStatusDropbox(status);
		Thread.sleep(2000);
	
	}
	
	@When("clicks on search button")
	public void clicks_on_search_button() throws InterruptedException {
		SearchUserPageObjects obj = new SearchUserPageObjects(driver, wait);
		obj.clickSearch();
		Thread.sleep(5000);
	}
	
	@Then("system should display the search results")
	public void system_should_display_the_search_results() throws InterruptedException {
		SearchUserPageObjects obj = new SearchUserPageObjects(driver, wait);
		if(obj.checkInvalid() || obj.checkMessagePopup()) {
			Assert.assertFalse(obj.checkInvalid(), "Error invalid field");
		}
		else if(obj.checkDataFound()) {
			Assert.assertTrue(obj.checkDataFound());
		}
		driver.quit();
	}
}
