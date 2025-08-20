package tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utilities.ConfigReader;

@Listeners(listeners.TestListener.class)
public class CancelBookingTest extends BaseTest {

	 @Test(priority = 1)
	    public void login() {
		// Step 1: Login
	    	
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

	        // Optional: assert login success
	        Assert.assertTrue(driver.getTitle().contains("Search Hotel"), "Login Failed!");
	        
	        test = extent.createTest("login");
	        test.info("Login Info");
	    }
	 
	 @Test(priority = 2)
	    public void navigateToBookedItinerary() {
		// Step 1: Login
	    	
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

	        driver.findElement(By.linkText("Booked Itinerary")).click();
	        
	     // Explicit wait for the header or specific element to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//td[contains(text(),'Booked Itinerary')]")));

	        // Assert page title or presence of cancel buttons
	        Assert.assertTrue(driver.getPageSource().contains("Booked Itinerary"), "Failed to load Booked Itinerary");
	        test = extent.createTest("navigateToBookedItinerary");
	        test.info("navigate To Booked Itinerary");
	    }
	 /*
	 @Test(priority = 3)
	    public void cancelBooking() {
		// Step 1: Login
	    	
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
	        
	        driver.findElement(By.linkText("Booked Itinerary")).click();


	        // Click on first cancel button
	        driver.findElement(By.name("ids[]")).click(); // Select first checkbox

	        driver.findElement(By.xpath("//input[@value='Cancel Selected']")).click();
	        
	        // Handle confirmation alert
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        Assert.assertTrue(alertText.contains("Are you sure"), "Unexpected alert text");

	        alert.accept(); // Click OK on alert
        test.info("cancel Booking");
	    }
	 */

@Test(priority = 3)
    public void validateCancellationMessageIsDisplayed() {
	LoginPage loginPage = new LoginPage(driver);
    loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
    
    driver.findElement(By.linkText("Booked Itinerary")).click();


    // Click on first cancel button
    driver.findElement(By.name("ids[]")).click(); // Select first checkbox

    driver.findElement(By.xpath("//input[@value='Cancel Selected']")).click();
    
    // Handle confirmation alert
    Alert alert = driver.switchTo().alert();
    String alertText = alert.getText();
    Assert.assertTrue(alertText.contains("Are you sure"), "Unexpected alert text");

    alert.accept(); // Click OK on alert

    	boolean messageDisplayed = driver.getPageSource().contains("The booking has been cancelled.");       
        Assert.assertTrue(messageDisplayed, "Cancellation message not displayed.");

        test = extent.createTest("Validate Cancellation Message");
        test.info("Cancellation confirmation message verified.");
    }
	
}
