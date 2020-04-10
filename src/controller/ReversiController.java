package controller;

import ai.ReversiLogic;
import ai.TicLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.reversi.PlayerColour;
import model.tic.PlayerType;
import model.reversi.ReversiTile;

import java.awt.*;
import java.util.LinkedList;

public class ReversiController {
    private ReversiLogic ai;
    LinkedList<ReversiTile> tiles;



    @FXML
    Pane pane;

    public void StartReversi(ActionEvent actionEvent) {
        tiles= new LinkedList<>();
        ai = new ReversiLogic(PlayerColour.White);
        drawTheBoard();

    }

    public void drawTheBoard(){
        int index=0;
        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
                ReversiTile tile= new ReversiTile();
                tiles.add(tile);
                tile.setIndex(index);
                //tile.DrawIndex(index);

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
                            System.out.println("test");
                            ai.move(tile.GetIndex());
                            tile.DrawX();

                            // the ai turn
                            int x=  ai.GetNextMove();
                            tiles.get(x).DrawO();
                        }

                    }
                });

            }
        }
    }
}
