package controller;

import ai.ReversiLogic;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.scene.text.Text;
import model.Move;
import model.Reversi.TileReversi;

import java.util.LinkedList;

import static java.lang.Thread.*;

public class ReversieController {


    private ReversiLogic ai;
    private LinkedList<TileReversi> tiles;
    private char playerType;
    private String difficulty;
    private String playerColour;
    private boolean hint = false;
    @FXML
    private Pane pane;
    @FXML
    private Label playerTypeText;

    @FXML
    private ChoiceBox choiceDifficulty;
    @FXML
    private Label difficultyLevel;
    @FXML
    private Button startButton;
    @FXML
    private CheckBox checkBoxHint;
    @FXML
    private Text yourScoreText;
    @FXML
    private Label OpponentScoreLabel;
    @FXML
    private Label chooseYourColourLabel;

    @FXML
    private ChoiceBox choiceBoxCoulour;


    public ReversieController() {

    }


    public void StartReversie(ActionEvent actionEvent) {
        //
        difficulty = choiceDifficulty.getSelectionModel().getSelectedItem().toString();
        playerColour = choiceBoxCoulour.getSelectionModel().getSelectedItem().toString();
        pane.setVisible(true);
        choiceDifficulty.setVisible(false);
        choiceBoxCoulour.setVisible(false);
        difficultyLevel.setVisible(false);
        chooseYourColourLabel.setVisible(false);


        tiles = new LinkedList<>();

        drawTheBoard();
        if (playerColour.equals("Black")) {
            playerType = 'B';
        } else {
            playerType = 'W';
        }
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


                                    updateBoard();

                                    Thread t = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(2500);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            ai.moveAI();
                                            updateBoard();
                                            if (hint)
                                                highLight(playerType);

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
                }else if(ai.getBoard()[x][y] == 'F'){
                    tiles.get(index).setColourToGreen();


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


    public void goBacktoGamelist(ActionEvent actionEvent) {

    }

    public void checkBoxClicked(ActionEvent actionEvent) {
        if (!hint){
            hint = true;
        highLight(playerType);}
        else{
            hint=false;
            updateBoard();}
    }

    public void highLight(char speler) {
        int xx = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (ai.higlight(xx, speler)) {
                    tiles.get(xx).setColourToHighLight();
                }


                xx++;

            }
        }

    }
}
