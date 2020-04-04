package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.tic.Tile;

import java.awt.*;

public class TicController {

    @FXML
    Pane pane;

    public void StartTic(ActionEvent actionEvent) {

    for(int i =0;i<3;i++){
        for(int j=0;j<3;j++){
            Tile tile= new Tile();
            tile.setTranslateX(j*100);
            tile.setTranslateY(i*100);
            pane.getChildren().add(tile);

        }
    }

    }
}
