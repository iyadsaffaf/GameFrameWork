package model.tic;


import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tile extends Pane {


    private Text text;
    private  int  TileIndex;
    public Tile(){
        setPrefWidth(100);
        setPrefHeight(100);
        String path = String.valueOf(getClass().getResource("/view/Stylesheets/ticStyle.css").toExternalForm());
        getStylesheets().add(path);
        getStyleClass().add("background_border");
//        text= new Text();
//        getChildren().addAll(text);
        //text.setFont(Font.font(72));

    }
  public void DrawX(){
       // text.setText("X");
      getStyleClass().clear();
      getStyleClass().addAll("background_x");

  }
    public void DrawO(){
       // text.setText("O");
        setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");

        getStyleClass().clear();
        getStyleClass().addAll("background_o");

    }
    public void DrawIndex(int s){
        // text.setText("O");
        setStyle("-fx-background-color: rgba(11, 165, 49, 0.2);");

//        getStyleClass().clear();
//        getStyleClass().addAll("background_win");


    }
    public void drawEmpty(){
        // text.setText("O");
     //   setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");

        getStyleClass().clear();
        setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");

        getStyleClass().add("background_border");


    }
    public void setIndex(int x){
        this.TileIndex=x;

    }
    public int GetIndex(){
        return this.TileIndex;

    }
    public int drawStrip(){

        return this.TileIndex;

    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
