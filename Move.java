

public class Move implements Comparable {

    private int x;
    private int y;
    private boolean captured;
    private boolean castle;
    private boolean enPassant;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public boolean getCapture() {
        return enPassant;
    }

    public boolean getCastle() {
        return castle;
    }

    public boolean getEnPassant() {
        return enPassant;
    }

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
        this.captured = false;
    }

    public Move(int x, int y, boolean captured) {
        this.x = x;
        this.y = y;
        this.captured = captured;
    }

    public Move(int x, int y, boolean captured, boolean castle) {
        this.x = x;
        this.y = y;
        this.captured = captured;
        this.castle = castle;
    }

    public Move(int x, int y, boolean captured, boolean castle, boolean enPassant) {
        this.x = x;
        this.y = y;
        this.captured = captured;
        this.castle = castle;
        this.enPassant = enPassant;
    }

    @Override
    public int compareTo(Object piece) {
        Move m = (Move) piece;

        if (x < m.getX()) {
            return 1;
        } else if (x > m.getX()) {
            return -1;
        } else {
            if (y < m.getY()) {
                return 1;
            } else if (y > m.getY()) {
                return -1;
            }

            return 0;
        }
    }
}
