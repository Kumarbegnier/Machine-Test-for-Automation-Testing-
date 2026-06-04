package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    public static Path takeScreenshot(WebDriver driver, String fileNameWithoutExt, Path reportsDir) {
        try {
            if (!(driver instanceof TakesScreenshot)) {
                return null;
            }

            Files.createDirectories(reportsDir);
            String safeName = fileNameWithoutExt.replaceAll("[^a-zA-Z0-9._-]", "_");
            Path target = reportsDir.resolve(safeName + ".png");

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            return target;
        } catch (IOException e) {
            throw new RuntimeException("Failed to take screenshot", e);
        }
    }
}

