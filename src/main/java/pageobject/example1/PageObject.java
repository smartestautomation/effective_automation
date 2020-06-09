package pageobject.example1;

import driver.DriverManager;
import org.openqa.selenium.support.PageFactory;

public abstract class PageObject {
    protected DriverManager driverManager;

    public PageObject(DriverManager driverManager) {
        // saving DriverManager instance
        this.driverManager = driverManager;
        // Selenium factory - initializing WebElement variables
        PageFactory.initElements(driverManager.getDriver(), this);
    }
}
