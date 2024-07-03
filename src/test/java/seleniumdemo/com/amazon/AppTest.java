package seleniumdemo.com.amazon;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
	@Test
	public void amazonTest() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.amazon.in/");
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.amazon.in/");

		driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]/option[19]"));
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("iphone 13");
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

		List<WebElement> productElements = driver.findElements(By.xpath(
				"//div[contains(@class, 's-main-slot')]//div[contains(@class, 's-result-item')]"));
		for (WebElement product : productElements) {
			try {
				// try to get all the products on the page
				String name = product.findElement(By.xpath(".//h2/a")).getText();
				System.out.println("Product Name: " + name);
			} catch (Exception e) {
				// handle cases where the product might not have the expected details
				System.out.println("Error extracting product details: " + e.getMessage());
			}
		}
		WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10);
        new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 5000).perform();
        
        driver.quit();
	}
}
