package tests.example2;

import org.testng.annotations.Test;
import pageobject.example2.TransactionsPage;
import tests.TestClass;

public class TransactionPageTest extends TestClass {

    @Test
    public void transactionPageTest() {
        TransactionsPage transactionPage = new TransactionsPage(super.driverManager);
        transactionPage.headerMenu().menu1Method();
    }
}
