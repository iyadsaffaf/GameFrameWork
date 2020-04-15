package model.tic;

import model.Move;

import java.util.LinkedList;

public class WinMove {
    private LinkedList<Move> winMoves = new LinkedList<>();
    private boolean isWin;
    private char player;
    private int index;

    public LinkedList<Move> getWinMoves() {
        return winMoves;
    }

    public void setWinMoves(LinkedList<Move> winMove) {
        this.winMoves = winMove;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "WinMove{" +
                "winMoves=" + winMoves +
                ", isWin=" + isWin +
                ", player=" + player +
                '}';
    }
}
