package bdd.stepdefinitions;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BaseTest {

    private final WebDriverWait wait;
    private final WebDriver webDriver;

    @After
    public void tearDown() {
        closeDriver();
    }

    /**
     * Constructor
     */
    public BaseTest() {
        Driver driver = Driver.getInstance();
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
    public void
    closeDriver() {
        webDriver.quit();
    }
}