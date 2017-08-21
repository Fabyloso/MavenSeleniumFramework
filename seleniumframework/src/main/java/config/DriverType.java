package config;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.LinkedHashMap;
import java.util.Map;

public enum DriverType implements DriverSetup {

    CHROME {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("no-default-browser-check");
            chromeOptions.addArguments("incognito");
            chromeOptions.addArguments("enable-automation");
            Map<String, Object> prefs = new LinkedHashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            chromeOptions.setExperimentalOption("prefs", prefs);
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

            return desiredCapabilities;
        }

        public WebDriver getWebDriver(DesiredCapabilities capabilities) {
            ChromeDriverManager.getInstance().setup();
            return new ChromeDriver(capabilities);
        }
    },
    FIREFOX {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setCapability("marionette", true);
            return desiredCapabilities;
        }

        public WebDriver getWebDriver(DesiredCapabilities desiredCapabilities) {
            FirefoxDriverManager.getInstance().setup();
            return new FirefoxDriver(desiredCapabilities);
        }
    }

    // TODO: 8/20/17 Add an IE version
}