package store;

import types.FilmType;
import types.PriceType;
import types.StatusType;

/**
 * Fujitsu internship test task 2018.
 *
 * @author  Eva Maria Veitmaa
 * @since   08-04-2018
 */
public class Film {

    private static final int TIME_QUOTA_FOR_REGULAR_FILMS = 3;
    private static final int TIME_QUOTA_FOR_OLD_FILMS = 5;

    private String title;
    private FilmType type;
    private StatusType status;

    public Film(String title, FilmType type, StatusType status) {
        this.title = title;
        this.type = type;
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }

    FilmType getType() {
        return this.type;
    }

    public void setType(FilmType type) {
        this.type = type;
    }

    StatusType getStatus() {
        return this.status;
    }

    void setStatus(StatusType newStatus) {
        this.status = newStatus;
    }

    /**
     * Returns the rental price of a film according to
     * its type and number of days the film is rented.
     * @param days time period for which film is rented
     * @return rental price of the film
     */
    int getRentalPrice(int days) {
        int rentalPrice = -1;
        switch (this.type) {
            case NEW:
                rentalPrice = PriceType.PREMIUM_PRICE.getNewFilmRentalPrice(days);
                break;
            case REGULAR:
                rentalPrice = PriceType.BASIC_PRICE.getFilmRentalPrice(days, TIME_QUOTA_FOR_REGULAR_FILMS);
                break;
            case OLD:
                rentalPrice = PriceType.BASIC_PRICE.getFilmRentalPrice(days, TIME_QUOTA_FOR_OLD_FILMS);
                break;
        }
        System.out.printf("%-30s %-10s %-20s\n", this.toString(), days + " day(s)", rentalPrice  + " EUR");
        return rentalPrice;
    }

    /**
     * Returns late charge of a film according to
     * its type and number of days over rental period.
     * @param days time period exceeding the rental period
     * @return late charge of the film
     */
    int getLateCharge(int days) {
        int returnPrice = 0;
        switch (this.type) {
            case NEW:
                returnPrice = PriceType.PREMIUM_PRICE.getReturnPrice(days);
                break;
            case REGULAR: case OLD:
                returnPrice = PriceType.BASIC_PRICE.getReturnPrice(days);
                break;
        }
        System.out.printf("%-30s %-20s %-20s\n", this.toString(), days + " extra day(s)", returnPrice  + " EUR");
        return returnPrice;
    }

    void setStatusToRentedOut() {
        this.setStatus(StatusType.RENTED_OUT);
    }

    void setStatusToInStore() {
        this.setStatus(StatusType.IN_STORE);
    }

    boolean isAvailableToRent() {
        return this.status == StatusType.IN_STORE;
    }

    boolean isNewRelease() {
        return this.type == FilmType.NEW;
    }

    @Override
    public String toString() {
        return this.title + " (" + this.type.getTypeString() + ")";
    }
}
