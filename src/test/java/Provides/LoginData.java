package Provides;

import org.testng.annotations.DataProvider;

public class LoginData {
	@DataProvider(name = "loginSuccess")
	public Object[][] DataSet1(){
		return new Object[][] {
			{"Admin","admin123"},
		};
	}
	
	@DataProvider(name = "loginFail")
	public Object[][] DataSet2(){
		return new Object[][] {
			{"",""},
			{"Admin",""},
			{"","admin123"},
			{"Admin","sadkjwjl"},
			{"asdjaslkdjl","admin123"},
		};
	}
}
