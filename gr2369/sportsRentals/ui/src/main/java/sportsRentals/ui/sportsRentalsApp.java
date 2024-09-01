package sportsRentals.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * The sporstRentalsApp class launches the application, loads the user interface and sets up the primary stage.
 * Extends the JavaFX Application class
 */
public class sportsRentalsApp extends Application {

    /**
     * Loads and intitializes the application window defined in the sportsRentals.fxml file.
     * @param stage The primary stage of the application
     * @throws IOException If there are problems loading the FXML file
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("sportsRentals.fxml"));
        Parent parent = fxmlLoader.load();

        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * Launches the application.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
