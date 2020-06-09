package tests;

import driver.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestClass {
    protected DriverManager driverManager;

    static {
        System.setProperty("appUrl", "https://google.com");
    }

    @BeforeClass
    public void init() {
        this.driverManager = new DriverManager();
    }

    @AfterClass
    public void reset() {
        driverManager.quit();
    }
}
