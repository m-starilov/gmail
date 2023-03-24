package com.gmail.page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class InboxPage extends AbstractPage {
    public static final String INBOX_URL = "https://mail.google.com/mail/u/0/#inbox";

    public InboxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isThisInboxPage() {
        log.info("Check is this an Inbox Page");
        return waitUntilUrlMatches(INBOX_URL);
    }


}
