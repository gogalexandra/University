package common.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EmployeeTest {
    private static final Long ID = 10L;
    private static final Long NEW_ID = 20L;
    private static final String POSITION = "cashier";
    private static final String NEW_POSITION = "manager";
    private static final String NAME = "Roberto";
    private static final String NEW_NAME = "Roberto The Third";
    private static final String PHONE_NUMBER = "0700000010";
    private static final String NEW_PHONE_NUMBER = "0700000110";
    private static final float SALARY = 123;
    private static final float NEW_SALARY = 1234;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        employee = new Employee(POSITION, NAME, PHONE_NUMBER, SALARY);
        employee.setId(ID);
    }

    @After
    public void tearDown() throws Exception {
        employee=null;
    }

    @Test
    public void testGetPosition() throws Exception {
        assertEquals("Position should be equal", POSITION, employee.getPosition());
    }

    @Test
    public void testSetPosition() throws Exception {
        employee.setPosition(NEW_POSITION);
        assertEquals("Position should be equal", NEW_POSITION, employee.getPosition());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("Ids should be equal", ID, employee.getId());
    }

    @Test
    public void testSetId() throws Exception {
        employee.setId(NEW_ID);
        assertEquals("Ids should be equal", NEW_ID, employee.getId());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Name should be equal", NAME, employee.getName());
    }

    @Test
    public void testSetName() throws Exception {
        employee.setName(NEW_NAME);
        assertEquals("Name should be equal", NEW_NAME, employee.getName());
    }

    @Test
    public void testGetPhoneNumber() throws Exception {
        assertEquals("Phone number should be equal", PHONE_NUMBER, employee.getPhoneNumber());
    }

    @Test
    public void testSetPhoneNumber() throws Exception {
        employee.setPhoneNumber(NEW_PHONE_NUMBER);
        assertEquals("Name should be equal", NEW_PHONE_NUMBER, employee.getPhoneNumber());
    }

    @Test
    public void testGetSalary() throws Exception {
        assertEquals("Salary should be equal", SALARY, employee.getSalary(), 0);
    }

    @Test
    public void testSetSalary() throws Exception {
        employee.setSalary(NEW_SALARY);
        assertEquals("Salary should be equal", NEW_SALARY, employee.getSalary(), 0);
    }
}