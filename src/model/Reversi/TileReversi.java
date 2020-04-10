package model.Reversi;


import javafx.scene.layout.*;




public class TileReversi extends Pane {


    private int TileIndex;

    // if 1  is black  2 is white  0 is free
    private int colour;
    private String path;

    public TileReversi() {
        setPrefWidth(75);
        setPrefHeight(75);
        path = String.valueOf(getClass().getResource("/view/Stylesheets/reversieStyle.css").toExternalForm());
        getStylesheets().add(path);

        getStyleClass().add("background_border");



    }

    public void setIndex(int x) {
        this.TileIndex = x;

    }

    public int GetIndex() {
        return this.TileIndex;

    }

    public void flip() {
        if (colour == 1) {
            System.out.println(" set to white");
            setColourToWhite();

        } else if (colour == 2) {
            System.out.println(" set to black");
            setColourToBlack();

        }

    }

    public void setColourToBlack() {
        colour = 1;
        getStyleClass().clear();
        getStyleClass().addAll("background_image");

    }

    public void setColourToWhite() {
        colour = 2;
        getStyleClass().clear();
        getStyleClass().addAll("background_imagew");

    }


}
