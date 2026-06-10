package com.orderflow.database;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseConfig {
    private static final Logger logger = Logger.getLogger(DatabaseConfig.class.getName());
    private Properties properties;

    public DatabaseConfig() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                logger.warning("Sorry, unable to find application.properties");
                return;
            }
            properties.load(input);
        } catch (Exception ex) {
            logger.severe("Exception loading properties: " + ex.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
