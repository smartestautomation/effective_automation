package pageobject.example3;

import annotations.Find;
import annotations.WaitUntilPresent;
import components.example2.menu.HeaderMenu;
import components.example3.NextGenTransactionFilter;
import components.example3.NextGenWebComponent;
import components.example3.menu.NextGenHeaderMenu;
import driver.DriverManager;
import org.openqa.selenium.Keys;


public class NextGenTransactionPage extends NextGenHeaderPage {

    @Find(xpath = "//*[@id='trn']")
    private NextGenTransactionFilter transactionFilter;

    @Find(xpath = "//input[@title='Hledat']")
    @WaitUntilPresent
    private NextGenWebComponent searchInput;

    public NextGenTransactionPage(DriverManager driverManager) {
        super(driverManager);
    }

    public NextGenTransactionPage search(String searchText) {
        searchInput.sendKeys(searchText, Keys.ENTER);
        return this;
    }

    public void filter(String accountName) {
        transactionFilter.filter(accountName);
    }
}
