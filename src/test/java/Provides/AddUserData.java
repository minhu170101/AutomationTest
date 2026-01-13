package Provides;

import org.testng.annotations.DataProvider;

public class AddUserData {
	//role -> employee -> status -> username -> password -> confirmPassword
	@DataProvider(name = "saveSuccess")
	public Object[][] DataSet1(){
		return new Object[][] {
			{"Admin","Ravi M B","Enabled","admin1234","admin1234","admin1234"},
		};
	}
	
	@DataProvider(name = "saveFail")
	public Object[][] DataSet2(){
		return new Object[][] {
			{null,null,null,null,null,null},
			{"Admin",null,null,null,null,null},
			{null,"Ravi M B",null,null,null,null},
			{null,null,"Enabled",null,null,null},
			{null,null,null,null,"admin123",null},
			{null,null,null,null,null,"admin123"},
			{"Admin","Ravi M B","Enabled","admin123","admin123","admin123"},
		};
	}
}
