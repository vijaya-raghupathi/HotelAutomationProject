package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectHotelPage {
    WebDriver driver;

    @FindBy(id = "radiobutton_0")
    WebElement firstHotelRadio;

    @FindBy(id = "continue")
    WebElement continueBtn;

    public SelectHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectFirstHotel() {
        firstHotelRadio.click();
        continueBtn.click();
    }
}
