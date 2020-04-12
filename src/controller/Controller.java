package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Controller {
    @FXML
    private GridPane root;


    public void openRemoteWindow(ActionEvent actionEvent) throws IOException {
        GridPane gridPane= FXMLLoader.load(getClass().getResource("../view/gameRemote.fxml"));
        root.getChildren().setAll(gridPane);
    }

    public void openLocakWindow(ActionEvent actionEvent) throws IOException {
        GridPane gridPane= FXMLLoader.load(getClass().getResource("../view/gameLocal.fxml"));
        root.getChildren().setAll(gridPane);

    }

}
