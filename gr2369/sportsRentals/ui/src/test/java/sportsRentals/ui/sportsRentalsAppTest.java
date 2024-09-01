package sportsRentals.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;


public class sportsRentalsAppTest extends ApplicationTest {

    private sportsRentalsController controller;
    private Parent root;
    private Stage primaryStage;

    /**
     * Launches the application and sets up the primary stage.
     * @param stage The primary stage
     */
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sportsRentals.fxml"));
        root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.initializeListView();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * @return The root node of the scene
     */
    public Parent getRootNode() {
        return root;
    }

    /**
     * @return The text of the equipmentType label
     */
    private String getEquipmentTypeLabel() {
        return ((Label) getRootNode().lookup("#equipmentType")).getText();
    }

    /**
     * @return The text of the price label
     */
    private String getPriceLabel() {
        return ((Label) getRootNode().lookup("#price")).getText();
    }

    /**
     * @return The text of the startDate label
     */
    private String getStartDate() {
        return ((Label) getRootNode().lookup("#startDate")).getText();
    }

    /**
     * @return The text of the endDate label
     */
    private String getEndDate() {
        return ((Label) getRootNode().lookup("#endDate")).getText();
    }

    @Test
    @DisplayName("Test the start method")
    public void testStartMethod() throws Exception {
        sportsRentalsApp app = new sportsRentalsApp();
        ApplicationTest.launch(app.getClass());
        assertNotNull(app);
    }

    @Test
    @DisplayName ("Tests that the correct equipment, rental period and price is displayed")
    public void testDisplayEquipment() throws InterruptedException {
        DatePicker date = (DatePicker) lookup("#calendar").query();
        date.setValue(LocalDate.of(2024, 01, 10));

        clickOn("#oneWeek");

        clickOn("#show");

        clickOn("Badminton Racket");

        clickOn("#confirmRental");

        assertEquals("Badminton Racket", getEquipmentTypeLabel());
        assertEquals("10. January 2024", getStartDate());
        assertEquals("17. January 2024", getEndDate());
        assertEquals("79.0", getPriceLabel());
        assertFalse(primaryStage.isShowing());
    }

    @Test
    @DisplayName("Test the startOver button")
    public void testStartOver() {
        DatePicker date = (DatePicker) lookup("#calendar").query();
        date.setValue(LocalDate.of(2024, 04, 01));

        clickOn("#twoWeeks");

        clickOn("#show");

        clickOn("Surfboard");

        clickOn("#startOver");

        assertTrue(primaryStage.isShowing());
    }
}
