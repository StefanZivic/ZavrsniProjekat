package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;

public class MealItemTest extends BasicTest {

	@Test(priority = 0)
	public void addMealToCartTest() throws InterruptedException {

		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
		NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
		MealPage mp = new MealPage(this.driver, this.waiter, this.js);

		locPopP.closeDialog();

		mp.addMealToCart("5");

		sa.assertEquals(nsp.getMessage(), "The Following Errors Occurred:\n" + "Please Select Location",
				"[Error] Location Failed");

		nsp.WaitNotifDissapear();

		locPopP.enterSelectLocation();
		locPopP.setLocation("City Center - Albany");

		Thread.sleep(2000);

		mp.addMealToCart("3");

		sa.assertEquals(nsp.getMessage(), "Meal Added To Cart", "[Error] Add Meal To Cart unsuccessful");

		sa.assertAll();
	}

	@Test(priority = 5)
	public void addMealToFavoritesTest() throws InterruptedException {

		LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
		LoginPage logP = new LoginPage(this.driver, this.waiter, this.js);
		NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
		MealPage mp = new MealPage(this.driver, this.waiter, this.js);

		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		locPopP.closeDialog();

		mp.addMealtoFavorites();

		sa.assertEquals(nsp.getMessage(), "Please login first!", "[Error] You're already logged in!");

		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");
		logP.loginUser(this.username, this.password);

		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		mp.addMealtoFavorites();

		sa.assertEquals(nsp.getMessage(), "Product has been added to your favorites.",
				"[Error] Product has not been added to your favorites.!");

		sa.assertAll();
	}

	@Test(priority = 10)
	public void clearCartTest() throws IOException, InterruptedException {

		this.driver.navigate().to(this.BaseUrl + "/meals");

		LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
		NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
		MealPage mp = new MealPage(this.driver, this.waiter, this.js);
		CartSummaryPage csp = new CartSummaryPage(this.driver, this.waiter, this.js);

		locPopP.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);

			this.driver.navigate().to(row.getCell(0).getStringCellValue());
			String str = String.format("%.0f", row.getCell(1).getNumericCellValue());

			mp.addMealToCart(str);

			sa.assertEquals(nsp.getMessage(), "Meal Added To Cart", "[Error] Meal hasn't been added to cart!");

		}

		fis.close();
		wb.close();

		csp.clearCart();

		sa.assertEquals(nsp.getMessage(), "All meals removed from Cart successfully",
				"[Error] All meals hasn't been removed from Cart successfully!");

	}

}
