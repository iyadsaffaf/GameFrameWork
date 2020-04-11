package ai;

import model.Reversi.ReversiBoard;
import model.Reversi.TileReversi;
import model.tic.PlayerType;

import javax.swing.border.Border;
import java.util.LinkedList;

public class ReversiLogic {
    private LinkedList<TileReversi> tiles;
    private ReversiBoard board;
    private char playerType;
    private char aiType;

    public ReversiLogic(LinkedList<TileReversi> tiles, char b) {
        this.tiles = tiles;
        this.board = new ReversiBoard(tiles);
        this.playerType = b;
        this.aiType = getAiType(b);
    }

    public void test() {

        tiles.get(3).setColourToWhite();
    }

    //myTurn
    public void move(int index) {

        // System.out.println(board.checkIfValidMove(index,'W'));
        if (board.checkIfValidMove(index, playerType)) {
            board.fillInCells(index, playerType);
            tiles.get(index).setColourToThisPlayer(playerType);

            try {
                System.out.println("StartSleeping");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("EndSleeping");

            moveAI();

        }
//        if(board.isValid()){
//
//      board.fillInCells(index,playerType);
//      board.flipAfterMove();
//
//
//
//      }

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
    public void moveAI() {
        int aiMove = GetRandomMove();

        System.out.println(aiMove+"GG");
        tiles.get(aiMove).setColourToThisPlayer(aiType);
        board.fillInCells(aiMove, aiType);

        board.isValid(playerType);
        board.flipAfterMove();

        PrintBoard();

    }

    //Random Algorithm
    public int GetRandomMove() {
        int index = -1;
        for (int i = 0; i < 8; i++) {
            for (int c = 0; c < 8; c++) {
                index++;


                if (board.checkIfValidMove(index, aiType)&&index<64) {
                    return index;

                }else if (index==63){
                    System.out.println("There are no move for  " +aiType);

                }

            }
        }
        return index;

    }

    //miniMAX Algorithm
    public int GetBestMove() {

        return 4;
    }



    public void PrintBoard() {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.print("   "+ board.getBoard()[x][y]+"  ");
            }

            System.out.println();
        }
    }

}
