package ai;

import model.tic.TicTacGame;
import model.tic.WinMove;

import java.util.LinkedList;
import java.util.Random;

public class TicLogic implements Ai {
    public TicTacGame state;
    private char playerType;
    private char aiType;

    boolean areWeFirst = false;
    int moveCount = 0;

    int lastMovePlayer;
    int lastMoveAi;


    public TicLogic(char playerType) {
        state = new TicTacGame();
        this.playerType = playerType;
        this.aiType = getAiType(this.playerType);
        System.out.println("Player  " + playerType);
        System.out.println("Ai    " + aiType);
        System.out.println("Start...");
    }

//    public void move(int x) {
//        if (state.isFree(x)) {
//           state.fillInCells(x, playerType.toString());
//
//       }
////        CheckWin();
//    }

    @Override
    public boolean move(int index) {
        boolean ismoved = false;
        if (isValidMove(index)) {
            ismoved = true;
            state.fillInCells(index, playerType);
            System.out.println(state.getScorex(playerType));


        } else if (!state.isMovesLeft()) {
            System.out.println("The board is full");

        }
        return ismoved;
    }

    @Override
    public int moveAI() {
        int move = getRandomMove();
        if (isValidMove(move)) {
            state.fillInCells(move, aiType);
            System.out.println(state.getScorex(aiType) + "  +++++++++++++++++++++++++++++++++++++++++++"+state.getScorex(aiType).getWinMoves().get(0));

        } else if (!state.isMovesLeft()) {
            System.out.println("The board is full");

        }

        return move;
    }

    //    public int GetRandomIndex(int posA, int posB) {
//        int randomNr = (int) Math.floor((Math.random() * 2) + 1);
//        System.out.println(randomNr);
//        if (randomNr == 1)
//            return posA;
//        else
//            return posB;
//    }
    public int getRandomMove() {
        int randomMove = 0;
        LinkedList<Integer> n = new LinkedList<Integer>();

        int index = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (state.checkIfValidMove(index, aiType))
                    n.add(index);
                index++;
            }
        }
        Random r = new Random();
        if (!n.isEmpty())
            randomMove = n.get(r.nextInt(n.size() - 1));

        return randomMove;
    }

    public WinMove checkWin(char playerType) {
        return state.getScorex(playerType);
    }

    // public int GetLastMovePlayer() {
//        return lastMovePlayer;
//    }

//    public void CheckWin() {
//        JOptionPane optionPane = null;
//
//        if (state.evaluate(playerType.toString()) == 10) {
//            System.out.println(playerType.toString() + " has won!");
//            optionPane = new JOptionPane(playerType.toString() + " wins!");
//        }
//        if (state.evaluate(aiType.toString()) == 10) {
//            System.out.println(aiType.toString() + " has won!");
//            optionPane = new JOptionPane(aiType.toString() + " wins!");
//        }
//        if (state.checkFinish() >= 8) {
//            optionPane = new JOptionPane("DRAW!");
//        }
//        if (optionPane != null) {
//            JDialog myDialog = optionPane.createDialog(null, "GAME OVER");
//            myDialog.setModal(false);
//            myDialog.setVisible(true);
//            state.FillInWithFree();
//        }
//    }

    //Return the best move

//    public int GetNextMove() {
//        int nextMove = 0;
//        CheckWin();
//
//        //start in een corner 0 2 6 8
//        //Eerste zet
//        if (moveCount == 0) {
//            System.out.println("Eerste beurt");
//            if (GetLastMovePlayer() > 4)
//                nextMove = GetRandomIndex(0, 2);
//            else if (GetLastMovePlayer() < 4)
//                nextMove = GetRandomIndex(6, 8);
//            else {
//                nextMove = GetRandomIndex(2, 8);
//            }
//            moveCount++;
//            if (isValidMove(nextMove))
//                return nextMove;
//        }
//
//
//        moveCount++;
//        nextMove = state.getTheFirstFreeIndex();
//        if (isValidMove(nextMove)) {
//            System.out.println(nextMove + " = " + isValidMove(nextMove) + ", IS VALID! (Ai)");
//            state.fillInCells(nextMove, aiType.toString());
//            return nextMove;
//        } else
//            System.out.println(nextMove + " = " + isValidMove(nextMove) + ", Not a valid move (Ai)");
//        return state.getTheFirstFreeIndex2();
//
//    }

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

}

