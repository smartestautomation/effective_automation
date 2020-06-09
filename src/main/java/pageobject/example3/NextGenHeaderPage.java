package pageobject.example3;

import annotations.Find;
import components.example3.menu.NextGenHeaderMenu;
import driver.DriverManager;

public class NextGenHeaderPage extends NextGenPageObject{

    @Find(xpath = "//*[@id='headerMenu']")
    private NextGenHeaderMenu headerMenu;

    public NextGenHeaderPage(DriverManager driverManager) {
        super(driverManager);
    }

    public NextGenHeaderMenu headerMenu() {
        return this.headerMenu;
    }
}
