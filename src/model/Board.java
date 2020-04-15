package model;

import model.Reversi.Score;

public interface Board {
    public void fillInWithFree();
    public void fillInCells(int x, char playerType);
    public boolean checkIfValidMove(int index, char playe);
    public Score getScore();
}
