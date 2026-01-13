package StepDefinitions.AddUser;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PageObjects.AddUserPageObjects;
import PageObjects.LoginPageObjects;
import io.cucumber.java.en.*;

public class AddUserSteps {
	WebDriver driver;
	WebDriverWait wait;

	@Given("user is on add user page")
	public void user_is_on_add_user_page() throws InterruptedException {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");

		LoginPageObjects loginPageObj = new LoginPageObjects(driver, wait);
		loginPageObj.setUsernameInput("Admin");
		loginPageObj.setPasswordInput("admin123");
		loginPageObj.clickLogin();

		Thread.sleep(3000);

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
	}

	@When("user entering {string}, {string}, {string}, {string}, {string} and {string}")
	public void user_entering_and(String username, String userRole, String employee, String status, String password, String confirmPassword) throws InterruptedException {

		AddUserPageObjects obj = new AddUserPageObjects(driver, wait);
		obj.setUsernameInput(username);
		obj.setEmployeeInput(employee);
		obj.selectRoleDropbox(userRole);
		obj.selectStatusDropbox(status);
		obj.setPasswordInput(password, confirmPassword);
		Thread.sleep(2000);

	}
	
	@When("clicks on save button")
	public void clicks_on_save_button() throws InterruptedException {
		AddUserPageObjects obj = new AddUserPageObjects(driver, wait);
		obj.clickSave();
		Thread.sleep(5000);
	}
	
	@Then("system should navigate to view user page")
	public void system_should_navigate_to_view_user_page() {
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		driver.quit();
	}


}
