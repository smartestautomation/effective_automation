package components.example3.menu;

import annotations.Find;
import components.example3.NextGenWebComponent;
import driver.DriverManager;

@Find(xpath = "//*[@data-test='menu']")
public class NextGenHeaderMenu extends NextGenWebComponent {

    @Find(xpath = "//*[@id='home']")
    private NextGenWebComponent menu1Component;

    @Find(xpath = "//*[@id='transactions']")
    private NextGenWebComponent menu2Component;

    @Find(xpath = "//*[@id='accounts']")
    private NextGenWebComponent menu3Component;

    @Find(xpath = "//*[@id='logout']")
    private NextGenWebComponent menu4Component;

    public NextGenHeaderMenu(DriverManager driverManager) {
        super(driverManager);
    }

    public void menu1Method() {
        System.out.println(this.menu1Component.getXpathLocator());
        // menu1Component.click();
        // return new HomePage(driverManager);
    }

    public void menu2Method() {
        // menu2Component.click();
        // return new TransactionPage(driverManager);
    }

    public void menu3Method() {
        // menu3Component.click();
        // return new AccountsPage(driverManager);
    }

    public void menu4Method() {
        // menu4Component.click();
        // return new LogoutPage(driverManager);
    }
}

