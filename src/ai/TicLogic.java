package ai;

import model.tic.PlayerType;
import model.tic.TicTacGame;

import java.util.Random;

public class TicLogic {
   private TicTacGame state ;
   private PlayerType playerType;
   private PlayerType aiType;



    public TicLogic(PlayerType playerType){
        state=new TicTacGame();
        this.playerType=playerType;
        this.aiType=getAiType(this.playerType);
    }
    public void move( int x){
        System.out.println("Player  "+playerType.toString());
        System.out.println("Ai    "+aiType.toString());

        if(state.isFree(x)){
        state.fillInCells(x,playerType.toString());
        }



    }
    //Return the best move
    public  int GetNextMove(){
        int nextMove=state.getTheFistFreeIndex();
        state.fillInCells(nextMove,aiType.toString());
        return nextMove;
    }
    public boolean isValidMove(int x){

        return state.isFree(x);
    }

    public PlayerType getAiType(PlayerType playerType) {
        PlayerType aiType;
        if (playerType.toString().equals("X")) {
            aiType=PlayerType.O;
        }else {
            aiType=PlayerType.X;


        }
        return aiType;
    }

}
