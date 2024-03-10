package com.sebadio.ciel.helpers;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static Dotenv config;

    public static void load() {
        if (config == null) {
            config = Dotenv.configure().load();
        }
    }

    public static Dotenv get() {
        if (config == null) {
            load();
        }
        return config;
    }
}
