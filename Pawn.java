

import java.util.Collection;
import java.util.TreeSet;

public class Pawn implements Piece {

    private int xPosition;
    private int yPosition;
    private boolean isWhite;
    private boolean isCaptured;
    private boolean captureFromEnPassant;

    public Pawn(int x, int y, boolean color) {
        xPosition = x;
        yPosition = y;
        isWhite = color;
        isCaptured = false;
        captureFromEnPassant = false;
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
        return "Pawn";
    }

    public String toString() {
        return "P";
    }

    @Override
    public Piece replicate() {
        return new King(xPosition, yPosition, isWhite);
    }

    public void setCaptureFromEnPassant(boolean enPassant) {
        captureFromEnPassant = enPassant;
    }

    public boolean isCaptureFromEnPassant() {
        if (isWhite) {
            return xPosition == 3;
        }

        return xPosition == 4;
    }

    public Collection<Move> getMove(int x, int y) {
        Collection<Move> move = new TreeSet<>();
        if (x == 1) {
            if (isWhite) {
                move.add(new Move(x + 1, y));
                move.add(new Move(x + 2, y));
            } else {
                move.add(new Move(x - 1, y));
            }
        } else if (x == 6) {
            if (isWhite) {
                move.add(new Move(x + 1, y));
            } else {
                move.add(new Move(x - 1, y));
                move.add(new Move(x - 2, y));
            }
        } else {
            if (isWhite) {
                int newX = x + 1;
                if (newX <= 6) {
                    move.add(new Move(newX, y));
                }
            } else {
                int newX = x - 1;
                if (newX >= 1) {
                    move.add(new Move(newX, y));
                }
            }
        }

        return move;
    }
}
