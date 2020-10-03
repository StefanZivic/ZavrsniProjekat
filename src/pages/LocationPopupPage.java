package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

// CONS	

	public LocationPopupPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

// GET METODS	

	public WebElement getSelectLocationBtn() {
		return this.driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[1]/div/a"));
	}

	public WebElement getCloseElementBtn() {
		return this.driver.findElement(By.xpath("//*[@id=\"location-popup\"]/div/div/div/div/a"));
	}

	public WebElement getKeyword() {
		return this.driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));

	}

	public WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return this.driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

// METODS

	public void enterSelectLocation() {
		this.getSelectLocationBtn().click();
	}

	public void setLocation(String locationName) throws InterruptedException {

		this.getKeyword().click();
		this.js.executeScript("arguments[0].value=arguments[1]", this.getLocationInput(),
				this.getLocationItem(locationName).getAttribute("data-value"));
		this.js.executeScript("arguments[0].click()", this.getSubmit());
	}

	public void closeDialog() {
		this.getCloseElementBtn().click();
	}

}
