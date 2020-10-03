package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

public class MealItemTest extends BasicTest {

	SoftAssert sa = new SoftAssert();

	@Test(priority=0)
	public void addMealToCartTest() throws InterruptedException {
		
		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		
		LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
		LoginPage logP = new LoginPage(this.driver, this.waiter, this.js);
		NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
		ProfilePage profP = new ProfilePage(this.driver, this.waiter, this.js);
		AuthPage ap = new AuthPage(this.driver, this.waiter, this.js);
		MealPage mp = new MealPage(this.driver, this.waiter, this.js);
		
		locPopP.closeDialog();
		
		mp.addMealToCart("5");
		
		sa.assertEquals(nsp.getMessage(), "The Following Errors Occurred:\r\n" + 
				"Please Select Location", "[Error] Add Meal To Cart unsuccessful");
		
		nsp.WaitNotifDissapear();
		
		locPopP.enterSelectLocation();
		locPopP.setLocation("City Center - Albany");
		
		mp.addMealToCart("3");
		
		
		sa.assertEquals(nsp.getMessage(), "Meal Added To Cart", "[Error] Add Meal To Cart unsuccessful");
		
		sa.assertAll();
	}

//	@Test(priority = 5)
//	public void addMealToFavoritesTest() throws InterruptedException {
//
//		LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
//		LoginPage logP = new LoginPage(this.driver, this.waiter, this.js);
//		NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
//		MealPage mp = new MealPage(this.driver, this.waiter, this.js);
//
//		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
//
//		locPopP.closeDialog();
//
//		mp.addMealtoFavorites();
//
//		sa.assertEquals(nsp.getMessage(), "Please login first!", "[Error] You're already logged in!");
//
//		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");
//		logP.loginUser(this.username, this.password);
//
//		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
//
//		mp.addMealtoFavorites();
//
//		sa.assertEquals(nsp.getMessage(), "Product has been added to your favorites.", "[Error] Something went wrong!");
//
//		sa.assertAll();
//	}

//	@Test(priority = 10)
//	public void clearCartTest() throws IOException, InterruptedException {
//		this.driver.navigate().to(this.BaseUrl + "/meals");
//
//		LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
//		LoginPage logP = new LoginPage(this.driver, this.waiter, this.js);
//		NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
//		ProfilePage profP = new ProfilePage(this.driver, this.waiter, this.js);
//		AuthPage ap = new AuthPage(this.driver, this.waiter, this.js);
//		MealPage mp = new MealPage(this.driver, this.waiter, this.js);
//		CartSummaryPage csp = new CartSummaryPage(this.driver, this.waiter, this.js);
//
//		locPopP.enterSelectLocation();
//		locPopP.setLocation("City Center - Albany");
//
//		File file = new File("data/Data.xlsx");
//		FileInputStream fis = new FileInputStream(file);
//
//		XSSFWorkbook wb = new XSSFWorkbook(fis);
//
//		XSSFSheet sheet = wb.getSheet("Meals");
//
//		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//			XSSFRow row = sheet.getRow(i);
//
//			this.driver.navigate().to(row.getCell(0).getStringCellValue());
//			mp.addMealToCart(row.getCell(1).getStringCellValue());
//
//			sa.assertEquals(nsp.getMessage(), "Meal Added To Cart", "[Error] Meal hasn't been added to cart!");
//
//		}
//
//		fis.close();
//		wb.close();
//		
//		csp.clearCart();
//
//		sa.assertEquals(nsp.getMessage(), "All meals removed from Cart successfully",
//				"[Error] All meals hasn't been removed from Cart successfully!");
//
//	}

//	@Test
//	public void blabla() throws InterruptedException {
//		
//	this.driver.navigate().to(this.BaseUrl + "/meals");
//	
//	LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
//	locPopP.getKeyword().click();
//	
//	Thread.sleep(5000);
//		
//	String locationName = "City Center - Albany";
//	
//	WebElement a = this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]"));
//		
//	
//		System.out.println(a.getAttribute("data-value"));
//	
//	
//		
//		
//	
//	
//	}
	
	
	
	
}
