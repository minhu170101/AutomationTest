package Provides;

import org.testng.annotations.DataProvider;

public class SearchUserData {
	@DataProvider(name = "dataFound")
	public Object[][] DataSet1(){
		return new Object[][] {
			{"admin",null,null,null},
			{null,"Admin",null,null},
			{null,null,"manda akhil user",null},
			{null,null,null,"Enabled"},
			{"admin","Admin","manda akhil user","Enabled"},
		};
	}
	
	@DataProvider(name = "dataNotFound")
	public Object[][] DataSet2(){
		return new Object[][] {
			{"asdqweqw",null,null,null},
			{null,null,"Ravi M B",null},
			{null,null,null,"Disabled"},
			{"asdqweqw","ESS","Ravi M B","Disabled"},
		};
	}
	
	@DataProvider(name = "dataInvalid")
	public Object[][] DataSet3(){
		return new Object[][] {
			{null,null,"asjdkwjlkqwjekl",null},
		};
	}
}
