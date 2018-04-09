package types;

/**
 * Fujitsu internship test task 2018.
 *
 * @author  Eva Maria Veitmaa
 * @since   08-04-2018
 */
public enum PriceType {
    PREMIUM_PRICE(4),
    BASIC_PRICE(3);

    private int price;

    PriceType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    /**
     * Calculates rental price for regular and old films.
     * @param days time period for which film is rented
     * @param i time quota after which daily charging begins
     * @return rental price of given film type over given period
     */
    public int getFilmRentalPrice(int days, int i) {
        int rentalPrice;
        int daysOver = days - i;
        if (daysOver > 0) {
            rentalPrice = this.price + daysOver * this.price;
        } else {
            rentalPrice = this.price;
        }
        return rentalPrice;
    }

    /**
     * Calculates rental price for new films.
     * @param days time period for which film is rented
     * @return rental price of given film type over given period
     */
    public int getNewFilmRentalPrice(int days) {
        int rentalPrice;
        rentalPrice = days * this.price;
        return rentalPrice;
    }

    /**
     * Calculates return price for given film type.
     * @param days time period exceeding rental period
     * @return price to pay upon returning the film
     */
    public int getReturnPrice(int days) {
        return this.price * days;
    }
}
