module com.example.yeterlan {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson; // Gson kütüphanesini dahil ediyoruz
    requires java.desktop;

    opens com.example.yeterlan to javafx.fxml, com.google.gson;  // Disclosure sınıfını hem Gson'a hem de FXML'e açıyoruz
    exports com.example.yeterlan;
}
