package controller;

import ai.ReversiLogic;
import javafx.application.Platform;
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
import observer.Observer;
import observer.ObserverSubject;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

import static java.lang.Thread.*;

public class ReversieController  {
    private ObserverSubject subject;

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

    public ReversieController() {

    }


    public void StartReversie(ActionEvent actionEvent) {
        subject = new ObserverSubject();


        //
        difficulty = choiceDifficulty.getSelectionModel().getSelectedItem().toString();
        System.out.println(difficulty);
        pane.setVisible(true);
        choiceDifficulty.setVisible(false);
        difficultyLevel.setVisible(false);

        tiles = new LinkedList<>();
        playerType = 'B';
        drawTheBoard();
        ai = new ReversiLogic(playerType, difficulty);
        playerTypeText.setText(getTextForPlayerType(playerType));


        updateBoard();

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
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //   tile.setColourToWhite();
                                System.out.println(tile.GetIndex());
                                Move move = new Move(tile.GetIndex());
                                System.out.println("the x = " + move.x + "  the y = " + move.y);
                                if (ai.move(tile.GetIndex())) {
                                    System.out.println("gdfsssssssssss");


                                    updateBoard();

                                    Thread t = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            ai.moveAI();
                                            updateBoard();

                                        }
                                    });

                                    t.start();
                                }
                            }
                        });



                    }
                });

            }
        }
    }


    public String getTextForPlayerType(char player) {
        String s = "";
        if (player == 'B') {
            s = " You are BLack";
        } else if (player == 'W') {
            s = "You are White";

        }


        return s;
    }

    public void clickck(KeyEvent keyEvent) {
        startButton.setVisible(true);
    }

    public void updateBoard() {
        int index = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (ai.getBoard()[x][y] == 'B') {

                    tiles.get(index).setColourToBlack();
                } else if (ai.getBoard()[x][y] == 'W') {
                    tiles.get(index).setColourToWhite();
                }


                index++;

            }
        }
    }



    public void update() {

        System.out.println("Hex String: ");
        try {
            System.out.println("StartSleeping");
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("EndSleeping");

        ai.moveAI();
        updateBoard();


    }


}
