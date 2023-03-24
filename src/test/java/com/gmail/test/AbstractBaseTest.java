package com.gmail.test;

import com.gmail.driver.DriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class AbstractBaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void openBrowser() {
        driver = DriverSingleton.getDriver();
    }

    @AfterEach
    public void closeBrowser() {
        DriverSingleton.closeDriver();
    }

}
