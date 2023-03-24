package com.gmail.test;

import com.gmail.model.Message;
import com.gmail.page.InboxPage;
import com.gmail.page.SentPage;
import com.gmail.page.SignInPage;
import com.gmail.service.ModelsCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SendMailTest extends AbstractBaseTest {
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
        inboxPage.sendNewMessage(message);
        assertTrue(inboxPage.isMessageSent());
    }

    @Test
    @Order(2)
    public void shouldCheckSentMail() {
        inboxPage.openSentMessages();
        SentPage sentPage = new SentPage(driver);
        sentPage.openLastSentMessages();
        assertEquals(message, sentPage.takeMessageData());
    }

}
