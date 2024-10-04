

import java.util.Collection;
import java.util.TreeSet;

public class Knight implements Piece {

    private int xPosition;
    private int yPosition;
    private boolean isWhite;
    private boolean isCaptured;

    public Knight(int x, int y, boolean color) {
        xPosition = x;
        yPosition = y;
        isWhite = color;
        isCaptured = false;
    }

    public int getXCoordinate() {
        return xPosition;
    }

    public int getYCoordinate() {
        return yPosition;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public void setCaptured(boolean captured) {
        isCaptured = captured;
    }

    public void setX(int x) {
        xPosition = x;
    }

    public void setY(int y) {
        yPosition = y;
    }

    public String getType() {
        return "Knight";
    }

    public String toString() {
        return "N";
    }

    @Override
    public Piece replicate() {
        return new Bishop(xPosition, yPosition, isWhite);
    }

    public boolean checkPosOutOfBound(int x, int y) {
        return ((x > -1 && x < 8) && (y > -1 && y < 8));
    }

    public Collection<Move> getMove(int x, int y) {
        Collection<Move> move = new TreeSet<>();

        for (int i : new int[] { -2, -1, 1, 2 }) {
            for (int j : new int[] { -2, -1, 1, 2 }) {
                if (Math.abs(i / j) == 2 || Math.abs(j / i) == 2) {
                    int newX = x + i;
                    int newY = y + j;
                    if (checkPosOutOfBound(newX, newY)) {
                        move.add(new Move(newX, newY));
                    }
                }
            }
        }

        return move;
    }
}
