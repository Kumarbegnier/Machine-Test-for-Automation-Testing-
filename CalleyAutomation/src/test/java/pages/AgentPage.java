package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static utilities.WaitUtils.waitFor;

public class AgentPage {

    private final WebDriver driver;

    public AgentPage(WebDriver driver) {
        this.driver = driver;
    }

    // TODO: verify locators once UI is confirmed
    private final By agentsNav = By.cssSelector("a[href*='agents'], #agentsMenu, [data-testid='agents']");
    private final By addAgentButton = By.cssSelector("button:has-text('Add Agent'), button#addAgent, [data-testid='add-agent']");

    private final By agentNameField = By.id("agent-name");
    private final By agentEmailField = By.id("agent-email");
    private final By agentPhoneField = By.id("agent-phone");
    private final By saveButton = By.cssSelector("button[type='submit'], button:has-text('Save')");

    private final By agentSuccessMessage = By.cssSelector(".toast-success, .alert-success, #agentSuccess");

    public AgentPage navigateToAgents() {
        driver.findElement(agentsNav).click();
        return this;
    }

    public AgentPage clickAddAgent() {
        driver.findElement(addAgentButton).click();
        return this;
    }

    public AgentPage enterAgentName(String name) {
        WebElement el = driver.findElement(agentNameField);
        el.clear();
        el.sendKeys(name);
        return this;
    }

    public AgentPage enterAgentEmail(String email) {
        WebElement el = driver.findElement(agentEmailField);
        el.clear();
        el.sendKeys(email);
        return this;
    }

    public AgentPage enterAgentPhone(String phone) {
        WebElement el = driver.findElement(agentPhoneField);
        el.clear();
        el.sendKeys(phone);
        return this;
    }

    public AgentPage saveAgent() {
        driver.findElement(saveButton).click();
        return this;
    }

    public String verifyAgentAdded() {
        return waitFor(driver, Duration.ofSeconds(15))
                .until(d -> {
                    WebElement msg = d.findElement(agentSuccessMessage);
                    String text = msg.getText();
                    return text == null ? "" : text.trim();
                });
    }
}

