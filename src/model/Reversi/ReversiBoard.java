package model.Reversi;

import java.util.LinkedList;

public class ReversiBoard {
    private int boardSize=8;
    private char[][] board = new char[boardSize][boardSize];
    private LinkedList<TileReversi> tiles ;

    // character W B F
    public ReversiBoard(LinkedList<TileReversi> tiles) {
        this.tiles=tiles;
        FillInWithFree();
        setUpTheFirstCoins();



    }
    // set the first four
    public void setUpTheFirstCoins(){
        fillInCells(27,'B');
        tiles.get(27).setColourToBlack();
        fillInCells(28,'W');
        tiles.get(28).setColourToWhite();
        fillInCells(35,'W');
        tiles.get(35).setColourToWhite();
        fillInCells(36,'B');
        tiles.get(36).setColourToBlack();




    }
    //fillfree
    public void FillInWithFree(){

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                board[r][c]='F';

            }

        }
    }
    //checkiffree

    public boolean isEmpty(int x){
        boolean free= false;
        int index= 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if(index==x){
                    if(board[r][c]=='F')
                        free=true;

                }
                index++;


            }

        }
        return free;
    }
    //
    public void fillInCells(int x, char playerType) {
        int index= 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if(index==x){
                    board[r][c]=playerType;

                }
                index++;


            }

        }


    }

    //checkifvalid
    //ismoveleft
    //evaluateboard
    //flibaftermove en fill current


}
