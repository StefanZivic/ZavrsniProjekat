package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import pages.NotificationSistemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest {

	protected WebDriver driver;
	protected WebDriverWait waiter;

	LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
	LoginPage logP = new LoginPage(this.driver, this.waiter, this.js);
	NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
	ProfilePage profP = new ProfilePage(this.driver, this.waiter, this.js);
	AuthPage ap = new AuthPage(this.driver, this.waiter, this.js);

	SoftAssert sa = new SoftAssert();

	@org.testng.annotations.BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void editProfileTest() throws InterruptedException {
		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");

		locPopP.closeDialog();
		logP.loginUser(this.username, this.password);

		sa.assertEquals(nsp.getMessage(), "Login Successfull", "[Error] Login unsuccessful!");

		this.driver.navigate().to(this.BaseUrl + "/member/profile");

		profP.changeAll(this.firstName, this.lastName, this.address, this.phone, this.zip, this.country, this.state,
				this.city);

		this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[5]/div/fieldset/input")).click();

		sa.assertEquals(nsp.getMessage(), "Setup Successful", "[Error] Setup unsuccessful!");

		ap.logout();

		sa.assertEquals(nsp.getMessage(), "Logout Successful", "[Error] Logout unsuccessful!");

		sa.assertAll();
	}

//	@Test(priority = 5)
//	public void changeProfileImage() throws IOException, InterruptedException {
//		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");
//
//		locPopP.closeDialog();
//		logP.loginUser(this.username, this.password);
//
//		sa.assertEquals(nsp.getMessage(), "Login Successfull", "[Error] login unsuccessful!");
//
//		this.driver.navigate().to(this.BaseUrl + "/member/profile");
//
//		String imgPath = new File("imagеs/Gorilla.png").getCanonicalPath();
//		
//		profP.profilePicUpload(imgPath);
//		
//		Thread.sleep(3000);
//		
//		
//		
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
