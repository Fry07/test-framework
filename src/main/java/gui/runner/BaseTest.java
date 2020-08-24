package gui.runner;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class BaseTest {

    private final WebDriverWait wait;
    private final WebDriver webDriver;
    Logger logger = LoggerFactory.getLogger(BaseTest.class);
    FileInputStream fileInputStream;
    Properties properties = new Properties();

    @Before
    public void setUp() {
        try {
            fileInputStream = new FileInputStream("src/main/resources/properties/gui_config.properties");
            properties.load(fileInputStream);
            String baseUrl = properties.getProperty("baseUrl");
            webDriver.get(baseUrl);
            log(String.format("Opened %s page", baseUrl));
        } catch (IOException e) {
            System.err.println("Properties file was not found");
        }
    }

    @After
    public void tearDown() {
        closeDriver();
    }

    /**
     * Constructor
     */
    public BaseTest() {
        Driver driver = Driver.getInstance(Browser.CHROME);
        webDriver = driver.getDriver();

        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    /**
     * Get WebDriver
     *
     * @return WebDriver
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * Get WebDriverWait
     *
     * @return WebDriverWait
     */
    public WebDriverWait getWait() {
        return wait;
    }

    /**
     * Close browser and driver
     */
    public void closeDriver() {
            webDriver.quit();
    }

    /**
     * Log info message
     *
     * @param logMessage
     */
    public void log(String logMessage) {
        logger.info(logMessage);
    }
}