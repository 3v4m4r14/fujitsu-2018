package store;

import customer.Customer;
import dataHolder.RentalData;
import dataHolder.ReturnData;
import exceptions.BonusPointsException;

import java.util.ArrayList;
import java.util.List;

/**
 * Fujitsu internship test task 2018.
 *
 * Video rental store's rental administration managing system.
 *
 * @author  Eva Maria Veitmaa
 * @since   08-04-2018
 */
public class Store {

    private static final int NEW_RELEASE_BONUS_POINTS = 2;
    private static final int REGULAR_AND_OLD_RELEASE_BONUS_POINTS = 1;
    private static final int BONUS_POINTS_DAILY_RENTAL_COST = 25;

    private Inventory inventory;

    public Store(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Enables cash-paying customers to rent films.
     * Loops through film wishlist and calculates the total cost of
     * films available to rent. Adds bonus points to customer's account.
     * @param customer who wants to rent films
     * @param filmsToRent films to be rented (all of them may not be available)
     * @return total cost of rented films
     */
    public int rentFilms(Customer customer, List<RentalData> filmsToRent) {
        int total = 0;
        List<Film> notAvailable = new ArrayList<>();

        for (RentalData data : filmsToRent) {
            Film film = data.getFilm();
            if (canBeRented(film)) {
                if (film.isNewRelease()) {
                    customer.addBonusPoints(NEW_RELEASE_BONUS_POINTS);
                } else {
                    customer.addBonusPoints(REGULAR_AND_OLD_RELEASE_BONUS_POINTS);
                }
                film.setStatusToRentedOut();
                total += film.getRentalPrice(data.getDays());
            } else if (!film.isAvailableToRent()) {
                notAvailable.add(film);
            }
        }

        printFilmsNotAvailable(notAvailable);
        System.out.println("Total price: " + total + " EUR");
        return total;
    }

    private boolean canBeRented(Film film) {
        return inventory.isInInventory(film) && film.isAvailableToRent();
    }

    /**
     * Enables customers to rent films with bonus points.
     * Loops through film wishlist and removes bonus points from
     * customer's account if the film is available to rent.
     * @param customer who wants to rent films
     * @param filmsToRent films to be rented (all of them may not be available)
     * @return customer's bonus points after renting films
     */
    public int rentFilmsWithBonusPoints(Customer customer, List<RentalData> filmsToRent) {
        int collectedBonus = 0;
        List<Film> notAvailable = new ArrayList<>();

        for (RentalData data : filmsToRent) {
            Film film = data.getFilm();
            int days = data.getDays();
            if (canBeRented(film) && film.isNewRelease()) {
                try {
                    int usedPoints = BONUS_POINTS_DAILY_RENTAL_COST * days;
                    customer.removeBonusPoints(usedPoints);
                    collectedBonus += NEW_RELEASE_BONUS_POINTS;
                    film.setStatusToRentedOut();
                    System.out.printf("%-30s %-10s %-20s\n", film.toString(), days + " day(s)", "(Paid with " + usedPoints + " bonus points)");
                } catch (BonusPointsException e) {
                    System.out.println(film.toString() + " " + e.getMessage());
                }
            } else if (!film.isNewRelease()) {
                System.out.printf("%-30s %-20s\n", film.toString(), "Bonus points cannot be used for this film.");
            } else if (!film.isAvailableToRent()) {
                notAvailable.add(film);
            }
        }
        customer.addBonusPoints(collectedBonus);
        printFilmsNotAvailable(notAvailable);
        System.out.println("Total price: 0 EUR");
        System.out.println(customer.getBonusPointsAsString());
        return customer.getBonusPoints();
    }

    /**
     * Calculates the extra charge of films being returned.
     * @param filmsToReturn films that are being returned
     * @return sum of extra charge of films returned late
     */
    public int returnFilms(List<ReturnData> filmsToReturn) {
        int total = 0;

        for (ReturnData data : filmsToReturn) {
            Film film = data.getFilm();
            film.setStatusToInStore();
            total += film.getLateCharge(data.getDays());
        }

        System.out.println("Total late charge: " + total + " EUR");
        return total;
    }


    private void printFilmsNotAvailable(List<Film> films) {
        for (Film film : films) {
            System.out.println(film.toString() + " currently not available.");
        }
    }
}
