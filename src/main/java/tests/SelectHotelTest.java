package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.SearchHotelPage;
import utilities.ConfigReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelectHotelTest extends BaseTest {

	  @Test(priority = 1)
    public void validateHotelDetailsDisplayed() {
        
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
	        
		  // Hotel Name
        WebElement hotelName = driver.findElement(By.id("hotel_name_0"));
        Assert.assertTrue(hotelName.isDisplayed(), "Hotel name is not displayed");

        // Price per night
        WebElement pricePerNight = driver.findElement(By.id("price_night_0"));
        Assert.assertTrue(pricePerNight.isDisplayed(), "Price per night is not displayed");

        // Total price including GST
        WebElement totalPrice = driver.findElement(By.id("total_price_0"));
        Assert.assertTrue(totalPrice.isDisplayed(), "Total price (with GST) is not displayed");
        
        test = extent.createTest("validateHotelDetailsDisplayed");
        test.info("validate Hotel Details Displayed");
    }

	  @Test(priority = 2)
    public void validateGSTCalculation() {
        
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
    	// Fetch price and GST
    	
    	    String roomPriceStr = driver.findElement(By.id("price_night_0")).getAttribute("value"); // e.g. "AUD $120"
    	    String totalPriceStr = driver.findElement(By.id("total_price_0")).getAttribute("value"); // e.g. "AUD $130"

    	    // Trim price values
    	    double roomPrice = Double.parseDouble(roomPriceStr.replaceAll("[^0-9.]", ""));
    	    double actualTotal = Double.parseDouble(totalPriceStr.replaceAll("[^0-9.]", ""));

    	    // Assuming GST is a fixed $10
    	    double expectedTotal = roomPrice + 10.0;

    	    Assert.assertEquals(actualTotal, expectedTotal, 0.01, "GST calculation mismatch: Expected total = Room Price + $10 GST");
    	    
    	    test = extent.createTest("validateGSTCalculation");
            test.info("validate GST Calculation");
    	}



}
