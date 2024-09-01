package sportsRentals.json.internal;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import sportsRentals.core.Equipment;

public class EquipmentDeserializer extends JsonDeserializer<Equipment> {

  /**
   * Parses a JSON representation of an Equipment object and creates a corresponding Equipment instance.
   * @param parser The JSON parser used to interpret and process data in a JSON format
   * @param ctxt The deserialization context refers to to the context in which the deserialization is performed
   * @throws IOException If an I/O error occurs during the deserialization
   * @throws JsonProcessingException If a JsonProcessingException error occurs during the deserialization
   * @return deserialized equipment
   */
  @Override
  public Equipment deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  /**
   * Deserializes an equipment object from a JsonNode.
   * @param jsonNode The node representing the object in the JSON-file
   * @return The deserialized equipment if valid input, if not it returns null
   */
  public Equipment deserialize(JsonNode jsonNode) {
  if (jsonNode instanceof ObjectNode objectNode) {
    HashMap<String, Double> emptyPrices = new HashMap<>();
    Equipment equipment = new Equipment("", emptyPrices);

    JsonNode textNode = objectNode.get("type");
    if (textNode instanceof TextNode) {
      equipment.setType(textNode.asText());
    }

    JsonNode priceNode = objectNode.get("prices");

    JsonNode oneDayNode = priceNode.get("oneDay");
    if (oneDayNode != null && oneDayNode.isDouble()) {
      equipment.addPrice("oneDay", oneDayNode.asDouble());
    }
    JsonNode threeHoursNode = priceNode.get("threeDays");
    if (threeHoursNode != null && threeHoursNode.isDouble()) {
      equipment.addPrice("threeDays", threeHoursNode.asDouble());
    }
    JsonNode oneWeekNode = priceNode.get("oneWeek");
    if (oneWeekNode != null && oneWeekNode.isDouble()) {
      equipment.addPrice("oneWeek", oneWeekNode.asDouble());
    }
    JsonNode twoWeeksNode = priceNode.get("twoWeeks");
    if (twoWeeksNode != null && twoWeeksNode.isDouble()) {
      equipment.addPrice("twoWeeks", twoWeeksNode.asDouble());
    }

    JsonNode availabilityNode = objectNode.get("availability");
    for (JsonNode datesNode : (JsonNode) availabilityNode) {
      String[] dates = datesNode.asText().split(",");
      equipment.addToAvailablityTwoDates(dates[0], dates[1]);
    }

    return equipment;
    }
    return null;
  }

}
