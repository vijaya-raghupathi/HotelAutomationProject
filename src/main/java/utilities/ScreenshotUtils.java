package utilities;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtils {
	public static String captureScreenshot(WebDriver driver, String screenshotName) {
	    if (driver == null) {
	        System.out.println("Driver is null. Cannot take screenshot.");
	        return null;
	    }

	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);
	    String path = "test-output/screenshots/" + screenshotName + ".png";

	    try {
	        FileUtils.copyFile(src, new File(path));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return path;
	}
}
