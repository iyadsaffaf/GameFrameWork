package model.tic;

import model.Board;
import model.Move;
import model.Reversi.Score;

import javax.swing.border.Border;

public class TicTacGame implements Board {

    int boardSize = 3;

    public char[][] tiles;

    // 1=x
    //2=o
    //0 free
    public TicTacGame() {
        tiles = new char[boardSize][boardSize];
        fillInWithFree();
    }


    public boolean isMovesLeft() {
        boolean free = false;
        int index = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                if (tiles[r][c] == 'F')
                    free = true;
                index++;


            }

        }
        return free;
    }


    public int getTheFirstFreeIndex() {
        int index = -1;

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                index++;

                if (tiles[r][c] == 'F') {
                    System.out.println("Free are " + (((r * 3) + c)));
                    return index;
                    //                   index = ((r * 3) + c);
                }


            }
        }
        return index;
    }

    public int getTheFirstFreeIndex2() {
        int index = 0;

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                if (tiles[r][c] == 'F') {
                    System.out.println("Free are " + (((r * 3) + c)));
                    return ((r * 3) + c);
                }
                if (index >= 8)
                    break;
            }
        }
        return index;
    }

    public int checkFinish() {
        int index = 0;

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                if (tiles[r][c] == 'F') {
                    index++;
                    break;
                }
                if (index >= 8)
                    break;
            }
        }
        return index;
    }
//
//    public int evaluate(char player) {
//        char opponent = getAiType(player);
//        // Checking for Rows for X or O victory.
//        for (int row = 0; row < 3; row++) {
//            if (cells[row][0] == cells[row][1] &&
//                    cells[row][1] == cells[row][2]) {
//                if (cells[row][0] == player) {
//                    return +10;
//                } else if (cells[row][0] == opponent) {
//                    return -10;
//                }
//
//                break;
//            }
//        }
//
//        // Checking for Columns for X or O victory.
//        for (int col = 0; col < 3; col++) {
//            if (cells[0][col] == cells[1][col] &&
//                    cells[1][col] == cells[2][col]) {
//                if (cells[0][col] == player) {
//                    System.out.print("X is the winner");
//                    return +10;
//
//                } else if (cells[0][col] == opponent) {
//                    System.out.print("X is the winner");
//                    return -10;
//                }
//                break;
//            }
//        }
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//
//                // Checking for Diagonals for X or O victory.
//                if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) {
//                    if (cells[0][0] == player) {
//                        System.out.print("X is the winner");
//                        return +10;
//                    } else if (cells[0][0] == opponent) {
//                        System.out.print("O is the winner");
//                        return -10;
//                    }
//
//                    break;
//                }
//
//
//                if (cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
//                    if (cells[0][2] == player) {
//                        System.out.print("X is the winner");
//                        return +10;
//                    } else if (cells[0][2] == opponent) {
//                        System.out.print("O is the winner");
//                        return -10;
//                    }
//                }
//
//                break;
//
//
//            }
//        }
//        // Else if none of them have won then return 0
//        return 0;
//    }


    public char getAiType(char playerType) {
        char aiType;
        if (playerType == 'X') {
            aiType = 'O';
        } else {
            aiType = 'O';
        }
        return aiType;
    }

    public void clearBoard() {
        //to do
    }

    public void win() {
//        int winner = 0;
//        String playerType = "";
//
//        if ((winner == evaluate(String.valueOf(+10))) && playerType=='X') {
//            System.out.print("X has won");
//
//        } else if (winner == evaluate(String.valueOf(-10)) && playerType.toString().equals("O")) {
//            System.out.print("O has won");
//        } else {
//            System.out.print("Draw!");
//        }
    }

    @Override
    public void fillInWithFree() {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                tiles[r][c] = 'F';

            }

        }
    }

    @Override
    public void fillInCells(int x, char playerType) {
        int index = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (index == x) {
                    tiles[r][c] = playerType;
                }
                index++;
            }
        }
    }

    @Override
    public boolean checkIfValidMove(int x, char playe) {
        boolean free = false;
        int index = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (index == x) {
                    if (tiles[r][c] == 'F')
                        free = true;
                }
                index++;
            }

        }
        return free;
    }

    @Override
    public Score getScore() {


        return null;
    }


    public WinMove getScorex(char playerType) {
        int counter = 0;
        WinMove winMove = new WinMove();
        winMove.setPlayer(playerType);

        // left to right scan
        int index=0;
        for (int i = 0; i < 3; i++) {
            counter = 0;
            winMove.getWinMoves().clear();
            for (int j = 0; j < 3; j++) {
                index=0;
                if (tiles[i][j] == playerType) {
                    winMove.getWinMoves().add(new Move(i, j));
                    counter++;
                    System.out.println(winMove.getWinMoves());
                    System.out.println("test "+ i+" i  "+j+" J" + "left to right scan");
                    if (counter == 3) {
                        winMove.setWin(true);
                        return winMove;

                    }


                }
            }
        }


        //   up to down scan

        for (int i = 0; i < 3; i++) {
            counter = 0;
            winMove.getWinMoves().clear();

            for (int j = 0; j < 3; j++) {
                if (tiles[j][i] == playerType) {
                    winMove.getWinMoves().add(new Move(j, i));
                    counter++;
                    if (counter == 3) {
                        System.out.println(winMove.getWinMoves());
                        System.out.println("test "+ i+" i  "+j+" J" + "up to down scan");


                        winMove.setWin(true);
                        return winMove;

                    }

                }

            }
        }

        //     up right to down left scan
        int j = 2;
        counter = 0;
        winMove.getWinMoves().clear();

        for (int i = 0; i < 3; i++) {
            if (tiles[i][j] == playerType) {
                winMove.getWinMoves().add(new Move(i, j));

                counter++;
                if (counter == 3) {
                    System.out.println(winMove.getWinMoves());
                    System.out.println("test "+ i+" i  "+j+" J" + "up right to down left scan");
                    winMove.setWin(true);
                    return winMove;

                }


            }
            j--;
        }
        //top left to right down
        winMove.getWinMoves().clear();
        counter = 0;
        for (int i = 0; i < 3; i++) {

            if (tiles[i][i] == playerType) {
                winMove.getWinMoves().add(new Move(i, i));

                counter++;
                System.out.println(winMove.getWinMoves());
                System.out.println("test "+ i+" i  "+i+" I" + "top left to right down");

                if (counter == 3) {
                    winMove.setWin(true);
                    return winMove;

                }


            }
        }

        return winMove;
    }

    public char[][] getBaord() {
        return this.tiles;
    }
}
