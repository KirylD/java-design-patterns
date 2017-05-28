package by.developer.singleton.example;

import com.sun.istack.internal.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * @author Kiryl.Drabysheuski
 * @since 1.0.0
 */
public final class Configuration {

    private static final Logger LOG = LogManager.getLogger(Configuration.class);

    public static final String PROJECT_NAME = "project.name";
    public static final String PATTERN_CURRENT_NAME = "pattern.current.name";

    private static volatile Configuration INSTANCE = null;

    private Properties properties = null;

    private Configuration() {
        properties = new Properties();
        String pathFile = "singleton/configuration.properties";
        URLClassLoader urlClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();

        try {
            URL url = urlClassLoader.getResource(pathFile);
            LOG.debug("url is {}", url);
            String path = URLDecoder.decode(url.getPath(), "UTF-8");
            LOG.debug("decoded path is {}", path);

            FileInputStream fis = new FileInputStream(path);
            properties.load(fis);
        } catch (FileNotFoundException e) {
            LOG.error("Configuration file doesn't exist", e);
        } catch (IOException e) {
            LOG.error("Configuration file cannot be loaded.", e);
        }
    }

    public static Configuration getInstance() {
        if (INSTANCE == null) {
            synchronized (Configuration.class) {
                if (INSTANCE == null) INSTANCE = new Configuration();
            }
        }
        return INSTANCE;
    }

    @Nullable
    public String getProperty(String key) {
        LOG.info("Requested key is {}", key);
        if (properties.containsKey(key)) {
            return (String) properties.get(key);
        } else {
            LOG.warn("Requested key '{}' was not found.", key);
            return null;
        }
    }

}
