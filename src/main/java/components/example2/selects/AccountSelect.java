package components.example2.selects;

import components.example2.WebComponent;
import driver.DriverManager;

public class AccountSelect extends WebComponent {
    private WebComponent accountOption;

    public AccountSelect(DriverManager driverManager) {
        super(driverManager);
    }

    public void selectAccount(String accountName) {
        // this.click();
        // findComponents(accountOption).stream()
        //                              .filter(component -> accountName.equals(component.getText()))
        //                              .findFirst()
        //                              .orElseThrow(() -> new RuntimeException("Account with name '" + accountName + "' does not exist."))
        //                              .click();
    }
}
