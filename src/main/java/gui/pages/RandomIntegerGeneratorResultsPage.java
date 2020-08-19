package gui.pages;

import gui.attributes.RandomIntegerAttributes;
import gui.runner.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Results page for Random Integer Generator
 */
public class RandomIntegerGeneratorResultsPage extends AbstractPage {

    @FindBy(xpath = "//pre[@class = 'data']")
    private WebElement randomNumbersText;

    @FindBy(xpath = "//input[@value='Again']")
    private WebElement againButton;

    @FindBy(xpath = "//input[@value='Go Back']")
    private WebElement goBackButton;

    public static final String PAGE_HEADING = "Random Integer Generator";

    /**
     * Constructor
     *
     * @param testClass
     */
    public RandomIntegerGeneratorResultsPage(BaseTest testClass) {
        super(testClass);
        waitTillElementIsVisible(navigationMenu);
    }

    /**
     * Click on 'Again' button
     *
     * @return RandomIntegerGeneratorResultsPage
     */
    public RandomIntegerGeneratorResultsPage clickAgain() {
        waitTillElementIsClickable(againButton);
        againButton.click();
        return new RandomIntegerGeneratorResultsPage(testClass);
    }

    /**
     * Click on 'Go Back' button
     *
     * @return RandomIntegerGeneratorPage
     */
    public RandomIntegerGeneratorPage clickGoBack() {
        waitTillElementIsClickable(goBackButton);
        goBackButton.click();
        return new RandomIntegerGeneratorPage(testClass);
    }

    /**
     * Get text of displayed random numbers
     *
     * @return String
     */
    public String getRandomNumbers() {
        waitTillElementIsVisible(randomNumbersText);
        return randomNumbersText.getText();
    }

    /**
     * Verify that all generated random numbers are in expected range (inclusive)
     *
     * @param min
     * @param max
     */
    public void verifyGeneratedNumbers(int min,  int max) {
        List<Integer> list = Arrays.stream(getRandomNumbers().replace("\n", " ").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        for (Integer number : list) {
            Assert.assertTrue("Generated number wasn't at expected range", number >= min && number <= max);
        }
    }

    /**
     * Verify that all generated random numbers are in expected range (inclusive)
     *
     * @param randomIntegerAttributes
     * @throws IOException
     */
    public void verifyGeneratedNumbers(RandomIntegerAttributes randomIntegerAttributes) throws IOException{
        if (randomIntegerAttributes.getMin() != null && randomIntegerAttributes.getMax() != null) {
            verifyGeneratedNumbers(randomIntegerAttributes.getMin(), randomIntegerAttributes.getMax());
        }
        else {
            throw new IOException("Incorrect attributes");
        }
    }
}
