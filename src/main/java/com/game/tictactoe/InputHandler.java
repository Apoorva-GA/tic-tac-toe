package com.game.tictactoe;

public class InputHandler {
    InputScanner inputScanner;

    public InputHandler(InputScanner inputScanner) {
        this.inputScanner = inputScanner;
    }

    public Player getPlayer(String player) {
        String playerName = inputScanner.readString();
        String playerSymbol = inputScanner.readString();
        if (playerSymbol.length() != 1) {
            if (player.equals("player1")) {
                System.out.println("Invalid symbol, assigning default symbol 'X'");
                return new Player(playerName, 'X');
            } else {
                System.out.println("Invalid symbol, assigning default symbol 'O'");
                return new Player(playerName, 'O');
            }
        }
        return new Player(playerName, playerSymbol.charAt(0));
    }

    public int getMove() {
        int move = 0;
        try {
            move = inputScanner.readInt();
        } catch (Exception e) {
            return -1;
        } finally {
            inputScanner.readLine();
        }
        return move > 0 && move <= 9 ? move : -1;
    }
}
