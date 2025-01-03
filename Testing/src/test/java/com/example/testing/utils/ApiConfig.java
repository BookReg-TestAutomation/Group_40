package com.example.testing.utils;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfig {
    private static final Properties properties = new Properties();
    private static final EnvironmentVariables environmentVariables = new SystemEnvironmentVariables();

    static {
        try (InputStream input = ApiConfig.class.getClassLoader().getResourceAsStream("serenity.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return getProperty("api.base.url", "http://localhost:7081");
    }

    public static String getAdminUsername() {
        return getProperty("api.admin.username", "admin");
    }

    public static String getAdminPassword() {
        return getProperty("api.admin.password", "password");
    }

    public static String getUserUsername() {
        return getProperty("api.user.username", "user");
    }

    public static String getUserPassword() {
        return getProperty("api.user.password", "password");
    }

    public static String getApiPath(String path) {
        return getProperty("api.path." + path, "/api/" + path);
    }

    private static String getProperty(String key, String defaultValue) {
        // First check system properties
        String value = System.getProperty(key);
        if (value != null) {
            return value;
        }

        // Then check environment variables
        value = environmentVariables.getProperty(key);
        if (value != null) {
            return value;
        }

        // Finally check properties file
        return properties.getProperty(key, defaultValue);
    }
}