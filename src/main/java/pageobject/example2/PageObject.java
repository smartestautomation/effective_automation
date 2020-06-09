package pageobject.example2;

import driver.DriverManager;
import factory.example2.PageFactory;

public abstract class PageObject {
    protected DriverManager driverManager;

    public PageObject(DriverManager driverManager) {
        // saving DriverManager instance
        this.driverManager = driverManager;
        // process page object fields
        PageFactory.processPageObjects(this, driverManager);
    }
}