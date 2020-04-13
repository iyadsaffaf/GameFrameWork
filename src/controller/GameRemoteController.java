package controller;

import connection.Connection;
import connection.ServerCommand;
import connection.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.security.spec.ECField;
import java.util.ArrayList;


public class GameRemoteController {
    private Connection connection;
    private ServerCommand serverCommand = new ServerCommand();
    @FXML
    private GridPane root;

    @FXML
    private Text textremote;

    @FXML
    private ListView<String> plyerListView;

    @FXML
    private ListView<String> gameListView;

    @FXML
    private ListView<String> challengeList;
    @FXML
    private TextField ipAddress;
    @FXML
    private TextField loginName;
    @FXML
    private Button loginButton;
    @FXML
    private Button getGameListButton;
    @FXML
    private Button getPlayerListButton;
    @FXML
    private Button connectButton;
    @FXML
    private Button sunscribeButton;
    @FXML
    private Button challengeButton;
    @FXML
    private Button acceptButton;

    @FXML
    private Text textremote13;

    @FXML
    private Label warning;
    @FXML
    private TextField portNumber;


    private boolean connected;
    private boolean loggedin = false;
    private  Thread thread;

    public void LoginButton(ActionEvent actionEvent) {
        // System.out.println(serverCommand.GetPlayersList("SVR GAME MOVE {PLAYER: \"1\", MOVE: \"3\", DETAILS: \"\"}").get(1));

        if (!loggedin) {
            loggedin = true;
            connection.getOutput().println("login " + loginName.getText());
            textremote.setText("Welcome " + loginName.getText());
            textremote13.setText("You are in");

            sunscribeButton.setDisable(false);
            challengeButton.setDisable(false);
            acceptButton.setDisable(false);

        } else {
            loggedin = false;


        }

    }

    public void GetGameListButton(ActionEvent actionEvent) {

        connection.getOutput().println("get gamelist");

    }

    public void GetPlyerlistButton(ActionEvent actionEvent) {

        connection.getOutput().println("get playerlist");
    }

    public void Move(int x) {
    }

    public void ConnectButton(ActionEvent actionEvent) {
        if (!loginName.getText().equals("")) {

            if (!connected) {


                try {
                    connection = new Connection(ipAddress.getText(), Integer.parseInt(portNumber.getText()));

                }catch (Exception s){
                    System.out.println("You cannot connect to server");
                    warning.setText("You cannot connect to server");

                    return;
                }
                textremote.setText("Hello " + loginName.getText()+" You can log in to join");
                textremote13.setText("Log in to join ");

                Connector connector = new Connector(connection, plyerListView, gameListView, challengeList, loginName.getText());
                System.out.println(loginName.getText() + "loginname test");
                 thread = new Thread(connector);
                thread.start();
                warning.setVisible(false);
                loginButton.setDisable(false);
                getGameListButton.setDisable(false);
                getPlayerListButton.setDisable(false);
                connectButton.setText("Disconnect");
                connected = true;



            } else {
                connected=false;
                connectButton.setText("Connect");
                disconnect();
            }
        } else {


            warning.setVisible(true);
        }
    }

    public void SubscribeButton(ActionEvent actionEvent) {
        String ss = gameListView.getSelectionModel().getSelectedItems().toString();
        String game = (ss.substring(1, ss.length() - 1));
        connection.getOutput().println("subscribe " + game);


    }

    public void test(ActionEvent actionEvent) {


    }

    public void acceptChallenge(ActionEvent actionEvent) {
        String ss = challengeList.getSelectionModel().getSelectedItems().toString();
        String game = (ss.substring(1, 3).trim());
        int number = Integer.parseInt(game);
        connection.getOutput().println("challenge accept " + number);

    }

    public void challenge(ActionEvent actionEvent) {

        String ss = gameListView.getSelectionModel().getSelectedItems().toString();
        String game = (ss.substring(1, ss.length() - 1));
        ss = plyerListView.getSelectionModel().getSelectedItems().toString();
        String player = (ss.substring(1, ss.length() - 1));
        String ssss = "challenge \"" + player + "\" \"" + game + "\"";
        System.out.println(ssss);
        connection.getOutput().println(ssss);
    }

    public void disconnect() {
        logOut();
        if(thread!=null)
        thread.interrupt();
        textremote.setText("Bye " + loginName.getText());
        textremote13.setText("You have been disconnected  connect again to login ");
        loginButton.setDisable(true);
        getGameListButton.setDisable(true);
        getPlayerListButton.setDisable(true);
        sunscribeButton.setDisable(true);
        challengeButton.setDisable(true);
        acceptButton.setDisable(true);

    }

    private void logOut() {
        if(connection!=null) {
            connection.getOutput().println("logout " + loginName.getText());
        }
    }

    public void backButton(ActionEvent actionEvent) {
        GridPane gridPane= null;
        try {
            gridPane = FXMLLoader.load(getClass().getResource("../view/gameLocal.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(gridPane);
    }
}
