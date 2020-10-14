package Pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import Base.BasePage;
import Base.MenuCommon;

public class SearchPage extends BasePage {
	public MenuCommon menu;

	public SearchPage(WebDriver driver, ExtentTest Logger) {
		this.driver = driver;
		this.Logger = Logger;
		menu = new MenuCommon(driver, Logger);
		PageFactory.initElements(driver, menu);
	}

	@FindBy(xpath = "//div[@id='s2id_autogen16']/a")
	public WebElement search;

	@FindBy(xpath = "/html[1]/body[1]/div[4]/div[1]/input[1]")
	public WebElement input;

	@FindBy(xpath = "//div[contains(text(),'Hotels')]//parent::li/ul/li[1]/div")
	public WebElement hotels;

	@FindBy(xpath = "//div[contains(text(),'Locations')]//parent::li/ul/li[1]/div")
	public WebElement Locations;

	@FindBy(xpath = "//input[@id='checkin']")
	public WebElement clickCheckin;

	@FindBy(xpath = "//body/div[@id='datepickers-container']/div[1]/nav[1]/div[2]/i[1]")
	public WebElement Year;

	@FindBy(xpath = "//body/div[@id='datepickers-container']/div[1]/nav[1]/div[2]")
	public WebElement Month;

	@FindBy(xpath = "//body/div[@id='datepickers-container']/div[2]/nav[1]/div[2]")
	public WebElement outMonth;

	@FindBy(xpath = "//body/div[@id='datepickers-container']/div[2]/nav[1]/div[3]")
	public WebElement outforwardArrow;

	@FindBy(xpath = "//body/div[@id='datepickers-container']/div[2]/nav[1]/div[1]")
	public WebElement outBackArrow;

	@FindBy(xpath = "//body/div[@id='datepickers-container']/div[1]/nav[1]/div[3]")
	public WebElement forwardArrow;

	@FindBy(xpath = "//body/div[@id='datepickers-container']/div[1]/nav[1]/div[1]")
	public WebElement BackArrow;

	@FindBy(xpath = "//body/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/button[1]")
	public WebElement plus;

	@FindBy(xpath = "//body/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/button[1]")
	public WebElement minus;

	// @FindBy(xpath="//body/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[1]/div[4]/button[1]")
	@FindBy(xpath = "//body/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[4]/button[1]")
	public WebElement Search;

	@FindBy(xpath = "//a[contains(text(),'Hotels')]")
	public WebElement ClickHotel;
	//
	// @FindBy(xpath="")
	// public WebElement minus;
	//

	public void Search(String Loc) throws InterruptedException {

		ClickHotel.click();
		try {

			ReportPass("");
		} catch (Exception e) {
			ReportFail(e.getMessage());
		}
		search.click();
		input.sendKeys(Loc);
		waitForPageLoad();
		// Thread.sleep(2000);

		// hotels.click();
		Locations.click();

	}

	public void inCalender(String date) throws ParseException {

		try {
			clickCheckin.click();
			Date currentdate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Date Expected = dateFormat.parse(date);
			String split[] = date.split("/");
			String sdate = split[0];
			String day = new SimpleDateFormat("dd").format(Expected);
			String month = new SimpleDateFormat("MMMM").format(Expected);
			String year = new SimpleDateFormat("yyyy").format(Expected);

			String ExpectedMY = month + ",\n" + year;
			while (true) {

				String disply = Month.getText();
				// System.out.println(ExpectedMY + " - " + disply);
				if (disply.equals(ExpectedMY)) {
					// System.out.println("inside Equal");

					WebElement daySelect = driver.findElement(
							By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[text()='" + (sdate) + "']"));
					// System.out.println(daySelect.getText());
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click()", daySelect);
					break;

				} else if (Expected.compareTo(currentdate) > 0) {
					forwardArrow.click();
				} else {
					BackArrow.click();
				}

			}
			ReportPass("check in selected successfully" + date);
		} catch (Exception e) {
			ReportFail(e.getMessage());
		}

	}

	public void outCalender(String date) throws ParseException {

		try {
			Date currentdate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Date Expected = dateFormat.parse(date);
			String split[] = date.split("/");
			String sdate = split[0];
			String day = new SimpleDateFormat("dd").format(Expected);
			String month = new SimpleDateFormat("MMMM").format(Expected);
			String year = new SimpleDateFormat("yyyy").format(Expected);

			String ExpectedMY = month + ",\n" + year;
			while (true) {

				String disply = outMonth.getText();
				// System.out.println(ExpectedMY + " - " + disply);
				if (disply.equals(ExpectedMY)) {
					// System.out.println("inside Equal");

					WebElement daySelect = driver.findElement(
							By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[text()='" + (day) + "']"));
					// System.out.println(daySelect.getText());
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click()", daySelect);
					break;

				} else if (Expected.compareTo(currentdate) > 0) {
					outforwardArrow.click();
				} else {
					outBackArrow.click();
				}

			}
			ReportPass("check out entered succesfully" + date);
		} catch (Exception e) {
			ReportFail(e.getMessage());
		}

	}

	public void Adults(int num) {

		try {
			int def = 2;
			System.out.println("going into while");
			while (num != def) {
				System.out.println("inside while");
				if (num < def) {
					def -= 1;
					minus.click();

				} else if (num > def) {

					def += 1;
					plus.click();
				}

			}

			ReportPass(" Adults added Succesfully" + num);
		} catch (Exception e) {
			ReportFail(e.getMessage());
		}

	}

	public ResultPage GoSearch() {
		// System.out.println("inside search");
		try {
			Search.click();
			StatusInfo("clicked on search");
		} catch (Exception e) {
			ReportFail(e.getMessage());
		}

		ResultPage RP = new ResultPage(driver, Logger);
		PageFactory.initElements(driver, RP);
		return RP;
	}

	public MenuCommon getmenu() {

		return menu;
	}

}
