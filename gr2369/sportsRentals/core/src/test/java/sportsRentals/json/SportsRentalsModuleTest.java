package sportsRentals.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sportsRentals.core.Equipment;
import sportsRentals.json.internal.SportsRentalsModule;
import sportsRentals.core.EquipmentStorage;

public class SportsRentalsModuleTest {

    private static ObjectMapper mapper;
    private static final String EMPTY_EQUIPMENT_STORAGE_JSON = "{ \"allEquipment\": [] }";
    private static final String EQUIPMENT_STORAGE_JSON = "{"
    + "\"allEquipment\":["
        + "{\"type\":\"Basketball\",\"availability\":[\"2023-10-07,2023-10-14\",\"2023-10-15,2023-10-18\"],"
        + "\"prices\":{\"oneDay\":39.0,\"twoWeeks\":299.0,\"threeDays\":99.0,\"oneWeek\":179.0}},"
        + "{\"type\":\"Canoe\",\"availability\":[\"2023-12-10,2023-12-17\"],"
        + "\"prices\":{\"oneDay\":139.0,\"twoWeeks\":749.0,\"threeDays\":279.0,\"oneWeek\":439.0}}"
        + "]"
    + "}";

    private HashMap<String, Double> prices1, prices2;
    private Equipment equipment1, equipment2;
    private EquipmentStorage equipmentStorage = new EquipmentStorage();

    @BeforeAll
    @DisplayName("Sets up an instnace of an ObjectMapper configured with the SportsRentalsModule")
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new SportsRentalsModule());
    }

    /**
     * Creates a new EquipmentStorage, and fills it with new Equipment-objects.
     * Is used in the tests.
     */
    private void createEquipmentStorage() {
        prices1 = new HashMap<>();
        equipment1 =  new Equipment("Basketball", prices1);
        equipment1.addPrice("twoWeeks", 299.0);
        equipment1.addPrice("oneWeek", 179.0);
        equipment1.addPrice("oneDay", 39.0);
        equipment1.addPrice("threeDays", 99.0);
        equipment1.addToAvailablity(LocalDate.of(2023, 10, 7), 7);
        equipment1.addToAvailablity(LocalDate.of(2023, 10, 15), 3);

        prices2 = new HashMap<>();
        equipment2 = new Equipment("Canoe", prices2);
        equipment2.addPrice("twoWeeks", 749.0);
        equipment2.addPrice("oneWeek", 439.0);
        equipment2.addPrice("oneDay", 139.0);
        equipment2.addPrice("threeDays", 279.0);
        equipment2.addToAvailablity(LocalDate.of(2023, 12, 10), 7);

        equipmentStorage.addToAllEquipment(equipment1);
        equipmentStorage.addToAllEquipment(equipment2);
    }

    @Test
    @DisplayName("Tests serializers by serializing the equipmentstorage and checking if it matches the expected string.")
    public void testSerializers() throws JsonProcessingException {
        createEquipmentStorage();
        try {
            assertEquals(EQUIPMENT_STORAGE_JSON, mapper.writeValueAsString(equipmentStorage));
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Tests the deserializers by checking if the deserialized EquipmentStorage matches the expected string.")
    public void testDeserializers() {
       createEquipmentStorage();
        try {
            EquipmentStorage equipmentStorage1 = mapper.readValue(EQUIPMENT_STORAGE_JSON, EquipmentStorage.class);
            assertEquals(equipmentStorage.getAllEquipment().get(0).getType(), equipmentStorage1.getAllEquipment()
                .get(0).getType());
            assertEquals(equipmentStorage.getAllEquipment().get(0).getPrices(), equipmentStorage1.getAllEquipment()
                .get(0).getPrices());
            assertEquals(equipmentStorage.getAllEquipment().get(0).getAvailablity(), equipmentStorage1.getAllEquipment()
                .get(0).getAvailablity());

            assertEquals(equipmentStorage.getAllEquipment().get(1).getType(), equipmentStorage1.getAllEquipment()
                .get(1).getType());
            assertEquals(equipmentStorage.getAllEquipment().get(1).getPrices(), equipmentStorage1.getAllEquipment()
                .get(1).getPrices());
            assertEquals(equipmentStorage.getAllEquipment().get(1).getAvailablity(), equipmentStorage1.getAllEquipment()
                .get(1).getAvailablity());

            EquipmentStorage emptyEquipmentStorage = mapper.readValue(EMPTY_EQUIPMENT_STORAGE_JSON, EquipmentStorage.class);
            assertTrue(emptyEquipmentStorage.getAllEquipment().isEmpty());
        } catch (JsonProcessingException e) {
            fail();
        }
    }
}
