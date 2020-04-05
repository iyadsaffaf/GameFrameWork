package model.tic;


public class TicTacGame {

    int boardSize=3;
    int[][] cells = new int[boardSize][boardSize];
    // 1=x
    //2=o
    //0 free

    public void fillInCells(int x) {
        int index= 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
              index++;
              if(index==x){
                  cells[r][c]=1;

              }

            }

        }


    }


public boolean isFree(int x){
        boolean free= true;
    int index= 0;
    for (int r = 0; r < boardSize; r++) {
        for (int c = 0; c < boardSize; c++) {
            index++;
            if(index==x){
                if(cells[r][c]!=0)
                free=false;

            }

        }

    }
        return free;
}


}
