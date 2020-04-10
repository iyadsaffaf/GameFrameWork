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
        this.tiles=tiles;
        this.board= new ReversiBoard(tiles);
        this.playerType=b;
        this.aiType=getAiType(b);
    }
    public void test(){

        tiles.get(3).setColourToWhite();
    }

//myTurn
    public void move(int index){

  System.out.println(board.checkIfValidMove(index,'B'));
//        if(board.isValid()){
//
//      board.fillInCells(index,playerType);
//      board.flipAfterMove();
//
//
//
//        }

    }
    public char getAiType(char playerType) {
        char aiType='B';
        if (playerType=='B') {
            aiType = 'W';
        } else if(playerType=='W') {
            aiType='B';


        }
        return aiType;
    }

//Ai Turn
    public void moveAI(){
        int movex = GetBestMove();

        if(board.isValid()){

            board.fillInCells(movex,aiType);
            board.flipAfterMove();



        }



    }
    public int GetBestMove(){

        return 4;
    }

}
