

import java.util.Collection;
import java.util.TreeSet;

public class Rook implements Piece {

    private int xPosition;
    private int yPosition;
    private boolean isWhite;
    private boolean isCaptured;
    private boolean canCastle;

    public Rook(int x, int y, boolean color) {
        xPosition = x;
        yPosition = y;
        isWhite = color;
        isCaptured = false;
        canCastle = true;
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
        return "Rook";
    }

    public String toString() {
        return "R";
    }

    @Override
    public Piece replicate() {
        return new Rook(xPosition, yPosition, isWhite);
    }

    public boolean canCastle() {
        return canCastle;
    }

    public void updateCastle() {
        if (canCastle) {
            canCastle = ((xPosition == 0) && (yPosition == 0)) ||
                    ((xPosition == 7) && (yPosition == 7)) ||
                    ((xPosition == 0) && (yPosition == 7)) ||
                    ((xPosition == 7) && (yPosition == 0));
        }
    }

    public Collection<Move> getMove(int x, int y) {
        Collection<Move> move = new TreeSet<>();
        for (int i = 0; i < 8; i++) {
            if (i != x) {
                move.add(new Move(i, y));
            } else if (i != y) {
                move.add(new Move(x, i));
            }
        }

        return move;
    }
}
