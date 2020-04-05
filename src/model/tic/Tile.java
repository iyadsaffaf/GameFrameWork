package model.tic;


import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tile extends StackPane {
    private Text text = new Text();
    private  int  TileIndex;
    public Tile(){
        Rectangle border =new Rectangle(100,100);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(border,text);
        text.setFont(Font.font(72));

    }
  public void DrawX(){
        text.setText("x");

  }
    public void DrawY(){
        text.setText("Y");

    }
    public void setIndex(int x){
        this.TileIndex=x;

    }
    public int GetIndex(){
        return this.TileIndex;

    }
}
