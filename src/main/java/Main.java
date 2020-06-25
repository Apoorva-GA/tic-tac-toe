import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        System.out.println(grid.createGrid());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player1 name and symbol:");
        String player1Name = scanner.next();
        char player1Symbol = scanner.next().charAt(0);

        System.out.println("Enter player2 name and symbol:");
        String player2Name = scanner.next();
        char player2Symbol = scanner.next().charAt(0);

        Game game = new Game(grid, new Player(player1Name, player1Symbol), new Player(player2Name, player2Symbol));
    }
}