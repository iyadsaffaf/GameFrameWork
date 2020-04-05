package model.tic;


public class TicTacGame {

    int boardSize=3;
    String[][] cells;
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
}
