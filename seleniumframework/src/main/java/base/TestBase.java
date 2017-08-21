package base;

import config.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestBase {

    private static final List<DriverFactory> webDriverThreads = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverFactoryThreadLocal;

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        driverFactoryThreadLocal = ThreadLocal.withInitial(() -> {
            DriverFactory driverFactory = new DriverFactory();
            webDriverThreads.add(driverFactory);
            return driverFactory;
        });
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreads) {
            driverFactory.quitDriver();
        }
    }

    protected static WebDriver getDriver(ITestContext testContext) {
        return driverFactoryThreadLocal.get().getDriver(testContext);
    }

    @BeforeMethod()
    public void beforeMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("Before test-method. Thread id is: " + id);
    }

}