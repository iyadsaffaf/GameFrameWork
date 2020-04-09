package ai;

import model.tic.PlayerType;
import model.tic.TicTacGame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
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
List list = new ArrayList();
list.add("Ffd");
list.add(2);
        Move m= findBestMove(state.getCells());
        System.out.println(" vf");
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

    public PlayerType getAiType() {
        return aiType;
    }

    public void setAiType(PlayerType aiType) {
        this.aiType = aiType;
    }


    static class Move
    {
        int row, col;
    };

    public int minimax(String board[][],
                       int depth, Boolean isMax)
    {
        int score = state.evaluate(playerType.toString());

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (!state.isMovesLeft())
            return 0;

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j].equals("FREE"))
                    {
                        // Make the move
                        board[i][j] = aiType.toString();

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = "FREE";
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] .equals( "FREE"))
                    {
                        // Make the move
                        board[i][j] = playerType.toString();

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = "FREE";
                    }
                }
            }
            return best;
        }
    }
   public  Move findBestMove(String[][] board)
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (board[i][j].equals("FREE"))
                {
                    // Make the move
                    board[i][j] = aiType.toString();

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = "FREE";

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);

        return bestMove;
    }

}

