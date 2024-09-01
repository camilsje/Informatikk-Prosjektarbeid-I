package sportsRentals.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The EquipmentStorage class represents a storage of Equipment objects initalized from a json file.
 * Equipment objects are stored in a list and this class allows you to manage this list with different methods.
 */
public class EquipmentStorage {

    private List<Equipment> allEquipment = new ArrayList<>();
    private List<Equipment> allAvailableEquipment = new ArrayList<>();

    /**
     * Initializes a new EquipmentStorage object.
     */
    public EquipmentStorage() {
    }

    /**
     *
     * @return A list containing all equipment objects which are stored in this equipmentStorage object
     */
    public List<Equipment> getAllEquipment() {
        return allEquipment;
    }

    /**
     * Filters the allAvailableEquipment-list to return a list containg exclusively equipments available
     * for the input params.
     * @param startDate The startDate of the rental period to filtrate on
     * @param days The duration of the rental period to filtrate on
     * @return A list containing all available Equipment
     */
    public List<Equipment> getAllAvailableEquipment(LocalDate startDate, int days) {
        allAvailableEquipment.clear();
        for (Equipment equipment : allEquipment) {
            if (equipment.isAvailable(startDate, days)) {
                allAvailableEquipment.add(equipment);
            }
        }
        return allAvailableEquipment;
    }

    /**
     * Adds Equipment to the list "allEquipment" in this equipmentStorage-object.
     * @param equipment The Equipment to be added to the list
     */
    public void addToAllEquipment(Equipment equipment) {
        allEquipment.add(equipment);
    }

    /**
     * Gets the equipment object which matches the given type.
     * @param type The type of the Equipment we want to retrieve
     * @return The corresponding Equipment object to the given type
     * @throws IllegalArgumentException If no element in the "allEquipment" list corresponds with the specified type
     */
    public Equipment getEquipmentFromType(String type) {
        for (Equipment equipment : allEquipment) {
            if (equipment.getType().equals(type)) {
                return equipment;
            }
        }
        throw new IllegalArgumentException("Equipment is not in list.");
    }
}
