# BUILD_READINESS.md

This document lists what’s ready, and what must be completed for the project to compile and run successfully.

## Compile/Run Status (as generated)

### Known blockers
- Java 17: **not detected** in this environment (no `java` / `javac` found on PATH).
- Maven: **not installed** / not available on PATH (no `mvn`).
- Selenium execution: cannot be validated until Java + Maven are installed.
- Excel test data: `src/test/resources/testdata/registration.xlsx` is a **placeholder**. It must contain required sheets and columns.

### Locator validation status
- UI locators in Page Objects are **not validated** against the actual Calley UI in this environment.

## What is already implemented (code readiness)
- Maven project skeleton + `pom.xml` + TestNG suite.
- Page Objects:
  - `RegistrationPage`
  - `LoginPage`
  - `AgentPage`
  - `ImportPage`
- Tests:
  - `SmokeTest`
  - `RegistrationTest` (**data-driven** via Excel)
  - `LoginTest`
  - `AgentTest` (**data-driven** via Excel)
  - `ImportCSVTest`
- Data-driven framework utilities:
  - `ExcelUtil` (reads XLSX -> `Object[][]`)
  - `TestDataProvider` (`@DataProvider`)
- Reporting hooks:
  - `TestListener` (screenshots on failure)
  - `ExtentManager` scaffold (generates `reports/ExtentReport.html`)

## Required Excel contents
Update/replace:
- `src/test/resources/testdata/registration.xlsx`

Sheets & columns:
1) `RegistrationData`
   - `Name`
   - `Email`
   - `Phone`
   - `Password`

2) `AgentData`
   - `AgentName`
   - `AgentEmail`
   - `AgentPhone`

## Expected first build command
Once Java 17 + Maven are installed and PATH is set:

```bash
mvn clean install
```

And to execute tests:

```bash
mvn test
```

(or `mvn -DtestngXmlFile=testng.xml test` depending on your setup)

## Notes on previously reported fixes
- `WaitUtils` was updated to remove `ExpectedConditions<T>` generics usage and replaced with a `Function<WebDriver, T>` based waiting API.
- `AgentTest` currently contains a single, DataProvider-based `addAgentSuccess` test method (no duplicate methods).
- `testng.xml` is well-formed and includes all 5 test classes.

