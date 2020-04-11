package model;

public class Move {
    public int x;
    public int y;
    private int boardSize;
    public Move(int index){
        boardSize=8;
        findTheMove(index);

    }

    public void findTheMove(int x) {
        int index= 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if(index==x){
                    this.x=c;
                    this.y=r;

                }
                index++;
            }
        }
    }
}
