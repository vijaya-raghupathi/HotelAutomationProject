package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utilities.ConfigReader;

@Listeners(listeners.TestListener.class)
public class LoginTest extends BaseTest {

  
	@Test(priority = 1)
	public void validateLoginPageTitle() {
		test = extent.createTest("validateLoginPageTitle");
	    String expectedTitle = "Adactin.com - Hotel Reservation System";
	    String actualTitle = driver.getTitle();
	    Assert.assertEquals(actualTitle, expectedTitle, "Login page title does not match");
	    test.info("Navigated to application");
	}

@Test(priority = 2)
	public void validateLoginFieldsPresence() {
	test = extent.createTest("validateLoginFieldsPresence");
	WebElement usernameField = driver.findElement(By.id("username"));
    WebElement passwordField = driver.findElement(By.id("password"));
    WebElement loginButton = driver.findElement(By.id("login"));

    Assert.assertTrue(usernameField.isDisplayed(), "Username field is not displayed");
    Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed");
    Assert.assertTrue(loginButton.isDisplayed(), "Login button is not displayed");
    test.info("Validate Login Feilds");
	    
	}

@Test(priority = 3)
	public void validateEmptyUsernameAndPassword() {
	test = extent.createTest("validateEmptyUsernameAndPassword");
	WebElement usernameField = driver.findElement(By.id("username"));
	WebElement passwordField = driver.findElement(By.id("password"));
	WebElement loginButton = driver.findElement(By.id("login"));
	
	// Leave both fields empty
	usernameField.clear();
	passwordField.clear();
	loginButton.click();
	
	// Capture and assert the error message
	WebElement errorText = driver.findElement(By.className("login_error"));
	Assert.assertTrue(errorText.isDisplayed(), "Error message is not displayed for empty credentials");
	Assert.assertTrue(errorText.getText().contains("Enter"), "Expected error message not shown for empty login");
	test.info("Validate Empty user Name and Password");
}


@Test(priority = 4)
public void validateSuccessfulLogin() {
	test = extent.createTest("validateSuccessfulLogin");
    String username = ConfigReader.get("username");
    String password = ConfigReader.get("password");
    
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(username, password);

// Simple check
Assert.assertTrue(driver.getTitle().contains("Search"), "Login failed");
test.info("Login Successful");
}



}
