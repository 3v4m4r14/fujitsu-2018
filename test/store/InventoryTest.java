package store;

import exceptions.InventoryException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import types.FilmType;
import types.StatusType;

import static org.testng.Assert.*;

/**
 * Fujitsu internship test task 2018.
 *
 * @author  Eva Maria Veitmaa
 * @since   08-04-2018
 */
public class InventoryTest {

    private Inventory inventory;

    private Film film1 = new Film("Film 1", FilmType.NEW, StatusType.IN_STORE);
    private Film film2 = new Film("Film 2", FilmType.REGULAR, StatusType.IN_STORE);
    private Film film3 = new Film("Film 3", FilmType.REGULAR, StatusType.RENTED_OUT);
    private Film film4 = new Film("Film 4", FilmType.OLD, StatusType.RENTED_OUT);
    private Film film5 = new Film("Film 5", FilmType.NEW, StatusType.IN_STORE);


    @BeforeMethod
    public void init() {
        inventory = new Inventory();
    }

    @Test
    public void testGetAllFilms() {
        addFilmsToInventory();
        assertEquals(inventory.getAllFilms().size(), 5);
    }

    @Test
    public void testGetFilmsInStore() {
        addFilmsToInventory();
        assertEquals(inventory.getFilmsInStore().size(), 3);
    }

    private void addFilmsToInventory() {
        inventory.addFilm(film1);
        inventory.addFilm(film2);
        inventory.addFilm(film3);
        inventory.addFilm(film4);
        inventory.addFilm(film5);
    }

    @Test
    public void testRemoveFilmThatIsInTheInventory() throws InventoryException {
        testGetAllFilms();
        inventory.removeFilm(film1);
        assertEquals(inventory.getAllFilms().size(), 4);
    }

    @Test(expectedExceptions = InventoryException.class, expectedExceptionsMessageRegExp = "The film could not be removed because there is no such film in the inventory.")
    public void testRemoveFilmThatIsNotInTheInventory() throws InventoryException {
        testRemoveFilmThatIsInTheInventory();
        inventory.removeFilm(film1);
    }

    @Test
    public void testIsInInventoryWithFilmThatIsInTheInventory() {
        addFilmsToInventory();
        assertTrue(inventory.isInInventory(film1));
    }

    @Test
    public void testIsInInventoryWithFilmThatIsNotInTheInventory() throws InventoryException {
        testRemoveFilmThatIsInTheInventory();
        assertFalse(inventory.isInInventory(film1));
    }
}