package controller;

import ai.TicLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.tic.PlayerType;
import model.tic.Tile;

import java.awt.*;
import java.util.LinkedList;

public class TicController {
    private TicLogic ai;
    LinkedList<Tile> tiles;



    @FXML
    Pane pane;

    public void StartTic(ActionEvent actionEvent) {
        tiles= new LinkedList<>();
        ai = new TicLogic(PlayerType.X);
        drawTheBoard();

    }

    public void drawTheBoard(){
        int index=0;
        for(int i =0;i<3;i++){
            for(int j=0;j<3;j++){
                Tile tile= new Tile();
                tiles.add(tile);
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
                        //my turn


                        if(ai.isValidMove(tile.GetIndex())){
                            //my turn
                            ai.move(tile.GetIndex());
                            tile.DrawX();
                            // the ai turn
                          int x=  ai.GetNextMove();
                          tiles.get(x).DrawO();

                      }
                        else {
                            System.out.println("Not A valid move");
                        }
                    }
                });

            }
        }
    }
}
