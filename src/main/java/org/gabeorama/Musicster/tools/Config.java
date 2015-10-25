package org.gabeorama.Musicster.tools;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by HaavardM on 4/18/2015.
 */
public class Config {

    /*Config values*/
    public static volatile int socketRWTimeout;




    //Properties object to load from file
    private static Properties properties = new Properties();

    //Load new config
    public static void loadConfig(String configName) {
        try {
            properties.load(new BufferedReader(new InputStreamReader(Config.class.getClass().getResourceAsStream(configName))));
            socketRWTimeout = Integer.parseInt(getProperty("read_write_timeout"));

        } catch (IOException | NumberFormatException e) {
            //TODO Handle exception
            e.printStackTrace();
        }

    }

    //Get property value
    public static String getProperty(String prop) {
        return properties.getProperty(prop);
    }

    //Set new property
    public static void setProperty(String prop, String value) {
        properties.setProperty(prop, value);
    }
}
