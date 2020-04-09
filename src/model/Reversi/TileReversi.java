package model.Reversi;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class TileReversi extends Pane {


    private int TileIndex;

    public TileReversi() {
        setPrefWidth(75);
        setPrefHeight(75);
        String xx = String.valueOf(getClass().getResource("/view/Stylesheets/reversieStyle.css").toExternalForm());
        getStylesheets().add(xx);
        getStyleClass().add("background_image");

    }

    public void setIndex(int x) {
        this.TileIndex = x;

    }

    public int GetIndex() {
        return this.TileIndex;

    }


}
