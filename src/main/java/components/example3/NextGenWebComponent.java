package components.example3;

import driver.DriverManager;
import org.openqa.selenium.interactions.Actions;

public class NextGenWebComponent extends NextGenComponent {

    public NextGenWebComponent(DriverManager driverManager) {
        super(driverManager);
    }

    public NextGenWebComponent scrollTo() {
        super.executeScript("arguments[0].scrollIntoView(true);", super.waitUntilPresent());
        return this;
    }

    public void clickJS() {
        super.executeScript("arguments[0].click();", super.waitUntilPresent());
    }

    public NextGenWebComponent moveOn() {
        new Actions(super.driverManager.getDriver()).moveToElement(super.waitUntilPresent()).perform();
        return this;
    }
}
