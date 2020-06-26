package com.game.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OutputHandler outputHandler = new OutputHandler();
        InputHandler inputHandler = new InputHandler(new InputScanner(new Scanner(System.in)));

        char[] gameMoves = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        Grid grid = new Grid(gameMoves);
        outputHandler.print(grid.printGrid());

        outputHandler.print("Enter player1's name and symbol:");
        Player player1 = inputHandler.getPlayer(PlayerType.HUMAN);
        Player player2 = new Player("Computer", 'O', PlayerType.COMPUTER);
        Game game = new Game(grid, player1, player2);
        game.startGame(inputHandler, outputHandler);
    }
}