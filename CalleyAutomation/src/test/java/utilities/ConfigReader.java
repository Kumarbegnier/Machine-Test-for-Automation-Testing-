package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static boolean loaded = false;

    private static synchronized void loadIfNeeded() {
        if (loaded) {
            return;
        }
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (in == null) {
                throw new IllegalStateException("Could not find config/config.properties on classpath");
            }
            properties.load(in);
            loaded = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config/config.properties", e);
        }
    }

    public static String getProperty(String key) {
        loadIfNeeded();
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing config key: " + key);
        }
        return value;
    }
}

