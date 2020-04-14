package controller;

import ai.ReversiLogic;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.scene.text.Text;
import model.Move;
import model.Reversi.TileReversi;

import java.io.IOException;
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
    private Pane frame;
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
    private Text whiteCount;
    @FXML
    private Label blackCount;
    @FXML
    private Label chooseYourColourLabel;
    @FXML
    private ChoiceBox choiceBoxCoulour;
    @FXML
    private GridPane root;

    private boolean startFase = true;


    public ReversieController() {

    }


    public void StartReversie(ActionEvent actionEvent) {
        //
        if (startFase) {
            startButton.setText("Play again");
            startFase = false;
            difficulty = choiceDifficulty.getSelectionModel().getSelectedItem().toString();
            playerColour = choiceBoxCoulour.getSelectionModel().getSelectedItem().toString();
            pane.setVisible(true);
            frame.setVisible(true);
            checkBoxHint.setVisible(true);
            blackCount.setVisible(true);
            whiteCount.setVisible(true);
            playerTypeText.setVisible(true);
            //
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
        } else {
            playAgain();
        }

    }

    public void playAgain() {
        int xx = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tiles.get(xx).setColourToGreen();
                xx++;

            }
        }
        ai = new ReversiLogic(playerType, difficulty);
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
                tile.setTranslateX(j * 60);
                tile.setTranslateY(i * 60);
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
                                    pane.setDisable(true);

                                    Thread t = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            ai.moveAI();
                                            pane.setDisable(false);

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

    private int whiteCounter = 0;

    public void updateBoard() {
        int index = 0;
        int whiteCounter = 0;
        int blackCounter = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (ai.getBoard()[x][y] == 'B') {

                    tiles.get(index).setColourToBlack();
                    blackCounter++;
                } else if (ai.getBoard()[x][y] == 'W') {
                    whiteCounter++;
                    tiles.get(index).setColourToWhite();
                } else if (ai.getBoard()[x][y] == 'F') {
                    tiles.get(index).setColourToGreen();


                }


                index++;

            }
        }


        //Counter
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                int whiteCounter = 0;
                int blackCounter = 0;
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        if (ai.getBoard()[x][y] == 'B') {

                            // tiles.get(index).setColourToBlack();
                            blackCounter++;
                        } else if (ai.getBoard()[x][y] == 'W') {
                            whiteCounter++;
                            //tiles.get(index).setColourToWhite();
                        }


                        index++;

                    }
                }
                blackCount.setText(blackCounter + "");
                whiteCount.setText(whiteCounter + "");

            }
        });

    }




    public void goBacktoGamelist(ActionEvent actionEvent) {
        GridPane gridPane = null;
        try {
            gridPane = FXMLLoader.load(getClass().getResource("../view/gameLocal.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(gridPane);
    }

    public void checkBoxClicked(ActionEvent actionEvent) {
        if (!hint) {
            hint = true;
            highLight(playerType);
        } else {
            hint = false;
            updateBoard();
        }
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
