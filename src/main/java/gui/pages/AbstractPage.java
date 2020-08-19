package gui.pages;

import gui.runner.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath = NAVIGATION_MENU + "//a[.='Numbers']")
    private WebElement numbersMenu;

    @FindBy(xpath = NAVIGATION_MENU + "//a[.='Numbers']//following::a[.='Integers']")
    private WebElement integersMenuItem;

    @FindBy(xpath = NAVIGATION_MENU + "//a[.='Lists & More']")
    private WebElement listsAndMoreMenu;

    @FindBy(xpath = NAVIGATION_MENU + "//a[.='Lists & More']//following::a[.='Strings']")
    private WebElement stringsMenuItem;

    @FindBy(xpath = "//button[.='Allow Selected']")
    private WebElement allowSelectedButton;

    @FindBy(xpath = "//h2")
    private WebElement pageHeadingLabel;

    private static boolean cookiesAccepted = false;

    /**
     * Constructor
     */
    public AbstractPage(BaseTest testClass) {
        this.testClass = testClass;
        PageFactory.initElements(testClass.getWebDriver(), this);
        waitTillElementIsVisible(navigationMenu);
        if (!cookiesAccepted) {
            clickAllowSelected();
        }
    }

    /**
     * Click on 'Allow Selected' button for accepting cookies policy
     */
    public void clickAllowSelected() {
        waitTillElementIsClickable(allowSelectedButton);
        allowSelectedButton.click();
        cookiesAccepted = true;
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
        waitTillElementIsVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Click on Numbers -> Integers menu
     *
     * @return RandomIntegerGeneratorPage
     */
    public RandomIntegerGeneratorPage clickIntegers() {
        clickMenuItem(numbersMenu, integersMenuItem);
        return new RandomIntegerGeneratorPage(testClass);
    }

    /**
     * Click on Lists & More -> Strings menu
     *
     * @return RandomStringGeneratorPage
     */
    public RandomStringGeneratorPage clickStrings() {
        clickMenuItem(listsAndMoreMenu, stringsMenuItem);
        return new RandomStringGeneratorPage(testClass);
    }

    /**
     * Verify displayed page heading
     *
     * @param expectedHeading
     */
    public void verifyPageHeading(String expectedHeading) {
        waitTillElementIsVisible(pageHeadingLabel);
        Assert.assertEquals("Wrong page heading", expectedHeading, getPageHeading());
    }

    /**
     * Get text of page heading
     *
     * @return String
     */
    public String getPageHeading(){
        return pageHeadingLabel.getText();
    }

    /**
     * Hover on parent menu item and then click on menu element
     *
     * @param menuTitle
     * @param menuElement
     */
    public void clickMenuItem(WebElement menuTitle, WebElement menuElement) {
        if (menuElement == null) {
            menuTitle.click();
        }
        else {
            Actions actions = new Actions(testClass.getWebDriver());
            waitTillElementIsVisible(menuTitle);
            actions.moveToElement(menuTitle).perform();
            waitTillElementIsVisible(menuElement);
            actions.moveToElement(menuElement).perform();
            actions.click().perform();
        }
    }
}
