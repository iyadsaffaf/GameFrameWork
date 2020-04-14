package controller;

import ai.TicLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.tic.PlayerType;
import model.tic.TicTacGame;
import model.tic.Tile;

import java.io.IOException;
import java.util.LinkedList;

public class TicController {
    private TicLogic ai;
    LinkedList<Tile> tiles;
    private TicTacGame state;
    private PlayerType playerType;
    @FXML
    private GridPane root;


    @FXML
    Pane pane;

    public void StartTic(ActionEvent actionEvent) {
        tiles= new LinkedList<Tile>();
        ai = new TicLogic(PlayerType.X);
        state = new TicTacGame();
        drawTheBoard();




    }


    public void drawTheBoard(){
        int index=0;
        for(int i =0;i<3;i++){
            for(int j=0;j<3;j++){
                Tile tile = new Tile();
                tiles.add(tile);
                tile.setIndex(index);

                index++;
                tile.setTranslateX(j*100);
                tile.setTranslateY(i*100);
                pane.getChildren().add(tile);
                tile. setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println(tile.GetIndex());
                        //my turn


                        if (ai.isValidMove(tile.GetIndex())) {
                            //my turn
                            ai.move(tile.GetIndex());
                            tile.DrawX();
                            // the ai turn
                            int x = ai.GetNextMove();
                            tiles.get(x).DrawO();
                            ai.state.getTheFirstFreeIndex2();

                            ai.CheckWin();

                        } else {
                            System.out.println("Not A valid move");
                        }

                    }

                });

            }
        }
    }


    public TicTacGame getState() {
        return state;
    }

    public void setState(TicTacGame state) {
        this.state = state;
    }
    public void gameOver(){
        state.win();
    }


    public void BackButton(ActionEvent actionEvent) {
        GridPane gridPane= null;
        try {
            gridPane = FXMLLoader.load(getClass().getResource("../view/gameLocal.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(gridPane);
    }

}



