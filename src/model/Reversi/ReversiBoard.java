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
        fillInCells(27, 'W');
        tiles.get(27).setColourToWhite();
        fillInCells(28, 'B');
        tiles.get(28).setColourToBlack();
        fillInCells(29, 'B');
        tiles.get(29).setColourToBlack();
        fillInCells(35, 'B');
        tiles.get(35).setColourToBlack();
        fillInCells(36, 'W');
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
        tiles.get(x).setColourToWhite();
        isValid();


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

        String direction = "nothing";

        if (getOwner(index) == 'F') {
            if (move.y != 0 && move.x != 0 && board[move.y - 1][move.x - 1] == player) {
                direction = "Topleft";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                }


            } else if (move.x != 0 && board[move.y][move.x - 1] == player) {
                direction = "Left";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                }

            } else if (move.y != 7 && move.x != 0 && board[move.y + 1][move.x - 1] == player) {
                direction = "Bottomleft";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                }

            } else if (move.y != 0 && board[move.y - 1][move.x] == player) {
                direction = "Top";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                }

            } else if (move.x != 7 && board[move.y][move.x + 1] == player) {
                direction = "Right";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                }

            } else if (move.y != 7 && move.x != 7 && board[move.y + 1][move.x + 1] == player) {
                direction = "Bottomright";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                }


            } else if (move.y != 7 && board[move.y + 1][move.x] == player) {
                direction = "Bottom";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                }
            } else if (move.y != 0 && move.x != 7 && board[move.y - 1][move.x + 1] == player) {
                direction = "Topright";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                }
            }


        }
        return isValid;
    }

    public boolean checkCapture(String direction, Move move, char player) {
        boolean checkCapture = false;
        //int captureScore = 2;
        int x = move.x;
        int y = move.y;
        char player2 = player;
        player = getAiType(player);
        System.out.println(player + "" + player2);
        switch (direction) {
            case "Topleft":
                for (int i = 2; (x - i) < boardSize && (y - i) < boardSize; i++) {
                    //capturescore ++;
                    if (board[y - i][x - i] == player) {
                        checkCapture = true;
                        break;

                    } else if (board[y - i][x - i] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Top":
                for (int i = 2; (y - i) < boardSize; i++) {
                    //capturescore ++;
                    if (board[y - i][x] == player) {
                        checkCapture = true;
                    } else if (board[y - i][x] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Topright":
                for (int i = 2; (x + i) < boardSize && y - i < boardSize; i++) {
                    //capturescore ++;
                    if (board[y - i][x + i] == player) {
                        checkCapture = true;
                    } else if (board[y - i][x + i] == player2) {
                        continue;
                    } else {
                        break;
                    }
                    break;
                }
            case "Left":
                for (int i = 2; (x - i) < boardSize; i++) {
                    //capturescore ++;
                    if (board[y][x - i] == player) {
                        checkCapture = true;
                    }else if (board[y][x - i] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Right":
                for (int i = 2; (x + i) < boardSize; i++) {
                    //capturescore ++;
                    System.out.println("index" + i);
                    if (board[y][x + i] == player) {
                        checkCapture = true;
                        System.out.println("right = true");
                    } else if (board[y][x + i] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Bottomleft":
                for (int i = 2; x - i < boardSize && y + i < boardSize; i++) {
                    //capturescore ++;

                    if (board[y + i][x - i] == player) {
                        checkCapture = true;
                    } else if (board[y + i][x - i] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Bottom":
                for (int i = 2; y + i < boardSize; i++) {
                    //capturescore ++;
                    if (board[y + i][x] == player) {
                        checkCapture = true;
                    } else if (board[y + i][x] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Bottomright":
                for (int i = 2; x + i < boardSize && y + i < boardSize; i++) {
                    //capturescore ++;
                    if (board[y + i][x + i] == player) {
                        checkCapture = true;
                    } else if (board[y + i][x + i] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
        }
        return checkCapture;
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
    public boolean isMoveLeft() {
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
    public void flipAfterMove() {

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
}