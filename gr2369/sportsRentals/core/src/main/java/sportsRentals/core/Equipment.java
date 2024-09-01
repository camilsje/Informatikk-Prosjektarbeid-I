package sportsRentals.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Equipment class represents an equipment available for rent.
 * It stores information about the type of equipment, its prices and its availability.
 * It includes various methods for accessing and managing this information.
 */
public class Equipment {

    private String type;
    private HashMap<String, Double> prices;
    private List<String> availablity = new ArrayList<String>();
    private String rentalPeriodString;
    private int rentalPeriod;
    private LocalDate startDate;

    /**
     * Initializes a new Equipment object with the specified type, prices and sets it as available.
     * @param type The type of Equipment as a String
     * @param prices The prices of an Equipment object as a hashmap that contains rental periods (Strings) and
     * corresponding prices (Doubles)
     */
    public Equipment(String type, HashMap<String, Double> prices) {
        this.type = type;
        this.prices = prices;
    }

    /**
     * Initializes a new empty Equipment object.
     */
    public Equipment() {
    }

    /**
     * Gets the list "availability" that contains all the rented periods of an equipment.
     * @return The list "availability" containing Strings representing rented periods in the format "startDate, endDate"
     */
    public List<String> getAvailablity() {
        return availablity;
    }

    /**
     * Adds a new rented period to the availablty list of an Equipment object in the format: "startDate, endDate".
     * @param startDate The start of the rental period as a LocalDate
     * @param days The duration of the rental period as an int
     */
    public void addToAvailablity(LocalDate startDate, int days) {
        LocalDate endDate = startDate.plusDays(days);
        availablity.add(startDate + "," + endDate);
    }

    /**
     * Adds a new rented period to the availabilty list of an Equipment object in the format: "startDate, endDate".
     * @param startDateString The start of the rental period as a String
     * @param endDateString The end of the rental period as a String
     */
    public void addToAvailablityTwoDates(String startDateString, String endDateString) {
        availablity.add(startDateString + "," + endDateString);
    }

    /**
     * Returns "true" or "false" based on the equipments availability in the given rental period.
     * @param startDate The start of the rental period as a LocalDate
     * @param days The duration of the rental period as an int
     * @return boolean "true" for available and "false" for unavailable
     */
    public Boolean isAvailable(LocalDate startDate, int days) {
        LocalDate endDate = startDate.plusDays((int) days);
        for (String unavailableDates : availablity) {
            String[] dates = unavailableDates.split(",");
            LocalDate unavailableStartDate = LocalDate.parse(dates[0]);

            LocalDate unavailableEndDate = LocalDate.parse(dates[1]);

            if ((startDate.isAfter(unavailableStartDate) && startDate.isBefore(unavailableEndDate))
                || (endDate.isAfter(unavailableStartDate) && endDate.isBefore(unavailableEndDate))
                || (startDate.isBefore(unavailableStartDate) && endDate.isAfter(unavailableEndDate))
                || (startDate.isEqual(unavailableStartDate) || startDate.isEqual(unavailableEndDate)
                || endDate.isEqual(unavailableEndDate) || endDate.isEqual(unavailableStartDate))) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return The type of equipment as a String
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type Sets the type of equipment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Adds a new entry to the hashmap with the rental period as the key and the corresponding price as the value.
     * @param key The rental period as a String
     * @param value The corresponding price as a Double
     */
    public void addPrice(String key, Double value) {
        prices.put(key, value);
    }

    /**
     *
     * @return The hashmap "prices" contains the valid rental periods and corresponding prices.
     */
    public HashMap<String, Double> getPrices() {
        return prices;
    }

    /**
     * Gets the price for a specific rental period.
     * @param key rental period as a String
     * @return the corresponding price (Double) to the given key
     */
    public Double getPrice(String key) {
        return prices.get(key);
    }

    /**
     *
     * @return startDate of the equipments rented period
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate the startdate to set as the equipments rented period
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return the rented period of the equipment as a string
     */
    public String getRentalPeriodString() {
        return rentalPeriodString;
    }

    /**
     *
     * @param rentalPeriodString sets the rented period of the equipment (as a string)
     */
    public void setRentalPeriodString(String rentalPeriodString) {
        this.rentalPeriodString = rentalPeriodString;
    }

    /**
     *
     * @return the rented period of the equipment
     */
    public int getRentalPeriod() {
        return rentalPeriod;
    }

    /**
     *
     * @param rentalPeriod sets the rented period of the equipment
     */
    public void setRentalPeriod(int rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    /**
     * Converts the rental period represented as a string to the corresponding integer value in days.
     * Checks all possible values of rentalPeriodString and maps it to a corresponding integer.
     */
    public void convertRentalPeriodToInt() {
        if ("oneDay".equals(rentalPeriodString)) {
            rentalPeriod = 1;
        } else if ("threeDays".equals(rentalPeriodString)) {
            rentalPeriod = 3;
        } else if ("oneWeek".equals(rentalPeriodString)) {
            rentalPeriod = 7;
        } else if ("twoWeeks".equals(rentalPeriodString)) {
            rentalPeriod = 14;
        }
    }
}
