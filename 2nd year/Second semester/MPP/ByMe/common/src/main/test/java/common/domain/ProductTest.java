package common.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ProductTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final float PRICE = 4;
    private static final float NEW_PRICE = 5;
    private Product product;

    @BeforeEach
    public void setUp() throws Exception{
        product = new Product(PRICE);
        product.setId(ID);
    }

    @AfterEach
    public void tearDown() throws Exception{
        product = null;
    }

    @org.junit.Test
    public void testGetId() throws Exception {
        assertEquals("Ids should be equal", ID, product.getId());
    }

    @org.junit.Test
    public void testSetId() throws Exception {
        product.setId(NEW_ID);
        assertEquals("Ids should be equal", NEW_ID, product.getId());
    }

    @Test
    public void testGetPrice() throws Exception{
        Assertions.assertEquals(PRICE, product.getPrice(),0, "Product price should be equal");
    }

    @Test
    public void testSetPrice() throws Exception{
        product.setPrice(NEW_PRICE);
        Assertions.assertEquals(NEW_PRICE, product.getPrice(),0, "Product price should be equal");
    }
}
