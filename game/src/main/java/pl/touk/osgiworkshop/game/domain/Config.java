/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.domain;

import java.io.IOException;
import java.util.Properties;

/**
 * @author arkadius
 */
public class Config {
    private static Config INSTANCE = new Config();

    public static Config getInstance() {
        return INSTANCE;
    }

    private Properties props = new Properties();

    private Config() {
        try {
            props.load(getClass().getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    public double getDouble(String key) {
        return Double.parseDouble(getString(key));
    }

    public String getString(String key) {
        return props.getProperty(key);
    }

}
