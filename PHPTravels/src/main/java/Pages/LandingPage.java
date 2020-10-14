package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import Base.BasePage;
import Base.MenuCommon;

public class LandingPage extends BasePage {

	public MenuCommon menu;

	public LandingPage(WebDriver driver, ExtentTest Logger) {
		this.driver = driver;
		this.Logger = Logger;
		menu = new MenuCommon(driver, Logger);
		PageFactory.initElements(driver, menu);
	}

	@FindBy(xpath = "//header/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/a[1]")
	public WebElement currency;

	@FindBy(xpath = "//header/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[1]/div[1]/a")
	public List<WebElement> selectCurrency;

	@FindBy(xpath = "//body[1]/div[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/div[1]/a[1]")
	public WebElement myAcc;

	@FindBy(xpath = "//header/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/div[1]/div[1]/div[1]/a[1]")
	public WebElement LoinBtn;

	public void ChangeCurrency(String type) {
		int i = 0;
		try {
			currency.click();
			while (!selectCurrency.get(i).getText().equalsIgnoreCase(type)) {
				i++;
			}

			selectCurrency.get(i).click();
			//waitForPageLoad();
			ReportPass("Eur Selected Successfully");
		} catch (Exception e) {
			ReportFail(e.getMessage());
		}
	}

	public LoginPage login() {
		try {
		myAcc.click();
		LoinBtn.click();
		ReportPass("successfully clicked on LoginButton");
		}catch(Exception e) {
			ReportFail(e.getMessage());
		}
		LoginPage LOP = new LoginPage(driver, Logger);
		PageFactory.initElements(driver, LOP);
		return LOP;
	}

	public MenuCommon getmenu() {

		return menu;
	}

}
