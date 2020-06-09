package components.example2;

import components.example2.selects.AccountSelect;
import driver.DriverManager;

public class TransactionFilter extends WebComponent {
    private WebComponent header;
    private WebComponent extendedFilterLink;
    private AccountSelect transactionAccountSelect;

    public TransactionFilter(DriverManager driverManager) {
        super(driverManager);
    }

    public void filter(String accountName) {
        transactionAccountSelect.selectAccount(accountName);
    }

    public void extendedFilter() {
        // extendedFilterLink.click();
        // return new ExtendedFilterPage(driverManager);
    }
}
