package com.game.tictactoe;

import java.util.Random;

public class Game {
    private Player player1;
    private Player player2;
    private Grid grid;
    private Player currentPlayer;

    public Game(Grid grid, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.grid = grid;
        this.currentPlayer = player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean makeMove(int move) {
        if (grid.getGameMoves()[move - 1] == ' ') {
            currentPlayer.makeMove(move);
            grid.getGameMoves()[move - 1] = currentPlayer.getSymbol();
            return true;
        }
        return false;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public void startGame(InputHandler inputHandler, OutputHandler outputHandler) {
        int count = grid.getGameMoves().length;
        while (count > 0) {
            Player currentPlayer = getCurrentPlayer();
            int move = getMove(inputHandler, outputHandler, currentPlayer);
            if (move != -1 && makeMove(move)) {
                outputHandler.print(grid.printGrid());
                if (currentPlayer.hasWon()) {
                    outputHandler.print(currentPlayer.getName() + " has won!!");
                    return;
                }
                switchPlayer();
                count--;
            } else {
                outputHandler.print("Invalid move, Enter a valid move.");
            }
        }
        outputHandler.print("Its a tie!!!");
    }

    private int getMove(InputHandler inputHandler, OutputHandler outputHandler, Player currentPlayer) {
        int move = 0;
        if (currentPlayer.getPlayerType() == PlayerType.HUMAN) {
            outputHandler.print(currentPlayer.getName() + "'s turn - Play move (1-9):");
            move = inputHandler.getMove();
        } else if (currentPlayer.getPlayerType() == PlayerType.COMPUTER) {
            outputHandler.print(currentPlayer.getName() + "'s turn");
            Random random = new Random();
            move = random.nextInt(9) + 1;
        }
        return move;
    }
}