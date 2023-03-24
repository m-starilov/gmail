package com.gmail.page;

import com.gmail.model.Signature;
import com.gmail.service.ModelsCreator;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import static java.lang.String.format;

@Log4j2
public class SettingsPage extends AbstractPage {

    public static final String OPTION_XPATH = "//select//option[text()='%s']";
    @FindBy(xpath = "//button[@aria-label='Create a new signature']")
    private WebElement newSignatureButton;

    @FindBy(xpath = "//input[@placeholder='Signature name']")
    private WebElement signatureNameInput;

    @FindBy(xpath = "//button[@name='ok']")
    private WebElement okButton;

    @FindBy(xpath = "//div[@aria-label='Signature']")
    private WebElement newSignatureTextBox;

    @FindBy(xpath = "//button[@guidedhelpid='save_changes_button']")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//span[@data-action='delete']")
    private WebElement deleteSignatureButton;

    public SettingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addSignature() {
        Signature signature = ModelsCreator.signatureWithCredentialsFromProperty();
        waitForElementClickable(newSignatureButton).click();
        waitForVisibilityOf(signatureNameInput).sendKeys(signature.getName());
        waitForElementClickable(okButton).click();
        StringSelection stringSelection = new StringSelection(signature.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        waitForVisibilityOf(newSignatureTextBox).sendKeys(Keys.CONTROL + "v");
        waitUntilTextToBePresent(newSignatureTextBox, signature.getText());
        waitForVisibilityOf(driver.findElement(By.xpath(format(OPTION_XPATH, signature.getName())))).click();
        waitForElementClickable(waitForVisibilityOf(saveChangesButton)).click();
        log.info("Signature added");
    }

    public void deleteSignature() {
        waitForElementClickable(deleteSignatureButton).click();
        waitForElementClickable(okButton).click();
        waitForElementClickable(waitForVisibilityOf(saveChangesButton)).click();
        log.info("Signature deleted");
    }

}
