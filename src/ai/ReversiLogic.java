package ai;
import java.util.Random;
import model.Reversi.ReversiBoard;
import model.Reversi.Score;
import java.util.ArrayList;
import java.util.List;
import model.Reversi.TileReversi;

import javax.swing.*;
import java.util.LinkedList;

public class ReversiLogic implements Ai{
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

    void ShowWinBox(Score score)
    {
        if(score.black > score.white)
            JOptionPane.showMessageDialog(null, "Black wins! Black: " + score.black + " White: " +score.white);
        else if (score.black < score.white)
            JOptionPane.showMessageDialog(null, "White wins! White: " + score.white + " Black: " +score.black);
        else if(score.black == score.white)
            JOptionPane.showMessageDialog(null, "DRAW");

    }

    //myTurn
    public boolean move(int index) {

        boolean valid = false;
        System.out.println("The AI is "+aiType+" and the player is  "+ playerType);
        // System.out.println(board.checkIfValidMove(index,'W'));
        //index=GetRandomMove(playerType);
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
            ShowWinBox(s);
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
        System.out.println("The AI is "+aiType+" and the player is "+ playerType);
        if ("Advanced".equals(difficulty)) {
            aiMove = GetBestMove();
        } else {
            aiMove = GetRandomMove(aiType);
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
    public int GetRandomMove(char playerType) {
        List<Integer> validMoves= new ArrayList<>();
        Random random = new Random();
        int index = -1;
        for (int i = 0; i < 8; i++) {
            for (int c = 0; c < 8; c++) {
                index++;


                if (board.checkIfValidMove(index, playerType) && index < 64) {
                    validMoves.add(index);
                    //return index;

                } else if (index == 63) {
                    System.out.println("There are no move for  " + aiType);

                }

            }
        }
        int randomMove = random.nextInt(validMoves.size());
        return validMoves.get(randomMove);

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
    public  boolean higlight(int index,char aiType) {

        return board.checkIfValidMove(index,aiType);

    }
}
