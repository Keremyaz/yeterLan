package com.example.yeterlan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;

public class MainController {

    @FXML
    private TableView<Disclosure> tableView;

    @FXML
    private TableColumn<Disclosure, String> disclosureIndexColumn;

    @FXML
    private TableColumn<Disclosure, String> publishDateColumn;

    @FXML
    private TableColumn<Disclosure, String> titleColumn;

    @FXML
    private TableColumn<Disclosure, String> companyTitleColumn;

    @FXML
    private TableColumn<Disclosure, String> disclosureType;

    @FXML
    private TableColumn<Disclosure, String> attachmentCount;

    @FXML
    private TableColumn<Disclosure,String> relatedStocks;

    @FXML
    private MenuItem filtrelerMenuItem; // "Filtreler" menü öğesi

    // FXML dosyası yüklendiğinde çalışır
    @FXML
    public void initialize() throws Exception {
        // Sütunların veriye nasıl bağlanacağını tanımla
        disclosureIndexColumn.setCellValueFactory(new PropertyValueFactory<>("disclosureIndex"));
        publishDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        companyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("companyTitle"));
        disclosureType.setCellValueFactory(new PropertyValueFactory<>("disclosureType"));
        attachmentCount.setCellValueFactory(new PropertyValueFactory<>("attachmentCount"));
        relatedStocks.setCellValueFactory(new PropertyValueFactory<>("relatedStocks"));

        // Veri kaynağı
        DataFetcher fetcher = new DataFetcher();
        String json = fetcher.getJsonFromUrl("https://kap.org.tr/tr/api/kapi/him/disclosure/list?fromDate=29.08.2024");
        List<Disclosure> disclosureList = fetcher.parseJsonToList(json);

        // TableView'e verileri ekle
        ObservableList<Disclosure> observableList = FXCollections.observableArrayList(disclosureList);
        tableView.setItems(observableList);

        // Satıra tıklama olayını işleme
        tableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                Disclosure selectedDisclosure = tableView.getSelectionModel().getSelectedItem();
                if (selectedDisclosure != null) {
                    // disclosureIndex'e göre dinamik URL oluştur
                    String url = "https://www.kap.org.tr/tr/Bildirim/" + selectedDisclosure.getDisclosureIndex();
                    openWebpage(url);
                }
            }
        });

        // "Filtreler" menü öğesine tıklandığında popup pencereyi aç
        filtrelerMenuItem.setOnAction(event -> openFiltersPopup());
    }

    // URL'yi tarayıcıda aç
    private void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // filters.fxml dosyasını popup olarak açan metod
    private void openFiltersPopup() {
        try {
            // FXML dosyasını yükle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("filters.fxml"));
            Parent root = fxmlLoader.load();

            // Yeni bir sahne ve stage oluştur
            Stage stage = new Stage();
            stage.setTitle("Filtreler");
            stage.setScene(new Scene(root));

            // Sahneyi modlu hale getir (arka plandaki pencere kapatılamaz)
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait(); // Pop-up olarak aç (pencere kapanana kadar bekletir)

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
