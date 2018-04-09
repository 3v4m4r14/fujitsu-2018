package store;

import customer.Customer;
import dataHolder.RentalData;
import dataHolder.ReturnData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import types.FilmType;
import types.PriceType;
import types.StatusType;

import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.*;

public class StoreTest {

    private Store store;
    private Customer customer;
    private Film film1;
    private Film film2;
    private Film film3;

    @BeforeMethod
    public void init() {
        film1 = new Film("Film 1", FilmType.NEW, StatusType.IN_STORE);
        film2 = new Film("Film 2", FilmType.REGULAR, StatusType.IN_STORE);
        film3 = new Film("Film 3", FilmType.OLD, StatusType.RENTED_OUT);

        Inventory inventory = new Inventory();
        inventory.addFilm(film1);
        inventory.addFilm(film2);
        inventory.addFilm(film3);

        customer = new Customer();

        store = new Store(inventory);
    }

    @Test
    public void testRentFilmsAllOfThemAvailable() {
        RentalData rentalData1 = new RentalData(film1, 3);
        RentalData rentalData2 = new RentalData(film2, 2);
        assertEquals(store.rentFilms(customer, Arrays.asList(rentalData1, rentalData2)), 15);
        assertEquals(customer.getBonusPoints(), 3);
    }

    @Test
    public void testRentFilmsOneNotAvailable() {
        RentalData rentalData1 = new RentalData(film1, 3);
        RentalData rentalData2 = new RentalData(film3, 2);
        assertEquals(store.rentFilms(customer, Arrays.asList(rentalData1, rentalData2)), 12);
        assertEquals(customer.getBonusPoints(), 2);
    }

    @Test
    public void testRentFilmsWithBonusPointsOneNewAndOneRegularFilm() {
        customer.addBonusPoints(60);
        RentalData rentalData1 = new RentalData(film1, 2);
        RentalData rentalData2 = new RentalData(film2, 2);
        assertEquals(store.rentFilmsWithBonusPoints(customer, Arrays.asList(rentalData1, rentalData2)), 12);
    }

    @Test
    public void testReturnFilmsOnTime() {
        ReturnData returnData1 = new ReturnData(film1, 0);
        assertEquals(store.returnFilms(Collections.singletonList(returnData1)), 0);
    }

    @Test
    public void testReturnFilmsLate() {
        ReturnData returnData1 = new ReturnData(film1, 5);
        assertEquals(store.returnFilms(Collections.singletonList(returnData1)), 5 * PriceType.PREMIUM_PRICE.getPrice());
    }
}