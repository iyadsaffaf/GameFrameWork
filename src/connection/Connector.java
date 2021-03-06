package connection;

import ai.ReversiLogic;
import ai.TicLogic;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class Connector implements Runnable {
    private TicLogic ai;
    private ReversiLogic reversiAi;
    private Connection connection;
    private ListView<String> playerListView;
    private ListView<String> gameListView;
    private ListView<String> challengeList;

    private ServerCommand serverCommand;
    private String gameType;
    private boolean amIThefirst = false;
    //private char playerType;
    private String loginName;
    private String difficulty;

    public Connector(Connection s, ListView<String> plyerListView, ListView<String> gameListView, ListView<String> challengeList, String loginName, String difficulty) {
        this.serverCommand = new ServerCommand();
        this.connection = s;
        this.playerListView = plyerListView;
        this.gameListView = gameListView;
        this.challengeList = challengeList;
        this.loginName = loginName;
        this.difficulty = difficulty;

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
        if (message.contains("Reversi")) {
            gameType = "Reversi";
        }

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
            gameLoss(message);

        } else if (message.contains("WIN")) {
            gameWin(message);

        } else if (message.contains("MOVE")) {
            //update PlayerList
            updateGame(message);
        } else if (message.contains("CHALLENGE")) {
            //update PlayerList
            updateChallengeList(message);
        }


    }

    private void updateChallengeList(String message) {

        ArrayList<String> array = serverCommand.GetPlayersList(message);
        String challenge = array.get(1) + " The player " + array.get(0) + " sent you a challenge to " + array.get(2);
        challengeList.getItems().add(challenge);
        System.out.println(challengeList.getSelectionModel().getSelectedItems());

    }

    public void updatePlayerList(String message) {
        ArrayList<String> array = serverCommand.GetPlayersList(message);
        ObservableList<String> data = FXCollections.observableArrayList();

        data.addAll(array);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                playerListView.getItems().clear();

                playerListView.setItems(data);
            }
        });

        System.out.println(playerListView.getSelectionModel().getSelectedItems());
    }

    public void updateGameList(String message) {
        System.out.println(message);
        ArrayList<String> array = serverCommand.GetPlayersList(message);
        ObservableList<String> data = FXCollections.observableArrayList();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gameListView.getItems().clear();
                data.addAll(array);
                gameListView.setItems(data);
            }
        });


    }

    public void startMatch(String message) {
        if (gameType == "Reversi") {
            this.reversiAi = new ReversiLogic('t', difficulty);
        }
        else{
            this.ai = new TicLogic('t',difficulty);
        }
        if (serverCommand.GetPlayersList(message).get(0).equals(this.loginName)) {
            amIThefirst = true;
            System.out.println("AmIthefirst = " + amIThefirst);
        }
        amIThefirst();

        //todo check the type of math
        //this.ai = new TicLogic(PlayerType.X);


    }

    public void amIThefirst() {
        if (amIThefirst) {
            if (gameType == "Reversi") {
                System.out.println("player1");
                this.reversiAi.setPlayerType('W');
                this.reversiAi.setAiType('B');
            } else {

                System.out.println("X " + ")))))))))))))))))))))))))))");
                this.ai.setPlayerType('X');
            }
        } else {
            if (gameType == "Reversi") {
                System.out.println("player2");
                this.reversiAi.setPlayerType('B');
                this.reversiAi.setAiType('W');
            }
            else {
                this.ai.setPlayerType('O');
            }
        }
    }

    public void move(String message) {
        if (gameType == "Reversi") {
            int x = reversiAi.moveAI();
            connection.getOutput().println("move " + x);
        } else {
           int x = ai.moveAI();
            connection.getOutput().println("move " + x);
        }
    }

    //todo check the type of math


    public void updateGame(String message) {


        if (gameType == "Reversi") {
            System.out.println(serverCommand.GetPlayersList(message).get(1) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            reversiAi.move(Integer.parseInt((serverCommand.GetPlayersList(message).get(1))));
        } else {
            ai.move(Integer.parseInt(serverCommand.GetPlayersList(message).get(1)));
        }


    }

    public void gameLoss(String message) {
        reversiAi = null;
        amIThefirst = false;

    }

    public void gameWin(String message) {
        reversiAi = null;
        amIThefirst = false;
    }

}
