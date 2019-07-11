package org.dimigo.gui.helloworld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class sickSearchMain extends Application {

    @Override
    public void start(Stage Stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage.setTitle("질병코드 / 질병명");
        Stage.setScene(new Scene(root));

        Stage.centerOnScreen();
        Stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
