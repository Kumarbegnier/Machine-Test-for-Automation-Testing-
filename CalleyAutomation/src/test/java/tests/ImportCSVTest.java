package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ImportPage;
import pages.LoginPage;
import utilities.ConfigReader;

import java.nio.file.Paths;

public class ImportCSVTest extends BaseTest {

    @Test
    public void csvImportSuccess() {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");

        String uniqueListName = "List-" + System.currentTimeMillis();

        new LoginPage(driver)
                .openLoginPage(baseUrl)
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin();

        String csvPath = Paths.get("src", "test", "resources", "testdata", "sample.csv").toString();


        ImportPage page = new ImportPage(driver)
                .navigateToPowerImport()
                .enterListName(uniqueListName)
                .selectAgent()
                .uploadCSV(csvPath)
                .mapFields()
                .clickImport();

        String success = page.verifyImportSuccess();
        Assert.assertTrue(success != null && !success.isEmpty(), "Import success message should be present");
    }
}

