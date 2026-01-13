package Functionals;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageObjects.LoginPageObjects;
import PageObjects.SearchUserPageObjects;
import Provides.SearchUserData;

public class SearchUserPageTest {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public void Setup() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
		
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		LoginPageObjects loginObj = new LoginPageObjects(driver, wait);
		loginObj.setUsernameInput("Admin");
		loginObj.setPasswordInput("admin123");
		loginObj.clickLogin();
		
		Thread.sleep(2000);
		
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
	}
	
	@Test(priority = 1,dataProvider = "dataFound", dataProviderClass = SearchUserData.class)
	public void searchDataFound(String username, String role, String employee, String status) throws InterruptedException {
		
		Setup();
		
		SearchUserPageObjects searchObj = new SearchUserPageObjects(driver,wait);
			
		searchObj.setUsernameInput(username);
		searchObj.setEmployeeInput(employee);
		searchObj.selectRoleDropbox(role);
		searchObj.selectStatusDropbox(status);
		
		Thread.sleep(2000);
		searchObj.clickSearch();
		
		Thread.sleep(2000);
		
		Thread.sleep(5000);
		Assert.assertTrue(searchObj.checkDataFound());
		
		driver.close();
		driver.quit();
		
	}
	
	@Test(priority = 2,dataProvider = "dataNotFound", dataProviderClass = SearchUserData.class)
	public void searchDataNotFound(String username, String role, String employee, String status) throws InterruptedException {
		
		Setup();
		
		SearchUserPageObjects searchObj = new SearchUserPageObjects(driver,wait);
		SoftAssert softAssert = new SoftAssert();
		
		searchObj.setUsernameInput(username);
		searchObj.setEmployeeInput(employee);
		searchObj.selectRoleDropbox(role);
		searchObj.selectStatusDropbox(status);
		
		Thread.sleep(2000);
		searchObj.clickSearch();
		
		Thread.sleep(2000);
		
		softAssert.assertFalse(searchObj.checkDataFound());
		softAssert.assertTrue(searchObj.checkMessagePopup());
		softAssert.assertAll();
		
		driver.close();
		driver.quit();
		
	}
	
	@Test(priority = 3,dataProvider = "dataInvalid", dataProviderClass = SearchUserData.class)
	public void searchDataInvalid(String username, String role, String employee, String status) throws InterruptedException {
		
		Setup();
		
		SearchUserPageObjects searchObj = new SearchUserPageObjects(driver,wait);
		
		searchObj.setUsernameInput(username);
		searchObj.setEmployeeInput(employee);
		searchObj.selectRoleDropbox(role);
		searchObj.selectStatusDropbox(status);
		
		Thread.sleep(2000);
		searchObj.clickSearch();
		
		Thread.sleep(2000);
		
		Assert.assertTrue(searchObj.checkInvalid());
		
		driver.close();
		driver.quit();
		
	}
}
