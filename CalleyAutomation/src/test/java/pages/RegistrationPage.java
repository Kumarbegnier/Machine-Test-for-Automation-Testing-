package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static utilities.WaitUtils.waitFor;

public class RegistrationPage {

    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    // TODO: verify locators once UI is confirmed
    private final By nameField = By.id("registration-name");
    private final By emailField = By.id("registration-email");
    private final By phoneField = By.id("registration-phone");
    private final By passwordField = By.id("registration-password");
    private final By calleyTeamsPlanRadio = By.id("plan-calley-teams");
    private final By registerButton = By.cssSelector("button[type='submit']");
    private final By successMessage = By.cssSelector(".toast-success, .alert-success, #successMessage");

    public RegistrationPage openRegistrationPage(String baseUrl) {
        driver.navigate().to(baseUrl + "/registration.aspx");
        return this;
    }

    public RegistrationPage enterName(String name) {
        WebElement el = driver.findElement(nameField);
        el.clear();
        el.sendKeys(name);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        WebElement el = driver.findElement(emailField);
        el.clear();
        el.sendKeys(email);
        return this;
    }

    public RegistrationPage enterPhone(String phone) {
        WebElement el = driver.findElement(phoneField);
        el.clear();
        el.sendKeys(phone);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        WebElement el = driver.findElement(passwordField);
        el.clear();
        el.sendKeys(password);
        return this;
    }

    public RegistrationPage selectCalleyTeamsPlan() {
        driver.findElement(calleyTeamsPlanRadio).click();
        return this;
    }

    public RegistrationPage clickRegister() {
        driver.findElement(registerButton).click();
        return this;
    }

    public String getSuccessMessage() {
        return waitFor(driver, Duration.ofSeconds(15))
                .until(d -> {
                    WebElement msg = d.findElement(successMessage);
                    String text = msg.getText();
                    return text == null ? "" : text.trim();
                });
    }
}

