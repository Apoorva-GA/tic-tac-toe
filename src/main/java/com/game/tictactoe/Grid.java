package com.game.tictactoe;

public class Grid {
    private char[] gameMoves;

    public Grid(char[] gameMoves) {
        this.gameMoves = gameMoves;
    }

    public char[] getGameMoves() {
        return gameMoves;
    }

    public String printGrid() {
        StringBuilder s = new StringBuilder("|");
        int count = 0;
        for (char gameMove : gameMoves) {
            if (count >= 3) {
                s.append("\n|");
                count = 0;
            }
            s.append(gameMove).append("|");
            count++;

        }
        return s.toString();
    }
}
