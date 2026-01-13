package Functionals;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AddUserPageObjects;
import PageObjects.LoginPageObjects;
import Provides.AddUserData;

public class AddUserPageTest {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public void Setup() throws InterruptedException {
		
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		LoginPageObjects loginObj = new LoginPageObjects(driver, wait);
		loginObj.setUsernameInput("Admin");
		loginObj.setPasswordInput("admin123");
		loginObj.clickLogin();
		
		Thread.sleep(2000);
		
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
	}
	
	@Test(priority = 1,dataProvider = "saveSuccess", dataProviderClass = AddUserData.class)
	public void addUserSuccess(String role, String employee, String status, String username, String password, String confirmPassword) throws InterruptedException {
		Setup();
		AddUserPageObjects user = new AddUserPageObjects(driver,wait);
		
		user.setUsernameInput(username);
		user.setPasswordInput(password, confirmPassword);
		user.setEmployeeInput(employee);
		user.selectRoleDropbox(role);
		user.selectStatusDropbox(status);
		
		Thread.sleep(2000);
		user.clickSave();
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		
		driver.close();
		driver.quit();
	}
	
	@Test(priority = 2,dataProvider = "saveFail", dataProviderClass = AddUserData.class)
	public void addUserFail(String role, String employee, String status, String username, String password, String confirmPassword) throws InterruptedException {
		Setup();
		AddUserPageObjects user = new AddUserPageObjects(driver,wait);
		
		user.setUsernameInput(username);
		user.setPasswordInput(password, confirmPassword);
		user.setEmployeeInput(employee);
		user.selectRoleDropbox(role);
		user.selectStatusDropbox(status);
		
		Thread.sleep(2000);
		user.clickSave();
		Thread.sleep(5000);
		
		Assert.assertTrue(user.checkError());
		
		driver.close();
		driver.quit();
	}
	
	@Test(priority = 3)
	public void cancelAddUser() throws InterruptedException {
		Setup();
		AddUserPageObjects user = new AddUserPageObjects(driver,wait);
		user.clickCancel();
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		
		driver.close();
		driver.quit();
	}
	
	
}
