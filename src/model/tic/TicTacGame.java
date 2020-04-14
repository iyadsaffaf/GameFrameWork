package model.tic;

public class TicTacGame {

    int boardSize = 3;

    public String[][] getCells() {
        return cells;
    }

    public void setCells(String[][] cells) {
        this.cells = cells;
    }

    private String[][] cells;

    // 1=x
    //2=o
    //0 free
    public TicTacGame() {
        cells = new String[boardSize][boardSize];
        FillInWithFree();

    }

    public void fillInCells(int x, String playerType) {
        int index = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (index == x) {
                    cells[r][c] = playerType;

                }
                index++;


            }

        }


    }


    public boolean isFree(int x) {
        boolean free = false;
        int index = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (index == x) {
                    if (cells[r][c].equals("FREE"))
                        free = true;

                }
                index++;


            }

        }
        return free;
    }

    public boolean isMovesLeft() {
        boolean free = false;
        int index = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                if (cells[r][c].equals("FREE"))
                    free = true;


                index++;


            }

        }
        return free;
    }

    public void FillInWithFree() {

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                cells[r][c] = "FREE";

            }

        }
    }

    public int getTheFirstFreeIndex() {
        int index = 0;

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                if (cells[r][c].equals("FREE")) {
                    //System.out.println("R AND C " + r + ":" + c + "so free are " + (((r *3)+c)));
                    return ((r *3)+c);
                }
                if (index >= 8)
                    break;
            }
        }
        return index;
    }

    public int evaluate(String player) {
        String opponent = getAiType(player);
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (cells[row][0] == cells[row][1] &&
                    cells[row][1] == cells[row][2]) {
                if (cells[row][0] == player) {
                    System.out.print("X is the winner");
                    return +10;
                } else if (cells[row][0] == opponent) {
                    System.out.print("O is the winner");
                    return -10;
                }

                break;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (cells[0][col] == cells[1][col] &&
                    cells[1][col] == cells[2][col]) {
                if (cells[0][col] == player) {
                    System.out.print("X is the winner");
                    return +10;

                } else if (cells[0][col] == opponent) {
                    System.out.print("X is the winner");
                    return -10;
                }
                break;
            }
        }
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                // Checking for Diagonals for X or O victory.
                if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) {
                    if (cells[0][0] == player) {
                        System.out.print("X is the winner");
                        return +10;
                    } else if (cells[0][0] == opponent) {
                        System.out.print("O is the winner");
                        return -10;
                    }

                    break;
                }


                if (cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
                    if (cells[0][2] == player) {
                        System.out.print("X is the winner");
                        return +10;
                    } else if (cells[0][2] == opponent) {
                        System.out.print("O is the winner");
                        return -10;
                    }
                }

                break;


            }
        }
        // Else if none of them have won then return 0
        return 0;
    }


    public String getAiType(String playerType) {
        String aiType;
        if (playerType.toString().equals("X")) {
            aiType = "O";
        } else {
            aiType = "X";
        }
        return aiType;
    }

    public void clearBoard() {
        //to do
    }

    public void win() {
        int winner = 0;
        String playerType = "";

        if ((winner == evaluate(String.valueOf(+10))) && playerType.toString().equals("X")) {
            System.out.print("X has won");

        } else if (winner == evaluate(String.valueOf(-10)) && playerType.toString().equals("O")) {
            System.out.print("O has won");
        } else {
            System.out.print("Draw!");


        }
    }


}
