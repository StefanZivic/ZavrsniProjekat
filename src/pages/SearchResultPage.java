package pages;

import java.util.ArrayList;
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

	public List<WebElement> getAllSearchResults() {
		return this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}

// METODS

	public List<String> allSearchResultMealNames() {
		List<String> allNames = new ArrayList<>();
		for (int i = 0; i < this.getAllSearchResults().size(); i++) {
			allNames.add(this.getAllSearchResults().get(i).getText());

		}
		return allNames;
	}

	public int numberOfResults() {
		return this.getAllSearchResults().size();

	}
}
