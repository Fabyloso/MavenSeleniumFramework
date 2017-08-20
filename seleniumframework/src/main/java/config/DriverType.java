package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public enum DriverType implements DriverSetup {

    CHROME {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("no-default-browser-check");
            chromeOptions.addArguments("incognito");
            chromeOptions.addArguments("enable-automation");
            chromeOptions.setExperimentalOption("profile.password_manager_enabled", false);
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

            return desiredCapabilities;
        }

        public WebDriver getWebDriver(DesiredCapabilities capabilities) {
            return new ChromeDriver(capabilities);
        }
    },
    FireFox {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setCapability("marionette", true);
            return desiredCapabilities;
        }

        public WebDriver getWebDriver(DesiredCapabilities desiredCapabilities) {
            return new FirefoxDriver(desiredCapabilities);
        }
    }

    // TODO: 8/20/17 Add an IE version
}