package Functionals;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.LoginPageObjects;

public class LoginPageTest {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public void RunBrowser() throws InterruptedException {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new");

		driver = new EdgeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@Test(priority = 1,dataProvider = "loginSuccess", dataProviderClass = Provides.LoginData.class)
	public void loginSuccessTest(String username, String password) throws InterruptedException {
		RunBrowser();
		
		LoginPageObjects loginPageObj = new LoginPageObjects(driver, wait);
		
		loginPageObj.setUsernameInput(username);
		loginPageObj.setPasswordInput(password);
		loginPageObj.clickLogin();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		Thread.sleep(2000);
		
		driver.quit();
	}
	
	@Test(priority = 2,dataProvider = "loginFail", dataProviderClass = Provides.LoginData.class)
	public void loginFailTest(String username, String password) throws InterruptedException {
		RunBrowser();
		
		LoginPageObjects loginPageObj = new LoginPageObjects(driver, wait);
		
		loginPageObj.setUsernameInput(username);
		loginPageObj.setPasswordInput(password);
		loginPageObj.clickLogin();
		
		Thread.sleep(2000);
		boolean checkError = loginPageObj.checkRequired();
		Assert.assertTrue(checkError);
		driver.quit();
	}
}
