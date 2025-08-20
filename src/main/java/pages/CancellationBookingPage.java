package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CancellationBookingPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CancellationBookingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By bookedItineraryLink = By.linkText("Booked Itinerary");
    private By itineraryHeader = By.xpath("//td[contains(text(),'Booked Itinerary')]");
    private By firstBookingCheckbox = By.name("ids[]");
    private By cancelSelectedButton = By.xpath("//input[@value='Cancel Selected']");
    private By cancellationMessage = By.xpath("//*[contains(text(),'The booking has been cancelled.')]");

    // Step 1: Navigate to Booked Itinerary
    public void goToBookedItinerary() {
        driver.findElement(bookedItineraryLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(itineraryHeader));
    }

    // Step 2: Cancel first available booking
    public void cancelFirstBooking() {
        driver.findElement(firstBookingCheckbox).click();
        driver.findElement(cancelSelectedButton).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        if (!alertText.contains("Are you sure")) {
            throw new IllegalStateException("Unexpected alert message: " + alertText);
        }
        alert.accept();  // Confirm the cancellation
    }

    // Step 3: Check if the cancellation message is displayed
    public boolean isCancellationMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(cancellationMessage)).isDisplayed();
        } catch ( Exception e) {
            return false;
        }
    }
}
