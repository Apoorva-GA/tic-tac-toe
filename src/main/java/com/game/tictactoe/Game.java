package com.game.tictactoe;

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

    private Player getOpponentPlayer() {
        return currentPlayer == player1 ? player2 : player1;
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
//            Random random = new Random();
//            move = random.nextInt(9) + 1;
            move = findBestMove(grid.getGameMoves(), currentPlayer.getSymbol());
        }
        return move;
    }

    private int evaluate(char[] b) {
        char currentPlayerSymbol = getCurrentPlayer().getSymbol();
        int row = 0;
        while (row < 9) {
            if (b[row] == b[row + 1] && b[row + 1] == b[row + 2]) {
                return b[row] == currentPlayerSymbol ? +10 : -10;
            }
            row = row + 3;
        }
        int col = 0;
        while (col < 3) {
            if (b[col] == b[col + 3] && b[col + 3] == b[col + 6]) {
                return b[col] == currentPlayerSymbol ? +10 : -10;
            }
            col = col + 1;
        }
        if (b[0] == b[4] && b[4] == b[8]) {
            return b[0] == currentPlayerSymbol ? +10 : -10;
        }
        if (b[2] == b[4] && b[4] == b[6]) {
            return b[2] == currentPlayerSymbol ? +10 : -10;
        }
        return 0;
    }

    private Boolean isMovesLeft(char[] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ')
                return true;
        }
        return false;
    }

    private int minimax(char[] board, int depth, Boolean isMax) {
        int score = evaluate(board);
        if (score == 10)
            return score;
        if (score == -10)
            return score;
        if (!isMovesLeft(board))
            return 0;
        if (isMax) {
            int best = -1000;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == ' ') {
                    board[i] = getCurrentPlayer().getSymbol();
                    best = Math.max(best, minimax(board, depth + 1, false));
                    board[i] = ' ';
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == ' ') {
                    board[i] = getOpponentPlayer().getSymbol();
                    best = Math.min(best, minimax(board, depth + 1, true));
                    board[i] = ' ';
                }
            }
            return best;
        }
    }

    private int findBestMove(char[] board, char currentPlayerSymbol) {
        int bestVal = -1000;
        int bestMove = -1;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') {
                board[i] = currentPlayerSymbol;
                int moveVal = minimax(board, 0, false);
                board[i] = ' ';
                if (moveVal > bestVal) {
                    bestMove = i;
                    bestVal = moveVal;
                }
            }
        }
        return bestMove + 1;
    }
}