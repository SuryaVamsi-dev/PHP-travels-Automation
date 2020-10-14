package TestCases;

import java.text.ParseException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Base.BasePage;
import Base.MenuCommon;
import Pages.LandingPage;
import Pages.LoginPage;
import Pages.ResultPage;
import Pages.SearchPage;
import Utilities.TestDataProvider;

public class Test1 extends BasePage{

	LandingPage lp;
	LoginPage lop;
	MenuCommon mc;
	SearchPage sp;
	ResultPage rp;
	
	
	@Test(dataProvider="GetData")
	public void Tes(Hashtable<String,String> Gdata) throws InterruptedException, ParseException {
		Logger=report.createTest("PHPTravels");
		
		Invoke();
		lp=OpenWebsite();
		lp.ChangeCurrency(Gdata.get("currency"));
		waitForPageLoad();
		//Thread.sleep(3000);
		lop=lp.login();
		
		lop.Signup(Gdata.get("Firstname"),Gdata.get("Lastname"),Gdata.get("mobile"),Gdata.get("gmail"),Gdata.get("pwd"));
		waitForPageLoad();
		//Thread.sleep(2000);
		mc=lop.getmenu();
		sp=mc.Home();
		sp.Search(Gdata.get("Place"));
		sp.inCalender(Gdata.get("inCalender"));
		sp.outCalender(Gdata.get("outCalender"));
		sp.Adults(5);
		rp=sp.GoSearch();
		rp.Filter(Gdata.get("stars"),Gdata.get("Place"));
		
		
	}
	
	@DataProvider
	public Object[][] GetData(){
		
		
		return TestDataProvider.getTestData("Data.xlsx", "Sheet1", "Search Hotels");
	}
	
}
