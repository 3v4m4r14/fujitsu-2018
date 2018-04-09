package customer;

import exceptions.BonusPointsException;

/**
 * Fujitsu internship test task 2018.
 *
 * @author  Eva Maria Veitmaa
 * @since   08-04-2018
 */
public class Customer {

    private String firstName;
    private String lastName;
    private int bonusPoints;

    public Customer() {
        this.bonusPoints = 0;
    }

    public Customer(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public int getBonusPoints() {
        return this.bonusPoints;
    }

    public void addBonusPoints(int toAdd) {
        this.bonusPoints += toAdd;
    }

    /**
     * Removes bonus points from customer's account if possible.
     * Throws exception if trying to remove more bonus points than
     * customer has on their account.
     * @param toRemove number of bonus points to remove
     * @throws BonusPointsException if trying to remove more bonus points
     * than customer has on their account
     */
    public void removeBonusPoints(int toRemove) throws BonusPointsException {
        if (this.bonusPoints - toRemove >= 0) {
            this.bonusPoints -= toRemove;
        } else {
            throw new BonusPointsException("Not enough bonus points.");
        }
    }

    public String getBonusPointsAsString() {
        return "Remaining bonus points: " + this.bonusPoints;
    }
}
