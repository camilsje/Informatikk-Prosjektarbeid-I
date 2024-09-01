package sportsRentals.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sportsRentals.core.Equipment;

/**
 * The controller class for the application.
 * It handles user interactions, manages equipment rentals and updates the UI according to how the user interacts with it.
 */
public class sportsRentalsController {

    @FXML
    private ListView<String> availableEquipment;

    @FXML
    private Button  oneDay, threeDays, oneWeek, twoWeeks, confirmRental, startOver, show;

    @FXML
    private TextField informationRental;

    @FXML
    private Label equipmentType, price, startDate, endDate;

    @FXML
    private DatePicker calendar;

    private Equipment equipmentTemp = new Equipment();
    private Equipment equipment;
    private SportsRentalsDataAccess dataAccess;

    /**
     * This method is automatically called by JavaFX when the application is launched.
     * Creates an instance of the RemoteSportsRentalsAccess and sets the endpoint, and uses this to send a
     * GET-request to retrieve the EquipmentStorage instance from the remote server
     * Disables manual input to the datepicker and prohibits user from choosing invalid dates
     */
    @FXML
    public void initialize() {
        try {
            dataAccess = new RemoteSportsRentalsAccess(new URI("http://localhost:8080/springboot/sportsrentals/"));
            dataAccess.getEquipmentStorage();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        calendar.getEditor().setDisable(true);
        calendar.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                LocalDate sixMonthsFromNow = LocalDate.now().plusMonths(6);
                setDisable(empty || date.compareTo(today) < 0 ||  date.compareTo(sixMonthsFromNow) > 0);
            }
        });
    }

    /**
     * Sets the start date of equipmentTemp to the selected date.
     */
    @FXML
    private void handleStartDate() {
        equipmentTemp.setStartDate(calendar.getValue());
    }

    /**
     * Sets the rental period of equipmentTemp to the selected rental period.
     * @param event The event triggered by one of the four buttons describing the rental periods being pressed
     */
    @FXML
    private void handleRentalPeriod(ActionEvent event) {
        Button buttonPressed = (Button) event.getSource();
        equipmentTemp.setRentalPeriodString(buttonPressed.getId());
    }

    /**
     * Sets the visibility status of the specified UI-components to the provided value b.
     * @param b The boolean value indicating the requested visibility of the components
     */
    private void setDisable(Boolean b) {
        oneDay.setDisable(b);
        threeDays.setDisable(b);
        oneWeek.setDisable(b);
        twoWeeks.setDisable(b);
        calendar.setDisable(b);
        show.setDisable(b);
    }

    /**
     * Fills the ListView with the available equipments based on the the parameters of equipmentTemp,
     * when "Show available options" is pressed.
     * Retrieves the available equipment by sending a GET-request to the remote server
     * Disables the datepicker and rental period buttons
     */
    @FXML
     public void initializeListView() {
        equipmentTemp.convertRentalPeriodToInt();

        try {
            List<Equipment> availableEquipmentList = dataAccess
                .getAllAvailableEquipment(equipmentTemp.getStartDate(), equipmentTemp.getRentalPeriod());
            for (Equipment equipment : availableEquipmentList) {
                availableEquipment.getItems().add(equipment.getType());
            }

            setDisable(true);

        } catch (Exception e) {
            System.err.println("Error when trying to fetch available equipment: " + e);
        }
    }

    /**
     * Displays the selected equipment from the ListView and information about the rental, when an equipment is
     * pressed in the Listview
     * Retrieves the equipment chosen from the remote server by sending a GET-request with the type.
     * Copies over the information about the rental currently on the equipmentTemp-object,
     * to the new equipment instance.
     */
    @FXML
    private void displayEquipment() {
        String selectedEquipmentType = availableEquipment.getSelectionModel().getSelectedItem();

        this.equipment = dataAccess.getSelectedEquipment(selectedEquipmentType);
        equipment.setRentalPeriod(equipmentTemp.getRentalPeriod());
        equipment.setRentalPeriodString(equipmentTemp.getRentalPeriodString());
        equipment.setStartDate(equipmentTemp.getStartDate());

        equipmentType.setText(selectedEquipmentType);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy", Locale.ENGLISH);
        startDate.setText(equipment.getStartDate().format(formatter));
        endDate.setText(equipment.getStartDate().plusDays(equipment.getRentalPeriod()).format(formatter));
        price.setText(String.valueOf(equipment.getPrice(equipment.getRentalPeriodString())));
    }

    /**
     * Clears all the information set and makes all fields interactable again, when the "start over"-button
     * is pressed.
     */
    @FXML
    private void startOver() {
        setDisable(false);
        availableEquipment.getItems().clear();
        calendar.setValue(null);
        equipmentType.setText(null);
        price.setText(null);
        startDate.setText(null);
        endDate.setText(null);
    }

    /**
     * Confirms a rental by sending a POST-request to the remote server with the information about the
     * selected equipment and the rental, when the "Confirm the rental" button is pressed.
     * Thereby making the chosen equipment unavailable in the given rental period when rerunning the application
     * @throws IOException If an I/O error occurs during the serialization to file
     * @throws IllegalStateException If there is an issue with the JSON file or its format
     */
    @FXML
    private void handleConfirmRental() throws IllegalStateException, IOException {
        dataAccess.postAvailability(equipment.getType(), equipment.getStartDate(), equipment.getRentalPeriod());
        Stage currentStage = (Stage) confirmRental.getScene().getWindow();
        currentStage.close();
    }
}
