package model.tic;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tile extends StackPane {


    private Text text;
    private  int  TileIndex;
    public Tile(){
        //setBackground(new Background(new BackgroundFill(Color.rgb(20, 205, 50), CornerRadii.EMPTY, Insets.EMPTY)));

        text= new Text();
        Rectangle border =new Rectangle(75,75);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(border,text);
        text.setFont(Font.font(72));

    }
  public void DrawX(){
        text.setText("X");

  }
    public void DrawO(){
        text.setText("O");

    }
    public void DrawIndex(int s){
        text.setText(s+"");

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
