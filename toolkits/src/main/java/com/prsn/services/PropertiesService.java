package com.prsn.services;

import org.apache.commons.configuration.*;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.Dependent;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

/**
 * @author プロソニーPRSN
 */
@Dependent
public class PropertiesService {

    private final Properties property = new Properties();

    /**
     * path to configs
     */
    public static final String CONFIG_PATH_PROPERTY = "ru.netbynet.toolkit.config.path";

    /**
     * Download configuration file.
     * @param className Any class of application based on its location.
     * @param config obviously.
     * @param configName u have three attempt to guess what is it yo? .
     */
    public static void loadConfig(Class<?> className, CompositeConfiguration config, String configName) {
        PropertiesConfiguration properties;
        try {
            if(new File(configName).isAbsolute()) {
                properties = new PropertiesConfiguration(ConfigurationUtils.locate(
                        FilenameUtils.getFullPathNoEndSeparator(configName), FilenameUtils.getName(configName))
                );
            } else {
                properties = new PropertiesConfiguration(
                        ConfigurationUtils.locate(getConfigPath(className), configName));
            }
            properties.setEncoding("UTF-8");
            properties.setReloadingStrategy(new FileChangedReloadingStrategy());
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
        config.addConfiguration(properties);
    }

    /**
     * Download file from file system or resources.
     * @param className Any class of application based on its location.
     * @param fileName obviously...File name.
     */
    public static InputStream loadFile(Class<?> className, String fileName) throws Exception {
        URL fileUrl;
        if(new File(fileName).isAbsolute()) {
            fileUrl = ConfigurationUtils.locate(
                    FilenameUtils.getFullPathNoEndSeparator(fileName), FilenameUtils.getName(fileName));
        } else {
            fileUrl = ConfigurationUtils.locate(
                    getConfigPath(className), fileName);
        }

        return FileSystem.getDefaultFileSystem().getInputStream(fileUrl);
    }

    /**
     * Path to the directory where the application configuration files are located ().
     * @param className Any class of application based on its location.
     */
    public static String getConfigPath(Class<?> className)  {
        return System.getProperty(CONFIG_PATH_PROPERTY, getAppPath(className));
    }

    /**
     * Path to the file system application.
     * @param className Any class of application based on its location.
     */
    public static String getAppPath(Class<?> className)  {
        String rawPath = className.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = StringUtils.EMPTY;
        try {
            decodedPath = URLDecoder.decode(rawPath, "UTF-8");
        } catch (UnsupportedEncodingException e) { }

        return "file://" + FilenameUtils.getFullPath(decodedPath);
    }


    public String getProperty(String key) {
        return property.getProperty(key);
    }
}
