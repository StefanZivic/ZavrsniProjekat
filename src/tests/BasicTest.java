package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;

	protected String BaseUrl = "http://demo.yo-meals.com";
	protected String username = "customer@dummyid.com";
	protected String password = "12345678a";

	protected String firstName = "Gordon";
	protected String lastName = "Ramsey";
	protected String address = "Branka Radicevica bb";
	protected String phone = "060/123456";
	protected String zip = "1000";
	protected String country = "India";
	protected String state = "Goa";
	protected String city = "Aldona";

	SoftAssert sa = new SoftAssert();

//	protected LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
//	protected LoginPage logP = new LoginPage(this.driver, this.waiter, this.js);
//	protected NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
//	protected ProfilePage profP = new ProfilePage(this.driver, this.waiter, this.js);
//	protected AuthPage ap = new AuthPage(this.driver, this.waiter, this.js);
//	protected MealPage mp = new MealPage(this.driver, this.waiter, this.js);

	@org.testng.annotations.BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		this.js = (JavascriptExecutor) this.driver;
		waiter = new WebDriverWait(driver, 30);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterTest(ITestResult result) throws IOException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {

			File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss'.png'").format(new Date());
			File save = new File("screenshots/" + fileName);
			FileHandler.copy(ss, save);
		}
		this.driver.manage().deleteAllCookies();
		Thread.sleep(2000);
		this.driver.navigate().refresh();
	}

	@org.testng.annotations.AfterClass
	public void AfterClass() {
		this.driver.quit();
	}

}
