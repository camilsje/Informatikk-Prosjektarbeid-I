package sportsRentals.springboot.restserver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.List;

import sportsRentals.core.Equipment;
import sportsRentals.core.EquipmentStorage;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { SportsRentalsController.class, SportsRentalsService.class,
    SportsRentalsApplication.class })
@WebMvcTest
public class SportsRentalsServiceTest {

    @Autowired
    private SportsRentalsService sportsRentalsService;

    @BeforeEach
    @DisplayName("Sets up an instance of an EquipmentStorage object and adds three Equipment-objects to it.")
    public void setUp() {
        EquipmentStorage equipmentStorage = new EquipmentStorage();
        sportsRentalsService.setEquipmentStorage(equipmentStorage);
        HashMap<String, Double> prices = new HashMap<>();
        Equipment equipment = new Equipment("Tennis Racket", prices);
        Equipment equipment2 = new Equipment("Basketball", prices);
        Equipment equipment3 = new Equipment("Bicycle", prices);
        equipmentStorage.addToAllEquipment(equipment);
        equipmentStorage.addToAllEquipment(equipment2);
        equipmentStorage.addToAllEquipment(equipment3);
    }

    @Test
    @DisplayName("Tests getAvailableEquipment by verifying that correct equipment are retrived")
    public void testGetAvailableEquipment() {
        List<Equipment> availableEquipment = sportsRentalsService.getAvailableEquipment("2024-06-11", "3");

        assertEquals("Tennis Racket", availableEquipment.get(0).getType());
    }

    @Test
    @DisplayName("Tests getEquipment by verifying that correct equipment are retrieved")
    public void testGetEquipment() {
        Equipment equipment = sportsRentalsService.getEquipment("Basketball");

        assertEquals("Basketball", equipment.getType());
    }

    @Test
    @DisplayName("Tests addToAvailability by verifying that the list has the right size and absence of equipment")
    public void testAddToAvailability() {
        sportsRentalsService.addToAvailablity("Tennis Racket", "2024-06-10", "3");
        List<Equipment> availableEquipment = sportsRentalsService.getAvailableEquipment("2024-06-10", "3");
        Equipment testEquipment = new Equipment("Tennis Racket", null);

        assertEquals(false, availableEquipment.contains(testEquipment));
        assertEquals(2, availableEquipment.size());
    }

    @Test
    @DisplayName("Tests autoSaveEquipmentStorage by verifying that the list has the right size and absence of equipment")
    public void testAutoSaveEquipmentStorage() {
        sportsRentalsService.addToAvailablity("Bicycle", "2024-06-10", "3");
        sportsRentalsService.autoSaveEquipmentStorage();
        Equipment testEquipment = new Equipment("Bicycle", null);
        List<Equipment> availableEquipment = sportsRentalsService.getAvailableEquipment("2024-06-10", "3");

        assertEquals(false, availableEquipment.contains(testEquipment));
        assertEquals(2, availableEquipment.size());
    }

    @Test
    @DisplayName("Tests getEquipmentStorage by verifying that the list has the right size")
    public void testGetEquipmentStorage() {
        assertEquals(3, sportsRentalsService.getEquipmentStorage().getAllEquipment().size());
    }
}
