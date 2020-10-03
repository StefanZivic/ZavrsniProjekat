package tests;


import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest {

	SoftAssert sa = new SoftAssert();
	
//	@Test(priority = 0)
//	public void editProfileTest() throws InterruptedException {
//		
//		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");
//
//		LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
//		LoginPage logP = new LoginPage(this.driver, this.waiter, this.js);
//		NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
//		ProfilePage profP = new ProfilePage(this.driver, this.waiter, this.js);
//		AuthPage ap = new AuthPage(this.driver, this.waiter, this.js);
//		
//		locPopP.closeDialog();
//		logP.loginUser(this.username, this.password);
//
//		sa.assertEquals(nsp.getMessage(), "Login Successfull", "[Error] Login unsuccessful!");
//
//		this.driver.navigate().to(this.BaseUrl + "/member/profile");
//
//		profP.changeAll(this.firstName, this.lastName, this.address, this.phone, this.zip, this.country, this.state,
//				this.city);
//
//		this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[5]/div/fieldset/input")).click();
//
//		sa.assertEquals(nsp.getMessage(), "Setup Successful", "[Error] Setup unsuccessful!");
//
//		ap.logout();
//
//		sa.assertEquals(nsp.getMessage(), "Logout Successfull!", "[Error] Logout unsuccessful!");
//
//		sa.assertAll();
//	}

	@Test(priority = 5)
	public void changeProfileImage() throws IOException, InterruptedException {
		
		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");

		LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
		LoginPage logP = new LoginPage(this.driver, this.waiter, this.js);
		NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
		ProfilePage profP = new ProfilePage(this.driver, this.waiter, this.js);
		AuthPage ap = new AuthPage(this.driver, this.waiter, this.js);
		
		locPopP.closeDialog();
		logP.loginUser(this.username, this.password);

		sa.assertEquals(nsp.getMessage(), "Login Successfull", "[Error] Login unsuccessful!");

		this.driver.navigate().to(this.BaseUrl + "/member/profile");
		
		Thread.sleep(3000);

		String imgPath = new File("images\\Simpanza.png").getCanonicalPath();
		
		profP.profilePicUpload(imgPath);
		
		sa.assertEquals(nsp.getMessage(), "Profile Image Uploaded Successfully", "[Error] Profile Image Uploaded unsuccessful!");
		
		nsp.WaitNotifDissapear();
		
		profP.removeImg();
		
		sa.assertEquals(nsp.getMessage(), "Profile Image Deleted Successfully", "[Error] Profile Image Deleted unsuccessful!");
		
		nsp.WaitNotifDissapear();
		
		ap.logout();
		
	}


}
