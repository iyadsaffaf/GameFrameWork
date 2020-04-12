package ai;

import model.Reversi.ReversiBoard;
import model.Reversi.Score;
import model.Reversi.TileReversi;

import java.util.LinkedList;

public class ReversiLogic {
    private ReversiBoard board;
    private char playerType;
    private char aiType;
    private String difficulty;


    public ReversiLogic(char b, String difficulty) {
        this.board = new ReversiBoard();
        this.playerType = b;
        this.aiType = getAiType(b);
        this.difficulty = difficulty;
    }

    public void test() {

    }

    //myTurn
    public boolean move(int index) {
        boolean valid = false;

        // System.out.println(board.checkIfValidMove(index,'W'));
        if (board.checkIfValidMove(index, playerType)) {
            board.fillInCells(index, playerType);

            board.flipAfterMove(index, playerType);
            valid = true;

        } else if (board.isMoveLeft() && board.isValid(playerType)) {
            // Give true so tha ai can start
            valid = true;


        } else if (!board.isMoveLeft()) {
            Score s = board.GetScore();
            System.out.println("score black " + s.black + "Score" + s.white);

        }
        return valid;

    }

    public char getAiType(char playerType) {
        char aiType = 'B';
        if (playerType == 'B') {
            aiType = 'W';
        } else if (playerType == 'W') {
            aiType = 'B';


        }
        return aiType;
    }

    //Ai Turn
    public int moveAI() {
        int aiMove;
        if ("Advanced".equals(difficulty)) {
            aiMove = GetBestMove();
        } else {
            aiMove = GetRandomMove();
        }

        System.out.println(aiMove + "GG");
        //    tiles.get(aiMove).setColourToThisPlayer(aiType);
        board.fillInCells(aiMove, aiType);
        board.flipAfterMove(aiMove, aiType);
        //   board.isValid(playerType);


        PrintBoard();
        return aiMove;

    }

    //Random Algorithm
    public int GetRandomMove() {
        int index = -1;
        for (int i = 0; i < 8; i++) {
            for (int c = 0; c < 8; c++) {
                index++;


                if (board.checkIfValidMove(index, aiType) && index < 64) {
                    return index;

                } else if (index == 63) {
                    System.out.println("There are no move for  " + aiType);

                }

            }
        }
        return index;

    }

    //miniMAX Algorithm
    public int GetBestMove() {
//todo
        System.out.println("NIKS");

        return 4;
    }


    public void PrintBoard() {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.print("   " + board.getBoard()[x][y] + "  ");
            }

            System.out.println();
        }
    }

    public char[][] getBoard() {

        return board.getBoard();
    }

    public char getPlayerType() {
        return playerType;
    }

    public void setPlayerType(char playerType) {
        this.playerType = playerType;
    }

    public char getAiType() {
        return aiType;
    }

    public void setAiType(char aiType) {
        this.aiType = aiType;
    }
}
