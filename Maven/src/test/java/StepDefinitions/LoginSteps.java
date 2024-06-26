package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	@Given("user is on login page")
	public void user_is_on_login_page() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Inside Step - User is on login page");
	}

	@When("user entering email and password")
	public void user_entering_email_and_password() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Inside Step - user enters email and password");
	}

	@And("clicks on login button")
	public void clicks_on_login_button() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Inside Step - clicks on login button");
	}

	@Then("user is navigated to the verify page")
	public void user_is_navigated_to_the_verify_page() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Inside Step - user is navigated to the verify page");
	}


}
