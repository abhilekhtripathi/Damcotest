package makeMyTripTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class MakeMytripTest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.makemytrip.com/");

        // Wait and close the initial login popup modal
        WebElement modalCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='commonModal__close']")));
        modalCloseButton.click();

        // Click on the "From" field
        WebElement fromCity = wait.until(ExpectedConditions.elementToBeClickable(By.id("fromCity")));
        fromCity.click();

        // Enter "Delhi" in the From field
        WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='From']")));
        fromInput.sendKeys("Delhi");

        // Select "Delhi, India" from dropdown
        WebElement fromSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Delhi, India')]")));
        fromSelect.click();

        // Click on the "To" field
        WebElement toCity = wait.until(ExpectedConditions.elementToBeClickable(By.id("toCity")));
        toCity.click();

        // Enter "Mumbai" in the To field
        WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='To']")));
        toInput.sendKeys("Mumbai");

        // Select "Dubai, United Arab Emirates" from dropdown
        WebElement toSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Chhatrapati Shivaji International Airport')]")));
        toSelect.click();

        // Click on Search Button
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Search']")));
        searchButton.click();
        
     // results by Early Departure Time
        WebElement earlyDepartureSort = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Departure']")));
        earlyDepartureSort.click();
        System.out.println("Flight search initiated!");
     //  Retrieve flight details
        List<WebElement> flightNames = driver.findElements(By.xpath("//span[@class='airlineName']"));
        List<WebElement> flightPrices = driver.findElements(By.xpath("//div[@class='priceSection']//span[@class='actual-price']"));

        if (flightNames.size() >= 2 && flightPrices.size() >= 2) {
            String airline = flightNames.get(1).getText();
            String price = flightPrices.get(1).getText();
            System.out.println("Second Lowest Priced Flight (sorted by Early Departure):");
            System.out.println("Airline: " + airline);
            System.out.println("Price: ₹" + price);
        } else {
            System.out.println("Less than 2 flights found.");
        }
       // driver.quit();
    }
}
