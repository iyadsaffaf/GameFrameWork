package model.reversi;


public class ReversiGame {

    int boardSize=8;
    String[][] cells;
    // 1=x
    //2=o
    //0 free
    public ReversiGame(){
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


    public String getOwner(int x){
        String owner= "";
        int index= 0;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if(index==x){
                    if(cells[r][c].equals("FREE"))
                        owner="FREE";
                    if(cells[r][c].equals("Black"))
                        owner="BLACK";
                    if(cells[r][c].equals("White"))
                        owner="WHITE";

                }
                index++;


            }

        }
        return owner;
    }

    public void FillInWithFree(){
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                cells[r][c] = "FREE";
            }
        }
        cells[3][3] = "White";
        cells [4][4] = "White";
        cells[3][4] = "Black";
        cells[4][3] = "Black";

    }
    public int getTheFistFreeIndex(){
        int index= 0;


        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {

                if(cells[r][c].equals("FREE")){

                    break;

                }
                if (index>=63)
                    break;
                index++;


            }

        }
        return index;
    }

}
