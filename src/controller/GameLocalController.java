package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class GameLocalController {
    @FXML
    private GridPane root;
    public void openTicWindow(ActionEvent actionEvent) {
        GridPane gridPane= null;
        try {
            gridPane = FXMLLoader.load(getClass().getResource("../view/tic.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(gridPane);
    }
}
