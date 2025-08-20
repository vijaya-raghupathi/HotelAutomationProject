package tests;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConfigReader;

@Listeners(listeners.TestListener.class)
public class BookHotelTest extends BaseTest {

	@Test(priority = 1)
	
	public void testBlankFormSubmission() {
		// Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        // Search Hotel
        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
        searchHotelPage.searchHotel(
                ConfigReader.get("location"),
                ConfigReader.get("hotel"),
                ConfigReader.get("room_type"),
                ConfigReader.get("rooms"),
                ConfigReader.get("checkin_date"),
                ConfigReader.get("checkout_date"),
                ConfigReader.get("adults"),
                ConfigReader.get("children")
        );

        // Select Hotel
        SelectHotelPage selectHotelPage = new SelectHotelPage(driver);
        selectHotelPage.selectFirstHotel();
        
        //Book Hotel
		BookHotelPage bookHotelPage = new BookHotelPage(driver);
		bookHotelPage.clickBookNow();
	    String alertText = bookHotelPage.getFirstNameAlert(); // Replace with actual locator for error
	    Assert.assertTrue(alertText.contains("Enter your First Name"), "Expected alert not shown for blank submission");
	    
	    test = extent.createTest("testBlankFormSubmission");
        test.info("test Blank Form Submission");
	}

	@Test(priority = 2)
	public void testInvalidCVV() {
		// Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        // Search Hotel
        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
        searchHotelPage.searchHotel(
                ConfigReader.get("location"),
                ConfigReader.get("hotel"),
                ConfigReader.get("room_type"),
                ConfigReader.get("rooms"),
                ConfigReader.get("checkin_date"),
                ConfigReader.get("checkout_date"),
                ConfigReader.get("adults"),
                ConfigReader.get("children")
        );

        // Select Hotel
        SelectHotelPage selectHotelPage = new SelectHotelPage(driver);
        selectHotelPage.selectFirstHotel();
        
        //Book Hotel
		BookHotelPage bookHotelPage = new BookHotelPage(driver);
		bookHotelPage.enterFirstName( ConfigReader.get("cvv_first_name"));
	    bookHotelPage.enterLastName( ConfigReader.get("cvv_last_name"));
	    bookHotelPage.enterAddress( ConfigReader.get("cvv_address"));
	    bookHotelPage.enterCreditCardNumber( ConfigReader.get("cvv_credit_card"));
	    bookHotelPage.selectCreditCardType( ConfigReader.get("cvv_card_type"));
	    bookHotelPage.selectExpiryMonth( ConfigReader.get("cvv_exp_month"));
	    bookHotelPage.selectExpiryYear( ConfigReader.get("cvv_exp_year"));
	    bookHotelPage.enterCVV( ConfigReader.get("cvv_cvv")); // Invalid CVV (should be 3 digits)
	    bookHotelPage.clickBookNow();

	    String cvvError = bookHotelPage.getCVVAlert(); // Replace with actual locator
	    Assert.assertTrue(cvvError.contains("Invalid CVV"), "CVV validation failed");
	    
	    test = extent.createTest("testInvalidCVV");
        test.info("test Invalid CVV");
	}

	@Test(priority = 3)
    public void testFullBookingFlow() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        // Search Hotel
        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
        searchHotelPage.searchHotel(
                ConfigReader.get("location"),
                ConfigReader.get("hotel"),
                ConfigReader.get("room_type"),
                ConfigReader.get("rooms"),
                ConfigReader.get("checkin_date"),
                ConfigReader.get("checkout_date"),
                ConfigReader.get("adults"),
                ConfigReader.get("children")
        );

        // Select Hotel
        SelectHotelPage selectHotelPage = new SelectHotelPage(driver);
        selectHotelPage.selectFirstHotel();

        // Book Hotel
        BookHotelPage bookHotelPage = new BookHotelPage(driver);
        bookHotelPage.bookHotel(
                ConfigReader.get("first_name"),
                ConfigReader.get("last_name"),
                ConfigReader.get("address"),
                ConfigReader.get("credit_card"),
                ConfigReader.get("card_type"),
                ConfigReader.get("exp_month"),
                ConfigReader.get("exp_year"),
                ConfigReader.get("cvv")
        );
        
        // ✅ Booking Confirmation Assertion
        // Wait and assert that booking confirmation ID is displayed
        BookingConfirmationPage confirmationPage = new BookingConfirmationPage(driver);
        String orderNo = confirmationPage.getOrderNumber();
        Assert.assertNotNull(orderNo, "Booking confirmation number is not displayed.");
        System.out.println("Booking confirmed. Order number: " + orderNo);
        
        test = extent.createTest("testFullBookingFlow");
        test.info("Hotel Booking Successful" + "Order No" + " " + orderNo);
        
    }
}
