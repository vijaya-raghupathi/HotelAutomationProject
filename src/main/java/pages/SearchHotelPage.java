package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchHotelPage {
    WebDriver driver;

    public SearchHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "location")
    WebElement locationDropdown;

    @FindBy(id = "hotels")
    WebElement hotelDropdown;

    @FindBy(id = "room_type")
    WebElement roomTypeDropdown;

    @FindBy(id = "room_nos")
    WebElement numberOfRoomsDropdown;

    @FindBy(id = "datepick_in")
    WebElement checkInDate;

    @FindBy(id = "datepick_out")
    WebElement checkOutDate;

    @FindBy(id = "adult_room")
    WebElement adultsDropdown;

    @FindBy(id = "child_room")
    WebElement childrenDropdown;

    @FindBy(id = "Submit")
    WebElement searchButton;

    public void searchHotel(String location, String hotel, String roomType, String rooms, String checkIn,
                            String checkOut, String adults, String children) {

        new Select(locationDropdown).selectByVisibleText(location);
        new Select(hotelDropdown).selectByVisibleText(hotel);
        new Select(roomTypeDropdown).selectByVisibleText(roomType);
        new Select(numberOfRoomsDropdown).selectByVisibleText(rooms);

        checkInDate.clear();
        checkInDate.sendKeys(checkIn);

        checkOutDate.clear();
        checkOutDate.sendKeys(checkOut);

        new Select(adultsDropdown).selectByVisibleText(adults);
        new Select(childrenDropdown).selectByVisibleText(children);

        searchButton.click();
    }
}
