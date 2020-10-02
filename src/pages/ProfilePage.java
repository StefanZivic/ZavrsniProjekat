package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

// CONS	

	public ProfilePage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	
// GET METODS

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.name("user_email"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZip() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		WebElement country = this.driver.findElement(By.name("user_country_id"));
		Select selectCountry = new Select(country);
		return selectCountry;
	}

	public Select getState() {
		WebElement state = this.driver.findElement(By.name("user_state_id"));
		Select selectState = new Select(state);
		return selectState;
	}

	public Select getCity() {
		WebElement city = this.driver.findElement(By.name("user_city"));
		Select selectCity = new Select(city);
		return selectCity;
	}

// METODS	

	private void FileUploadBtn() {
		WebElement uploadImg = this.driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a"));
		this.js.executeScript("arguments[0].click();", uploadImg);
	}

	public void profilePicUpload(String imgPath) {
		this.FileUploadBtn();
		this.driver.findElement(By.xpath("//*[@id=\"form-upload\"]/input")).sendKeys(imgPath);
	}

	public void removeImg() {
		WebElement removeBtn = this.driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a[2]"));
		this.js.executeScript("arguments[0].click();", removeBtn);
	}

	public void changeAll(String firstName, String lastName, String address, String phone, String zip,
			String country, String state, String city) throws InterruptedException {
		
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
		this.getAddress().clear();
		this.getAddress().sendKeys(address);
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
		this.getZip().clear();
		this.getZip().sendKeys(zip);
		Thread.sleep(2000);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(2000);
		this.getState().selectByVisibleText(state);
		Thread.sleep(2000);
		this.getCity().selectByVisibleText(city);

	}

}
