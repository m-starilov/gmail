package com.gmail.test;

import com.gmail.page.InboxPage;
import com.gmail.page.SignInPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignInTest extends AbstractBaseTest {

    @Test
    public void shouldSignIn() {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.openPage();
        signInPage.signInToGmail();
        InboxPage inboxPage = new InboxPage(driver);
        assertTrue(inboxPage.isThisInboxPage());
    }

}
