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

public abstract class BasicTest {

	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	
	protected String BaseUrl = "http://demo.yo-meals.com/";
	protected String username = "customer@dummyid.com";
	protected String password = "12345678a";
	protected String firstName = "Gordon";
	protected String lastName = "Ramsey";
	protected String address = "Branka Radicevica bb";
	protected String phone = "060/123456";
	protected String zip = "1000";
	protected String country = "India";
	protected String state = "Asir";
	protected String city = "Barpeta";
		
	@org.testng.annotations.BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
//	@Test
//	public void test() {
//		
//		LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
//		LoginPage logP = new LoginPage(this.driver, this.waiter, this.js);
//		NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
//		ProfilePage profP = new ProfilePage(this.driver, this.waiter, this.js);
//		AuthPage ap = new AuthPage(this.driver, this.waiter, this.js);
//	}

	@AfterMethod
	public void afterTest(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {

			File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss'.png'").format(new Date());
			File save = new File("screenshots/" + fileName);
			FileHandler.copy(ss, save);
			this.driver.manage().deleteAllCookies();
		}
		this.driver.manage().deleteAllCookies();
	}

	@org.testng.annotations.AfterClass
	public void AfterClass() {
		this.driver.quit();
	}

}
