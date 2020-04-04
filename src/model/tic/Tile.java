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
    public Tile(){
        Rectangle border =new Rectangle(100,100);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(border,text);
        text.setFont(Font.font(72));
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
        DrawX();
            }
        });




    }
  public void DrawX(){
        text.setText("x");

  }
    public void DrawY(){
        text.setText("Y");

    }
}
