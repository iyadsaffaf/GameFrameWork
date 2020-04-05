package controller;

import ai.TicLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.tic.Tile;

import java.awt.*;

public class TicController {
    private TicLogic ai;


    @FXML
    Pane pane;

    public void StartTic(ActionEvent actionEvent) {
        ai = new TicLogic();
        drawTheBoard();

    }

    public void drawTheBoard(){
        int index=0;
        for(int i =0;i<3;i++){
            for(int j=0;j<3;j++){
                Tile tile= new Tile();
                tile.setIndex(index);
                tile.DrawIndex(index);

                index++;
                tile.setTranslateX(j*100);
                tile.setTranslateY(i*100);
                pane.getChildren().add(tile);
                tile. setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println(tile.GetIndex());

                        if(ai.isVailedMove(tile.GetIndex())){
                            //my turn
                            ai.move(tile.GetIndex());

                            // the ai turn
                            ai.GetNextMove();
                        tile.DrawX();}
                        else {
                            System.out.println("Not A valid move");
                        }
                    }
                });

            }
        }
    }
}
