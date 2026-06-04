# CalleyAutomation

## Overview
Selenium + TestNG framework skeleton for Calley web automation.

## Tech Stack
- Java 17
- Selenium WebDriver
- TestNG
- WebDriverManager
- Apache POI
- Extent Reports
- Log4j2

## Framework Architecture
- `base/BaseTest.java`: WebDriver bootstrap + lifecycle (`@BeforeMethod` / `@AfterMethod`).
- `utilities/*`: common helpers (config reading, waits, screenshots).
- `pages/*`: Page Object classes encapsulating UI actions.
- `tests/*`: TestNG test classes (Smoke, Registration, Login, Agent, CSV Import).

## POM Design
Dependencies declared in `pom.xml`:
- Selenium (`selenium-java`)
- WebDriverManager
- TestNG
- Apache POI
- Extent Reports
- Log4j2

Build plugin:
- Surefire configured for TestNG.

## Data Driven Design
- Config: `src/test/resources/config/config.properties`
- CSV test data: `src/test/resources/testdata/sample.csv`
- Excel placeholder: `src/test/resources/testdata/registration.xlsx`

(Excel/CSV dataprovider utilities can be added next; currently the framework is locator- and flow-ready.)

## Execution
After installing Java 17 and Maven:
```bash
mvn clean test
```

To run the full suite configured in `testng.xml`.

## Framework Structure
```
src/test/java
  base
    BaseTest.java
  utilities
    ConfigReader.java
    WaitUtils.java
    ScreenshotUtil.java
  pages
    LoginPage.java
    RegistrationPage.java
    AgentPage.java
    ImportPage.java
  tests
    SmokeTest.java
    RegistrationTest.java
    LoginTest.java
    AgentTest.java
    ImportCSVTest.java
```

## Notes
Locators are currently implemented as TODO/placeholder-friendly selectors and may require minor adjustment after you confirm the actual DOM.

