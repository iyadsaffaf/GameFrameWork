package controller;

import ai.ReversiLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import model.Move;
import model.Reversi.TileReversi;

import java.util.LinkedList;

public class ReversieController {

    private ReversiLogic ai;
    private LinkedList<TileReversi> tiles;
    private char playerType;


    @FXML
    Pane pane;
    @FXML
    Label playerTypeText;


    public void StartReversie(ActionEvent actionEvent) {
        tiles = new LinkedList<>();
        playerType='B';
        drawTheBoard();
        ai = new ReversiLogic(tiles,playerType);
        playerTypeText.setText(getTextForPlayerType(playerType));


    }

    public void drawTheBoard() {
        int index = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                TileReversi tile = new TileReversi();
                tiles.add(tile);
                tile.setIndex(index);
                index++;
                tile.setTranslateX(j * 75);
                tile.setTranslateY(i * 75);
                pane.getChildren().add(tile);

                tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        System.out.println(tile.GetIndex());
                        Move move = new Move(tile.GetIndex());
                        System.out.println("the x = " + move.x + "  the y = " + move.y);

                        ai.move(tile.GetIndex());
                    //    ai.moveAI();


                       // tile.flip();
                        // ai.test();
                    }
                });

            }
        }
    }


    public String getTextForPlayerType(char player){
        String s ="";
        if(player=='B'){
            s=" You are BLack";
        }else if(player=='W'){
            s="You are White";

        }



        return s;}
}
