package components.example3.selects;

import annotations.Find;
import components.example2.WebComponent;
import components.example3.NextGenWebComponent;
import driver.DriverManager;

@Find(xpath = "//select[@data-test='accSelect']")
public class NextGenAccountSelect extends NextGenWebComponent {

    @Find(xpath = "//i-option[contains(@id='accName')]")
    private NextGenWebComponent accountOption;

    public NextGenAccountSelect(DriverManager driverManager) {
        super(driverManager);
    }

    public void selectAccount(String accountName) {
        System.out.println(this.accountOption.getXpathLocator());
        // this.click();
        // findComponents(accountOption).stream()
        //                              .filter(component -> accountName.equals(component.getText()))
        //                              .findFirst()
        //                              .orElseThrow(() -> new RuntimeException("Account with name '" + accountName + "' does not exist."))
        //                              .click();
    }
}
