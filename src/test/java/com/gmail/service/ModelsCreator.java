package com.gmail.service;

import com.gmail.model.Message;
import com.gmail.model.Profile;
import com.gmail.model.Signature;

public class ModelsCreator {
    public static final String TESTDATA_PROFILE_LOGIN = "testdata.profile.login";
    public static final String TESTDATA_PROFILE_PASSWORD = "testdata.profile.pass";
    public static final String TESTDATA_MESSAGE_TO = "testdata.message.to";
    public static final String TESTDATA_MESSAGE_SUBJECT = "testdata.message.subject";
    public static final String TESTDATA_MESSAGE_TEXT = "testdata.message.text";
    public static final String TESTDATA_SIGNATURE_NAME = "testdata.signature.name";
    public static final String TESTDATA_SIGNATURE_TEXT = "testdata.signature.text";

    public static Profile profileWithCredentialsFromProperty() {
        return new Profile(TestDataReader.getTestData(TESTDATA_PROFILE_LOGIN),
                TestDataReader.getTestData(TESTDATA_PROFILE_PASSWORD));
    }

    public static Message messageWithCredentialsFromProperty() {
        return new Message(TestDataReader.getTestData(TESTDATA_MESSAGE_TO),
                TestDataReader.getTestData(TESTDATA_MESSAGE_SUBJECT),
                TestDataReader.getTestData(TESTDATA_MESSAGE_TEXT));
    }

    public static Signature signatureWithCredentialsFromProperty() {
        return new Signature(TestDataReader.getTestData(TESTDATA_SIGNATURE_NAME),
                TestDataReader.getTestData(TESTDATA_SIGNATURE_TEXT));
    }

}
