package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import pages.LocationPopupPage;
import pages.SearchResultPage;

public class SearchTest extends BasicTest {

	@Test(priority = 0)
	public void searchResultsTest() throws InterruptedException, IOException {

		LocationPopupPage locPopP = new LocationPopupPage(this.driver, this.waiter, this.js);
		SearchResultPage srp = new SearchResultPage(this.driver, this.waiter, this.js);

		this.driver.navigate().to(this.BaseUrl + "/meals");

		locPopP.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");

		for (int i = 1; i < 7; i++) {

			XSSFRow row = sheet.getRow(i);

			XSSFCell location = row.getCell(0);
			XSSFCell link = row.getCell(1);
			XSSFCell numberOfResultsData = row.getCell(2);

			int intNumOfResults = (int) numberOfResultsData.getNumericCellValue();

			this.driver.navigate().to(link.getStringCellValue());

			locPopP.enterSelectLocation();
			locPopP.setLocation(location.getStringCellValue());

			Thread.sleep(2000);
			sa.assertEquals(intNumOfResults, srp.numberOfResults(), "[Error] Number of results are not the same!");

			for (int j = 3; j < 3 + intNumOfResults; j++) {

				sa.assertTrue(srp.allSearchResultMealNames().get(j - 3).contains(row.getCell(j).getStringCellValue()),
						"[Error] Meal names are not the same!");

			}

			fis.close();
			wb.close();
		}
	}
}
