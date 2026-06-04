package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class WaitUtils {

    public static WebDriverWait waitFor(WebDriver driver, Duration timeout) {
        return new WebDriverWait(driver, timeout);
    }

    public static <T> T waitUntil(WebDriver driver, Duration timeout, Function<WebDriver, T> condition) {
        return waitFor(driver, timeout).until(condition);
    }

    public static void waitUntilPageTitleNotEmpty(WebDriver driver, Duration timeout) {
        waitFor(driver, timeout).until(webDriver -> webDriver.getTitle() != null && !webDriver.getTitle().trim().isEmpty());
    }
}


