package pageobject.example2;

import components.example2.TransactionFilter;
import driver.DriverManager;
import components.example2.menu.HeaderMenu;

public class TransactionsPage extends PageObject {
    private HeaderMenu headerMenu;
    private TransactionFilter transactionFilter;

    public TransactionsPage(DriverManager driverManager) {
        super(driverManager);
    }

    public HeaderMenu headerMenu() {
        return this.headerMenu;
    }

    public TransactionsPage filter(String accountName) {
        this.transactionFilter.filter(accountName);
        return this;
    }
}
