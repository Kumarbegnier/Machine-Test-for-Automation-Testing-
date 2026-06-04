package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void loginSuccess() {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");

        LoginPage page = new LoginPage(driver)
                .openLoginPage(baseUrl)
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin();

        Assert.assertTrue(page.isDashboardVisible(), "Dashboard should be visible after login");
    }
}

