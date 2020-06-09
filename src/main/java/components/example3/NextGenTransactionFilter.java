package components.example3;

import annotations.Find;
import components.example2.WebComponent;
import components.example2.selects.AccountSelect;
import components.example3.selects.NextGenAccountSelect;
import driver.DriverManager;

@Find(xpath = "//*[@id='trnFilter']")
public class NextGenTransactionFilter extends NextGenWebComponent {
    private NextGenWebComponent header;
    private NextGenWebComponent extendedFilterLink;
    private NextGenAccountSelect transactionAccountSelect;

    public NextGenTransactionFilter(DriverManager driverManager) {
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
