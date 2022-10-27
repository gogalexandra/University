package common.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class FoodTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final String BRAND = "Purina";
    private static final String NEW_BRAND= "Whiskas";
    private static final String ANIMAL = "cat";
    private static final String NEW_ANIMAL = "dog";
    private static final float PRICE = 4;
    private static final float NEW_PRICE = 5;

    private Food food;

    @BeforeEach
    public void setUp() throws Exception{
        food = new Food(BRAND, ANIMAL, PRICE);
        food.setId(ID);
    }

    @AfterEach
    public void tearDown() throws Exception{
        food = null;
    }

    @Test
    public void testGetBrand() throws Exception{
        Assertions.assertEquals(BRAND, food.getBrand(), "Brands should be equal");
    }

    @Test
    public void testSetBrand() throws Exception{
        food.setBrand(NEW_BRAND);
        Assertions.assertEquals(NEW_BRAND, food.getBrand(), "Brands should be equal");
    }

    @Test
    public void testGetId() throws Exception{
        Assertions.assertEquals(ID, food.getId(), "Ids should be equal");
    }

    @Test
    public void testSetId() throws Exception{
        food.setId(NEW_ID);
        Assertions.assertEquals(NEW_ID, food.getId(), "Ids should be equal");
    }

    @Test
    public void testGetAnimal() throws Exception{
        Assertions.assertEquals(ANIMAL, food.getAnimal(), "Animals should be equal");
    }

    @Test
    public void testSetAnimal() throws Exception{
        food.setAnimal(NEW_ANIMAL);
        Assertions.assertEquals(NEW_ANIMAL, food.getAnimal(), "Animals should be equal");
    }

    @Test
    public void testGetPrice() throws Exception{
        Assertions.assertEquals(PRICE, food.getPrice(),0, "Foods price should be equal");
    }

    @Test
    public void testSetPrice() throws Exception{
        food.setPrice(NEW_PRICE);
        Assertions.assertEquals(NEW_PRICE, food.getPrice(),0, "Foods price should be equal");
    }
}
