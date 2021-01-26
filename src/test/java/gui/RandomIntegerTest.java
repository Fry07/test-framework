package gui;

import common.RunBrowser;
import gui.attributes.RandomIntegerAttributes;
import gui.pages.AbstractPage;
import gui.pages.RandomIntegerGeneratorPage;
import gui.pages.RandomIntegerGeneratorResultsPage;
import gui.pages.RandomStringGeneratorPage;
import gui.runner.BaseTest;
import gui.runner.Browser;
import org.junit.Test;

import java.io.IOException;

import static common.Log.log;


public class RandomIntegerTest extends BaseTest {

    @Test
    @RunBrowser(browser = Browser.CHROME)
    public void randomIntegerPositiveTest() throws IOException {
        // Open random.org page
        AbstractPage abstractPage = new AbstractPage(this);

        // Click on Numbers -> Integers menu
        RandomIntegerGeneratorPage randomIntegerGeneratorPage = abstractPage.clickIntegers();
        randomIntegerGeneratorPage.verifyPageHeading(RandomIntegerGeneratorPage.PAGE_HEADING);
        log("Clicked on Numbers -> Integers menu");

        // Populate count, min and max values
        RandomIntegerAttributes randomIntegerAttributes = new RandomIntegerAttributes.RandomIntegerAttributesBuilder()
                .numbers(50).min(25).max(30).build();
        randomIntegerGeneratorPage.fillInFields(randomIntegerAttributes);
        log("Populated count, min and max values");

        // Click on 'Get Numbers' button
        RandomIntegerGeneratorResultsPage randomIntegerGeneratorResultsPage = randomIntegerGeneratorPage.clickGetNumbers();
        log("Clicked on 'Get Numbers' button");

        // Verify page heading. Verify that all numbers are in range entered previously
        randomIntegerGeneratorPage.verifyPageHeading(RandomIntegerGeneratorResultsPage.PAGE_HEADING);
        randomIntegerGeneratorResultsPage.verifyGeneratedNumbers(randomIntegerAttributes);
        log("Verified page heading. Verified that all numbers are in range entered previously");

        // Click on Lists & More -> Strings menu
        RandomStringGeneratorPage randomStringGeneratorPage = randomIntegerGeneratorPage.clickStrings();
        log("Clicked on Lists & More -> Strings menu");

        // Verify page heading
        randomStringGeneratorPage.verifyPageHeading(RandomStringGeneratorPage.PAGE_HEADING);
        log("Verified page heading");
    }
}
