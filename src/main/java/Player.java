import java.util.ArrayList;

public class Player {
    private String name;
    private char symbol;
    private ArrayList<Integer> moves;

    public Player(String name,char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.moves = new ArrayList<>();
    }
}