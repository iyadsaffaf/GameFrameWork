package ai;

import model.tic.PlayerType;
import model.tic.TicTacGame;

import java.util.Random;

public class TicLogic {
   private TicTacGame state ;
   private PlayerType playerType;



    public TicLogic(){
        state=new TicTacGame();
    }
    public void move( int x){
        if(state.isFree(x)){
        state.fillInCells(x);
        }



    }
    //Return the best move
    public  int GetNextMove(){
        int nextmovet=0;
        Random r = new Random();
        int x = r.nextInt(8);
        return nextmovet;
    }
    public boolean isVailedMove(int x){

        return state.isFree(x);
    }

}
