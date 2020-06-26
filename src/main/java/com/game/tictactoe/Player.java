package com.game.tictactoe;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class Player {
    private String name;
    private char symbol;
    private ArrayList<Integer> moves;
    private ArrayList<ArrayList<Integer>> winningCombinations;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.moves = new ArrayList<>();
        this.winningCombinations = createWinningCombinations();
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public ArrayList<Integer> getMoves() {
        return moves;
    }

    private ArrayList<ArrayList<Integer>> createWinningCombinations() {
        ArrayList<ArrayList<Integer>> winningCombinations = new ArrayList<>();
        winningCombinations.add(new ArrayList<>(asList(1, 2, 3)));
        winningCombinations.add(new ArrayList<>(asList(4, 5, 6)));
        winningCombinations.add(new ArrayList<>(asList(7, 8, 9)));
        winningCombinations.add(new ArrayList<>(asList(1, 4, 7)));
        winningCombinations.add(new ArrayList<>(asList(2, 5, 8)));
        winningCombinations.add(new ArrayList<>(asList(3, 6, 9)));
        winningCombinations.add(new ArrayList<>(asList(1, 5, 9)));
        winningCombinations.add(new ArrayList<>(asList(3, 5, 7)));
        return winningCombinations;
    }

    public void makeMove(int move) {
        if (!moves.contains(move)) {
            moves.add(move);
        }
    }

    public boolean hasWon() {
        for (ArrayList<Integer> combination : winningCombinations) {
            if (moves.containsAll(combination)) {
                return true;
            }
        }
        return false;
    }
}