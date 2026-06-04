package utilities;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.nio.file.Path;

public class TestDataProvider {

    private static final String EXCEL_FILE = "src/test/resources/testdata/registration.xlsx";

    @DataProvider(name = "registrationData")
    public static Object[][] registrationData() {
        String file = toAbsolute(EXCEL_FILE);
        return ExcelUtil.readExcel(file, "RegistrationData");
    }

    @DataProvider(name = "agentData")
    public static Object[][] agentData() {
        String file = toAbsolute(EXCEL_FILE);
        return ExcelUtil.readExcel(file, "AgentData");
    }

    private static String toAbsolute(String relativeOrAbsolutePath) {
        try {
            Path p = Path.of(relativeOrAbsolutePath);
            if (!p.isAbsolute()) {
                return p.toAbsolutePath().toString();
            }
            return relativeOrAbsolutePath;
        } catch (Exception e) {
            // fallback
            return new File(relativeOrAbsolutePath).getAbsolutePath();
        }
    }
}

