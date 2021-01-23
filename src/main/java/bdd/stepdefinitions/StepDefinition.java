package bdd.stepdefinitions;


import bdd.pages.AbstractPage;
import bdd.pages.RandomIntegerPage;
import bdd.pages.RandomIntegerResultsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class StepDefinition extends BaseTest {

    AbstractPage abstractPage;
    BaseTest baseTest;
    RandomIntegerPage randomIntegerPage;
    RandomIntegerResultsPage randomIntegerResultsPage;

    @Given("^Open Chrome Browser$")
    public void open_Chrome_Browser() {
        baseTest = new BaseTest();
    }

    @When("^Open Random Integer main page$")
    public void open_Random_Integer_main_page() {
        baseTest.getWebDriver().get("https://www.random.org/");
        abstractPage = new AbstractPage(baseTest);
    }

    @Then("^Accept Cookies$")
    public void accept_Cookies() {
        randomIntegerPage.clickAllowSelected();
    }

    @Given("^Open Random Integer page$")
    public void open_Random_Integer_page() {
        baseTest.getWebDriver().get("https://www.random.org/integers/");
        randomIntegerPage = new RandomIntegerPage(baseTest);
    }

    @When("^populate min value with \"([^\"]*)\" and max value with \"([^\"]*)\"$")
    public void populate_min_value_with_and_max_value_with(String arg1, String arg2) {
        randomIntegerPage.setMin(Integer.parseInt(arg1));
        randomIntegerPage.setMax(Integer.parseInt(arg2));
    }

    @Then("^click on Get Numbers button$")
    public void click_on_Get_Numbers_button() {
        randomIntegerResultsPage = randomIntegerPage.clickGetNumbers();
    }

    @Then("^all generated numbers are with min value = \"([^\"]*)\" and max value = \"([^\"]*)\"$")
    public void all_generated_numbers_are_with_min_value_and_max_value(String arg1, String arg2) {
        randomIntegerResultsPage.verifyGeneratedNumbers(Integer.parseInt(arg1), Integer.parseInt(arg2));
    }

    @Then("^Close browser$")
    public void closeBrowser() {
        baseTest.closeDriver();
    }
}
