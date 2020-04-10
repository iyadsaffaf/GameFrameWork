package model.Reversi;

import model.Move;

import java.util.LinkedList;

public class ReversiBoard {
    private int boardSize = 8;
    private char[][] board = new char[boardSize][boardSize];
    private LinkedList<TileReversi> tiles;
    private Score score;


    // character W B F
    public ReversiBoard(LinkedList<TileReversi> tiles) {
        this.tiles = tiles;
        FillInWithFree();
        score = new Score();
        setUpTheFirstCoins();
        isValid();

    }

    // set the first four
    public void setUpTheFirstCoins() {
        fillInCells(27, 'B');
        tiles.get(27).setColourToWhite();
        fillInCells(28, 'W');
        tiles.get(28).setColourToBlack();
        fillInCells(35, 'W');
        tiles.get(35).setColourToBlack();
        fillInCells(36, 'B');
        tiles.get(36).setColourToWhite();


    }

    //fillfree
    public void FillInWithFree() {

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                board[r][c] = 'F';

            }

        }
    }
    //checkiffree

    public boolean isEmpty(int x) {
        boolean free = false;
        int index = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                if (index == x) {
                    if (board[r][c] == 'F')
                        free = true;

                }
                index++;


            }

        }
        return free;
    }

    //
    public void fillInCells(int x, char playerType) {

//        int index= 0;
//        for (int r = 0; r < boardSize; r++) {
//            for (int c = 0; c < boardSize; c++) {
//                if(index==x){
//                    board[r][c]=playerType;
//
//                }
//                index++;
//
//
//            }
//
//        }
        Move move = new Move(x);
        board[move.y][move.x] = playerType;


    }

    // highlight
    public boolean isValid() {

        boolean free = false;
        int index = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                checkIfValidMove(index, 'B');
                index++;


            }

        }
        return free;
    }

    //checkifvalid
    public boolean checkIfValidMove(int index, char player) {
        boolean isValid = true;
        Move move = new Move(index);
        if (move.x != 0 && move.y != 0 && move.x != 7 && move.y != 7 && isEmpty(index)) {
            if (board[move.y - 1][move.x - 1] == player) {
                tiles.get(index).setColourToHighLight();
            } else if (board[move.y][move.x - 1] == player) {
                tiles.get(index).setColourToHighLight();

            } else if (board[move.y + 1][move.x - 1] == player) {
                tiles.get(index).setColourToHighLight();

            } else if (board[move.y - 1][move.x] == player) {
                tiles.get(index).setColourToHighLight();

            } else if (board[move.y][move.x + 1] == player) {
                tiles.get(index).setColourToHighLight();

            } else if (board[move.y + 1][move.x + 1] == player) {
                tiles.get(index).setColourToHighLight();

            } else if (board[move.y + 1][move.x] == player) {
                tiles.get(index).setColourToHighLight();

            }
        }
        return isValid;
    }

    //ismoveleft
    //evaluateboard
    public Score GetScore() {
        score.setWhite(CountTiles('W'));
        score.setBlack(CountTiles('B'));
        score.setFree(CountTiles('F'));

        return score;
    }

    int CountTiles(char color) {
        int result = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == color) {
                    result++;
                }
            }
        }
        return result;
    }
    //flibaftermove en fill current
}
