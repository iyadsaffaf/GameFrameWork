package ai;

import model.tic.TicTacGame;
import model.tic.WinMove;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class TicLogic implements Ai {
    public TicTacGame state;
    private char playerType;
    private char aiType;
    private String difficulty;

    boolean areWeFirst = false;
    int moveCount = 0;

    int lastMovePlayer;
    int lastMoveAi;


    public TicLogic(char playerType,String difficulty) {
        state = new TicTacGame();
        this.playerType = playerType;
        this.aiType = getAiType(this.playerType);
        this.difficulty = difficulty;
        System.out.println("Player  " + playerType);
        System.out.println("Ai    " + aiType);
        System.out.println("Start...");
    }

    @Override
    public boolean move(int index) {
        boolean ismoved = false;
        if (isValidMove(index)) {
            ismoved = true;
            state.fillInCells(index, playerType);
            //  System.out.println(state.getScorex(playerType));


        } else if (!state.isMovesLeft()) {
            System.out.println("The board is full");

        }
        return ismoved;
    }

    @Override
    public int moveAI() {
        int move;
        if ("Beginner".equals(difficulty)) {
            move = getRandomMove();
        }
        else{
            move = getRandomMove();
        }
        if (isValidMove(move)) {
            state.fillInCells(move, aiType);
            //  System.out.println(state.getScorex(aiType) + "  +++++++++++++++++++++++++++++++++++++++++++"+state.getScorex(aiType).getWinMoves().get(0));

        } else if (!state.isMovesLeft()) {
            System.out.println("The board is full");
            CheckWin();
        }

        return move;
    }

    public int getRandomIndex(int posA, int posB) {
        int randomNr = (int) Math.floor((Math.random() * 2) + 1);
        System.out.println(randomNr);
        if (randomNr == 1)
            return posA;
        else
            return posB;
    }

    public int getRandomMove() {
        int randomMove = 0;
        LinkedList<Integer> validMoves = new LinkedList<>();
        Random r = new Random();

        int index = -1;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                index++;
                if (state.checkIfValidMove(index, aiType)&&index <9) {
                    validMoves.add(index);
                }
            }
        }

        if (!validMoves.isEmpty()) {
            randomMove = validMoves.get(r.nextInt(validMoves.size()));
            return randomMove;
        }
        else{
            System.out.println("There is no valid Move");
        }


        return 9;
    }

    public WinMove checkWin(char playerType) {
        return state.getScorex(playerType);
    }

    public int GetLastMovePlayer() {
        return lastMovePlayer;
    }
    public void CheckWin() {
        JOptionPane optionPane = null;
        optionPane = new JOptionPane("DRAW!");
        if (optionPane != null) {
            JDialog myDialog = optionPane.createDialog(null, "GAME OVER");
            myDialog.setModal(false);
            myDialog.setVisible(true);
        }
    }

    //Return the best move
    public int GetNextMove() {
        int nextMove = 0;
        //start in een corner 0 2 6 8
        //Eerste zet
        if (moveCount == 0) {
            if (GetLastMovePlayer() > 4)
                nextMove = getRandomIndex(0, 2);
            else if (GetLastMovePlayer() < 4)
                nextMove = getRandomIndex(6, 8);
            else {
                nextMove = getRandomIndex(2, 8);
            }
            moveCount++;
            if (isValidMove(nextMove))
                return nextMove;
        }
        moveCount++;

        //CHECK ERGENS 2 OP EEN RIJ


        WinMove winMove = new WinMove();
        winMove.setPlayer('X');

        nextMove = state.getTheFirstFreeIndex();
        if (isValidMove(nextMove)) {
            System.out.println(nextMove + " = " + isValidMove(nextMove) + ", IS VALID! (Ai)");
            state.fillInCells(nextMove, aiType);
            return nextMove;
        } else
            System.out.println(nextMove + " = " + isValidMove(nextMove) + ", Not a valid move (Ai)");
        return getRandomMove();

    }

    public boolean isValidMove(int x) {
        return state.checkIfValidMove(x, playerType);
    }


    public char getAiType(char playerType) {
        char aiType;
        if (playerType == 'X') {
            aiType = 'O';
        } else {
            aiType = 'X';


        }
        return aiType;
    }

    public char[][] getBoard() {
        return state.getBaord();
    }
    public void setPlayerType(char playerType) {
        this.playerType = playerType;
    }
    public void setAiType(char aiType) {
        this.aiType = aiType;
    }

}

