import customer.Customer;
import exceptions.InventoryException;
import store.Film;
import store.Inventory;
import dataHolder.RentalData;
import dataHolder.ReturnData;
import store.Store;
import types.FilmType;
import types.StatusType;

import java.util.*;

/**
 * Fujitsu internship test task 2018.
 *
 * Demonstrates various functions of the video rental store.
 *
 * @author  Eva Maria Veitmaa
 * @since   08-04-2018
 */
public class Main {

    public static void main(String[] args) {
        Film film1 = new Film("Film 1", FilmType.NEW, StatusType.IN_STORE);
        Film film2 = new Film("Film 2", FilmType.REGULAR, StatusType.IN_STORE);
        Film film3 = new Film("Film 3", FilmType.REGULAR, StatusType.RENTED_OUT);
        Film film4 = new Film("Film 4", FilmType.OLD, StatusType.IN_STORE);
        Film film5 = new Film("Film 5", FilmType.NEW, StatusType.IN_STORE);

        Customer cust1 = new Customer(60);

        System.out.println("================================================");

        System.out.println("Changing film type...");
        Film film6 = new Film("Film 6", FilmType.OLD, StatusType.IN_STORE);
        System.out.println(film6.toString());
        film6.setType(FilmType.NEW);
        System.out.println(film6.toString());

        System.out.println("================================================");

        Inventory inventory = new Inventory();

        System.out.println("Empty inventory...");
        System.out.println("All films: " + inventory.getAllFilms());
        System.out.println("Films currently in store: " + inventory.getFilmsInStore());

        System.out.println("================================================");

        System.out.println("After adding films...");
        inventory.addFilm(film1);
        inventory.addFilm(film2);
        inventory.addFilm(film3);
        inventory.addFilm(film4);
        inventory.addFilm(film5);
        System.out.println("All films: " + inventory.getAllFilms());
        System.out.println("Films currently in store: " + inventory.getFilmsInStore());

        System.out.println("================================================");

        System.out.println("Remove film that is in the inventory...");

        try {
            inventory.removeFilm(film5);
        } catch (InventoryException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("All films: " + inventory.getAllFilms());

        System.out.println("================================================");

        System.out.println("Remove film that is not in the inventory...");

        try {
            inventory.removeFilm(film6);
        } catch (InventoryException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("All films: " + inventory.getAllFilms());

        System.out.println("================================================");

        Store store = new Store(inventory);

        System.out.println("Renting films...");
        RentalData rental1 = new RentalData(film1, 2);
        RentalData rental2 = new RentalData(film2, 5);
        RentalData rental3 = new RentalData(film3, 2);
        RentalData rental4 = new RentalData(film4, 7);
        List<RentalData> wishlist = new ArrayList<>(Arrays.asList(rental1, rental2, rental3, rental4));
        store.rentFilmsWithBonusPoints(cust1, wishlist);

        System.out.println("Available films after bonus points rental: " + inventory.getFilmsInStore());

        System.out.println("================================================");

        System.out.println("Returning films...");
        ReturnData return1 = new ReturnData(film1, 3);
        ReturnData return2 = new ReturnData(film2, 1);
        ReturnData return3 = new ReturnData(film3, 7);
        ReturnData return4 = new ReturnData(film4, 0);
        List<ReturnData> returnList = new ArrayList<>(Arrays.asList(return1, return2, return3, return4));
        store.returnFilms(returnList);

        System.out.println("Available films after returning: " + inventory.getFilmsInStore());

        System.out.println("================================================");

        System.out.println("Renting films...");
        store.rentFilms(cust1, wishlist);

        System.out.println("Available films after cash rental: " + inventory.getFilmsInStore());
    }
}
