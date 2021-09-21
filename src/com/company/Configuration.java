package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static Properties propFileContent;
    public static void loadConfigurations() throws IOException {
        InputStream inputStream = null;
        Properties prop = new Properties();

        try {
            File initialFile = new File("C:\\Users\\mgeorgiev29\\IdeaProjects\\Pro\\resources\\config.properties");

            inputStream = new FileInputStream(initialFile);
            prop.load(inputStream);
        } catch (Exception e){
            System.out.println("Exception: " + e); 
        } finally {
            inputStream.close();
            propFileContent = prop;
        }
    }

    public static String readUsername(){
        return propFileContent.get("username").toString();
    }

    public static String readPass(){
        return propFileContent.get("pass").toString();
    }

    public static String readScreenshotDirectory(){
        return propFileContent.get("screenshotDirectory").toString();
    }

    public static String readBaseUrl(){
        return propFileContent.get("baseUrl").toString();
    }

    public static String readFilesDirectory(){
        return propFileContent.get("filesDirectory").toString();
    }
}
