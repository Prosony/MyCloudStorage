package com.prsn.services;

import javax.enterprise.context.Dependent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

@Dependent
public class PropertiesService {

    private final Properties property = new Properties();

    public PropertiesService(){
        Path currentDir = Paths.get(".");
        File file = new File(String.valueOf(currentDir));
        for (String path : Objects.requireNonNull(file.list())){
            System.out.println("path: "+path);
        }
        System.out.println("currentDir: "+currentDir);
        FileInputStream stream = null;
        try {
            stream = new FileInputStream("/config.properties");
            property.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            property.load(this.getClass().getResourceAsStream("config.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public String getProperty(String key) {
        return property.getProperty(key);
    }
}
