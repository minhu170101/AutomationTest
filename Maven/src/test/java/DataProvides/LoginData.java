package DataProvides;

import org.testng.annotations.DataProvider;

public class LoginData {
	
	@DataProvider(name = "loginSuccess")
	public Object[][] DataSet1(){
		return new Object[][] {
			{"charlie.tu@yopmail.com","Charlie@123"},
			{"cindysysadmin@yopmail.com","Admin@123"},
			{"cindy.ong16@yopmail.com","Admin@123"},
			{"cindylender@yopmail.com","Admin@123"},
		};
	}
	
	@DataProvider(name = "loginFail")
	public Object[][] DataSet2(){
		return new Object[][] {
			{"","Charlie@123"},
			{"cindysysadmin@yopmail.com",""},
			{"asdasdwqe@gmail.com","Admin@123"},
			{"asdweqwewqe","Admin@123"},
		};
	}
}
