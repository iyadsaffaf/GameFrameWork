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
    public void openRevWindow(ActionEvent actionEvent) {
        GridPane gridPane= null;
        try {
            gridPane = FXMLLoader.load(getClass().getResource("../view/reversie.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(gridPane);
    }

    public void backButton(ActionEvent actionEvent) {
        GridPane gridPane= null;
        try {
            gridPane = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(gridPane);
    }
}
