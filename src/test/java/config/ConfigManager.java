package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input =
                     ConfigManager.class
                             .getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                        "config.properties was not found"
                );
            }

            PROPERTIES.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load config.properties",
                    e
            );
        }
    }

    private ConfigManager() {
    }

    public static String getBaseUrl() {
        return PROPERTIES.getProperty("base.url");
    }
}