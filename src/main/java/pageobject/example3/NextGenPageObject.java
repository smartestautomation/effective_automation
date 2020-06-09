package pageobject.example3;

import driver.DriverManager;
import factory.example3.NextGenPageFactory;

public abstract class NextGenPageObject {
    protected DriverManager driverManager;

    public NextGenPageObject(DriverManager driverManager) {
        // saving DriverManager instance
        this.driverManager = driverManager;
        // process page object fields
        NextGenPageFactory.processPageObjects(this, driverManager);
    }
}
