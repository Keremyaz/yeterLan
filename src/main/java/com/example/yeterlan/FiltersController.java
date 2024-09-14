package com.example.yeterlan;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FiltersController {

    @FXML
    private ComboBox<String> companyComboBox; // Şirketler sekmesi için ComboBox
    @FXML
    private ComboBox<String> fundComboBox; // Fonlar sekmesi için ComboBox
    @FXML
    private ComboBox<String> companyNotificationComboBox; // Şirket Bildirim Konuları için ComboBox
    @FXML
    private ComboBox<String> fundNotificationComboBox; // Fon Bildirim Konuları için ComboBox

    @FXML
    private ListView<String> companyListView; // Şirketler sekmesi için ListView
    @FXML
    private ListView<String> fundListView; // Fonlar sekmesi için ListView
    @FXML
    private ListView<String> companyNotificationListView; // Şirket Bildirim Konuları için ListView
    @FXML
    private ListView<String> fundNotificationListView; // Fon Bildirim Konuları için ListView

    @FXML
    public void initialize() {

        // Şirketler ComboBox'ına veriler ekleniyor
        companyComboBox.setItems(FXCollections.observableArrayList(
                "BIST Şirketleri",
                "Yatırım Kuruluşları",
                "Portföy Yönetim Şirketleri",
                "Bağımsız Denetim Kuruluşları",
                "Derecelendirme Şirketleri",
                "Değerleme Şirketleri",
                "Diğer KAP Üyeleri ve İşlem Görmeyen Şirketler",
                "Tümü"
        ));

        // Fonlar ComboBox'ına veriler ekleniyor
        fundComboBox.setItems(FXCollections.observableArrayList(
                "Yatırım Fonu",
                "Emeklilik Yatırım Fonu",
                "Yabancı Yatırım Fonu",
                "Borsa Yatırım Fonu",
                "Gayrimenkul Yatırım Fonları",
                "Girişim Sermayesi Yatırım Fonları"
                /*"Varlık Finansman Fonları",
                "Konut Finansman Fonları",
                "Proje Finansman Fonu",
                "OKS Emeklilik Fonu"*/
        ));

        // Şirket Bildirim Konuları ComboBox'ına veriler ekleniyor
        companyNotificationComboBox.setItems(FXCollections.observableArrayList(
                "Finansal Rapor",
                "Özel Durum Açıklaması",
                "Düzenleyici Kurum Bildirimleri",
                "Diğer",
                "Tümü",
                "Hak Kullanımları"
        ));

        // Fon Bildirim Konuları ComboBox'ına veriler ekleniyor
        fundNotificationComboBox.setItems(FXCollections.observableArrayList(
                "Finansal Rapor",
                "Özel Durum Açıklaması",
                "Düzenleyici Kurum Bildirimleri",
                "Diğer",
                "Tümü",
                "Hak Kullanımları"
        ));

        // Şirketler ComboBox'unda seçim yapıldığında
        companyComboBox.setOnAction(event -> {
            String selectedValue = companyComboBox.getValue();
            if (selectedValue != null) {
                switch (selectedValue) {
                    case "BIST Şirketleri":
                        loadDataFromApi("https://www.kap.org.tr/tr/api/kapmembers/IGS/A", companyListView);
                        break;
                    case "Yatırım Kuruluşları":
                        loadDataFromApi("https://www.kap.org.tr/tr/api/kapmembers/YK/A", companyListView);
                        break;
                    case "Portföy Yönetim Şirketleri":
                        loadDataFromApi("https://www.kap.org.tr/tr/api/kapmembers/PYS/A", companyListView);
                        break;
                    case "Bağımsız Denetim Kuruluşları":
                        loadDataFromApi("https://www.kap.org.tr/tr/api/kapmembers/BDK/A", companyListView);
                        break;
                    case "Derecelendirme Şirketleri":
                        loadDataFromApi("https://www.kap.org.tr/tr/api/kapmembers/DCS/A", companyListView);
                        break;
                    case "Değerleme Şirketleri":
                        loadDataFromApi("https://www.kap.org.tr/tr/api/kapmembers/DS/A", companyListView);
                        break;
                    case "Diğer KAP Üyeleri ve İşlem Görmeyen Şirketler":
                        loadDataFromApi("https://www.kap.org.tr/tr/api/kapmembers/DK/A", companyListView);
                        break;
                }
            }
        });

        // Fonlar ComboBox'unda seçim yapıldığında
        fundComboBox.setOnAction(event -> {
            String selectedValue = fundComboBox.getValue();
            if (selectedValue != null) {
                switch (selectedValue) {
                    case "Yatırım Fonu":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/4028328c55064295015506dc365b4c2c", fundListView);
                        /*loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/4028328d71bc3c65017237144e7778a3", fundListView);*/
                        break;
                    case "Emeklilik Yatırım Fonu":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/4028328c55064295015506de3edf4f98", fundListView);
                        break;
                    case "Yabancı Yatırım Fonu":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/4028328c55064295015506decc1b50d1", fundListView);
                        break;
                    case "Borsa Yatırım Fonu":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/4028328c55064295015506db980a4bec", fundListView);
                        break;
                    case "Gayrimenkul Yatırım Fonları":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/4028328c5fc60da7015fe285cb2c63a6", fundListView);
                        break;
                    case "Girişim Sermayesi Yatırım Fonları":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/4028328c7436d8ce01744eef65f14601", fundListView);
                        break;
                    /*case "Varlık Finansman Fonları":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/", fundListView);
                        break;
                    case "Konut Finansman Fonları":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/", fundListView);
                        break;*/

                    /*case "Proje Finansman Fonu":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/", fundListView);
                        break;
                    case "OKS Emeklilik Fonu":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/", fundListView);
                        break;
                    case "Tümü":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fund/filterByGroupOid/", fundListView);
                        break;*/
                }
            }
        });

        // Şirket Bildirim Konuları ComboBox'unda seçim yapıldığında
        companyNotificationComboBox.setOnAction(event -> {
            String selectedValue = companyNotificationComboBox.getValue();
            if (selectedValue != null) {
                switch (selectedValue) {
                    case "Finansal Rapor":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/disclosureTopic/filterByClassType/FR", companyNotificationListView);
                        break;
                    case "Özel Durum Açıklaması":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/disclosureTopic/filterByClassType/ODA", companyNotificationListView);
                        break;
                    case "Düzenleyici Kurum Bildirimleri":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/disclosureTopic/filterByClassType/DUY", companyNotificationListView);
                        break;
                    case "Diğer":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/disclosureTopic/filterByClassType/DG", companyNotificationListView);
                        break;
                    case "Tümü":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/disclosureTopic/filterByClassType/ALL", companyNotificationListView);
                        break;
                    case "Hak Kullanımları":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/disclosureTopic/filterByClassType/HK", companyNotificationListView);
                        break;
                }
            }
        });


        // Fon Bildirim Konuları ComboBox'unda seçim yapıldığında
        fundNotificationComboBox.setOnAction(event -> {
            String selectedValue = fundNotificationComboBox.getValue();
            if (selectedValue != null) {
                switch (selectedValue) {
                    case "Finansal Rapor":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fundDisclosureTopic/filterByClassType/FR", fundNotificationListView);
                        break;
                    case "Özel Durum Açıklaması":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fundDisclosureTopic/filterByClassType/ODA", fundNotificationListView);
                        break;
                    case "Düzenleyici Kurum Bildirimleri":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fundDisclosureTopic/filterByClassType/DUY", fundNotificationListView);
                        break;
                    case "Diğer":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fundDisclosureTopic/filterByClassType/DG", fundNotificationListView);
                        break;
                    case "Tümü":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fundDisclosureTopic/filterByClassType/ALL", fundNotificationListView);
                        break;
                    case "Hak Kullanımları":
                        loadDataFromApiForTitle("https://www.kap.org.tr/tr/api/kapi/him/fundDisclosureTopic/filterByClassType/HK", fundNotificationListView);
                        break;
                }
            }

        });
    }

    // Genel API veri yükleme fonksiyonu
    private void loadDataFromApi(String apiUrl, ListView<String> targetListView) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            // JSON verisini parse et
            Gson gson = new Gson();
            Type listType = new TypeToken<List<KapMember>>() {
            }.getType();
            List<KapMember> kapMembers = gson.fromJson(content.toString(), listType);

            // kapMemberTitle değerlerini benzersiz ve alfabetik hale getir
            Set<String> uniqueTitles = new HashSet<>();
            for (KapMember member : kapMembers) {
                uniqueTitles.add(member.getKapMemberTitle());
            }

            // Benzersiz ve sıralanmış title değerlerini ListView'e ekle
            ObservableList<String> items = FXCollections.observableArrayList(uniqueTitles);
            FXCollections.sort(items);
            targetListView.setItems(items);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadDataFromApiForTitle(String apiUrl, ListView<String> targetListView) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            // JSON verisini parse et
            Gson gson = new Gson();
            Type listType = new TypeToken<List<KapMember>>() {
            }.getType();
            List<KapMember> kapMembers = gson.fromJson(content.toString(), listType);

            // title değerlerini benzersiz ve alfabetik hale getir
            Set<String> uniqueTitles = new HashSet<>();
            for (KapMember member : kapMembers) {
                uniqueTitles.add(member.getTitle());  // Aynı title değerleri Set'e eklenirken otomatik olarak elenir
            }

            // Benzersiz ve sıralanmış title değerlerini ListView'e ekle
            ObservableList<String> items = FXCollections.observableArrayList(uniqueTitles);
            FXCollections.sort(items);
            targetListView.setItems(items);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}