module sportsRentals.ui {
    requires sportsRentals.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    opens sportsRentals.ui to javafx.graphics, javafx.fxml;
}
