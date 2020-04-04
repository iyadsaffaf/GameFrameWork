package controller;

import connection.Connect;
import javafx.event.ActionEvent;

public class GameRemoteController {
    public void LoginButton(ActionEvent actionEvent) {
    }

    public void GetGameListButton(ActionEvent actionEvent) {
    }

    public void GetPlyerlistButton(ActionEvent actionEvent) {
    }

    public void ConnectButton(ActionEvent actionEvent) {
        Connect s = new Connect();
        s.setUpConnection();
    }
}
