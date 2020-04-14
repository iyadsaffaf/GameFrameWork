package ai;

import java.util.Random;
import model.Reversi.ReversiBoard;
import model.Reversi.Score;
import java.util.ArrayList;
import java.util.List;


public class ReversiLogic implements Ai {
    private ReversiBoard board;
    private char playerType;
    private char aiType;
    private String difficulty;

    public ReversiLogic(char b, String difficulty) {
        this.board = new ReversiBoard();
        this.playerType = b;
        this.aiType = getAiType(b);
        this.difficulty = difficulty;
    }

    public void test() {

    }


    //myTurn
    public boolean move(int index) {

        boolean valid = false;
        System.out.println("The AI is " + aiType + " and the player is  " + playerType);
        // System.out.println(board.checkIfValidMove(index,'W'));
        //index=GetRandomMove(playerType);
        if (board.checkIfValidMove(index, playerType)) {


            board.fillInCells(index, playerType);

            board.flipAfterMove(index, playerType);
            valid = true;

        } else if (board.isMoveLeft() && board.isValid(aiType)) {
            // Give true so tha ai can start
            valid = true;


        } else if (!board.isMoveLeft()) {
            Score s = board.GetScore();
            System.out.println("score black " + s.black + "Score" + s.white);
        }
        board.CheckWin();

        return valid;

    }

    public char getAiType(char playerType) {
        char aiType = 'B';
        if (playerType == 'B') {
            aiType = 'W';
        } else if (playerType == 'W') {
            aiType = 'B';


        }
        return aiType;
    }

    //Ai Turn
    public int moveAI() {
        int aiMove;
        System.out.println("The AI is " + aiType + " and the player is " + playerType);
        if ("Advanced".equals(difficulty)) {
            aiMove = getTheBestMove(board);
        } else {
            aiMove = GetRandomMove(aiType);
            System.out.println(aiMove + " Random mode ");

        }

        System.out.println(aiMove + " Ai best Move");
        //    tiles.get(aiMove).setColourToThisPlayer(aiType);
        board.fillInCells(aiMove, aiType);
        board.flipAfterMove(aiMove, aiType);
        //   board.isValid(playerType);


        PrintBoard();
        return aiMove;

    }

    //Random Algorithm
    public int GetRandomMove(char playerType) {
        List<Integer> validMoves = new ArrayList<>();
        Random random = new Random();
        int index = -1;
        for (int i = 0; i < 8; i++) {
            for (int c = 0; c < 8; c++) {
                index++;


                if (board.checkIfValidMove(index, playerType) && index < 64) {
                    validMoves.add(index);
                    //return index;
                }
            }
        }
        if (validMoves.size() != 0) {
            int randomMove = random.nextInt(validMoves.size());
            return validMoves.get(randomMove);
        }else{
            System.out.println("there are no moves for: "+aiType);
        }
        return 63;
    }

    //miniMAX Algorithm
//    public int GetBestMove() {
//        int indexOfTheBes
//        System.out.println("NIKS");
//
//        return 4;
//    }

    //minimax
    public int minimax(ReversiBoard board, int depth, boolean isMaximizer, char maximizerType) {
        System.out.println(depth);
        if (depth == 0 || !board.isMoveLeft()) {

            return board.CountTiles(maximizerType);
        }

        int index = 0;

        // get the Result for the maximizer

        if (isMaximizer) {
            int best = -1000;
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (board.checkIfValidMove(index, aiType)) {
                        board.getBoard()[x][y] = playerType;
                        best = Math.max(best, minimax(board, depth-1,false, aiType));
                        board.getBoard()[x][y] = 'F';

                    }
                    index++;


                }
            }
          return best;
        } else {
            int best = 1000;

            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (board.checkIfValidMove(index, aiType)) {
                        board.getBoard()[x][y] = aiType;
                        best = Math.min(best, minimax(board, depth-1,true, aiType));
                        board.getBoard()[x][y] = 'F';

                    }
                    index++;


                }
            }

            return best;
        }

    }

    public int getTheBestMove(ReversiBoard board) {
        int bestMove = -55555;
       int index=-1;
       int indexOfTheBestMove=0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                index++;
                if (board.checkIfValidMove(index, aiType)) {

                    board.getBoard()[x][y] = aiType;
                    int moveV = minimax(board, 4, false,aiType);
                    System.out.println(moveV);
                    board.getBoard()[x][y] = 'F';
                    if (moveV > bestMove) {

                        bestMove = moveV;
                        indexOfTheBestMove=index;
                    }
                }


            }
        }

        return indexOfTheBestMove;

    }


    public void PrintBoard() {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.print("   " + board.getBoard()[x][y] + "  ");
            }

            System.out.println();
        }
    }

    public char[][] getBoard() {

        return board.getBoard();
    }

    public char getPlayerType() {
        return playerType;
    }

    public void setPlayerType(char playerType) {
        this.playerType = playerType;
    }

    public char getAiType() {
        return aiType;
    }

    public void setAiType(char aiType) {
        this.aiType = aiType;
    }

    public boolean higlight(int index, char aiType) {

        return board.checkIfValidMove(index, aiType);

    }
}
