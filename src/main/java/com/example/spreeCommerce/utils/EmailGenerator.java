package com.example.spreeCommerce.utils;
import java.util.UUID;

public class EmailGenerator {

    public static String generateUniqueEmail() {
        String uuid = UUID.randomUUID().toString().substring(0, 8); // Optional: shorten
        return "user" + uuid + "@example.com";
    }
}