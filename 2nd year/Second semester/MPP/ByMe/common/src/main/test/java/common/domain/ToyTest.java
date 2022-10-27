package common.domain;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToyTest {
    private static final Long ID = 1L;
    private static final Long  NEW_ID = 2L;
    private static final String NAME = "minge";
    private static final String NEW_NAME = "minge2";
    private static final int PRICE = 10;
    private static final int NEW_PRICE = 20;
    private static final int STOCK = 100;
    private static final int NEW_STOCK = 200;

    private Toy toy;

    @BeforeEach
    public void SetUp() throws Exception{
        toy = new Toy(NAME, PRICE, STOCK);
        toy.setId(ID);
    }

    @AfterEach
    public void tearDown() throws Exception {
        toy=null;
    }

    @Test
    public void testGetId() throws Exception{
        //Assert.assertEquals("test", "test");
        Assertions.assertEquals(toy.getId(), ID, "IDs should be equal");
    }

    @Test
    public void testGetNAME() throws Exception{
        Assertions.assertEquals(toy.getName(), NAME, "Names should be equal");
    }

    @Test
    public void testGetPrice() throws Exception{
        Assertions.assertEquals(toy.getPrice(), PRICE, "Prices should be equal");
    }

    @Test
    public void testGetStock() throws Exception{
        Assertions.assertEquals(toy.getStock(), STOCK, "Stocks should be equal");
    }

    @Test
    public void testSetId() throws Exception{
        toy.setId(NEW_ID);
        Assertions.assertEquals(toy.getId(), NEW_ID, "IDs should be equal");
    }

    @Test
    public void testSetName() throws Exception{
        toy.setName(NEW_NAME);
        Assertions.assertEquals(toy.getName(), NEW_NAME, "Names should be equal");
    }

    @Test
    public void testSetPrice() throws Exception{
        toy.setPrice(NEW_PRICE);
        Assertions.assertEquals(toy.getPrice(), NEW_PRICE, "Prices should be equal");
    }

    @Test
    public void testSetStock() throws Exception{
        toy.setStock(NEW_STOCK);
        Assertions.assertEquals(toy.getStock(), NEW_STOCK, "Stocks should be equal");
    }
}
