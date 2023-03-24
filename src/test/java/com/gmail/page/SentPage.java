package com.gmail.page;

import com.gmail.model.Message;
import com.gmail.model.Signature;
import com.gmail.service.ModelsCreator;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class SentPage extends AbstractPage {

    @FindBy(xpath = "//div[@role='main']//tr[@tabindex='-1']")
    private WebElement lastSentMessage;

    @FindBy(xpath = "//div[@role='main']//h2")
    private WebElement messageSubject;

    @FindBy(xpath = "//div[@role='main']//div/span/span[@email]")
    private WebElement messageAddressee;

    @FindBy(xpath = "//div[@role='main']//div[@dir='ltr']")
    private WebElement messageText;

    public SentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openLastSentMessages() {
        waitForVisibilityOf(lastSentMessage).click();
        log.info("openLastSentMessages");
    }

    public Message takeMessageData() {
        Message message = new Message();
        message.setTo(waitForVisibilityOf(messageAddressee).getAttribute("email"));
        message.setSubject(waitForVisibilityOf(messageSubject).getText());
        message.setText(waitForVisibilityOf(messageText).getText());
        log.info("takeInfo");
        return message;
    }

    public boolean isMessageSigned(Message message) {
        Signature signature = ModelsCreator.signatureWithCredentialsFromProperty();
        return message.getText().contains(signature.getText());
    }

}
