package com.game.tictactoe;

import com.game.tictactoe.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

class GridTest {
    private Grid grid;

    @BeforeEach
    public void init() {
        grid = new Grid(new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '});
    }

    @Test
    void shouldGetGameMoves() {
        char[] moves = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        assertArrayEquals(moves, grid.getGameMoves());
    }

    @Test
    void shouldPrintGrid() {
        String gridAsString = "| | | |\n| | | |\n| | | |";
        assertEquals(gridAsString, grid.printGrid());
    }
}