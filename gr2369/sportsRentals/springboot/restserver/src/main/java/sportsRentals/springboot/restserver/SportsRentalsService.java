package sportsRentals.springboot.restserver;

import sportsRentals.core.Equipment;
import sportsRentals.core.EquipmentStorage;
import sportsRentals.json.sportsRentalsPersistence;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.net.URL;
import org.springframework.stereotype.Service;


/**
 * Service class to provide logic used by the SportsRentalsController.class.
 */
@Service
public class SportsRentalsService {

    private EquipmentStorage equipmentStorage;
    private sportsRentalsPersistence sportsRentalsPersistence;

    /**
     * Constructs a SportsRentalsService object.
     * Sets the equipmentStorage based on the input param and creates a new sportsRentalsPersistence object.
     * @param equipmentStorage The initial equipmentStorage object
     */
    public SportsRentalsService(EquipmentStorage equipmentStorage) {
        this.equipmentStorage = equipmentStorage;
        this.sportsRentalsPersistence = new sportsRentalsPersistence();
   }

   /**
    * Constructs a SportsRentalsService object with a default equipmentStorage.
    * Uses the createDefaultEquipmentStorage()-method to create the equipment storage
    */
    public SportsRentalsService() {
        this(createDefaultEquipmentStorage());
    }

    /**
     *
     * @return the current instance of the equipmentStorage
     */
    public EquipmentStorage getEquipmentStorage() {
        return equipmentStorage;
    }

    /**
     * Retrieves all the available equipment based on the input params.
     * @param startDate The startdate of a rental period
     * @param days The amount of days to rent
     * @return A list of all equipment available for rent
     */
    public List<Equipment> getAvailableEquipment(String startDate, String days) {
        List<Equipment> equipments = equipmentStorage
            .getAllAvailableEquipment(LocalDate.parse(startDate), Integer.parseInt(days));
        return equipments;
    }

    /**
     *
     * @param equipmentStorage The equipmentStorage instance to set
     */
    public void setEquipmentStorage(EquipmentStorage equipmentStorage) {
        this.equipmentStorage = equipmentStorage;
    }

    /**
     * Creates and returns a default EquipmentStorage based on the equipment.json-file.
     * @return The default EquipmentStorage from file
     */
    private static EquipmentStorage createDefaultEquipmentStorage() {
        sportsRentalsPersistence sportsRentalsPersistence = new sportsRentalsPersistence();
        URL url = SportsRentalsService.class.getResource("equipment.json");
        try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
            return sportsRentalsPersistence.readEquipmentStorage(reader);
        } catch (IOException e) {
            System.out.println("Could not read from file. Exception thrown: " + e + "");
        }
        return null;
    }

    /**
     * Saves the current EquipmentStorage instance to the equipment.json-file.
     */
    public void autoSaveEquipmentStorage() {
        URL url = SportsRentalsService.class.getResource("equipment.json");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(Paths.get(url.getPath())
            .toAbsolutePath().toFile()), StandardCharsets.UTF_8)) {
            sportsRentalsPersistence.writeEquipmentStorage(equipmentStorage, writer);
        } catch (Exception e) {
            System.out.println("feil i auotosave");
            System.out.println(e);
        }
    }

    /**
     * Takes in a type and returns the corresponding equipment.
     * @param type The type of equipment to return
     * @return The corresponding equipment
     */
    public Equipment getEquipment(String type) {
        return equipmentStorage.getEquipmentFromType(type);
    }

    /**
     * Adds a rental period to the equipment matching the input param type.
     * @param type The type of the equipment-object
     * @param startDate The startdate of the rental period to add to the equipment-object
     * @param days The amount of days in the rental period to add to the equipment-object
     */
    public void addToAvailablity(String type, String startDate, String days) {
        equipmentStorage.getEquipmentFromType(type).addToAvailablity(LocalDate.parse(startDate), Integer.parseInt(days));
    }
}
