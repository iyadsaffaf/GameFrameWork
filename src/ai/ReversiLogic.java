package ai;

import model.Reversi.ReversiBoard;
import model.Reversi.TileReversi;

import java.util.LinkedList;

public class ReversiLogic {
    private LinkedList<TileReversi> tiles;
    private ReversiBoard Board;
    public ReversiLogic(LinkedList<TileReversi> tiles) {
        this.tiles=tiles;
        Board= new ReversiBoard(tiles);
    }
    public void test(){

        tiles.get(3).setColourToWhite();
    }
}
