package controller;

import connection.Connection;
import connection.ServerCommand;
import connection.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class GameRemoteController {
    private Connection connection;
    private ServerCommand serverCommand= new ServerCommand();

    @FXML
    private Text textremote;

    @FXML
    private ListView<String> plyerListView;

    @FXML
    private ListView<String> gameListView;


    public void LoginButton(ActionEvent actionEvent) {
       // System.out.println(serverCommand.GetPlayersList("SVR GAME MOVE {PLAYER: \"1\", MOVE: \"3\", DETAILS: \"\"}").get(1));

        textremote.setText("Dddd");
        connection.getOutput().println("login Iyad");

    }

    public void GetGameListButton(ActionEvent actionEvent) {

        connection.getOutput().println("get gamelist");

    }

    public void GetPlyerlistButton(ActionEvent actionEvent) {

        connection.getOutput().println("get playerlist");
    }
    public void Move(int x)
    {
    }

    public void ConnectButton(ActionEvent actionEvent) {
        connection = new Connection();
        Connector connector = new Connector(connection,plyerListView,gameListView);
        Thread thread = new Thread(connector);
        thread.start();
    }

    public void SubscribeButton(ActionEvent actionEvent) {
        String ss = gameListView.getSelectionModel().getSelectedItems().toString();
        String game=(ss.substring(1,ss.length()-1));
        connection.getOutput().println("subscribe "+game);



    }
}
