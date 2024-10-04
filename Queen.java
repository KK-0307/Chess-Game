

import java.util.Collection;
import java.util.TreeSet;

public class Queen implements Piece {

    private int xPosition;
    private int yPosition;
    private boolean isWhite;
    private boolean isCaptured;

    public Queen(int x, int y, boolean color) {
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
        return "Queen";
    }

    public String toString() {
        return "Q";
    }

    @Override
    public Piece replicate() {
        return new Queen(xPosition, yPosition, isWhite);
    }

    public boolean checkPosOutOfBound(int x, int y) {
        return ((x > -1 && x < 8) && (y > -1 && y < 8));
    }

    public Collection<Move> getMove(int x, int y) {
        Collection<Move> move = new TreeSet<>();

        for (int i = -7; i < 8; i++) {
            int newX = x + i;
            int newY = y + i;
            int nX = x - i;

            if (newX != x && newY != y) {
                if (checkPosOutOfBound(newX, newY)) {
                    move.add(new Move(newX, newY));
                }
            } else if (nX != x && newY != y) {
                if (checkPosOutOfBound(nX, newY)) {
                    move.add(new Move(nX, newY));
                }
            } else if (i != x) {
                if (checkPosOutOfBound(i, y)) {
                    move.add(new Move(i, y));
                }
            } else if (i != y) {
                if (checkPosOutOfBound(x, i)) {
                    move.add(new Move(x, i));
                }
            }
        }

        return move;
    }
}
