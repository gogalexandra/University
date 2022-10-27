package common.domain;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class ReceiptTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private Receipt receipt;

    @BeforeEach
    public void SetUp() throws Exception{
        receipt = new Receipt(1L, 1L);
        receipt.setId(ID);
    }

    @AfterEach
    public void tearDown() throws Exception {
        receipt=null;
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("Ids should be equal", ID, receipt.getId());
    }

    @Test
    public void testSetId() throws Exception {
        receipt.setId(NEW_ID);
        assertEquals("Ids should be equal", NEW_ID, receipt.getId());
    }
}
