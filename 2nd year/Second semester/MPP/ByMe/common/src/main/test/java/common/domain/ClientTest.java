package common.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final String NAME = "clientName";
    private static final String NEW_NAME = "newClientName";
    private static final String EMAIL_ADDRESS = "email@gmail.com";
    private static final String NEW_EMAIL_ADDRESS = "newemail@gmail.com";
    private static final String PHONE_NUMBER = "0733787626";
    private static final String NEW_PHONE_NUMBER = "0744898363";
    private Client client;

    @BeforeEach
    public void setUp() throws Exception {
        client = new Client(NAME, EMAIL_ADDRESS, PHONE_NUMBER);
        client.setId(ID);
    }

    @AfterEach
    public void tearDown() throws Exception {
        client = null;
    }

    @Test
    public void testGetName() throws Exception {
        Assertions.assertEquals(NAME, client.getName(), "Names should be equal");
    }

    @Test
    public void testSetName() throws Exception {
        client.setName(NEW_NAME);
        Assertions.assertEquals(NEW_NAME, client.getName(), "Names should be equal");
    }

    @Test
    public void testGetId() throws Exception {
        Assertions.assertEquals(ID, client.getId(), "Ids should be equal");
    }

    @Test
    public void testSetId() throws Exception {
        client.setId(NEW_ID);
        Assertions.assertEquals(NEW_ID, client.getId(), "Ids should be equal");
    }

    @Test
    public void testGetEmailAddress() throws Exception {
        Assertions.assertEquals(EMAIL_ADDRESS, client.getEmailAddress(), "Email addresses should be equal");
    }

    @Test
    public void testSetEmailAddress() throws Exception {
        client.setEmailAddress(NEW_EMAIL_ADDRESS);
        Assertions.assertEquals(NEW_EMAIL_ADDRESS, client.getEmailAddress(), "Email addresses should be equal");
    }

    @Test
    public void testGetPhoneNumber() throws Exception {
        Assertions.assertEquals(PHONE_NUMBER, client.getPhoneNumber(), "Phone numbers should be equal");
    }

    @Test
    public void testSetPhoneNumber() throws Exception {
        client.setPhoneNumber(NEW_PHONE_NUMBER);
        Assertions.assertEquals(NEW_PHONE_NUMBER, client.getPhoneNumber(), "Phone numbers should be equal");
    }

}