package components.example2.menu;

import components.example2.WebComponent;
import driver.DriverManager;

public class HeaderMenu extends WebComponent {
    private WebComponent menu1Component;
    private WebComponent menu2Component;
    private WebComponent menu3Component;
    private WebComponent menu4Component;

    public HeaderMenu(DriverManager driverManager) {
        super(driverManager);
    }

    public void menu1Method() {
        System.out.println("menu1Method");
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

