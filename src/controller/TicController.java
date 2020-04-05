package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.tic.Tile;

import java.awt.*;

public class TicController {

    @FXML
    Pane pane;

    public void StartTic(ActionEvent actionEvent) {
    int index=0;
    for(int i =0;i<3;i++){
        for(int j=0;j<3;j++){
            Tile tile= new Tile();
            tile.setIndex(index);
            index++;
            tile.setTranslateX(j*100);
            tile.setTranslateY(i*100);
            pane.getChildren().add(tile);
            tile. setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                   tile.DrawX();
                   System.out.println(tile.GetIndex());
                }
            });

        }
    }

    }
}
