package customer;

import exceptions.BonusPointsException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Fujitsu internship test task 2018.
 *
 * @author  Eva Maria Veitmaa
 * @since   08-04-2018
 */
public class CustomerTest {

    private Customer customer;

    @BeforeMethod
    public void init() {
        customer = new Customer();
    }

    @Test
    public void testAddBonusPoints() {
        assertEquals(customer.getBonusPoints(), 0);
        customer.addBonusPoints(40);
        assertEquals(customer.getBonusPoints(), 40);
    }

    @Test
    public void testRemoveLessBonusPointsThanTheCustomerHas() throws BonusPointsException {
        assertEquals(customer.getBonusPoints(), 0);
        customer.addBonusPoints(100);
        customer.removeBonusPoints(20);
        assertEquals(customer.getBonusPoints(), 80);
    }

    @Test
    public void testRemoveMoreBonusPointsThanTheCustomerHasThrowsException() throws BonusPointsException {
        assertEquals(customer.getBonusPoints(), 0);
        customer.addBonusPoints(100);
        customer.removeBonusPoints(20);
        assertEquals(customer.getBonusPoints(), 80);
    }
}