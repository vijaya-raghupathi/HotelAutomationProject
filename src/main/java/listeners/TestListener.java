package listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import base.BaseTest;
import utilities.DriverFactory;

public class TestListener extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        ChromeOptions options = new ChromeOptions();
        
        Object testClass = result.getInstance();
        driver = DriverFactory.getDriver(options);

        try {
            driver = (WebDriver) result.getTestClass().getRealClass()
                    .getDeclaredField("driver")
                    .get(testClass);
        } catch (Exception e) {
            System.out.println("Could not access WebDriver instance: " + e.getMessage());
        }

        if (driver != null) {
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotName = "./screenshots/" + result.getName() + ".png";
                FileUtils.copyFile(src, new File(screenshotName));
                System.out.println("Screenshot saved to: " + screenshotName);
            } catch (IOException e) {
                System.out.println("Screenshot capture failed: " + e.getMessage());
            }
        } else {
            System.out.println("Driver is null. Cannot take screenshot.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    // Add other override methods if needed
}
