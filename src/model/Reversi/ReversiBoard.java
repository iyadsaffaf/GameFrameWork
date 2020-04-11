package model.Reversi;

import model.Move;

import java.util.LinkedList;

public class ReversiBoard {
    private int boardSize = 8;
    private char[][] board;

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    private LinkedList<TileReversi> tiles;
    private Score score;


    // character W B F
    public ReversiBoard(LinkedList<TileReversi> tiles) {
        this.board = new char[boardSize][boardSize];
        this.tiles = tiles;
        FillInWithFree();
        score = new Score();
        setUpTheFirstCoins();

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

        fillInCells(45, 'W');
        tiles.get(45).setColourToWhite();
        fillInCells(54, 'W');
        tiles.get(54).setColourToWhite();

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
    public boolean isValid(char player) {

        boolean free = false;
        int index = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                free = checkIfValidMove(index, player);
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
    public boolean checkIfValidMove(int index, char playe) {
        boolean isValid = false;
        Move move = new Move(index);
        String direction = "nothing";
        char player = getAiType(playe);

        if (board[move.y][move.x] == 'F') {
            if (move.y != 0 && move.x != 0 && board[move.y - 1][move.x - 1] == player) {
                direction = "Topleft";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                    return true;
                }
                System.out.println("Check for Topleft   pleyer:" + player + "  results:" + isValid);
                System.out.println("x  :" + move.x + "  y:" + move.y);


            }
            if (move.x != 0 && board[move.y][move.x - 1] == player) {
                direction = "Left";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                    return true;

                }
                System.out.println("Check for Left   pleyer:" + player + "  results:" + isValid);
                System.out.println("x  :" + move.x + "  y:" + move.y);


            }
            if (move.y != 7 && move.x != 0 && board[move.y + 1][move.x - 1] == player) {
                direction = "Bottomleft";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                    return true;

                }
                System.out.println("Check for Bottomleft   pleyer:" + player + "  results:" + isValid);
                System.out.println("x  :" + move.x + "  y:" + move.y);


            }
            if (move.y != 0 && board[move.y - 1][move.x] == player) {
                direction = "Top";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                    return true;

                }
                System.out.println("Check for Top   pleyer:" + player + "  results:" + isValid);
                System.out.println("x  :" + move.x + "  y:" + move.y);


            }
            if (move.x != 7 && board[move.y][move.x + 1] == player) {
                direction = "Right";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                    return true;

                }
                System.out.println("Check for Right   pleyer:" + player + "  results:" + isValid);
                System.out.println("x  :" + move.x + "  y:" + move.y);


            }
            if (move.y != 7 && move.x != 7 && board[move.y + 1][move.x + 1] == player) {
                direction = "Bottomright";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                    return true;
                }
                System.out.println("Check for Bottomright   pleyer:" + player + "  results:" + isValid);
                System.out.println("x  :" + move.x + "  y:" + move.y);


            }
            if (move.y != 7 && board[move.y + 1][move.x] == player) {
                direction = "Bottom";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                    return true;
                }
                System.out.println("Check for Bottom   pleyer:" + player + "  results:" + isValid);
                System.out.println("x  :" + move.x + "  y:" + move.y);


            }
            if (move.y != 0 && move.x != 7 && board[move.y - 1][move.x + 1] == player) {
                direction = "Topright";
                isValid = checkCapture(direction, move, player);
                if (isValid) {
                    tiles.get(index).setColourToHighLight();
                    return true;

                }
                System.out.println("Check for Topright   pleyer:" + player + "  results:" + isValid);
                System.out.println("x  :" + move.x + "  y:" + move.y);


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
        switch (direction) {
            case "Topleft":
                for (int i = 2; (x - i) > 0 && (y - i) > 0; i++) {
                    //capturescore ++;
                    System.out.println("x  :" + move.x + "  y:" + move.y + "  I  " + "Player  " + player);

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

                for (int i = 2; (y - i) > 0; i++) {
                    System.out.println("x  :" + move.x + "  y:" + move.y + "  I  " + "Player  " + player);

                    //capturescore ++;
                    if (board[y - i][x] == player) {
                        checkCapture = true;
                        break;
                    } else if (board[y - i][x] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Topright":
                for (int i = 2; (x + i) < boardSize && (y - i) > 0; i++) {
                    System.out.println("x  :" + move.x + "  y:" + move.y + "  I  " + "Player  " + player);

                    //capturescore ++;
                    if (board[y - i][x + i] == player) {
                        checkCapture = true;
                        break;
                    } else if (board[y - i][x + i] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Left":
                for (int i = 2; (x - i) > 0; i++) {
                    System.out.println("x  :" + move.x + "  y:" + move.y + "  I  " + "Player  " + player);

                    //capturescore ++;
                    if (board[y][x - i] == player) {
                        checkCapture = true;
                        break;
                    } else if (board[y][x - i] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Right":
                for (int i = 2; (x + i) < boardSize; i++) {
                    System.out.println("x  :" + move.x + "  y:" + move.y + "  I  " + "Player  " + player);

                    //capturescore ++;
                    if (board[y][x + i] == player) {
                        checkCapture = true;
                        break;
                    } else if (board[y][x + i] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Bottomleft":
                for (int i = 2; x - i > 0 && y + i < boardSize; i++) {
                    System.out.println("x  :" + move.x + "  y:" + move.y + "  I  " + "Player  " + player);

                    //capturescore ++;

                    if (board[y + i][x - i] == player) {
                        checkCapture = true;
                        break;
                    } else if (board[y + i][x - i] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Bottom":
                for (int i = 2; y + i < boardSize; i++) {
                    System.out.println("x  :" + move.x + "  y:" + move.y + "  I  " + "Player  " + player);

                    //capturescore ++;
                    if (board[y + i][x] == player) {
                        checkCapture = true;
                        break;
                    } else if (board[y + i][x] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            case "Bottomright":
                for (int i = 2; x + i < boardSize && y + i < boardSize; i++) {
                    System.out.println("x  :" + move.x + "  y:" + move.y + "  I  " + "Player  " + player);

                    //capturescore ++;
                    if (board[y + i][x + i] == player) {
                        checkCapture = true;
                        break;
                    } else if (board[y + i][x + i] == player2) {
                        continue;
                    } else {
                        break;
                    }
                }
        }
        return checkCapture;
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
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == color) {
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
        }
        return aiType;
    }
}
