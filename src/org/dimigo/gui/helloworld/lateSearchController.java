package org.dimigo.gui.helloworld;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class lateSearchController implements Initializable {

    @FXML
    private Button goBack;

    @FXML
    TableView<lateSearch> lateSearchTable;
    ArrayList<lateSearch> list = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<lateSearch> data = FXCollections.observableArrayList(list);
        lateSearchTable.setItems(data);
        TableColumn sickNm = lateSearchTable.getColumns().get(0);
        sickNm.setCellValueFactory(new PropertyValueFactory<sickCd, String>("sickNm"));
        TableColumn sickCd = lateSearchTable.getColumns().get(1);
        sickCd.setCellValueFactory(new PropertyValueFactory<sickCd, String>("sickCd"));


    }
    public void setListItems(ArrayList<lateSearch> list) {
        this.list = list;
        ObservableList<lateSearch> data = FXCollections.observableArrayList(list);
        lateSearchTable.setItems(data);
    }

    @FXML
    private void clickGoBack(ActionEvent event) throws IOException {

        Stage stage = (Stage) lateSearchTable.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
//        ((sickSearchController)loader.getController()).initialize();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }
}
