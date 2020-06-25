public class Grid {
    private char[] gameMoves;

    public Grid() {
        this.gameMoves = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    }

    public String createGrid() {
        StringBuilder s = new StringBuilder("|");
        int count = 0;
        for (char gameMove: gameMoves) {
            if(count>=3) {
                s.append("\n|");
                count = 0;
            }
            s.append(gameMove).append("|");
            count++;

        }
        return s.toString();
    }
}
