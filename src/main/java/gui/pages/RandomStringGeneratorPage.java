package gui.pages;

import gui.runner.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Random String Generator page
 */
public class RandomStringGeneratorPage extends AbstractPage {

    @FindBy(xpath = "//input[@value='Go Back']")
    private WebElement goBackButton;

    public static final String PAGE_HEADING = "Random String Generator";

    /**
     * Constructor
     *
     * @param testClass
     */
    public RandomStringGeneratorPage(BaseTest testClass) {
        super(testClass);
        waitTillElementIsVisible(navigationMenu);
    }

}
