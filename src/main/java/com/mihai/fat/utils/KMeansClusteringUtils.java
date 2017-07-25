package com.mihai.fat.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class serves as a tool to be used various operations related to the K-Means clustering implementation.
 */
public class KMeansClusteringUtils {

    /**
     * This method is used to read from the {@code config.properties} file to determine which of the distance
     * calculators are to be used by the K-Means clustering processor.
     *
     * @return  name of the distance calculator
     */
    public static String obtainDistanceCalculatorName() {
        Properties properties = new Properties();
        try (InputStream is = new FileInputStream("config.properties")) {
            properties.load(is);
            return properties.getProperty("distanceCalculator");
        } catch (IOException e) {
            return StringUtils.EMPTY;
        }

    }

}
