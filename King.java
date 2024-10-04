

import java.util.Collection;
import java.util.TreeSet;

public class King implements Piece {

    private int xPosition;
    private int yPosition;
    private boolean isWhite;
    private boolean isCaptured;
    private boolean canCastle;

    public King(int x, int y, boolean color) {
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
        return "King";
    }

    public String toString() {
        return "K";
    }

    @Override
    public Piece replicate() {
        return new King(xPosition, yPosition, isWhite);
    }

    public boolean ableToCastle() {
        return canCastle;
    }

    public void updateCastle() {
        if (canCastle) {
            if (isWhite) {
                canCastle = (xPosition == 0 && yPosition == 3);
            } else {
                canCastle = (xPosition == 7 && yPosition == 3);
            }
        }
    }

    public boolean checkPosOutOfBound(int x, int y) {
        return ((x > -1 && x < 8) && (y > -1 && y < 8));
    }

    public Collection<Move> getMove(int x, int y) {
        Collection<Move> move = new TreeSet<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = j + y;
                if (newX != x || newY != y) {
                    if (checkPosOutOfBound(newX, newY)) {
                        move.add(new Move(newX, newY));
                    }
                }
            }
        }

        return move;
    }
}
