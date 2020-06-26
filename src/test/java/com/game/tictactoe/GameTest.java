package com.game.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class GameTest {
    private Game game;
    @Mock
    private Grid grid;

    @Mock
    private Player player1;

    @Mock
    private Player player2;

    @Mock
    private InputHandler inputHandler;

    @Mock
    private OutputHandler outputHandler;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        game = new Game(grid, player1, player2);
    }

    @Test
    public void shouldHaveSymbolxIn1() {
        char[] gameMoves = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

        when(player1.getSymbol()).thenReturn('x');
        when(grid.getGameMoves()).thenReturn(gameMoves);

        boolean isSuccessfulMove = game.makeMove(1);

        assertEquals(grid.getGameMoves()[0], 'x');
        assertTrue(isSuccessfulMove);
    }

    @Test
    public void shouldHaveSymboloIn1() {
        char[] gameMoves = {'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

        when(player2.getSymbol()).thenReturn('x');
        when(grid.getGameMoves()).thenReturn(gameMoves);

        boolean isSuccessfulMove = game.makeMove(1);

        assertEquals(grid.getGameMoves()[0], 'o');
        assertFalse(isSuccessfulMove);
    }

    @Test
    public void shouldGetCurrentPlayer() {
        Player currentPlayer = game.getCurrentPlayer();

        assertEquals(player1, currentPlayer);
    }

    @Test
    public void shouldSwitchPlayer() {
        Player previousPlayer = game.getCurrentPlayer();

        game.switchPlayer();
        Player currentPlayer = game.getCurrentPlayer();

        assertEquals(player1, previousPlayer);
        assertEquals(player2, currentPlayer);
    }

    @Test
    public void shouldStartGameWhenBothPlayersAreHuman() {
        char[] gameMoves = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

        when(grid.getGameMoves()).thenReturn(gameMoves);
        when(inputHandler.getMove()).thenReturn(1).thenReturn(1).thenReturn(-1).thenReturn(2);
        when(player1.getSymbol()).thenReturn('x');
        when(player1.getPlayerType()).thenReturn(PlayerType.HUMAN);
        when(player2.getPlayerType()).thenReturn(PlayerType.HUMAN);
        when(player1.hasWon()).thenReturn(false);
        when(player2.hasWon()).thenReturn(true);

        game.startGame(inputHandler, outputHandler);

        verify(grid, times(6)).getGameMoves();
        verify(inputHandler, times(4)).getMove();
        verify(player1, times(1)).getSymbol();
        verify(player1, times(1)).hasWon();
        verify(player2, times(1)).hasWon();
        verify(player1, times(1)).makeMove(1);
        verify(player2, times(1)).makeMove(2);
    }

    @Test
    public void shouldVerifyThatGameIsTie() {
        char[] gameMoves = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

        when(grid.getGameMoves()).thenReturn(gameMoves);
        when(inputHandler.getMove()).thenReturn(1).thenReturn(2).thenReturn(5).thenReturn(4)
                .thenReturn(3).thenReturn(7).thenReturn(8).thenReturn(9).thenReturn(6);
        when(player1.getSymbol()).thenReturn('x');
        when(player1.getPlayerType()).thenReturn(PlayerType.HUMAN);
        when(player2.getPlayerType()).thenReturn(PlayerType.HUMAN);
        when(player1.hasWon()).thenReturn(false);
        when(player2.hasWon()).thenReturn(false);

        game.startGame(inputHandler, outputHandler);

        verify(outputHandler, times(1)).print("Its a tie!!!");
    }

    @Test
    public void shouldStartGameWhenOnePlayerIsComputer() {
        char[] gameMoves = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

        when(grid.getGameMoves()).thenReturn(gameMoves);
        when(inputHandler.getMove()).thenReturn(1);
        when(player1.getSymbol()).thenReturn('X');
        when(player1.getPlayerType()).thenReturn(PlayerType.HUMAN);
        when(player2.getPlayerType()).thenReturn(PlayerType.COMPUTER);
        when(player1.hasWon()).thenReturn(false);
        when(player2.hasWon()).thenReturn(true);

        game.startGame(inputHandler, outputHandler);

        verify(grid, times(5)).getGameMoves();
        verify(inputHandler, times(1)).getMove();
        verify(player1, times(1)).getSymbol();
        verify(player1, times(1)).hasWon();
        verify(player2, times(1)).hasWon();
        verify(player1, times(1)).makeMove(1);
    }
}