package model.rev;

public class Reversie {
        int boardSize=8;
        int[][] cells = new int[boardSize][boardSize];

        public void fillInCells(int x) {
            int index = 0;
            for (int r = 0; r < boardSize; r++) {
                for (int c = 0; c < boardSize; c++) {
                    index++;
                    if (index == x) {
                        cells[r][c] = 1;
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
