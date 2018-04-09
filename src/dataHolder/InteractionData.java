package dataHolder;

import store.Film;

/**
 * Fujitsu internship test task 2018.
 *
 * @author  Eva Maria Veitmaa
 * @since   08-04-2018
 */
public class InteractionData {

    private Film film;
    private int days;

    public InteractionData(Film film, int days) {
        this.film = film;
        this.days = days;
    }

    public Film getFilm() {
        return this.film;
    }

    public int getDays() {
        return this.days;
    }
}
