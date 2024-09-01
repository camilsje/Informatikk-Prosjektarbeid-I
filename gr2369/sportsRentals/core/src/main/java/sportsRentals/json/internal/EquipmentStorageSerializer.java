package sportsRentals.json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import sportsRentals.core.Equipment;
import sportsRentals.core.EquipmentStorage;

public class EquipmentStorageSerializer extends JsonSerializer<EquipmentStorage> {

    /**
     * Serializes an EquipmentStorage object into JSON representation (JSON data).
     * @param equipmentStorage The EquipmentStorage object to be serialized
     * @param generator The JSON generator which is used to write JSON data
     * @param serializers A helper for managing several serializers
     * @throws IOException If an I/O error occurs during serialization, such as writing to the JSON output
     * Format: { "allEquipment": [ ... ] }
     */
    @Override
    public void serialize(EquipmentStorage equipmentStorage, JsonGenerator generator, SerializerProvider serializers)
            throws IOException {
        generator.writeStartObject();
        generator.writeArrayFieldStart("allEquipment");
        for (Equipment equipment : equipmentStorage.getAllEquipment()) {
            generator.writeObject(equipment);
        }
        generator.writeEndArray();
        generator.writeEndObject();
    }
}
