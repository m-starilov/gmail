package com.gmail.page;

import com.gmail.model.Profile;
import com.gmail.service.ModelsCreator;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class SignInPage extends AbstractPage {
    private static final String URL = "https://gmail.com/";
    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//div[@id='identifierNext']//button//span")
    private WebElement identifierNextButton;

    @FindBy(xpath = "//div[@id='passwordNext']//button//span")
    private WebElement passwordNextButton;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(URL);
        log.info("Opened site by URL: [" + URL + "] in max window size");
    }

    public void signInToGmail() {
        Profile profile = ModelsCreator.profileWithCredentialsFromProperty();
        waitForVisibilityOf(emailInput).sendKeys(profile.getLogin());
        waitForElementClickable(identifierNextButton).click();
        waitForVisibilityOf(passwordInput).sendKeys(profile.getPassword());
        waitForElementClickable(passwordNextButton).click();
        log.info("Signed in to gmail successfully");
    }

}
