package com.game.tictactoe;

import com.game.tictactoe.InputHandler;
import com.game.tictactoe.InputScanner;
import com.game.tictactoe.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

class InputHandlerTest {
    private InputHandler inputHandler;

    @Mock
    private InputScanner inputScanner;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        inputHandler = new InputHandler(inputScanner);
    }

    @Test
    public void shouldGetMoveAs1() {
        when(inputScanner.readInt()).thenReturn(1);

        assertEquals(1, inputHandler.getMove());
    }

    @Test
    public void shouldGetMoveAsMinus1() {
        when(inputScanner.readInt()).thenReturn(22);

        assertEquals(-1, inputHandler.getMove());
    }

    @Test
    public void shouldGetPlayerWithEnteredNameAndSymbol() {
        String name = "player1";
        String symbol = "X";
        Player expectedPlayer = new Player(name, symbol.charAt(0));
        when(inputScanner.readString()).thenReturn(name).thenReturn(symbol);

        Player actualPlayer = inputHandler.getPlayer("player1");

        assertEquals(expectedPlayer.getName(), actualPlayer.getName());
        assertEquals(expectedPlayer.getSymbol(), actualPlayer.getSymbol());
    }

    @Test
    public void shouldGetPlayer1WithDefaultSymbol() {
        String name = "player1";
        String symbol = "aws";
        Player expectedPlayer = new Player(name, 'X');
        when(inputScanner.readString()).thenReturn(name).thenReturn(symbol);

        Player actualPlayer = inputHandler.getPlayer("player1");

        assertEquals(expectedPlayer.getName(), actualPlayer.getName());
        assertEquals(expectedPlayer.getSymbol(), actualPlayer.getSymbol());
    }

    @Test
    public void shouldGetPlayer2WithDefaultSymbol() {
        String name = "player2";
        String symbol = "aws";
        Player expectedPlayer = new Player(name, 'O');
        when(inputScanner.readString()).thenReturn(name).thenReturn(symbol);

        Player actualPlayer = inputHandler.getPlayer("player2");

        assertEquals(expectedPlayer.getName(), actualPlayer.getName());
        assertEquals(expectedPlayer.getSymbol(), actualPlayer.getSymbol());
    }
}