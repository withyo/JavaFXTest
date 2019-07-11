package org.dimigo.gui.helloworld;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.Desktop;

public class sickSearchController implements Initializable {

    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<SearchType> cbSearch;
    @FXML
    private Button btnSearch;

    @FXML
    private Button lateSearchList;

    @FXML
    TableView<sickCd> CdTableView;
    @FXML
    TableView<sickNm> NmTableView;

    ArrayList<lateSearch> lateList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // ComboBox 항목 추가
        ObservableList<SearchType> comboItems = FXCollections.observableArrayList();
        comboItems.add(new SearchType("질병코드", "sickCd"));
        comboItems.add(new SearchType("질병명", "sickNm"));
        cbSearch.setItems(comboItems);

        lateList = new ArrayList();

        CdTableView.setRowFactory(l -> {
            TableRow<sickCd> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && !row.isEmpty()) {
                    sickCd rowData = row.getItem();
                    lateList.add(new lateSearch(rowData.getSickNm(),rowData.getSickCd()));

                    try {
                        Desktop.getDesktop().browse(new URI("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=" + URLEncoder.encode(rowData.getSickNm(),"utf-8")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });


        NmTableView.setRowFactory(l -> {
            TableRow<sickNm> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && !row.isEmpty()) {

                    sickNm rowData = row.getItem();
                    lateList.add(new lateSearch(rowData.getSickNm(),rowData.getSickCd()));

                    try {
                        Desktop.getDesktop().browse(new URI("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=" + URLEncoder.encode(rowData.getSickNm(),"utf-8")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    @FXML
    public void handleSearchAction(ActionEvent event) {
        SearchType item = cbSearch.getSelectionModel().getSelectedItem();
        String type = item.getValue();
        String text = txtSearch.getText();
        System.out.printf("type: %s, text: %s\n", type, text);

        try {
            if ("sickCd".equals(type)) {
                CdTableView.setVisible(true);
                NmTableView.setVisible(false);

                List<sickCd> cdList= sickSearch.getSickCdList(text);
                System.out.println(cdList);

                ObservableList<sickCd> data = FXCollections.observableArrayList(cdList);
                CdTableView.setItems(data);
                bindCdTableColumn();

            } else if ("sickNm".equals(type)) {
                CdTableView.setVisible(false);
                NmTableView.setVisible(true);

                List<sickNm> NmList= sickSearch.getSickNmList(text);
                System.out.println(NmList);

                ObservableList<sickNm> data = FXCollections.observableArrayList(NmList);
                NmTableView.setItems(data);
                bindNmTableColumn();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void clickLate(ActionEvent event) throws IOException{

                // lateSearch 화면 띄우기
                Stage stage = (Stage) lateSearchList.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("lateSearchTable.fxml"));
                Parent root = loader.load();
                ((lateSearchController)loader.getController()).setListItems(lateList);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
    }





    private void bindCdTableColumn(){
        TableColumn sickCd = CdTableView.getColumns().get(0);
        sickCd.setCellValueFactory(new PropertyValueFactory<sickCd, String>("sickCd"));

        TableColumn sickNm = CdTableView.getColumns().get(1);
        sickNm.setCellValueFactory(new PropertyValueFactory<sickCd, String>("sickNm"));

    }


    private void bindNmTableColumn() {
            TableColumn sickNm = NmTableView.getColumns().get(0);
            sickNm.setCellValueFactory(new PropertyValueFactory<sickNm, String>("sickNm"));

            TableColumn sickCd = NmTableView.getColumns().get(1);
            sickCd.setCellValueFactory(new PropertyValueFactory<sickNm, String>("sickCd"));


    }

}