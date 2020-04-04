package model.tic;

public enum PlayerType {
    NONE(' '),
    PLAYER_ONE('X'),
    PLAYER_TWO('O');

    public final char symbol;

    private PlayerType(char symbol) {
        this.symbol = symbol;
    }
}
