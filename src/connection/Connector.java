package connection;

import ai.TicLogic;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.tic.PlayerType;

import java.io.IOException;
import java.util.ArrayList;

public class Connector implements Runnable {
    private TicLogic ai;
    private Connection connection;
    private ListView<String> playerListView;
    private ListView<String> gameListView;
    private ListView<String> challengeList;

    private ServerCommand serverCommand;
    private String gameType;
    private boolean amIThefirst = true;
    private PlayerType playerType;


    public Connector(Connection s, ListView<String> plyerListView, ListView<String> gameListView, ListView<String> challengeList) {
        this.serverCommand = new ServerCommand();
        this.connection = s;
        this.playerListView = plyerListView;
        this.gameListView = gameListView;
        this.challengeList = challengeList;

    }


    @Override
    public void run() {
        while (true) {
            try {
                String message = connection.getInput().readLine();
                System.out.println(message);

                // analyese
                if (message == null) {
                    break;
                } else {
                    analyse(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void analyse(String message) {
        if (message.contains("MATCH")) {
            //start match
            startMatch(message);


        } else if (message.contains("PLAYERLIST")) {
            //update PlayerList
            updatePlayerList(message);

        } else if (message.contains("GAMELIST")) {
            updateGameList(message);
            //update PlayerList

        } else if (message.contains("OK")) {
            //update PlayerList

        } else if (message.contains("YOURTURN")) {
            //update PlayerList
            move(message);

        } else if (message.contains("LOSS")) {
            //update PlayerList

        } else if (message.contains("MOVE")) {
            //update PlayerList
            updateGame(message);
        } else if (message.contains("CHALLENGE")) {
            //update PlayerList
            updateChallengeList(message);
        }


    }

    private void updateChallengeList(String message) {

        ArrayList<String> array= serverCommand.GetPlayersList(message);
        String challenge = array.get(1) +" The player "+ array.get(0)+" sent you a challenge to "+ array.get(2);
        challengeList.getItems().add(challenge);
        System.out.println(challengeList.getSelectionModel().getSelectedItems());

    }

    public void updatePlayerList(String message) {
        ArrayList<String> array = serverCommand.GetPlayersList(message);
        ObservableList<String> data = FXCollections.observableArrayList();
        playerListView.getItems().clear();

        data.addAll(array);
        playerListView.setItems(data);

        System.out.println(playerListView.getSelectionModel().getSelectedItems());
    }

    public void updateGameList(String message) {
        System.out.println(message);
        ArrayList<String> array = serverCommand.GetPlayersList(message);
        ObservableList<String> data = FXCollections.observableArrayList();
        gameListView.getItems().clear();
        data.addAll(array);
        gameListView.setItems(data);

        System.out.println(gameListView.getSelectionModel().getSelectedItems());
    }

    public void startMatch(String message) {
        System.out.println(message);
        //todo check the type of math

        this.ai = new TicLogic(PlayerType.X);


    }

    public void move(String message) {
        System.out.println(message);
        if (amIThefirst) {
            amIThefirst = false;
            playerType = PlayerType.X;
            this.ai.setAiType(playerType);

        }
        //todo check the type of math
        int x = ai.GetNextMove();
        connection.getOutput().println("move " + x);


    }

    public void updateGame(String message) {
        if (amIThefirst) {
            amIThefirst = false;
            playerType = PlayerType.O;
            this.ai.setAiType(playerType);


        }

        System.out.println(serverCommand.GetPlayersList(message).get(1));

        ai.move(Integer.parseInt(serverCommand.GetPlayersList(message).get(1)));


    }

}
