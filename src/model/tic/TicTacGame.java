package model.tic;


public class TicTacGame {

    int boardSize=3;

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
    public TicTacGame(){
        cells = new String[boardSize][boardSize];
        FillInWithFree();

    }

    public void fillInCells(int x, String playerType) {
        int index= 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
              if(index==x){
                  cells[r][c]=playerType;

              }
                index++;


            }

        }


    }


public boolean isFree(int x){
        boolean free= false;
    int index= 0;
    for (int r = 0; r < boardSize; r++) {
        for (int c = 0; c < boardSize; c++) {
            if(index==x){
                if(cells[r][c].equals("FREE"))
                free=true;

            }
            index++;


        }

    }
        return free;
}

public boolean isMovesLeft(){
    boolean free= false;
    int index= 0;
    for (int r = 0; r < boardSize; r++) {
        for (int c = 0; c < boardSize; c++) {

                if(cells[r][c].equals("FREE"))
                    free=true;


            index++;


        }

    }
    return free;
}

public void FillInWithFree(){

    for (int r = 0; r < boardSize; r++) {
        for (int c = 0; c < boardSize; c++) {

                cells[r][c]="FREE";

        }

    }
}
    public int getTheFistFreeIndex(){
        int index= 0;


        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                if(cells[r][c].equals("FREE")){

                    break;

                }
                if (index>=8)
                    break;
                index++;


            }

        }
        return index;
    }

    public int evaluate(String player)
    {
        String opponent =getAiType(player);
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++)
        {
            if ( cells[row][0] == cells[row][1] &&
                    cells[row][1] == cells[row][2])
            {
                if (cells[row][0] == player)
                    return +10;
                else if (cells[row][0] == opponent)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if (cells[0][col] == cells[1][col] &&
                    cells[1][col] == cells[2][col])
            {
                if (cells[0][col] == player)
                    return +10;

                else if (cells[0][col] == opponent)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2])
        {
            if (cells[0][0] == player)
                return +10;
            else if (cells[0][0] == opponent)
                return -10;
        }

        if (cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0])
        {
            if (cells[0][2] == player)
                return +10;
            else if (cells[0][2] == opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }
    public String getAiType(String  playerType) {
        String aiType;
        if (playerType.toString().equals("X")) {
            aiType="O";
        }else {
            aiType="X";


        }
        return aiType;
    }
}
