package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.Paths;
import java.time.Duration;

import static utilities.WaitUtils.waitFor;

public class ImportPage {

    private final WebDriver driver;

    public ImportPage(WebDriver driver) {
        this.driver = driver;
    }

    // TODO: verify locators once UI is confirmed
    private final By powerImportNav = By.cssSelector("a[href*='powerimport'], #powerImportMenu, [data-testid='power-import']");
    private final By listNameField = By.id("list-name");
    private final By agentDropdown = By.id("agent-select");
    private final By fileUploadInput = By.cssSelector("input[type='file']");

    // Mapping screen placeholders
    private final By mapContinueButton = By.cssSelector("button:has-text('Continue'), button#mapContinue");
    private final By importButton = By.cssSelector("button:has-text('Import'), button#btnImport");

    private final By importSuccessMessage = By.cssSelector(".toast-success, .alert-success, #importSuccess");

    public ImportPage navigateToPowerImport() {
        driver.findElement(powerImportNav).click();
        return this;
    }

    public ImportPage enterListName(String listName) {
        WebElement el = driver.findElement(listNameField);
        el.clear();
        el.sendKeys(listName);
        return this;
    }

    public ImportPage selectAgent() {
        // TODO: implement actual agent selection logic
        driver.findElement(agentDropdown).click();
        return this;
    }

    public ImportPage uploadCSV(String filePath) {
        WebElement fileInput = driver.findElement(fileUploadInput);
        // Ensure filePath is absolute for WebDriver
        String abs = Paths.get(filePath).toAbsolutePath().toString();
        fileInput.sendKeys(abs);
        return this;
    }

    public ImportPage mapFields() {
        // TODO: map fields if mapping UI exists
        // Placeholder: continue mapping screen
        try {
            driver.findElement(mapContinueButton).click();
        } catch (Exception ignored) {
            // mapping step may not exist depending on app state
        }
        return this;
    }

    public ImportPage clickImport() {
        driver.findElement(importButton).click();
        return this;
    }

    public String verifyImportSuccess() {
        return waitFor(driver, Duration.ofSeconds(20))
                .until(d -> {
                    WebElement msg = d.findElement(importSuccessMessage);
                    String text = msg.getText();
                    return text == null ? "" : text.trim();
                });
    }
}

