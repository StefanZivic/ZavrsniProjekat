package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.testng.annotations.Test;

import pages.LocationPopupPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.SearchResultPage;

public class SearchTest extends BasicTest {

	@Test(priority = 0)
	public void searchResultsTest() throws InterruptedException, IOException {

		LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
		NotificationSistemPage nsp = new NotificationSistemPage(this.driver, this.waiter, this.js);
		MealPage mp = new MealPage(this.driver, this.waiter, this.js);
		SearchResultPage srp = new SearchResultPage(this.driver, this.waiter, this.js);

		this.driver.navigate().to(this.BaseUrl + "/meals");

		locPopP.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");

		for (int i = 1; i < sheet.getLastRowNum(); i++) {

			XSSFRow row = sheet.getRow(i);

			XSSFCell location = row.getCell(0);
			XSSFCell link = row.getCell(1);
			XSSFCell numberOfResultsData = row.getCell(2);
			int inum = (int) numberOfResultsData.getNumericCellValue();

			this.driver.get(link.getStringCellValue());

			locPopP.enterSelectLocation();
			locPopP.setLocation(location.getStringCellValue());

			for (int j = 3; j < 3 + inum; j++) {
				
				row.getCell(j).getStringCellValue();
			}
			
			Assert.assertEquals(inum, srp.numberOfResults());

			Thread.sleep(3000);

		}

		fis.close();
		wb.close();

	}

}
