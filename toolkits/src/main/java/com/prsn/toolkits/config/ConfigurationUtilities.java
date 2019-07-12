package com.prsn.toolkits.config;


import org.apache.commons.configuration.*;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Configuration.
 * @author プロソニーPRSN
 */
public final class ConfigurationUtilities {

    /**
     * Path to configs.
     */
    public static final String CONFIG_PATH_PROPERTY = "com.prsn.domain.services.config.path";

    /**
     * Load config file.
     * @param className Any class of application, based on it is the location of the application.
     * @param config config file.
     * @param configName file name.
     */
    public static void loadConfig(Class<?> className, CompositeConfiguration config, String configName) {
        PropertiesConfiguration properties;
        try {
            if(new File(configName).isAbsolute()) {
                properties = new PropertiesConfiguration(
                        ConfigurationUtils.locate(FilenameUtils.getFullPathNoEndSeparator(configName), FilenameUtils.getName(configName)));
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
     * Download a file from the file system or resources.
     * @param className Any class of application, based on it is the location of the application.
     * @param fileName file name.
     */
    public static InputStream loadFile(Class<?> className, String fileName)
            throws Exception {
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
     * Path to the directory where the application configuration files are located.
     * @param className Any class of application, based on it is the location of the application.
     */
    public static String getConfigPath(Class<?> className)  {
        return System.getProperty(CONFIG_PATH_PROPERTY, getAppPath(className));
    }

    /**
     * Path to app in FS
     * @param className Any class of application, based on it is the location of the application.
     */
    public static String getAppPath(Class<?> className)  {
        String rawPath = className.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = StringUtils.EMPTY;
        try {
            decodedPath = URLDecoder.decode(rawPath, "UTF-8");
        } catch (UnsupportedEncodingException e) { }

        return "file://" + FilenameUtils.getFullPath(decodedPath);
    }

}

