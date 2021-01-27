package gui.runner;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Collections;

import static common.Log.log;
import static org.openqa.selenium.PageLoadStrategy.NORMAL;

/**
 * Initialisation of WebDriver
 */
public class Driver {

    private static Driver INSTANCE;
    private WebDriver driver;

    /**
     * Private constructor that prevents creation of the object outside the class
     */
    private Driver(Browser browser) {
        BasicConfigurator.configure();
        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption(
                    "excludeSwitches", Collections.singletonList("enable-automation"));
                options.setExperimentalOption("useAutomationExtension", false);
                options.setPageLoadStrategy(NORMAL);
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(NORMAL);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                log("Unsupported browser");
                break;
        }
    }

    /**
     * Getter instance of Driver
     *
     * @return Driver
     */
    public static Driver getInstance(Browser browser) {
        if (INSTANCE == null) {
            INSTANCE = new Driver(browser);
        }
        return INSTANCE;
    }

    /**
     * Getter for WebDriver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }
}
