package types;

/**
 * Fujitsu internship test task 2018.
 *
 * @author  Eva Maria Veitmaa
 * @since   08-04-2018
 */
public enum FilmType {
    NEW("New release"),
    REGULAR("Regular release"),
    OLD("Old release");

    private String typeString;

    FilmType(String typeString) {
        this.typeString = typeString;
    }

    public String getTypeString() {
        return this.typeString;
    }
}
