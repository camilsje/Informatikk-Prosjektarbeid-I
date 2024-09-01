package sportsRentals.ui;

import java.time.LocalDate;
import java.util.List;

import sportsRentals.core.Equipment;
import sportsRentals.core.EquipmentStorage;

/**
 * Interface defining methods necessary for interacting with a remote server.
 */
public interface SportsRentalsDataAccess {

    /**
     * Retrieves an instance of an EquipmentStorage object from the remote server.
     * @return An EquipmentStorage
     */
    EquipmentStorage getEquipmentStorage();

    /**
     * Retrieves a filtered list containing only the available equipments, based on the
     * input params, from the remote server.
     * @param startDate The startdate of a rental period
     * @param days The amount of days in the rental period
     * @return The filtered list of available equipment
     */
    List<Equipment> getAllAvailableEquipment(LocalDate startDate, int days);

    /**
     * Retrieves a selected Equipment based on the input param.
     * @param type The type of the Equipment
     * @return The Equipment matching the input type
     */
    Equipment getSelectedEquipment(String type);

    /**
     * Updates the availability of a selected Equipment based on the input params to the remote server.
     * @param type The type of the Equipment to select
     * @param startDate The start date of the rental period
     * @param days The duration of days in the rental period
     */
    void postAvailability(String type, LocalDate startDate, int days);
}
