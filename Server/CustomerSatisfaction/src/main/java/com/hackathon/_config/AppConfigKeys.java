package com.hackathon._config;

import com.hackathon.util.UtilApps;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class AppConfigKeys {

    private Properties propertiesApi = null;
    private Properties propertiesMessage = null;
    private static AppConfigKeys instance = null;

    private static final String NAME_FILE_API_PROPERTIES = "apiConfig.properties";
    private static final String NAME_FILE_MESSAGE_PROPERTIES = "messages.properties";

    public static AppConfigKeys getInstance() {
        if (instance == null) {
            instance = new AppConfigKeys();
        }
        return instance;
    }

    //TODO log in file

    public AppConfigKeys() {
        propertiesApi = new Properties();
        propertiesMessage = new Properties();
        initProperties(propertiesApi, NAME_FILE_API_PROPERTIES);
        initProperties(propertiesMessage, NAME_FILE_MESSAGE_PROPERTIES);
    }


    private void initProperties(Properties properties, String fileName) {

        File f = new File("/" + fileName);
        InputStream inputStream;
        try {
            if (f.exists()) {
                //if file exists use as default configuration
                inputStream = new FileInputStream(f);
            } else {
                //if the configuration file is not exists. Use the default file
                inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            }
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getApiPropertyValue(String key) {
        try {
            return UtilApps.trim(propertiesApi.getProperty(key));
        } catch (Exception e) {
            return null;
        }
    }

    public double getApiPropertyDouble(String key) {
        try {
            String value = getApiPropertyValue(key);
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0d;
        }
    }

    public String getMessagePropertyValue(String key) {
        try {
            return UtilApps.trim(propertiesMessage.getProperty(key));
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(AppConfigKeys.getInstance().getMessagePropertyValue("message.test"));
        System.out.println(AppConfigKeys.getInstance().getApiPropertyValue("api.person.group.train.person"));
    }
}
