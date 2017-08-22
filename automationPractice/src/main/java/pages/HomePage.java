package pages;

import base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

    private final By btnSignIn = By.linkText("Sign in");

    protected HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSignIn() {
        clickElement(btnSignIn);
    }

}
