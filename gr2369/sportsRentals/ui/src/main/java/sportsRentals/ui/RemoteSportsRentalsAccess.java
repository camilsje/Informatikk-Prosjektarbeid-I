package sportsRentals.ui;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import sportsRentals.core.Equipment;
import sportsRentals.core.EquipmentStorage;
import sportsRentals.json.sportsRentalsPersistence;

/**
 * Implementation of the SportsRentalsDataAccess interface.
 * Communicates with the remote server with HTTP-requests to perform operations on the application data
 */
public class RemoteSportsRentalsAccess implements SportsRentalsDataAccess {

    private final URI endpointBaseUri;
    private ObjectMapper objectMapper;
    private EquipmentStorage equipmentStorage;
    private Equipment equipment;

    private static final String APPLICATION_JSON = "application/json";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    /**
     * Constructs a RemoteSportsRentalsAccess object with the specified URI endpoint, and sets the objectMapper.
     * @param endpointBaseUri
     */
    public RemoteSportsRentalsAccess(final URI endpointBaseUri) {
        this.endpointBaseUri = endpointBaseUri;
        objectMapper = sportsRentalsPersistence.createObjectMapper();
    }

    /**
     * Encodes an input param String to be used in an URI.
     * @param s The string to encode
     * @return The encoded string
     */
    private String uriParam(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    /**
     * Creates an endpoint URI for retrieving a specific equipment based on the input param "type".
     * @param type The type of equipment
     * @return The constructed endpoint URI
     */
    private URI equipmentUri(String type) {
        return endpointBaseUri.resolve("/springboot/sportsrentals/equipment/").resolve(uriParam(type));
    }

    /**
     * Creates an endpoint URI with the input params "startDate" and "days".
     * for retrieving available equipment based on the input params
     * @param startDate The startdate of the rental period as a string
     * @param days The amount of days in the rental period as a string
     * @return The constructed endpoint URI
     */
    private URI equipmentAvaliableUri(String startDate, String days) {
        return endpointBaseUri.resolve("/springboot/sportsrentals/equipment/available/")
            .resolve(uriParam(startDate) + "/" + uriParam(days));
    }

    /**
     * Sends a GET-request to retrieve an instance of an EquipmentStorage object from the remote server.
     * If the equipmentStorage-object has already been retrieved (is not null), the method simply returns this
     * @return The equipmentStorage object retrieved from server
     */
    @Override
    public EquipmentStorage getEquipmentStorage() {
        if (equipmentStorage == null) {
            HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();
            try {
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
                final String responseString = response.body();
                this.equipmentStorage = objectMapper.readValue(responseString, EquipmentStorage.class);
            } catch (Exception e) {
                System.err.println("Error when trying to get equipment storage: " + e);
            }
        }
        return equipmentStorage;
    }

    /**
     * Sends a GET-request to retrieve a filtered list containing only the available equipments, based on the
     * input params, from the remote server.
     * @param startDate The startdate of a rental period
     * @param days The amount of days in the rental period
     * @return The filtered list of available equipment
     */
    @Override
    public List<Equipment> getAllAvailableEquipment(LocalDate startDate, int days) {
        List<Equipment> availableEquipment = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder(equipmentAvaliableUri(startDate.toString(), Integer.toString(days)))
            .header(ACCEPT_HEADER, APPLICATION_JSON)
            .GET()
            .build();
        try {
            final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            availableEquipment = objectMapper.readValue(responseString, objectMapper.getTypeFactory()
                .constructCollectionType(List.class, Equipment.class));
        } catch (Exception e) {
            System.err.println("Error when trying to get all available equipment: " + e);
        }
        return availableEquipment;
    }

    /**
     * Sends a GET-request to retrieve a selected equipment, based on the input param, from the remote server.
     * @param type The type of the equipment to retrieve
     * @return The equipment-object matching the type provided
     */
    @Override
    public Equipment getSelectedEquipment(String type) {
        HttpRequest request = HttpRequest.newBuilder(equipmentUri(type))
            .header(ACCEPT_HEADER, APPLICATION_JSON)
            .GET()
            .build();
        try {
            final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            this.equipment = objectMapper.readValue(responseString, Equipment.class);
        } catch (Exception e) {
            System.err.println("Error when trying to get selected equipment: " + e);
        }
        return equipment;
    }

    /**
     * Sends a POST-request to update the availability of a specified equipment to the remote server.
     * Constructs a JSON-string of the input params, and sends this with the POST-request
     * If the deserialized response indicates a change in the availability, the equipments availability is updated
     * @param type The type of the equipment to update
     * @param startDate The startdate of a rental period
     * @param days The amount of days in the rental period
     */
    @Override
    public void postAvailability(String type, LocalDate startDate, int days) {
        try {

            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{")
            .append("\"type\": \"").append(type).append("\",")
            .append("\"startdate\": \"").append(startDate.toString()).append("\",")
            .append("\"days\": \"").append(Integer.toString(days)).append("\",");
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
            jsonBuilder.append("}");
            String jsonString = jsonBuilder.toString();

            HttpRequest request = HttpRequest.newBuilder(endpointBaseUri
                .resolve("/springboot/sportsrentals/equipment/available"))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

            final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();

            Boolean changed = objectMapper.readValue(responseString, Boolean.class);
            if (changed != null) {
                equipment.addToAvailablity(startDate, days);
            }
        } catch (Exception e) {
            System.err.println("Error when trying to post availability: " + e);
        }
    }
}
