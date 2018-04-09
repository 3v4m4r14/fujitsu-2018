package store;

import exceptions.InventoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Fujitsu internship test task 2018.
 *
 * @author  Eva Maria Veitmaa
 * @since   08-04-2018
 */
public class Inventory {
    private List<Film> films;

    public Inventory() {
        this.films = new ArrayList<>();
    }

    public List<Film> getAllFilms() {
        return this.films;
    }

    public List<Film> getFilmsInStore() {
        return this.films.stream().filter(Film::isAvailableToRent).collect(Collectors.toList());
    }

    public void addFilm(Film film) {
        this.films.add(film);
    }

    public void removeFilm(Film film) throws InventoryException {
        if (isInInventory(film)) {
            this.films.remove(film);
        } else {
            throw new InventoryException("The film could not be removed because there is no such film in the inventory.");
        }
    }

    boolean isInInventory(Film film) {
        return this.films.contains(film);
    }
}
