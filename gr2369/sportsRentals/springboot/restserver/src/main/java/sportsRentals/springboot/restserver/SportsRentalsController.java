package sportsRentals.springboot.restserver;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import sportsRentals.core.Equipment;
import sportsRentals.core.EquipmentStorage;


/**
 * Handles REST requests made by the client.
 */
@RestController
@RequestMapping(SportsRentalsController.SPORTSRENTALS_SERVICE_PATH)
public class SportsRentalsController {

    /**
     * Base path for all endpoints used in this class.
     */
    public static final String SPORTSRENTALS_SERVICE_PATH = "springboot/sportsrentals";

    @Autowired
    private SportsRentalsService sportsRentalsService;

    /**
     * Maps to the base path and returns the equipmentStorage-object.
     * @return the EquipmentStorage-object retrieved
     */
    @GetMapping
    public EquipmentStorage getEquipmentStorage() {
        return sportsRentalsService.getEquipmentStorage();
    }

    /**
     * Retrieves all the available equipment based on the input params.
     * @param startdate The startdate of a rental period
     * @param days The amount of days to rent
     * @return A list of all equipment available for rent
     */
    @GetMapping(path = "/equipment/available/{startdate}/{days}")
    public List<Equipment> getAvailableEquipment(@PathVariable("startdate") String startdate,
            @PathVariable("days") String days) {
        return sportsRentalsService.getAvailableEquipment(startdate, days);
    }

    /**
     * Retrieves a specific equipment object based on the type provided as an input param.
     * Replaces "+" with " " in case the equipment's type contains a space
     * @param type The type of equipment to retrieve
     * @return The corresponding equipment
     */
    @GetMapping(path = "/equipment/{type}")
    public Equipment getSelectedEquipment(@PathVariable("type") String type) {
        return sportsRentalsService.getEquipment(type.replace("+", " "));
    }

    /**
     * Handles a POST-request to update an equipment's availability.
     * The information to update is parsed from the JSON request body, which contains the type of equipment
     * to update, the startdate of the rental and the amount of days in the rental.
     * Uses the addToAvailability() and autoSaveEquipmentStorage() to save the new equipmentStorage
     * @param body A JSON request body containing information about the rental
     * @return True to indicate a successfull update
     * @throws JsonMappingException If an issue occurs when trying to map the JSON request
     * @throws JsonProcessingException If an issue occurs when trying to process the JSON request
     */
    @PostMapping(path = "/equipment/available")
    public boolean postAvailability(@RequestBody String body) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(body);
        String type = node.get("type").asText();
        String startDate = node.get("startdate").asText();
        String days = node.get("days").asText();
        sportsRentalsService.addToAvailablity(type.replace("+", " "), startDate, days);
        sportsRentalsService.autoSaveEquipmentStorage();
        return true;
    }
}
