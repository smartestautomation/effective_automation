package tests.example3;

import org.testng.annotations.Test;
import pageobject.example3.NextGenTransactionPage;
import tests.TestClass;

public class NextGenTransactionPageTest extends TestClass {

    @Test
    public void nextGenTransactionPageTest() {
        NextGenTransactionPage nextGenTransactionPage = new NextGenTransactionPage(super.driverManager);
        nextGenTransactionPage.headerMenu().menu1Method();
        nextGenTransactionPage.search("selenium")
                              .filter("acc");
    }
}
