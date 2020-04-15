package model;

public class Move {
    public int x;
    public int y;
    private int boardSize;

    public Move(int index) {
        boardSize = 8;
        findTheMove(index);

    }

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void findTheMove(int x) {
        int index = 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (index == x) {
                    this.x = c;
                    this.y = r;

                }
                index++;
            }
        }}
    public int findTheIndex ( int x,int y){
        int index = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (x== r &&y==c) {
                   return index;
                }
                index++;
            }
        }
        return index;
    }


        @Override
        public String toString () {
            return "Move{" +
                    "x=" + x +
                    ", y=" + y +
                    ", boardSize=" + boardSize +
                    '}';
        }
    }
