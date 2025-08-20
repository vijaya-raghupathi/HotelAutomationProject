package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingConfirmationPage {
    WebDriver driver;

    public BookingConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "order_no")
    WebElement orderNumber;

    public String getOrderNumber() {
        try {
            Thread.sleep(5000); // Wait for booking to process (for demo only – ideally use WebDriverWait)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orderNumber.getAttribute("value");
    }
}