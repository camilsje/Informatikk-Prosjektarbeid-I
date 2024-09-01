package sportsRentals.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EquipmentStorageTest {

    private EquipmentStorage equipmentStorage;
    private Equipment equipment1, equipment2;

    @BeforeEach
    @DisplayName("Sets up an EquipmentStorage object and adds two Equipment-objects to it and unavailable dates")
    public void setUp() {
        equipmentStorage = new EquipmentStorage();
        HashMap<String, Double> p1 = new HashMap<>();
        p1.put("oneDay", 100.0);
        p1.put("threeDays", 200.0);
        p1.put("oneWeek", 400.0);
        p1.put("twoWeeks", 600.0);
        equipment1 = new Equipment("Snowboard", p1);
        equipmentStorage.addToAllEquipment(equipment1);

        HashMap<String, Double> p2 = new HashMap<>();
        p2.put("oneDay", 100.0);
        p2.put("threeDays", 140.0);
        p2.put("oneWeek", 300.0);
        p2.put("twoWeeks", 430.0);
        equipment2 = new Equipment("Basketball", p2);
        equipmentStorage.addToAllEquipment(equipment2);
        equipment2.addToAvailablity(LocalDate.of(2023, 12, 13), 3);
    }

    @Test
    @DisplayName("Tests addToAllEquipment(Equipment equipment)")
    public void testAddToAllEquipment() {
        HashMap<String, Double> prices = new HashMap<>();
        prices.put("oneDay", 30.0);
        prices.put("threeDays", 50.0);
        prices.put("oneWeek", 330.0);
        prices.put("twoWeeks", 530.0);
        Equipment equipment = new Equipment("Canoe", prices);
        equipmentStorage.addToAllEquipment(equipment);

        assertTrue(equipmentStorage.getAllEquipment().contains(equipment));
    }

    @Test
    @DisplayName("Tests getEquipmentFromType(String type)")
    public void testGetEquipmentFromType() {
        assertEquals(equipment1, equipmentStorage.getEquipmentFromType("Snowboard"));
        assertThrows(IllegalArgumentException.class, () -> equipmentStorage.getEquipmentFromType("Tennis racket"));
    }

    @Test
    @DisplayName("Tests getAllEquipment by checking if the list has the expected size")
    public void testGetAllEquipment() {
        assertEquals(2, equipmentStorage.getAllEquipment().size());
        assertTrue(equipmentStorage.getAllEquipment().contains(equipment1));
        assertTrue(equipmentStorage.getAllEquipment().contains(equipment2));
    }

    @Test
    @DisplayName("Tests getAllAvailableEquipment by checking if the list has expected size for diffferent dates")
    public void testGetAllAvailableEquipment() {
        List<Equipment> availableEquipment = equipmentStorage.getAllAvailableEquipment(LocalDate.of(2023, 12, 14), 1);
        assertEquals(1, availableEquipment.size());

        List<Equipment> availableEquipment1 = equipmentStorage.getAllAvailableEquipment(LocalDate.of(2024, 05, 11), 7);
        assertEquals(2, availableEquipment1.size());
    }
}
