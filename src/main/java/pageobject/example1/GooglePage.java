package pageobject.example1;

import driver.DriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends PageObject {

    @FindBy(xpath = "//input[@title='Hledat']")
    private WebElement searchInput;

    public GooglePage(DriverManager driverManager) {
        super(driverManager);
    }

    public FoundResultsPage searchFor(String searchedText) {
        searchInput.sendKeys(searchedText, Keys.ENTER);
        return new FoundResultsPage(super.driverManager);
    }
}
