package config;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

public class DriverFactory {
    private final DriverType defaultDriver = DriverType.CHROME;
    private WebDriver driver;
    private DriverType selectedDriver;
    private String browser;

    public WebDriver getDriver(ITestContext testContext) {
        browser = testContext.getCurrentXmlTest().getParameter("browser");
        if (driver == null) {
            selectedDriver = defaultDriver;
            try {
                selectedDriver = DriverType.valueOf(browser);
            } catch (Exception ignored) {

            }
            driver = selectedDriver.getWebDriver(selectedDriver.getDesiredCapabilities());
        }
        return driver;
    }

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
        }
    }
}
