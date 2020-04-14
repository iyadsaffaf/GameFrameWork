package ai;

import model.tic.PlayerType;
import model.tic.TicTacGame;

import javax.swing.*;

public class TicLogic {
    public  TicTacGame state;
    private PlayerType playerType;
    private PlayerType aiType;

    boolean areWeFirst = false;
    int moveCount = 0;

    int lastMovePlayer;
    int lastMoveAi;


    public TicLogic(PlayerType playerType) {
        state = new TicTacGame();
        this.playerType = playerType;
        this.aiType = getAiType(this.playerType);
        System.out.println("Player  " + playerType.toString());
        System.out.println("Ai    " + aiType.toString());
        System.out.println("Start...");
    }

    public void move(int x) {
        if (state.isFree(x)) {
            state.fillInCells(x, playerType.toString());
            lastMovePlayer = x;
        }
        CheckWin();
    }

    public int GetRandomIndex(int posA, int posB) {
        int randomNr = (int) Math.floor((Math.random() * 2) + 1);
        System.out.println(randomNr);
        if (randomNr == 1)
            return posA;
        else
            return posB;
    }

    public int GetLastMovePlayer() {
        return lastMovePlayer;
    }

    public void CheckWin()
    {
        JOptionPane optionPane;

        if(state.evaluate(playerType.toString()) == 10 )
        {
            System.out.println(playerType.toString() + " has won!");

                optionPane = new JOptionPane(playerType.toString() + " wins!");
            JDialog myDialog = optionPane.createDialog(null, "GAME OVER");
            myDialog.setModal(false);
            myDialog.setVisible(true);
            state.FillInWithFree();
        }
        if(state.evaluate(aiType.toString()) == 10)
        {
            System.out.println(aiType.toString() + " has won!");

            optionPane = new JOptionPane(aiType.toString() + " wins!");
            JDialog myDialog = optionPane.createDialog(null, "GAME OVER");
            myDialog.setModal(false);
            myDialog.setVisible(true);
            state.FillInWithFree();
        }
        if(state.checkFinish() >= 8) {
            optionPane = new JOptionPane("DRAW!");
            JDialog myDialog = optionPane.createDialog(null, "GAME OVER");
            myDialog.setModal(false);
            myDialog.setVisible(true);
        }
    }

    //Return the best move
    public int GetNextMove() {
        int nextMove = 0;

        //Can we win?

        //Do we need to defend?


        //start in een corner 0 2 6 8
        //Eerste zet
        if (moveCount == 0) {
            System.out.println("Eerste beurt");
            if (GetLastMovePlayer() > 4)
                nextMove = GetRandomIndex(0, 2);
            else if (GetLastMovePlayer() < 4)
                nextMove = GetRandomIndex(6, 8);
            else {
                nextMove = GetRandomIndex(2, 8);
            }
            moveCount++;
            if (isValidMove(nextMove))
                return nextMove;
        }

        //als hij middel heeft pak de tegenovergestelde hoek

        moveCount++;
        nextMove = state.getTheFirstFreeIndex();
        if (isValidMove(nextMove)) {
            System.out.println(nextMove + " = " + isValidMove(nextMove) + ", IS VALID! (Ai)");
            state.fillInCells(nextMove, aiType.toString());
            return nextMove;
        } else
            System.out.println(nextMove + " = " + isValidMove(nextMove) + ", Not a valid move (Ai)");
        return state.getTheFirstFreeIndex2();

    }

    public boolean isValidMove(int x) {
        return state.isFree(x);
    }


    public PlayerType getAiType(PlayerType playerType) {
        PlayerType aiType;
        if (playerType.toString().equals("X")) {
            aiType = PlayerType.O;
        } else {
            aiType = PlayerType.X;


        }
        return aiType;
    }

    public PlayerType getAiType() {
        return aiType;
    }

    public void setAiType(PlayerType aiType) {
        this.aiType = aiType;
    }


    static class Move {
        int row, col;
    }
}

