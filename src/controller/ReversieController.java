package controller;

import ai.ReversiLogic;
import ai.TicLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.tic.PlayerType;
import model.Reversi.TileReversi;

import java.util.LinkedList;

public class ReversieController {

    private ReversiLogic ai;
    LinkedList<TileReversi> tiles;



    @FXML
    Pane pane;


    public void StartReversie(ActionEvent actionEvent) {
            tiles= new LinkedList<>();
            ai = new ReversiLogic();
            drawTheBoard();


    }

    public void drawTheBoard(){
        int index=0;
        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
                TileReversi tile= new TileReversi();
                tiles.add(tile);
                tile.setIndex(index);
             //   tile.DrawIndex(index);

                index++;
//                tile.setIndex(j*75);
//                tile.setIndex(i*75);
                tile.setTranslateX(j*75);
               tile.setTranslateY(i*75);
                pane.getChildren().add(tile);
                tile. setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                      System.out.println(tile.GetIndex());
                    }
                });

            }
        }
    }
}
