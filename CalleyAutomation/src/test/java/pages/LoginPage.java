package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static utilities.WaitUtils.waitFor;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // TODO: verify locators once UI is confirmed
    private final By emailField = By.id("login-email");
    private final By passwordField = By.id("login-password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By dashboardMarker = By.cssSelector(".dashboard, [data-testid='dashboard']");

    public LoginPage openLoginPage(String baseUrl) {
        driver.navigate().to(baseUrl + "/login.aspx");
        return this;
    }

    public LoginPage enterEmail(String email) {
        WebElement el = driver.findElement(emailField);
        el.clear();
        el.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        WebElement el = driver.findElement(passwordField);
        el.clear();
        el.sendKeys(password);
        return this;
    }

    public LoginPage clickLogin() {
        driver.findElement(loginButton).click();
        return this;
    }

    public boolean isDashboardVisible() {
        try {
            waitFor(driver, Duration.ofSeconds(15))
                    .until(d -> d.findElement(dashboardMarker).isDisplayed());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

