package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import utilities.WaitUtils;

public class SmokeTest extends BaseTest {

    @Test
    public void launchTest() {
        WaitUtils.waitUntilPageTitleNotEmpty(driver, Duration.ofSeconds(15));
        Assert.assertNotNull(driver.getTitle(), "Page title should not be null");
        Assert.assertFalse(driver.getTitle().trim().isEmpty(), "Page title should not be empty");
    }
}

