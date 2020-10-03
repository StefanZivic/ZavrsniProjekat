package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);

	}
//GET METODS
	
	public WebElement getFavouriteBtn() {
		return this.driver.findElement(By.xpath("//*[@id=\"item_119\"]"));
	}

	public WebElement getAddMealToCartBtn() {
		return this.driver.findElement(By.xpath("//*[@id=\"body\"]/section[1]/div/div/div[2]/div/div[3]/div[2]/a"));
	}
	
	public WebElement getAddQuantity() {
		return this.driver.findElement(By.name("product_qty"));
	}

// METODS
	
	public void addMealToCart(String quantity) throws InterruptedException {
		this.getAddQuantity().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		this.getAddQuantity().sendKeys(quantity);
		this.getAddMealToCartBtn().click();
	}

	public void addMealtoFavorites() {
		this.getFavouriteBtn().click();
	}
	
}
