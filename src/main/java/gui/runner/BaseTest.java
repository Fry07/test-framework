package gui.runner;

import common.RunBrowser;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import static common.Log.log;


public class BaseTest {

    private final WebDriverWait wait;
    private final WebDriver webDriver;
    FileInputStream fileInputStream;
    Properties properties = new Properties();

    @Before
    public void setUp() {
        try {
            fileInputStream = new FileInputStream("src/main/resources/properties/gui_config.properties");
            properties.load(fileInputStream);
            String baseUrl = properties.getProperty("baseUrlGui");
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
        // default browser
       Browser browser = Browser.FIREFOX;

        try {
            for (Method method : BaseTest.class.getClassLoader().loadClass("gui.RandomIntegerTest").getMethods()) {
                if (method.isAnnotationPresent(common.RunBrowser.class)) {
                    RunBrowser runBrowser = method.getAnnotation(RunBrowser.class);
                    browser = runBrowser.browser();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Driver driver = Driver.getInstance(browser);
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
}