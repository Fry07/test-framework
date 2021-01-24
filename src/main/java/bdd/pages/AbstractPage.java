package bdd.pages;

import bdd.runner.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Abstract page for common WebElements and methods
 */
public class AbstractPage {

    protected BaseTest testClass;

    //
    // WebElements
    //
    private static final String NAVIGATION_MENU = "//ul[@id='navigation']";

    @FindBy(xpath = NAVIGATION_MENU)
    protected WebElement navigationMenu;

    @FindBy(xpath = "//button[.='Allow Selected']")
    private WebElement allowSelectedButton;


    /**
     * Constructor
     */
    public AbstractPage(BaseTest testClass) {
        PageFactory.initElements(testClass.getWebDriver(), this);
        //waitTillElementIsVisible(navigationMenu);
//        if (!cookiesAccepted) {
//            clickAllowSelected();
//        }
    }

    /**
     * Click on 'Allow Selected' button for accepting cookies policy
     */
    public void clickAllowSelected() {
        //waitTillElementIsClickable(allowSelectedButton);
        //Thread.sleep(2500);
        allowSelectedButton.click();
    }

    /**
     * Wait till element is visible
     *
     * @param element
     */
    protected void waitTillElementIsVisible(WebElement element) {
        testClass.getWait().until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait till element is clickable
     *
     * @param element
     */
    protected void waitTillElementIsClickable(WebElement element) {
        testClass.getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Populate text to WebElement
     *
     * @param element
     * @param text
     */
    protected void typeIn(WebElement element, String text) {
        //waitTillElementIsVisible(element);
        element.clear();
        element.sendKeys(text);
    }
}
