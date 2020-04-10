package model.reversi;


import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ReversiTile extends StackPane {


    private Text text;
    private  int  TileIndex;
    public ReversiTile(){
        text= new Text();
        Rectangle border =new Rectangle(100,100);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(border,text);
        text.setFont(Font.font(72));

    }
    public void DrawX(){
        text.setText("B");

    }
    public void DrawO(){
        text.setText("W");

    }

    public void DrawF(){

    }

    public void setIndex(int x){
        this.TileIndex=x;

    }
    public int GetIndex(){
        return this.TileIndex;

    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}