package com.example.spreeCommerce.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
//    public String readConfig(String config){
//        Properties properties = new Properties();
//
//        try(FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties")){
//            properties.load(fis);
//            return properties.getProperty(config);
//        } catch(IOException e){
//            return null;
//        }
//    }

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String readConfig(String key) {
        return properties.getProperty(key);
    }
}
