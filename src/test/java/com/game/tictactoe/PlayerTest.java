package com.game.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class PlayerTest {
    private Player player1;

    @BeforeEach
    public void init() {
        player1 = new Player("player1", 'x');
    }

    @Test
    public void shouldMakeMove() {
        player1.makeMove(1);
        player1.makeMove(3);

        assertEquals(Arrays.asList(1, 3), player1.getMoves());
    }

    @Test
    public void shouldErrorForIllegalMove() {
        player1.makeMove(1);
        player1.makeMove(1);

        assertEquals(Arrays.asList(1), player1.getMoves());
    }

    @Test
    public void shouldHaveWinningCombinations() {
        player1.makeMove(1);
        player1.makeMove(4);
        player1.makeMove(7);

        assertTrue(player1.hasWon());
    }

    @Test
    public void shouldNotHaveWinningCombinations() {
        player1.makeMove(1);
        player1.makeMove(2);
        player1.makeMove(7);

        assertFalse(player1.hasWon());
    }
}