package sportsRentals.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import sportsRentals.core.Equipment;
import sportsRentals.core.EquipmentStorage;
import sportsRentals.json.internal.EquipmentDeserializer;
import sportsRentals.json.internal.EquipmentSerializer;
import sportsRentals.json.internal.EquipmentStorageDeserializer;
import sportsRentals.json.internal.EquipmentStorageSerializer;
import sportsRentals.json.internal.SportsRentalsModule;

/**
 * Handles the persistence of the application data.
 * Provides methods for reading and writing an equipmentstorage-object and configures
 * the serializers and deserializers defined in the classes under json/internal
 */
public class sportsRentalsPersistence {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Initializes a sportsRentalsPersistence object and configures custom serializers and deserializers for Equipment.
     * and EquipmentStorage classes. This allows for JSON data serialization and deserialization.
     */
    public sportsRentalsPersistence() {
        SimpleModule sportsRentalsModule = new SimpleModule();
        sportsRentalsModule.addDeserializer(Equipment.class, new EquipmentDeserializer());
        sportsRentalsModule.addDeserializer(EquipmentStorage.class, new EquipmentStorageDeserializer());
        sportsRentalsModule.addSerializer(Equipment.class, new EquipmentSerializer());
        sportsRentalsModule.addSerializer(EquipmentStorage.class, new EquipmentStorageSerializer());
        mapper.registerModule(sportsRentalsModule);
    }

    /**
     * Creates a sportsRentalsModule for this application to configure serializers and deserializers.
     * @return a SportsRentalsModule
     */
    public static SimpleModule createJacksonModule() {
        return new SportsRentalsModule();
    }

    /**
     * Creates an ObjectMapper configured with the module used in the createJacksonModule()-method.
     * @return an objectmapper
     */
    public static ObjectMapper createObjectMapper() {
        return new ObjectMapper().registerModule(createJacksonModule());
    }


    /**
     * Reads and deserializes an equipmentStorage from the provided reader.
     * @param reader The reader to be used to read the EquipmentStorage object from file
     * @return An EquipmentStorage object containing the equipment deserialized from the reader
     * @throws IOException If an I/O error occurs during the deserialization
     */
    public EquipmentStorage readEquipmentStorage(Reader reader) throws IOException {
        return mapper.readValue(reader, EquipmentStorage.class);
    }

    /**
     * Uses the objectmapper to write the equipmentStorage to the provided writer.
     * @param equipmentStorage The EquipmentStorage object to be serialized and written to the writer
     * @param writer The writer to be used to write the EquipmentStorage object to file
     * @throws IOException If an I/O error occurs during file writing
     * @throws IllegalStateException If there is an issue with the JSON file or its format
     */
    public void writeEquipmentStorage(EquipmentStorage equipmentStorage, Writer writer)
            throws IOException, IllegalStateException {
        mapper.writeValue(writer, equipmentStorage);
    }
}
