package store;

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
public class FilmTest {

    private Film film;

    @BeforeMethod
    public void init() {
        film = new Film("Film title", FilmType.NEW, StatusType.IN_STORE);
    }

    @Test
    public void testChangeTypeFromNewToOld() {
        film.setType(FilmType.NEW);
        assertEquals(film.getType(), FilmType.NEW);
        film.setType(FilmType.OLD);
        assertEquals(film.getType(), FilmType.OLD);
    }

    @Test
    public void testChangeStatus() {
        film.setStatus(StatusType.IN_STORE);
        assertEquals(film.getStatus(), StatusType.IN_STORE);
        film.setStatus(StatusType.RENTED_OUT);
        assertEquals(film.getStatus(), StatusType.RENTED_OUT);
        film.setStatus(StatusType.IN_STORE);
        assertEquals(film.getStatus(), StatusType.IN_STORE);
    }

    @Test
    public void testGetRentalPriceForNewFilm() {
        film.setType(FilmType.NEW);
        assertEquals(film.getRentalPrice(5), 20);
    }

    @Test
    public void testGetShortPeriodRentalPriceForRegularFilm() {
        film.setType(FilmType.REGULAR);
        assertEquals(film.getRentalPrice(2), 3);
    }

    @Test
    public void testGetShortPeriodRentalPriceForOldFilm() {
        film.setType(FilmType.OLD);
        assertEquals(film.getRentalPrice(2), 3);
    }

    @Test
    public void testGetLongPeriodRentalPriceForRegularFilm() {
        film.setType(FilmType.REGULAR);
        assertEquals(film.getRentalPrice(10), 24);
    }

    @Test
    public void testGetLongPeriodRentalPriceForOldFilm() {
        film.setType(FilmType.OLD);
        assertEquals(film.getRentalPrice(10), 18);
    }

    @Test
    public void testGetLateChargeForNewFilm() {
        film.setType(FilmType.NEW);
        assertEquals(film.getLateCharge(10), 40);
    }

    @Test
    public void testGetLateChargeForRegularFilm() {
        film.setType(FilmType.REGULAR);
        assertEquals(film.getLateCharge(8), 24);
    }

    @Test
    public void testGetLateChargeForOldFilm() {
        film.setType(FilmType.OLD);
        assertEquals(film.getLateCharge(5), 15);
    }
}