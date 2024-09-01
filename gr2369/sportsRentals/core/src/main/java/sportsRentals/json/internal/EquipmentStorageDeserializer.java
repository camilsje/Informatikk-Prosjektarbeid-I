package sportsRentals.json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import sportsRentals.core.Equipment;
import sportsRentals.core.EquipmentStorage;

public class EquipmentStorageDeserializer extends JsonDeserializer<EquipmentStorage> {

    private EquipmentDeserializer equipmentDeserializer = new EquipmentDeserializer();

    /**
     * Deserializes JSON data into an EquipmentStorage object containing Equipment objects.
     * @param parser parser The JSON parser used to interpret and process data in a JSON format
     * @param ctxt The deserialization context refers to to the context in which the deserialization is performed
     * @throws IOException If an I/O error occurs during the deserialization
     * @throws JsonProcessingException If a JsonProcessingException error occurs during the deserialization
     * @return The deserialized equipmentstorage object
     */
    public EquipmentStorage deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        if (treeNode instanceof ObjectNode) {
            EquipmentStorage equipmentStorage = new EquipmentStorage();
            JsonNode allEquipmentNode = (JsonNode) treeNode.get("allEquipment");
            if (allEquipmentNode instanceof ArrayNode) {
                for (JsonNode equipmentNode : (ArrayNode) allEquipmentNode) {
                    Equipment equipment = equipmentDeserializer.deserialize(equipmentNode);
                    if (equipment != null) {
                        equipmentStorage.addToAllEquipment(equipment);
                    }
                }
            }
            return equipmentStorage;
        }
        return null;
    }

}
