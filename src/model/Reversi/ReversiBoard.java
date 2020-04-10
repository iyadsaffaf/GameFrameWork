package model.Reversi;

import java.util.List;

import java.util.Arrays;

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

                free = checkIfValidMove(index, 'B');
                index++;


            }

        }
        return free;
    }


    public char getOwner(int x) {
        char owner = 'F';
        int index = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (index == x) {
                    if (board[r][c] == 'B')
                        owner = 'B';
                    if (board[r][c] == 'W')
                        owner = 'W';

                }
                index++;


            }

        }
        return owner;
    }

    //checkifvalid
    public boolean checkIfValidMove(int index, char player) {
        boolean isValid = false;
        boolean opponentCheck = false;
        Move move = new Move(index);
        int x = move.x;
        int y = move.y;

        if (getOwner(index) == 'F') {
            if (  move.y != 0 &&move.x != 0 && board[move.y - 1][move.x - 1] == player ) {
                tiles.get(index).setColourToHighLight();



            } else if ( move.x != 0 &&board[move.y][move.x - 1] == player) {
                tiles.get(index).setColourToHighLight();

            } else if ( move.y != 7 &&move.x != 0 &&board[move.y + 1][move.x - 1] == player) {
                tiles.get(index).setColourToHighLight();

            } else if (move.y != 0 &&board[move.y - 1][move.x] == player) {
                tiles.get(index).setColourToHighLight();

            } else if (move.x != 7&&board[move.y][move.x + 1] == player) {
                tiles.get(index).setColourToHighLight();

            } else if (move.y != 7&&move.x != 7&&board[move.y + 1][move.x + 1] == player) {
                while (x<boardSize && y<boardSize) {

                    if (board[move.y + y][move.x + x] == player) {
                        isValid = true;
                        tiles.get(index).setColourToHighLight();
                    }else{
                        break;
                    }
                    x++;
                    y++;
                }
            } else if (move.y != 7&&board[move.y + 1][move.x] == player) {
                tiles.get(index).setColourToHighLight();

            } else if (move.y != 0&&move.x != 7&&board[move.y - 1][move.x + 1] == player) {
                tiles.get(index).setColourToHighLight();
            }
        }

        return isValid;
    }

    public boolean checkIfValidMove2(int x, char i) {
        List<Integer> uitzonderingl = Arrays.asList(0, 8, 16, 24, 32, 40, 48, 56);
        List<Integer> uitzonderingr = Arrays.asList(7, 15, 23, 31, 39, 47, 55, 63);
        List<Integer> uitzonderingb = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
        List<Integer> uitzonderingo = Arrays.asList(56, 57, 58, 59, 60, 61, 62, 63);
        List<Integer> opponentlocation = Arrays.asList();
        boolean valid = false;
        boolean opponentCheck = false;
        //System.out.println(getOwner(x));
        if (getOwner(x) == 'F') {
            if (uitzonderingl.contains(x)) {
                if (uitzonderingb.contains(x)) {
                    if (getOwner(x + 1) == 'W' || getOwner(x + 8) == 'W' || getOwner(x + 9) == 'W') {
                        opponentCheck = true;
                    }
                } else if (uitzonderingo.contains(x)) {
                    if (getOwner(x + 1) == 'W' || getOwner(x - 8) == 'W' || getOwner(x - 7) == 'W') {
                        opponentCheck = true;
                    }
                } else if (getOwner(x + 1) == 'W' || getOwner(x + 8) == 'W' || getOwner(x + 9) == 'W' || getOwner(x - 8) == 'W' || getOwner(x - 7) == 'W') {
                    opponentCheck = true;
                }
            } else if (uitzonderingr.contains(x)) {
                if (uitzonderingb.contains(x)) {
                    if (getOwner(x - 1) == 'W' || getOwner(x + 7) == 'W' || getOwner(x + 8) == 'W') {
                        opponentCheck = true;
                    }
                } else if (uitzonderingo.contains(x)) {
                    if (getOwner(x - 1) == 'W' || getOwner(x - 8) == 'W' || getOwner(x - 9) == 'W') {
                        opponentCheck = true;
                    }
                } else if (getOwner(x - 1) == 'W' || getOwner(x - 8) == 'W' || getOwner(x - 9) == 'W' || getOwner(x + 8) == 'W' || getOwner(x + 7) == 'W') {
                    opponentCheck = true;
                }
            } else if (uitzonderingb.contains(x)) {
                if (getOwner(x + 1) == 'W' || getOwner(x - 1) == 'W' || getOwner(x + 7) == 'W' || getOwner(x + 8) == 'W' || getOwner(x + 9) == 'W') {
                    opponentCheck = true;
                }
            } else if (uitzonderingo.contains(x)) {
                if (getOwner(x + 1) == 'W' || getOwner(x - 1) == 'W' || getOwner(x - 9) == 'W' || getOwner(x - 8) == 'W' || getOwner(x - 7) == 'W') {
                    opponentCheck = true;
                }
            } else if (uitzonderingb.contains(x) == false && uitzonderingl.contains(x) == false && uitzonderingo.contains(x) == false == uitzonderingr.contains(x) == false) {
                if (getOwner(x + 1) == 'W' || getOwner(x - 1) == 'W' || getOwner(x + 7) == 'W' || getOwner(x - 7) == 'W' || getOwner(x + 8) == 'W' || getOwner(x - 8) == 'W' || getOwner(x + 9) == 'W' || getOwner(x - 9) == 'W') {
                    opponentCheck = true;
                    tiles.get(x).setColourToHighLight();
                }
            }
            if (opponentCheck == true) {
                // nog check bouwen of steen van zelf aan de andere kant is
                valid = true; //tijdelijk voor test
            }
        }
        return valid;
    }


        //ismoveleft
        public boolean isMoveLeft () {
            boolean moveleft = false;
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[i].length; ++j) {
                    if (board[i][j] == 'F') {
                        moveleft = true;
                        break;
                    }
                }
            }
            return moveleft;
        }

        //evaluateboard
        public Score GetScore () {
            score.setWhite(CountTiles('W'));
            score.setBlack(CountTiles('B'));
            score.setFree(CountTiles('F'));

            return score;
        }

        int CountTiles ( char color){
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
        public void flipAfterMove () {

        }
    }
