package controller;

import com.sun.javafx.beans.event.AbstractNotifyListener;
import connection.Connect;
import connection.ServerCommand;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class GameRemoteController {
    private Connect s;
    private ServerCommand serverCommand= new ServerCommand();

    @FXML
    private Text textremote;

    @FXML
    private ListView<String> plyerListView;

    @FXML
    private ListView<String> gameListView;


    public void LoginButton(ActionEvent actionEvent) {
        textremote.setText("Dddd");
        textremote.setText(s.WriteToServer("login Iyad"));

    }

    public void GetGameListButton(ActionEvent actionEvent) {



        ArrayList<String> array=serverCommand.GetPlayersList(s.WriteToServer("get gamelist"));
        gameListView.getItems().clear();

        for ( String var : array)
        {
            gameListView.getItems().add(var);

        }

        System.out.println(gameListView.getSelectionModel().getSelectedItems());

    }

    public void GetPlyerlistButton(ActionEvent actionEvent) {
        //s.WriteToServer("get playerlist");

        ArrayList<String> array=serverCommand.GetPlayersList(s.WriteToServer("get playerlist"));
        plyerListView.getItems().clear();

        for ( String var : array)
        {
            plyerListView.getItems().add(var);

        }

        System.out.println(plyerListView.getSelectionModel().getSelectedItem());
    }
    public void Move(int x) {
        s.WriteToServer("Move "+x);
    }

    public void ConnectButton(ActionEvent actionEvent) {
        s = new Connect();
        s.setUpConnection();
    }

    public void SubscribeButton(ActionEvent actionEvent) {
       System.out.println(s.WriteToServer("subscribe "+gameListView.getSelectionModel().getSelectedItems().toString()));

    }
}
