package gui.pages;

import gui.attributes.RandomIntegerAttributes;
import gui.runner.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Random Integer Generator page
 */
public class RandomIntegerGeneratorPage extends AbstractPage {

    @FindBy(xpath = "//input[@name='num']")
    private WebElement numberInput;

    @FindBy(xpath = "//input[@name='min']")
    private WebElement minInput;

    @FindBy(xpath = "//input[@name='max']")
    private WebElement maxInput;

    @FindBy(xpath = "//input[@name='col']")
    private WebElement columnFormatInput;

    @FindBy(xpath = "//input[@value='Get Numbers']")
    private WebElement getNumbersButton;

    @FindBy(xpath = "//input[@value='Reset Form']")
    private WebElement resetFormButton;

    @FindBy(xpath = "//input[@value='Switch to Advanced Mode']")
    private WebElement switchToAdvancedModeButton;

    public static final String PAGE_HEADING = "Random Integer Generator";

    /**
     * Constructor
     *
     * @param testClass
     */
    public RandomIntegerGeneratorPage(BaseTest testClass) {
        super(testClass);
        waitTillElementIsVisible(navigationMenu);
    }

    /**
     * CLick on 'Get Numbers' button
     *
     * @return RandomIntegerGeneratorResultsPage
     */
    public RandomIntegerGeneratorResultsPage clickGetNumbers() {
        waitTillElementIsClickable(getNumbersButton);
        getNumbersButton.click();
        return new RandomIntegerGeneratorResultsPage(testClass);
    }

    /**
     * Populate all input fields
     *
     * @param randomIntegerAttributes
     */
    public void fillInFields(RandomIntegerAttributes randomIntegerAttributes) {
        if (randomIntegerAttributes.getNumbers() != null) {
            setNumbers(randomIntegerAttributes.getNumbers());
        }
        if (randomIntegerAttributes.getMin() != null) {
            setMin(randomIntegerAttributes.getMin());
        }
        if (randomIntegerAttributes.getMax() != null) {
            setMax(randomIntegerAttributes.getMax());
        }
    }

    /**
     * Populate count numbers
     *
     * @param numbers
     */
    public void setNumbers(int numbers) {
        typeIn(numberInput, String.valueOf(numbers));
    }

    /**
     * Populate minimum value
     *
     * @param min
     */
    public void setMin(int min) {
        typeIn(minInput, String.valueOf(min));
    }

    /**
     * Populate maximum value
     *
     * @param max
     */
    public void setMax(int max) {
        typeIn(maxInput, String.valueOf(max));
    }
}
