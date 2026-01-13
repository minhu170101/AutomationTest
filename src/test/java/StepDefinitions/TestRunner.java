package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features/searchUser.feature",
glue= {"StepDefinitions/SearchUser"},
monochrome = true,
plugin = {"pretty","junit:target/JUnitReports/reportSearchUser.xml"}
		)
public class TestRunner {

}