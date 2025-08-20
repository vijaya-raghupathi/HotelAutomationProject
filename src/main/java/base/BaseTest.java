package base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class BaseTest {
	public WebDriver driver;
    
    public static ExtentReports extent;
    public static ExtentTest test;
    
    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Tester", "Vijaya");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");
    }
    
    @BeforeMethod
     public void setUp() {
    	ChromeOptions options = new ChromeOptions();

    	 // Disable password manager, autofill, and save card popups
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false); // Disable credentials
        prefs.put("profile.password_manager_enabled", false); // Disable password saving
        prefs.put("autofill.profile_enabled", false); // Disable autofill for names/addresses
        prefs.put("autofill.credit_card_enabled", false); // Disable credit card save prompts

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-autofill-keyboard-accessory-view");
        options.addArguments("--disable-popup-blocking"); // Optional
             
      	ConfigReader.loadProperties();  // Load config before test starts
    	driver = DriverFactory.getDriver(options);
        driver.manage().window().maximize();
        
        driver.get("http://adactinhotelapp.com/index.php");
        
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        }

        DriverFactory.quitDriver();
    }

    
    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
    
}
