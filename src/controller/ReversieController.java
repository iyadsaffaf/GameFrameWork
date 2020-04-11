package controller;

import ai.ReversiLogic;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import model.Move;
import model.Reversi.TileReversi;

import java.util.LinkedList;

public class ReversieController {

    private ReversiLogic ai;
    private LinkedList<TileReversi> tiles;
    private char playerType;
    private String difficulty;
    @FXML
    Pane pane;
    @FXML
    Label playerTypeText;

    @FXML
    ChoiceBox choiceDifficulty;
    @FXML
    Label difficultyLevel;
    @FXML
    Button startButton;

    public ReversieController(){

    }



    public void StartReversie(ActionEvent actionEvent) {
        difficulty = choiceDifficulty.getSelectionModel().getSelectedItem().toString();
        System.out.println(difficulty);
        pane.setVisible(true);
        choiceDifficulty.setVisible(false);
        difficultyLevel.setVisible(false);

        tiles = new LinkedList<>();
        playerType='B';
        drawTheBoard();
        ai = new ReversiLogic(tiles,playerType,difficulty);
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

    public void clickck(KeyEvent keyEvent) {
        startButton.setVisible(true);
    }
}
