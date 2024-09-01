package sportsRentals.json.internal;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import sportsRentals.core.Equipment;
import sportsRentals.core.EquipmentStorage;

public class SportsRentalsModule extends SimpleModule {

    private static final String NAME = "SportsRentalsModule";

    /**
     * Creates a new sportsRentalsModule with custom serializers and deserializers.
     * This model contains configuration for serializers and deserializers for the Equipment class
     * and EquipmentStorage class
     */
    public SportsRentalsModule() {
        super(NAME, Version.unknownVersion());
        addSerializer(Equipment.class, new EquipmentSerializer());
        addSerializer(EquipmentStorage.class, new EquipmentStorageSerializer());
        addDeserializer(Equipment.class, new EquipmentDeserializer());
        addDeserializer(EquipmentStorage.class, new EquipmentStorageDeserializer());
    }
}
