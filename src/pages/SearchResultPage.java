package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

// GET METODS

	private List<WebElement> getAllSearchResults() {
		List<WebElement> allMeals = this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
		return allMeals;
	}

// METODS

//	public String allSearchResultMealNames() {
//		for (int i = 0; i < this.getAllSearchResults().size(); i++) {
//			if(a == 2) {
//			return 	this.getAllSearchResults().get(i).getText();
//			}
//			else {
//				return null;
//			}
//		}
//	
//	}

	public int numberOfResults() {
		return this.getAllSearchResults().size();
	}

}
