package driver;

import components.example3.NextGenComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverManager {
    private RemoteWebDriver remoteWebDriver;

    public DriverManager() {
        init();
    }

    private void init() {
        if (remoteWebDriver != null) {
            return;
        }
        // get browser type from system
        Browser browser = Browser.valueOf(System.getProperty("browser", "CHROME"));
        // initialize proper type of browser
        this.remoteWebDriver = browser.init();
        // maximize browser window
        maximize();
        // connect to app url
        connectToApp();
    }

    private void maximize() {
        this.remoteWebDriver.manage().window().maximize();
    }

    private void connectToApp() {
        String url = System.getProperty("appUrl");
        if (url == null || !url.contains("http")) {
            throw new RuntimeException("'appUrl' property must be defined in system and must contain http protocol.");
        }
        this.remoteWebDriver.get(url);
    }

    public RemoteWebDriver getDriver() {
        return remoteWebDriver;
    }

    public enum Browser {
        CHROME{
            @Override
            public RemoteWebDriver init() {
                return new ChromeDriver();
            }
        },
        FIREFOX {
            @Override
            public RemoteWebDriver init() {
                return new FirefoxDriver();
            }
        };

        public abstract RemoteWebDriver init();
    }

    public void quit() {
        if (this.remoteWebDriver != null) {
            this.remoteWebDriver.quit();
        }
    }

    public WebElement findElement(String xpathLocator) {
        return this.remoteWebDriver.findElementByXPath(xpathLocator);
    }

    public WebElement findElementByCSS(String cssLocator) {
        return this.remoteWebDriver.findElementByCssSelector(cssLocator);
    }

    public WebElement waitForElement(final NextGenComponent component) {
        Wait<WebDriver> wait = new WebDriverWait(this.remoteWebDriver, 60);
        ExpectedCondition<WebElement> expectation = driver -> {
            try {
                if (driver == null) {
                    return null;
                }
                return driver.findElement(By.xpath(component.getXpathLocator()));
            } catch (WebDriverException e) {
                return null;
            }
        };

        try {
            System.out.println("Waiting for " + component);
            return wait.until(input -> expectation.apply(this.remoteWebDriver));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element is not visible after " + 60 + " seconds. Element -> " + component);
        }
    }
}
