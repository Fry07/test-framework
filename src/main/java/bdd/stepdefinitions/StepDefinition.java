package bdd.stepdefinitions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(Cucumber.class)
public class StepDefinition {

    @FindBy(xpath = "//button[.='Allow Selected']")
    private WebElement allowSelectedButton;

    @FindBy(xpath = "//input[@name='min']")
    private WebElement minInput;

    @FindBy(xpath = "//input[@name='max']")
    private WebElement maxInput;

    @FindBy(xpath = "//input[@value='Get Numbers']")
    private WebElement getNumbersButton;

    @FindBy(xpath = "//pre[@class = 'data']")
    private WebElement randomNumbersText;

    WebDriver driver;

    @Given("^Open Chrome Browser$")
    public void open_Chrome_Browser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(
                "excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
    }

    @When("^Open Random Integer main page$")
    public void open_Random_Integer_main_page() {
        driver.get("https://www.random.org/");
    }

    @Then("^Accept Cookies$")
    public void accept_Cookies() {
        waitTillElementIsVisible(allowSelectedButton);
        allowSelectedButton.click();
    }

    @Given("^Open Random Integer page$")
    public void open_Random_Integer_page() {
        driver.get("https://www.random.org/integers/");
    }

    @When("^populate min value with \"([^\"]*)\" and max value with \"([^\"]*)\"$")
    public void populate_min_value_with_and_max_value_with(String arg1, String arg2) {
        waitTillElementIsVisible(minInput);
        minInput.sendKeys(arg1);
        maxInput.sendKeys(arg2);
    }

    @Then("^click on Get Numbers button$")
    public void click_on_Get_Numbers_button() {
        waitTillElementIsVisible(getNumbersButton);
        getNumbersButton.click();
    }

    @Then("^all generated numbers are with min value = \"([^\"]*)\" and max value = \"([^\"]*)\"$")
    public void all_generated_numbers_are_with_min_value_and_max_value(String arg1, String arg2) {
        waitTillElementIsVisible(randomNumbersText);
        List<Integer> list = Arrays.stream(randomNumbersText.getText().replace("\n", " ").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        for (Integer number : list) {
            Assert.assertTrue("Generated number wasn't at expected range",
                    number >= Integer.parseInt(arg1) && number <= Integer.parseInt(arg2));
        }
    }

    @And("^Close Driver$")
    public void closeDriver() {
        driver.quit();
    }

    protected void waitTillElementIsVisible(WebElement element) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
    }
}
