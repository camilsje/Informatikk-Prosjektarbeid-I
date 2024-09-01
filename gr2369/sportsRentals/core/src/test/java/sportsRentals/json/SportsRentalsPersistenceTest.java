package sportsRentals.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sportsRentals.core.EquipmentStorage;

public class SportsRentalsPersistenceTest {

    private sportsRentalsPersistence sportsRentalsPersistence = new sportsRentalsPersistence();
    private String filePath = "../../sportsRentals/core/src/test/resources/testFile.json";

    @Test
    @DisplayName("Tests the loadEquipmentStorage and saveEquipmentStorage methods by reading and writing from/to file")
    public void testWriteAndRead() throws IOException {
        Reader reader = new FileReader(filePath);
        EquipmentStorage equipmentStorage = sportsRentalsPersistence.readEquipmentStorage(reader);
        equipmentStorage.getAllEquipment().get(0).addToAvailablity(LocalDate.of(2023, 11,  11), 3);

        Writer writer = new FileWriter(filePath);
        sportsRentalsPersistence.writeEquipmentStorage(equipmentStorage, writer);

        Reader reader1 = new FileReader(filePath);
        EquipmentStorage equipmentStorage1 = sportsRentalsPersistence.readEquipmentStorage(reader1);

        assertEquals(equipmentStorage.getEquipmentFromType("Basketball").getAvailablity().size(),
            equipmentStorage1.getEquipmentFromType("Basketball").getAvailablity().size());
        assertEquals(equipmentStorage1.getAllEquipment().size(), equipmentStorage.getAllEquipment().size());

        Reader reader2 = new FileReader(filePath);
        equipmentStorage = sportsRentalsPersistence.readEquipmentStorage(reader2);
        assertEquals("Basketball", equipmentStorage.getAllEquipment().get(0).getType());
    }
}
