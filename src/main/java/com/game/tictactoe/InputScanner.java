package com.game.tictactoe;

import java.util.Scanner;

public class InputScanner {
    private Scanner scanner;

    InputScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    public String readString(){
        return scanner.next();
    }

    public int readInt(){
        return scanner.nextInt();
    }

    public String readLine(){
        return scanner.nextLine();
    }
}
