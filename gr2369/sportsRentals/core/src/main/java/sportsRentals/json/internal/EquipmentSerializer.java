package sportsRentals.json.internal;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import sportsRentals.core.Equipment;

public class EquipmentSerializer extends JsonSerializer<Equipment> {

    /**
     * Serializes an Equipment object into JSON representation (JSON data).
     * @param equipment The Equipment object to be serialized
     * @param generator The JSON generator which is used to write JSON data
     * @param serializers A helper for managing several serializers
     * @throws IOException If an I/O error occurs during serialization, such as writing to the JSON output
     * Format: {
            "type": "Basketball",
            "availability": ["2023-10-07,2023-10-13", "2023-10-15,2023-10-23"],
            "prices": {
                "oneDay": 39.00,
                "threeDays": 99.0,
                "oneWeek": 179.0,
                "twoWeeks": 299.0
            }
        }, ...
     */
    @Override
    public void serialize(Equipment equipment, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeStartObject();

        generator.writeStringField("type", equipment.getType());

        generator.writeArrayFieldStart("availability");
        for (String unavailableDates : equipment.getAvailablity()) {
            generator.writeString(unavailableDates);
        }
        generator.writeEndArray();

        generator.writeObjectFieldStart("prices");
        for (Map.Entry<String, Double> prices : equipment.getPrices().entrySet()) {
            generator.writeNumberField(prices.getKey(), prices.getValue());
        }
        generator.writeEndObject();

        generator.writeEndObject();
    }

}
