package components.example3;

import driver.DriverManager;
import factory.example3.NextGenPageFactory;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.List;

public abstract class NextGenComponent implements WebElement {
    protected final DriverManager driverManager;
    private NextGenComponent parentComponent;
    private String xpathLocator = "";
    private String cssLocator;

    public NextGenComponent(DriverManager driverManager) {
        this.driverManager = driverManager;
        NextGenPageFactory.processComponents(this, driverManager);
    }

    public NextGenComponent getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(NextGenComponent parentComponent) {
        this.parentComponent = parentComponent;
    }

    public boolean hasParent() {
        return parentComponent != null;
    }

    public String getXpathLocator() {
        if (hasParent()) {
            // if has parent, build full xpath locator
            return getParentComponent().getXpathLocator().concat(this.xpathLocator);
        }
        // if has no parent, return single component locator
        return xpathLocator;
    }

    public void setXpathLocator(String xpathLocator) {
        this.xpathLocator = xpathLocator;
    }

    private WebElement find() {
        if (this.xpathLocator.isEmpty()) {
            throw new RuntimeException("Component has no xpath locator.");
        }
        return this.driverManager.findElement(getXpathLocator());
    }

    public WebElement waitUntilPresent() {
        return this.driverManager.waitForElement(this);
    }

    public void executeScript(String script, Object... arguments) {
        this.driverManager.getDriver().executeScript(script, arguments);
    }

    public void assertText(String expectedText) {
        Assert.assertEquals(getText(), expectedText);
    }

    /*
     * *********************** WebElement implementation ***********************
     */

    @Override
    public void click() {
        //...
        //...
        waitUntilPresent().click();
    }

    @Override
    public void submit() {
        waitUntilPresent().submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        waitUntilPresent().sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        waitUntilPresent().clear();
    }

    @Override
    public String getTagName() {
        return waitUntilPresent().getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return waitUntilPresent().getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return waitUntilPresent().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return waitUntilPresent().isEnabled();
    }

    @Override
    public String getText() {
        return waitUntilPresent().getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return waitUntilPresent().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return waitUntilPresent().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        try {
            return find().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public Point getLocation() {
        return waitUntilPresent().getLocation();
    }

    @Override
    public Dimension getSize() {
        return waitUntilPresent().getSize();
    }

    @Override
    public Rectangle getRect() {
        return waitUntilPresent().getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return waitUntilPresent().getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return waitUntilPresent().getScreenshotAs(target);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": [xpathLocator='" + xpathLocator + "']";
    }
}
