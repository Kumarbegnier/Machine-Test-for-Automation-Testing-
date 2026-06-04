package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utilities.ConfigReader;

public class RegistrationTest extends BaseTest {

    @Test(dataProvider = "registrationData", dataProviderClass = utilities.TestDataProvider.class)
    public void registrationSuccess_calleyTeams(String name, String email, String phone, String password) {
        String baseUrl = ConfigReader.getProperty("baseUrl");

        RegistrationPage page = new RegistrationPage(driver)
                .openRegistrationPage(baseUrl)
                .enterName(name)
                .enterEmail(email)
                .enterPhone(phone)
                .enterPassword(password)
                .selectCalleyTeamsPlan()
                .clickRegister();

        String success = page.getSuccessMessage();
        Assert.assertTrue(success != null && !success.isEmpty(), "Registration success message should be present");
    }

}

