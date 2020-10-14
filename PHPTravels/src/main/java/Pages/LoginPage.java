package Pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import Base.BasePage;
import Base.MenuCommon;

public class LoginPage extends BasePage {

	public MenuCommon menu;

	public LoginPage(WebDriver driver, ExtentTest Logger) {
		this.driver = driver;
		this.Logger = Logger;
		menu = new MenuCommon(driver, Logger);
		PageFactory.initElements(driver, menu);
	}

	Random random = new Random();
	int unique = random.nextInt(100);

	@FindBy(name = "username")
	public WebElement enterUsrname;

	@FindBy(name = "password")
	public WebElement Pwd;

	@FindBy(id = "remember")
	public WebElement check;

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	public WebElement LoinBtn;

	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	public List<WebElement> singup;

	@FindBy(name = "firstname")
	public WebElement fstname;

	@FindBy(name = "lastname")
	public WebElement lstname;

	@FindBy(name = "phone")
	public WebElement mobile;

	@FindBy(name = "email")
	public WebElement Email;

	@FindBy(name = "password")
	public WebElement pwd;

	@FindBy(name = "confirmpassword")
	public WebElement confpwd;

	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	public WebElement SignUP;

	public void SignIn(String name, String pawd) throws InterruptedException {

		// Thread.sleep(2000);
		enterUsrname.sendKeys(name);
		Pwd.sendKeys(pawd);

		LoinBtn.click();
	}

	public void Signup(String fname, String lname, String number, String mail, String pass)
			throws InterruptedException {
		try {
		singup.get(1).click();
		fstname.sendKeys(fname);
		lstname.sendKeys(lname);
		mobile.sendKeys(number);
		Email.sendKeys(mail+ unique);
		pwd.sendKeys(pass);
		confpwd.sendKeys(pass);
		Thread.sleep(2000);
		StatusInfo("Credentials Entered Successfully");
		}catch(Exception e) {
			ReportFail(e.getMessage());
		}

		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", SignUP);
		StatusInfo("Clicked on Signup Button");
		}catch(Exception e) {
			ReportFail(e.getMessage());
		}
	}

	public MenuCommon getmenu() {

		return menu;
	}

}
