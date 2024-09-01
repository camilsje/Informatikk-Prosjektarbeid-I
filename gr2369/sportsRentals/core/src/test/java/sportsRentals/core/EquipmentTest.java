package sportsRentals.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EquipmentTest {

    private Equipment equipment;
    private HashMap<String, Double> prices = new HashMap<>();

    @BeforeEach
    @DisplayName("Sets up an instance of an Equipment-object.")
    public void setUp() {
        prices.put("oneDay", 100.0);
        prices.put("threeDays", 200.0);
        prices.put("oneWeek", 400.0);
        prices.put("twoWeeks", 600.0);
        equipment = new Equipment("Basketball", prices);
    }

    @Test
    @DisplayName ("Tests the getPrice method.")
    public void testGetPrice() {
        assertEquals(100.0, equipment.getPrice("oneDay"));
    }

    @Test
    @DisplayName ("Tests the addPrice method.")
    public void testAddPrice() {
        equipment.addPrice("threeHours", 200.0);
        assertEquals(200.0, equipment.getPrice("threeHours"));
    }

    @Test
    @DisplayName ("Tests the getType and setType method.")
    public void testGetAndSetType() {
        assertEquals("Basketball", equipment.getType());

        equipment.setType("Kajak");
        assertEquals("Kajak", equipment.getType());
    }

    @Test
    @DisplayName("Tests isAvailable by checking availability for different dates.")
    public void testIsAvailable() {
        assertTrue(equipment.isAvailable(LocalDate.of(2023, 11, 10), 3));
        equipment.addToAvailablity(LocalDate.of(2023, 11, 9), 07);
        assertFalse(equipment.isAvailable(LocalDate.of(2023, 11, 10), 3));
        assertFalse(equipment.isAvailable(LocalDate.of(2023, 11, 07), 14));
        assertFalse(equipment.isAvailable(LocalDate.of(2023, 11, 15), 7));
        assertFalse(equipment.isAvailable(LocalDate.of(2023, 11, 16), 3));
    }

    @Test
    @DisplayName("Tests addToAvailability by checking if the list has the expected size")
    public void testAddToAvailablity() {
        equipment.addToAvailablity(LocalDate.of(2023, 10, 10), 3);
        assertEquals(1, equipment.getAvailablity().size());
    }

    @Test
    @DisplayName("Tests addToAvailabilityTwoDates by ckecking if the list has the expected size")
    public void testAddToAvailablityTwoDates() {
        equipment.addToAvailablityTwoDates("2023-10-10", "2023-10-12");
        assertEquals(1, equipment.getAvailablity().size());
    }

    @Test
    @DisplayName("Tests getStartdate and getRentalperiod methods")
    public void testGetStartDateAndGetEndDate() {
        equipment.setStartDate(LocalDate.of(2024, 02, 05));
        equipment.setRentalPeriod(3);
        assertEquals("2024-02-05", equipment.getStartDate().toString());
        assertEquals(3, equipment.getRentalPeriod());
    }

    @Test
    @DisplayName("Tests convertRentalPeriodToInt method by verifying if the converted string matches the expected string")
    public void testConvertRentalPeriodToInt() {
        equipment.setRentalPeriodString("oneDay");
        equipment.convertRentalPeriodToInt();
        assertEquals(1, equipment.getRentalPeriod());
        assertEquals("oneDay", equipment.getRentalPeriodString());

        equipment.setRentalPeriodString("threeDays");
        equipment.convertRentalPeriodToInt();
        assertEquals(3, equipment.getRentalPeriod());

        equipment.setRentalPeriodString("oneWeek");
        equipment.convertRentalPeriodToInt();
        assertEquals(7, equipment.getRentalPeriod());

        equipment.setRentalPeriodString("twoWeeks");
        equipment.convertRentalPeriodToInt();
        assertEquals(14, equipment.getRentalPeriod());
    }

}
