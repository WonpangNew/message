package bupt.zou.message.util;

import java.io.IOException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author zoujian
 * @Date 2019-01-15
 */
@Slf4j
public class Utils {

    private static Properties properties;

    static {
        properties = new Properties();
    }

    private static final String propertiesName = "config.properties";

    public static String getStringValue(String key) {
        String value = null;
        try {
            properties.load(Utils.class.getClassLoader()
                    .getResourceAsStream(propertiesName));
            value = properties.getProperty(key);
        } catch (IOException e) {
            log.error("properties file loads error, {}", e);
        }
        return value == null ? "" : value;
    }

    public static int getIntValue(String key) {
        Integer value = null;
        try {
            properties.load(Utils.class.getClassLoader()
                    .getResourceAsStream(propertiesName));
            value = Integer.parseInt(properties.getProperty(key));
        } catch (IOException e) {
            log.error("properties file loads error, {}", e);
        }
        return value;
    }

}
