package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookHotelPage {
    WebDriver driver;

    public BookHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first_name")
    WebElement firstName;

    @FindBy(id = "last_name")
    WebElement lastName;

    @FindBy(id = "address")
    WebElement billingAddress;

    @FindBy(id = "cc_num")
    WebElement creditCardNumber;

    @FindBy(id = "cc_type")
    WebElement creditCardType;

    @FindBy(id = "cc_exp_month")
    WebElement expiryMonth;

    @FindBy(id = "cc_exp_year")
    WebElement expiryYear;

    @FindBy(id = "cc_cvv")
    WebElement cvvField;

    @FindBy(id = "book_now")
    WebElement bookNowBtn;
    
    // Error Messages
    @FindBy(id = "first_name_span")
    WebElement firstNameError;

    @FindBy(id = "cc_cvv_span")
    WebElement cvvError;

    @FindBy(id = "cc_exp_year_span")
    WebElement expiryYearError;
    
    
 // Actions
    public void enterFirstName(String fname) {
    	firstName.clear();
    	firstName.sendKeys(fname);
    }

    public void enterLastName(String lname) {
    	lastName.clear();
    	lastName.sendKeys(lname);
    }

    public void enterAddress(String address) {
    	billingAddress.clear();
    	billingAddress.sendKeys(address);
    }

    public void enterCreditCardNumber(String ccNum) {
    	creditCardNumber.clear();
    	creditCardNumber.sendKeys(ccNum);
    }

    public void selectCreditCardType(String type) {
        Select dropdown = new Select(creditCardType);
        dropdown.selectByVisibleText(type);
    }

    public void selectExpiryMonth(String month) {
        Select dropdown = new Select(expiryMonth);
        dropdown.selectByVisibleText(month);
    }

    public void selectExpiryYear(String year) {
        Select dropdown = new Select(expiryYear);
        dropdown.selectByVisibleText(year);
    }

    public void enterCVV(String cvv) {
    	cvvField.clear();
    	cvvField.sendKeys(cvv);
    }

    public void clickBookNow() {
    	bookNowBtn.click();
    }

    // Validation Messages
    public String getFirstNameAlert() {
        return firstNameError.getText();
    }

    public String getCVVAlert() {
        return cvvError.getText();
    }

    public String getExpiryYearAlert() {
        return expiryYearError.getText();
    }

    public void bookHotel(String fName, String lName, String address, String ccNum,
                          String ccType, String expMonth, String expYear, String cvvNum) {

        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        billingAddress.sendKeys(address);
        creditCardNumber.sendKeys(ccNum);
        new Select(creditCardType).selectByVisibleText(ccType);
        new Select(expiryMonth).selectByVisibleText(expMonth);
        new Select(expiryYear).selectByVisibleText(expYear);
        cvvField.sendKeys(cvvNum);
        bookNowBtn.click();
    }
}
