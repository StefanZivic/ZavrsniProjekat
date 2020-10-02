package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);

	}

	public WebElement getFavouriteBtn() {
		return this.driver.findElement(By.className("favourite  itemfav link"));
	}

	public WebElement getAddMealToCartBtn() {
		return this.driver.findElement(By.className("btn btn--primary btn--large js-proceedtoAddInCart "));
	}

// METODS

	public void addMealToCart() {
		this.getAddMealToCartBtn().click();
	}

	public void addMealtoFavorites() {
		this.getFavouriteBtn().click();
	}

}
