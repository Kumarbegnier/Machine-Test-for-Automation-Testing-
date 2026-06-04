package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AgentPage;
import pages.LoginPage;
import utilities.ConfigReader;

public class AgentTest extends BaseTest {

    @Test(dataProvider = "agentData", dataProviderClass = utilities.TestDataProvider.class)
    public void addAgentSuccess(String agentName, String agentEmail, String agentPhone) {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");

        new LoginPage(driver)
                .openLoginPage(baseUrl)
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin();

        AgentPage agentPage = new AgentPage(driver)
                .navigateToAgents()
                .clickAddAgent()
                .enterAgentName(agentName)
                .enterAgentEmail(agentEmail)
                .enterAgentPhone(agentPhone)
                .saveAgent();

        String success = agentPage.verifyAgentAdded();
        Assert.assertTrue(success != null && !success.isEmpty(), "Agent added success message should be present");
    }

}

