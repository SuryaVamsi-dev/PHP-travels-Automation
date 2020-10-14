package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import Base.BasePage;
import Base.MenuCommon;
import Utilities.WriteExcelData;

public class ResultPage extends BasePage {

	public MenuCommon menu;

	public ResultPage(WebDriver driver, ExtentTest Logger) {
		this.driver = driver;
		this.Logger = Logger;
		menu = new MenuCommon(driver, Logger);
		PageFactory.initElements(driver, menu);
	}

	@FindBy(xpath = "//body/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/h5[1]")
	public List<WebElement> hotelNames;

	@FindBy(xpath = "//body/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/aside[1]/div[1]/div[1]/div[1]/form[1]/div[3]/div[2]/div/div[1]/label[1]")
	public List<WebElement> hotelAmenties;

	@FindBy(xpath = "//button[@id='searchform']")
	public WebElement search;

	public void Filter(String star, String Loc) {
		try {
			List<String> htl = new ArrayList<String>();
			for (WebElement name : hotelNames) {
				htl.add(name.getText());
				StatusInfo("Hotels Found in" + Loc + " are " + name.getText());
			}

			WriteExcelData.ListOutHotels(htl, Loc);

			WebElement Stars = driver.findElement(By.xpath(
					"//body/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/aside[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div["
							+ (star) + "]"));
			Stars.click();

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", search);
			ReportPass("Filtered successfully with stars " + star);
		} catch (Exception e) {
			ReportFail(e.getMessage());
		}

	}

	public MenuCommon getmenu() {

		return menu;
	}

}
