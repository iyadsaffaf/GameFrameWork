package ai;
import java.util.List;
import model.reversi.ReversiGame;
import model.reversi.PlayerColour;
import model.reversi.ReversiTile;

import java.util.ArrayList;
import java.util.Arrays;

public class ReversiLogic {
    private ReversiGame state ;
    private PlayerColour playerColour;
    private PlayerColour aiType;



    public ReversiLogic(PlayerColour playerColour){
        state=new ReversiGame();
        this.playerColour =playerColour;
        this.aiType=getAiType(this.playerColour);
    }
    public void move( int x){
        System.out.println("Player  "+ playerColour.toString());
        System.out.println("Ai    "+aiType.toString());

        if(state.getOwner(x) == "FREE"){
            state.fillInCells(x, playerColour.toString());
        }



    }
    //Return the best move
    public  int GetNextMove(){
        int nextMove=state.getTheFistFreeIndex();
        state.fillInCells(nextMove,aiType.toString());
        return nextMove;
    }
    public boolean isValidMove(int x) {
        List<Integer> uitzonderingl = Arrays.asList(0, 8, 16, 24, 32, 40, 48, 56);
        List<Integer> uitzonderingr = Arrays.asList(7, 15, 23, 31, 39, 47, 55, 63);
        List<Integer> uitzonderingb = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
        List<Integer> uitzonderingo = Arrays.asList(56, 57, 58, 59, 60, 61, 62, 63);
        List<Integer> opponentlocation = Arrays.asList();
        Boolean valid = false;
        Boolean opponentCheck = false;
        System.out.println(state.getOwner(x));
        if (state.getOwner(x) == "FREE") {
            System.out.println("test");
            if (uitzonderingl.contains(x)) {
                if (uitzonderingb.contains(x)) {
                    if (state.getOwner(x + 1) == "White" || state.getOwner(x + 8) == "White" || state.getOwner(x + 9) == "White") {
                        opponentCheck = true;
                    }
                } else if (uitzonderingo.contains(x)) {
                    if (state.getOwner(x + 1) == "White" || state.getOwner(x - 8) == "White" || state.getOwner(x - 7) == "White") {
                        opponentCheck = true;
                    }
                } else if (state.getOwner(x + 1) == "White" || state.getOwner(x + 8) == "White" || state.getOwner(x + 9) == "White" || state.getOwner(x - 8) == "White" || state.getOwner(x - 7) == "White") {
                    opponentCheck = true;
                }
            } else if (uitzonderingr.contains(x)) {
                if (uitzonderingb.contains(x)) {
                    if (state.getOwner(x - 1) == "White" || state.getOwner(x + 7) == "White" || state.getOwner(x + 8) == "White") {
                        opponentCheck = true;
                    }
                } else if (uitzonderingo.contains(x)) {
                    if (state.getOwner(x - 1) == "White" || state.getOwner(x - 8) == "White" || state.getOwner(x - 9) == "White") {
                        opponentCheck = true;
                    }
                } else if (state.getOwner(x - 1) == "White" || state.getOwner(x - 8) == "White" || state.getOwner(x - 9) == "White" || state.getOwner(x + 8) == "White" || state.getOwner(x + 7) == "White") {
                    opponentCheck = true;
                }
            } else if (uitzonderingb.contains(x)) {
                if (state.getOwner(x + 1) == "White" || state.getOwner(x - 1) == "White" || state.getOwner(x + 7) == "White" || state.getOwner(x + 8) == "White" || state.getOwner(x + 9) == "White") {
                    opponentCheck = true;
                }
            } else if (uitzonderingo.contains(x)) {
                if (state.getOwner(x + 1) == "White" || state.getOwner(x - 1) == "White" || state.getOwner(x - 9) == "White" || state.getOwner(x - 8) == "White" || state.getOwner(x - 7) == "White") {
                    opponentCheck = true;
                }
            } else if (uitzonderingb.contains(x) == false && uitzonderingl.contains(x) == false && uitzonderingo.contains(x) == false == uitzonderingr.contains(x) == false) {
                if (state.getOwner(x + 1) == "White" || state.getOwner(x - 1) == "White" || state.getOwner(x + 7) == "White" || state.getOwner(x - 7) == "White" || state.getOwner(x + 8) == "White" || state.getOwner(x-8) == "White"|| state.getOwner(x +9) =="White" || state.getOwner(x-9) =="White") {
                    opponentCheck = true;
                }
            }
            if(opponentCheck == true){
                valid = true; //tijdelijk voor test
            }
        }
        return valid;
    }

    public PlayerColour getAiType(PlayerColour playerColour) {

        PlayerColour aiType;
        if (playerColour.toString().equals("Black")) {
            aiType=PlayerColour.Black;
        }else {
            aiType=PlayerColour.White;


        }
        return aiType;
    }

    public PlayerColour getAiType() {
        return aiType;
    }

    public void setAiType(PlayerColour aiType) {
        this.aiType = aiType;
    }
}
