package Base;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Pages.LandingPage;
import Utilities.DateUtils;
import Utilities.ExtentReportmanager;

public class BasePage {

	
	public WebDriver driver;
	public SoftAssert sAssert=new SoftAssert();
	public ExtentReports report= ExtentReportmanager.getReportInstance();
	public ExtentTest Logger;
	public void Invoke() {
		try {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		ReportPass("chrome initialized successfully");
		}catch(Exception e) {
			ReportFail(e.getMessage());
		}
		try {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		StatusInfo("Browser maximized");
		}catch(Exception e) {
			ReportFail(e.getMessage());
		}
	}
	
	public LandingPage OpenWebsite() {
		try {
		driver.get("https://phptravels.net/");
		ReportPass("webpage opened");
		}catch(Exception e) {
			ReportFail(e.getMessage());
		}
		LandingPage LP=new LandingPage(driver,Logger);
		PageFactory.initElements(driver, LP);
		return LP;
	}
	
	public void GetTitle(String title) {
		try {
		Assert.assertEquals(driver.getTitle(), title);
		ReportPass("Actual title - "+driver.getTitle()+"  Equals to the Expected title - "+title);
		}catch(Exception e) {
			ReportFail(e.getMessage());
		}
	}
	
	public void  ReportFail(String report) {
		Logger.log(Status.FAIL, report);
		takeSS();
		Assert.fail(report);
	}
	
	public void takeSS() {
		
		TakesScreenshot take=(TakesScreenshot)driver;
		File srcfile=take.getScreenshotAs(OutputType.FILE);
		File destFile=new File(System.getProperty("user.dir")+"\\test-output\\"+DateUtils.getTymStamp()+".png");
		try {
			FileUtils.copyFile(srcfile,destFile);
			Logger.addScreenCaptureFromPath(System.getProperty("user.dir")+"\\test-output\\"+DateUtils.getTymStamp()+".png");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void ReportPass(String report) {
		System.out.println("report passed called");
		Logger.log(Status.PASS, report);
	}
	
	public void StatusInfo(String info) {
		
		Logger.log(Status.INFO, info);
	}
	
	
	/***************** Wait Functions in Framework *****************/
	public void waitForPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		while (i != 180) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
		}

		waitLoad(2);

		i = 0;
		while (i != 180) {
			Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (jsState) {
				break;
			} else {
				waitLoad(1);
			}
		}
	}

	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@AfterMethod
	public void FlushReports() {
		report.flush();
		driver.close();
	}
}
