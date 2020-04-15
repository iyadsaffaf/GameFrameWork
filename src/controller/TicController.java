package controller;

import ai.TicLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Move;
import model.tic.TicTacGame;
import model.tic.Tile;
import model.tic.WinMove;

import java.io.IOException;
import java.util.LinkedList;

public class TicController {
    private TicLogic ai;
    private LinkedList<Tile> tiles;
    private TicTacGame state;
    private char playerType;
    @FXML
    private GridPane root;


    @FXML
    Pane pane;

    public void StartTic(ActionEvent actionEvent) {
        pane.setDisable(false);

        playerType = 'X';
        tiles = new LinkedList<Tile>();
        ai = new TicLogic(playerType);
        state = new TicTacGame();
        drawTheBoard();


    }


    public void drawTheBoard() {
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tiles.add(tile);
                tile.setIndex(index);

                index++;
                tile.setTranslateX(j * 100);
                tile.setTranslateY(i * 100);
                pane.getChildren().add(tile);
                tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println(tile.GetIndex());
//MY turn
                        if (ai.move(tile.GetIndex())) {
                            updateBoard();
                            WinMove winMove = ai.checkWin(playerType);
                            if (winMove.isWin()) {
                                gameEnd(winMove);

                            } else {

                                // the ai turn
                                ai.moveAI();
                                updateBoard();
                                winMove = ai.checkWin(getAiType(playerType));
                                if (winMove.isWin())
                                    gameEnd(winMove);

//                            ai.state.getTheFirstFreeIndex2();

                            }
                        }

                    }

                });

            }
        }
    }


//    public TicTacGame getState() {
//        return state;
//    }
//
//    public void setState(TicTacGame state) {
//        this.state = state;
//    }
//
//    public void gameOver() {
//        state.win();
//    }


    public void BackButton(ActionEvent actionEvent) {
        GridPane gridPane = null;
        try {
            gridPane = FXMLLoader.load(getClass().getResource("../view/gameLocal.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(gridPane);
    }


    public void updateBoard() {
        int index = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (ai.getBoard()[x][y] == 'X') {
                    tiles.get(index).DrawX();
                } else if (ai.getBoard()[x][y] == 'O') {
                    tiles.get(index).DrawO();
                }

                index++;

            }
        }


    }
    public void gameEnd(WinMove winMove) {
        pane.setDisable(true);
        System.out.println("end dddddddddddddddddddddddddddddddddd dddddddddddddddd");
     for( int i=0 ;i<winMove.getWinMoves().size();i++){
         Move move = winMove.getWinMoves().get(i);
         System.out.println("end dddddddddddddddddddddddddddddddddd dddddddddddddddd "+move.toString());

         tiles.get(move.findTheIndex(move.x,move.y)).DrawIndex(44);
     }

    }

    public char getAiType(char playerType) {
        char aiType;
        if (playerType == 'X') {
            aiType = 'O';
        } else {
            aiType = 'X';


        }
        return aiType;
    }

}



