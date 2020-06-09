package tests.example1;

import org.testng.annotations.Test;
import pageobject.example1.GooglePage;
import tests.TestClass;

public class GoogleTest extends TestClass {

    @Test
    public void googleTest() {
        GooglePage googlePage = new GooglePage(super.driverManager);
        googlePage.searchFor("selenium");
    }
}
