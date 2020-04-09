package controller;

import ai.AiLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.rev.RevTile;


public class ReversieController {
    private AiLogic ai;

    @FXML
    Pane paneR;

    public void StartDraw(ActionEvent actionEvent) {
        ai = new AiLogic();
        drawTheBoard();

    }

    public void drawTheBoard(){
        int index=0;
        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
                RevTile revTile = new RevTile();
               // tiles.add(tile);

                revTile.setIndex(index);
                revTile.DrawIndex(index);

                index++;
                revTile.setTranslateX(j*60);
                revTile.setTranslateY(i*60);
                paneR.getChildren().add(revTile);
                revTile. setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println(revTile.GetIndex());
                        //my turn


                        if(ai.isValidMove(revTile.GetIndex())){
                            //my turn
                            ai.move(revTile.GetIndex());
                            revTile.DrawC();
                            // the ai turn
                            int x=  ai.GetNextMove();
                            //tiles.get(x).DrawCW();

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

