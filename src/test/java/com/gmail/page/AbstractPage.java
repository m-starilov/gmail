package com.gmail.page;

import com.gmail.model.Message;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

@Log4j2
public abstract class AbstractPage {

    protected WebDriver driver;

    protected final int WAIT_TIMEOUT_SECONDS = 10;

    protected final int POLLING_EVERY_SECONDS = 1;

    @FindBy(xpath = "//div[@data-tooltip='Settings']")
    private WebElement settingsButton;

    @FindBy(xpath = "//button[@aria-label='See all settings']")
    private WebElement seeAllSettingsButton;

    @FindBy(xpath = "//div[@class='T-I T-I-KE L3']")
    private WebElement composeButton;

    @FindBy(xpath = "//div[@role='listbox']//input[@id]")
    private WebElement toInput;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement subjectInput;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement textboxInput;

    @FindBy(xpath = "//a[contains(@href,'#sent')]")
    private WebElement goToSentMessagesButton;

    @FindBy(xpath = "//div[@role='alert']//span[@id='link_vsm']")
    private WebElement messageSentAlert;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SettingsPage goToSettings() {
        waitForElementClickable(settingsButton).click();
        waitForElementClickable(seeAllSettingsButton).click();
        return new SettingsPage(driver);
    }

    public void sendNewMessage(Message message) {
        waitForElementClickable(composeButton).click();
        waitForVisibilityOf(toInput).sendKeys(message.getTo());
        subjectInput.sendKeys(message.getSubject());
        subjectInput.sendKeys(Keys.TAB);
        textboxInput.sendKeys(message.getText());
        textboxInput.sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        log.info("Message sent");
    }

    public void openSentMessages() {
        waitForVisibilityOf(goToSentMessagesButton).click();
        waitUntilUrlContains("#sent");
        log.info("Sent Messages");
    }

    public boolean isMessageSent() {
        waitForVisibilityOf(messageSentAlert).click();
        return waitUntilUrlContains("https://mail.google.com/mail/u/0/#sent/");
    }

    public WebElement waitForVisibilityOf(WebElement element) {
        return getNewFluentWait().ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementClickable(WebElement element) {
        return getNewFluentWait().ignoring(ElementClickInterceptedException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitUntilUrlContains(String string) {
        return getNewFluentWait()
                .until(ExpectedConditions.urlContains(string));
    }

    public boolean waitUntilUrlMatches(String string) {
        return getNewFluentWait()
                .until(ExpectedConditions.urlMatches(string));
    }

    public void waitUntilTextToBePresent(WebElement element, String text) {
        getNewFluentWait()
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public FluentWait<WebDriver> getNewFluentWait() {
        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofSeconds(POLLING_EVERY_SECONDS)).ignoring(NoSuchElementException.class);
    }
}
