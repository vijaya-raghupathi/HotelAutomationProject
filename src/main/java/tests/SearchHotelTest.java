package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.SearchHotelPage;
import utilities.ConfigReader;

@Listeners(listeners.TestListener.class)
public class SearchHotelTest extends BaseTest {

	  @Test(priority = 1)
	    public void validateSearchPageTitle() {
  
		  // Login
	        LoginPage loginPage = new LoginPage(driver);
		    loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

		  	String expectedSerchTitle = "Adactin.com - Search Hotel";
	        String actualSerchTitle = driver.getTitle();
	        Assert.assertEquals(actualSerchTitle, expectedSerchTitle, "Search Hotel page title does not match");
	        test = extent.createTest("validateSearchPageTitle");
	        test.info("Validate Search Page Title");
	    }

	    @Test(priority = 2)
	    public void validateSearchFieldsPresence() {
	    	// Login
	        LoginPage loginPage = new LoginPage(driver);
		    loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

	        Assert.assertTrue(driver.findElement(By.id("location")).isDisplayed(), "Location dropdown not displayed");
	        Assert.assertTrue(driver.findElement(By.id("hotels")).isDisplayed(), "Hotels dropdown not displayed");
	        Assert.assertTrue(driver.findElement(By.id("room_type")).isDisplayed(), "Room type dropdown not displayed");
	        Assert.assertTrue(driver.findElement(By.id("Submit")).isDisplayed(), "Search button not displayed");
	        test = extent.createTest("validateSearchFieldsPresence");
	        test.info("validate Search Fields Presence");
	    }

	    @Test(priority = 3)
	    public void validateDefaultDropdownSelection() {
	    	// Login
	        LoginPage loginPage = new LoginPage(driver);
		    loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

	        Select location = new Select(driver.findElement(By.id("location")));
	        String selectedLocation = location.getFirstSelectedOption().getText();
	        Assert.assertNotEquals(selectedLocation, "-- Select --", "Default location should be selected");
	        test = extent.createTest("validateDefaultDropdownSelection");
	        test.info("validate Default Dropdown Selection");
	    }

	    @Test(priority = 4)
	    public void validateSearchWithoutEnteringFields() {
	    	// Login
	        LoginPage loginPage = new LoginPage(driver);
		    loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

	        driver.findElement(By.id("Submit")).click();

	        WebElement errorMessage = driver.findElement(By.id("location_span")); // Assuming error appears here
	        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed when location is missing");
	        Assert.assertEquals(errorMessage.getText(), "Please Select a Location", "Correct error message not shown");
	        test = extent.createTest("validateSearchWithoutEnteringFields");
	        test.info("validate Search Without Entering Fields");
	    }

	  	    
	@Test(priority = 5)
    public void testSearchHotel() {
    	
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
        
        test = extent.createTest("testSearchHotel");
        test.info("Search Hotel Successfuls");
    }
}
