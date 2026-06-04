package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            try {
                Path reportsDir = Path.of("reports");
                Files.createDirectories(reportsDir);

                ExtentSparkReporter reporter = new ExtentSparkReporter(reportsDir.resolve("ExtentReport.html").toString());
                extent = new ExtentReports();
                extent.attachReporter(reporter);

            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize ExtentReports", e);
            }
        }
        return extent;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}

