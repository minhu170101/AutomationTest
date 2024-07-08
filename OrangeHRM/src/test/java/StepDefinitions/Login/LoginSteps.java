package StepDefinitions.Login;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PageObjects.LoginPageObjects;
import io.cucumber.java.en.*;

public class LoginSteps {
	WebDriver driver;
	WebDriverWait wait;

	@Given("user is logged in to the system")
	public void user_is_logged_in_to_the_system() {
		driver = new ChromeDriver();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    
		driver.manage().window().maximize();
	    driver.get("https://opensource-demo.orangehrmlive.com/");
	}

	@When("user entering {string} and {string}")
	public void user_entering_and(String string, String string2) {
		LoginPageObjects obj = new LoginPageObjects(driver,wait);
	    obj.setUsernameInput(string);
	    obj.setPasswordInput(string2);
	}

	@When("clicks on login button")
	public void clicks_on_login_button() {
		LoginPageObjects obj = new LoginPageObjects(driver,wait);
		obj.clickLogin();
	}

	@Then("user should be redirected to {string} if successful")
	public void user_should_be_redirected_to_if_successful(String string) throws InterruptedException {
		Thread.sleep(5000);
		
		String actualUrl = driver.getCurrentUrl();
		if (!string.equals(actualUrl)) {
            throw new AssertionError("Expected URL: " + string + " but got: " + actualUrl);
		}
	}

	@Then("user should see message if unsuccessful")
	public void user_should_see_message_if_unsuccessful() {
		LoginPageObjects obj = new LoginPageObjects(driver,wait);
		if(obj.checkRequired()) {
			Assert.assertTrue(obj.checkRequired());
		}
		
		driver.quit();

	}
}
