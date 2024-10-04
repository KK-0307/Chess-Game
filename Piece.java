

import java.util.Collection;

public interface Piece {

    int getXCoordinate();

    int getYCoordinate();

    boolean isWhite();

    boolean isCaptured();

    String getType();

    void setX(int newX);

    void setY(int newY);

    Collection<Move> getMove(int x, int y);

    Piece replicate();
}
