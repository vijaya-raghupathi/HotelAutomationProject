package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookedItineraryPage {

    WebDriver driver;

    public BookedItineraryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[contains(@id, 'order_id_text')]")
    WebElement searchOrderTextbox;

    @FindBy(xpath = "//input[@value='Search Hotel']")
    WebElement searchButton;

    @FindBy(xpath = "//input[contains(@id, 'btn_id')]")
    WebElement cancelButton;

    public void cancelBooking(String orderId) {
        searchOrderTextbox.sendKeys(orderId);
        searchButton.click();
        cancelButton.click();

        // Handle alert popup
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
