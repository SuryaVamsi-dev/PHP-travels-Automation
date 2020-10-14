package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import Pages.LoginPage;
import Pages.SearchPage;

public class MenuCommon extends BasePage {

	public MenuCommon(WebDriver driver, ExtentTest Logger) {
		this.driver = driver;
		this.Logger = Logger;
	}

	@FindBy(xpath = "//a[@id='dropdownLangauge']")
	public WebElement ClickLanguage;

	@FindBy(xpath = "//a[contains(text(),'Home')]")
	public WebElement home;

	@FindBy(xpath = "//header/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/div[1]/a[1]")
	public WebElement myacc;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	public WebElement logout;

	public LoginPage Logout() {

		myacc.click();
		logout.click();
		LoginPage LOP = new LoginPage(driver, Logger);
		PageFactory.initElements(driver, LOP);
		return LOP;
	}

	public SearchPage Home() {
		GetTitle("My Account");
		try {
			home.click();
			ReportPass("clicked on home");
		} catch (Exception e) {
			ReportFail(e.getMessage());
		}

		SearchPage SP = new SearchPage(driver, Logger);
		PageFactory.initElements(driver, SP);
		return SP;
	}

}
