package com.gmail.test;

import com.gmail.model.Message;
import com.gmail.page.InboxPage;
import com.gmail.page.SentPage;
import com.gmail.page.SettingsPage;
import com.gmail.page.SignInPage;
import com.gmail.service.ModelsCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SendSignedMailTest extends AbstractBaseTest {
    private static Message message;
    private InboxPage inboxPage;

    @BeforeAll
    public static void setUp() {
        message = ModelsCreator.messageWithCredentialsFromProperty();
    }

    @BeforeEach
    public void signIn() {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.openPage();
        signInPage.signInToGmail();
        inboxPage = new InboxPage(driver);
    }

    @Test
    @Order(1)
    public void shouldSendMail() {
        SettingsPage settingsPage = inboxPage.goToSettings();
        settingsPage.addSignature();
        inboxPage.isThisInboxPage();
        inboxPage.sendNewMessage(message);
        assertTrue(inboxPage.isMessageSent());
    }

    @Test
    @Order(2)
    public void shouldCheckSentMail() {
        inboxPage.openSentMessages();
        SentPage sentPage = new SentPage(driver);
        sentPage.openLastSentMessages();
        Message signedMessage = sentPage.takeMessageData();
        assertTrue(sentPage.isMessageSigned(signedMessage));
    }

    @Test
    @Order(3)
    public void shouldDeleteSignature() {
        SettingsPage settingsPage = inboxPage.goToSettings();
        settingsPage.deleteSignature();
        assertTrue(inboxPage.isThisInboxPage());
    }
}
