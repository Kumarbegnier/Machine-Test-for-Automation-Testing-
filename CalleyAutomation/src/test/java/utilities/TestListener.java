package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            Object instance = result.getInstance();
            WebDriver driver = extractDriver(instance);
            if (driver instanceof TakesScreenshot) {
                Path screenshotsDir = Path.of("reports", "screenshots");
                Files.createDirectories(screenshotsDir);

                String method = result.getMethod().getMethodName();
                String timestamp = LocalDateTime.now().toString().replaceAll(":", "-");
                String fileName = method + "_" + timestamp + ".png";

                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Path target = screenshotsDir.resolve(fileName);
                Files.copy(src.toPath(), target);
            }
        } catch (Exception ignored) {
            // Listener should never fail the test run
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // no-op
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // no-op
    }

    @Override
    public void onTestStart(ITestResult result) {
        // no-op
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // no-op
    }

    @Override
    public void onStart(ITestContext context) {
        // no-op
    }

    @Override
    public void onFinish(ITestContext context) {
        // no-op
    }

    private WebDriver extractDriver(Object instance) {
        if (instance == null) {
            return null;
        }
        try {
            // BaseTest has protected WebDriver driver;
            var field = instance.getClass().getSuperclass().getDeclaredField("driver");
            field.setAccessible(true);
            return (WebDriver) field.get(instance);
        } catch (Exception e) {
            return null;
        }
    }
}

