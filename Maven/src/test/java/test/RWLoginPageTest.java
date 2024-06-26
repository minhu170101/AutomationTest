package test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import DataProvides.LoginData;
import pages.RWLoginPageObjects;

public class RWLoginPageTest {
	private WebDriver driver;

	private WebDriverWait wait;
	
	public void RunBrowser() {
		driver = new EdgeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://gwy.qa.marketnode.com/login");
	}

	@Test(priority = 1,dataProvider = "loginSuccess", dataProviderClass = LoginData.class)
	public void RWLoginSuccessTest(String email, String password) throws InterruptedException {
		RunBrowser();
		
		RWLoginPageObjects loginPageObj = new RWLoginPageObjects(driver, wait);
		loginPageObj.SetEmailInput(email);
		loginPageObj.SetPassInput(password);
		loginPageObj.ClickLogin();

		loginPageObj.SetCodeInput();
		loginPageObj.ClickVerify();

		Thread.sleep(2000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://gwy.qa.marketnode.com/home");
		Thread.sleep(2000);
		
		driver.quit();
	}
	
	@Test(priority = 2, dataProvider = "loginFail", dataProviderClass = LoginData.class)
	public void RWLoginFailTest(String email, String password) throws InterruptedException {
		RunBrowser();
		
		RWLoginPageObjects loginPageObj = new RWLoginPageObjects(driver, wait);
		loginPageObj.SetEmailInput(email);
		loginPageObj.SetPassInput(password);
		loginPageObj.ClickLogin();

		Thread.sleep(5000);
		boolean error = loginPageObj.CheckError();
		Assert.assertEquals(error, true);
		
		driver.quit();
	}
}
