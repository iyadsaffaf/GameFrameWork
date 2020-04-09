package model.rev;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RevTile extends StackPane {


        private Circle circle;
        private  int  TileIndex;

        public RevTile(){
            circle = new Circle(20);
            circle.setFill(Color.GREEN);
            Rectangle border =new Rectangle(80,70,80,70);
            border.setFill(Color.GREEN);
            border.setStroke(Color.BLACK);

            setAlignment(Pos.CENTER);
            getChildren().addAll(border,circle);


        }

    public void DrawIndex(int index) {
        this.TileIndex= index;
    }

    public void DrawC(){
            circle.setFill(Color.BLACK);

        }
        public void DrawCW(){
            circle.setFill(Color.WHITE);


        }

        public void setIndex(int x){
            this.TileIndex=x;

        }
        public int GetIndex(){
            return this.TileIndex;

        }


    }

