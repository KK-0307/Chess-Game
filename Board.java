

import java.util.*;

public class Board {

    private Piece[][] board;
    List<Piece> pinkCaptured;
    List<Piece> whiteCaptured;
    Map<Piece, Collection<Move>> currentMove;
    boolean playerTurn;
    boolean check;
    boolean checkmate;
    boolean stalemate;
    boolean[][] allMoves;

    public Board() {
        board = new Piece[8][8];
        allMoves = new boolean[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                allMoves[i][j] = false;
            }
        }

        pinkCaptured = new ArrayList<>();
        whiteCaptured = new ArrayList<>();
        currentMove = new HashMap<>();

        playerTurn = true;
        check = false;
        checkmate = false;
        stalemate = false;
    }

    public List<Piece> getPinkCaptured() {
        return pinkCaptured;
    }

    public List<Piece> getWhiteCaptured() {
        return whiteCaptured;
    }

    public boolean getPlayerTurn() {
        return playerTurn;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckmate() {
        return checkmate;
    }

    public boolean getStalemate() {
        return stalemate;
    }

    public Piece getChessPiece(int x, int y) {
        return board[x][y];
    }

    public boolean doesMoveExist(int x, int y) {
        return allMoves[x][y];
    }

    public Board(boolean setPieces) {

        if (setPieces) {
            board = new Piece[8][8];
            pinkCaptured = new ArrayList<>();
            whiteCaptured = new ArrayList<>();
            currentMove = new HashMap<>();
            playerTurn = true;
            check = false;
            checkmate = false;

            // Initialize pink pawn pieces
            board[6][0] = new Pawn(6, 0, false);
            board[6][1] = new Pawn(6, 1, false);
            board[6][2] = new Pawn(6, 2, false);
            board[6][3] = new Pawn(6, 3, false);
            board[6][4] = new Pawn(6, 4, false);
            board[6][5] = new Pawn(6, 5, false);
            board[6][6] = new Pawn(6, 6, false);
            board[6][7] = new Pawn(6, 7, false);

            // Initialize white pawn pieces
            board[1][0] = new Pawn(1, 0, true);
            board[1][1] = new Pawn(1, 1, true);
            board[1][2] = new Pawn(1, 2, true);
            board[1][3] = new Pawn(1, 3, true);
            board[1][4] = new Pawn(1, 4, true);
            board[1][5] = new Pawn(1, 5, true);
            board[1][6] = new Pawn(1, 6, true);
            board[1][7] = new Pawn(1, 7, true);

            // Initialize the other pink chess pieces
            board[7][0] = new Rook(7, 0, false);
            board[7][1] = new Knight(7, 1, false);
            board[7][2] = new Bishop(7, 2, false);
            board[7][3] = new King(7, 3, false);
            board[7][4] = new Queen(7, 4, false);
            board[7][5] = new Bishop(7, 5, false);
            board[7][6] = new Knight(7, 6, false);
            board[7][7] = new Rook(7, 7, false);

            // Initialize the other white chess pieces
            board[0][0] = new Rook(0, 0, true);
            board[0][1] = new Knight(0, 1, true);
            board[0][2] = new Bishop(0, 2, true);
            board[0][3] = new King(0, 3, true);
            board[0][4] = new Queen(0, 4, true);
            board[0][5] = new Bishop(0, 5, true);
            board[0][6] = new Knight(0, 6, true);
            board[0][7] = new Rook(0, 7, true);

        }
    }

    public void resetBoard() {
        board = new Piece[8][8];
        pinkCaptured = new ArrayList<>();
        whiteCaptured = new ArrayList<>();
        currentMove = new HashMap<>();
        allMoves = new boolean[8][8];
        playerTurn = true;
        check = false;
        checkmate = false;
        stalemate = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                allMoves[i][j] = false;
            }
        }

        // Re-initialize pink pawn pieces
        board[6][0] = new Pawn(6, 0, false);
        board[6][1] = new Pawn(6, 1, false);
        board[6][2] = new Pawn(6, 2, false);
        board[6][3] = new Pawn(6, 3, false);
        board[6][4] = new Pawn(6, 4, false);
        board[6][5] = new Pawn(6, 5, false);
        board[6][6] = new Pawn(6, 6, false);
        board[6][7] = new Pawn(6, 7, false);

        // Re-initialize white pawn pieces
        board[1][0] = new Pawn(1, 0, true);
        board[1][1] = new Pawn(1, 1, true);
        board[1][2] = new Pawn(1, 2, true);
        board[1][3] = new Pawn(1, 3, true);
        board[1][4] = new Pawn(1, 4, true);
        board[1][5] = new Pawn(1, 5, true);
        board[1][6] = new Pawn(1, 6, true);
        board[1][7] = new Pawn(1, 7, true);

        // Re-initialize the other pink chess pieces
        board[7][0] = new Rook(7, 0, false);
        board[7][1] = new Knight(7, 1, false);
        board[7][2] = new Bishop(7, 2, false);
        board[7][3] = new King(7, 3, false);
        board[7][4] = new Queen(7, 4, false);
        board[7][5] = new Bishop(7, 5, false);
        board[7][6] = new Knight(7, 6, false);
        board[7][7] = new Rook(7, 7, false);

        // Re-initialize the other white chess pieces
        board[0][0] = new Rook(0, 0, true);
        board[0][1] = new Knight(0, 1, true);
        board[0][2] = new Bishop(0, 2, true);
        board[0][3] = new King(0, 3, true);
        board[0][4] = new Queen(0, 4, true);
        board[0][5] = new Bishop(0, 5, true);
        board[0][6] = new Knight(0, 6, true);
        board[0][7] = new Rook(0, 7, true);
    }

    public void resetAllMove() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                allMoves[i][j] = false;
            }
        }
    }

    public void deselectChessPiece(int xPosition, int yPosition) {
        resetAllMove();
    }

    public boolean selectChessPiece(int xPosition, int yPosition) {
        resetAllMove();
        Piece chessPiece = board[xPosition][yPosition];

        if (chessPiece == null) {
            return false;
        }

        if (chessPiece.isWhite() == playerTurn) {
            Collection<Move> move = currentMove.get(chessPiece);

            for (Move m : move) {
                allMoves[m.getX()][m.getY()] = true;
            }

            allMoves[chessPiece.getXCoordinate()][chessPiece.getYCoordinate()] = true;
            return true;
        }

        return false;
    }

    public boolean isTheMoveValid(int x, int y, int newX, int newY) {
        Piece chessPiece = board[x][y];
        Collection<Move> move = currentMove.get(chessPiece);
        return move.contains(new Move(newX, newY));
    }

    public boolean isCastleMove(int x, int y, int newX, int newY) {
        Piece chessPiece = board[x][y];
        if (chessPiece.getType().equals("King")) {
            Collection<Move> move = currentMove.get(chessPiece);
            for (Move m : move) {
                if (m.getX() == newX && m.getY() == newY) {
                    if (m.getCastle()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isEnPassantMove(int x, int y, int newX, int newY) {
        Piece chessPiece = board[x][y];
        if (chessPiece.getType().equals("Pawns")) {
            Collection<Move> move = currentMove.get(chessPiece);
            for (Move m : move) {
                if (m.getX() == newX && m.getY() == newY) {
                    if (m.getEnPassant()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void moveChessPiece(int x, int y, int newX, int newY, boolean addCaptured) {
        if (isTheMoveValid(x, y, newX, newY)) {
            if (!isCastleMove(x, y, newX, newY)) {
                if (!isEnPassantMove(x, y, newX, newY)) {
                    Piece chessPiece = board[x][y];
                    board[x][y] = null;
                    Piece capPiece = board[newX][newY];

                    if (capPiece != null) {
                        if (addCaptured) {
                            if (capPiece.isWhite()) {
                                whiteCaptured.add(capPiece);
                            } else {
                                pinkCaptured.add(capPiece);
                                System.out.println("Your Piece has been Captured!");
                            }
                        }
                    }

                    board[newX][newY] = chessPiece;
                    board[newX][newY].setX(newX);
                    board[newX][newY].setY(newY);

                    resetAllMove();
                    playerTurn = !playerTurn;
                } else {
                    Piece chessPiece = board[x][y];
                    board[x][y] = null;
                    Piece capPiece = null;

                    if (chessPiece.isWhite()) {
                        capPiece = board[newX - 1][newY];
                        board[newX - 1][newY] = null;
                    } else {
                        capPiece = board[newX + 1][newY];
                        board[newX + 1][newY] = null;
                    }

                    if (capPiece != null) {
                        if (addCaptured) {
                            if (capPiece.isWhite()) {
                                whiteCaptured.add(capPiece);
                            } else {
                                pinkCaptured.add(capPiece);
                                System.out.println("Your Piece has been Captured!");
                            }
                        }
                    }

                    board[newX][newY] = chessPiece;
                    board[newX][newY].setX(newX);
                    board[newX][newY].setY(newY);

                    resetAllMove();
                    playerTurn = !playerTurn;

                }
            } else {
                Piece chessPiece = board[x][y];
                board[x][y] = null;
                if (newY == 1) {
                    Piece rook = board[x][0];
                    board[x][0] = null;
                    board[newX][2] = rook;
                    board[newX][2].setX(newX);
                    board[newX][2].setY(2);
                } else if (newY == 5) {
                    Piece rook = board[x][7];
                    board[x][7] = null;
                    board[newX][4] = rook;
                    board[newX][4].setX(newX);
                    board[newX][4].setY(4);
                }

                board[newX][newY] = chessPiece;
                board[newX][newY].setX(newX);
                board[newX][newY].setY(newY);

                resetAllMove();
                playerTurn = !playerTurn;
            }
        }
    }

    // Private helper methods to check that the bounds of the columns, rows, board,
    // and diagonal are valid

    private boolean isInBound(int x, int y) {
        return ((x < 8 && x > -1) && (y < 8 && y > -1));
    }

    private boolean isOutOfBound(int x, int y, int rightB, int leftB, int topB, int bottomB) {
        return ((x <= bottomB && x >= topB) && (y <= rightB && y >= leftB));
    }

    private boolean diagonalBound(
            int x, int y, int cx, int cy, int topLB, int bottomLB, int topRB, int bottomRB
    ) {

        if (y < cy) {
            if (x > cx) {
                return (y >= cy - bottomLB);
            } else if (x < cx) {
                return (y >= cy - topLB);
            }

            return true;
        } else if (y > cy) {
            if (x > cx) {
                return (y <= cy + bottomRB);
            } else if (x < cx) {
                return (y <= cy + topRB);
            }

            return true;
        }

        return true;
    }

    private int checkCastle(Piece king, Piece[][] b) {
        int x = 0;

        if (((King) king).ableToCastle()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Piece chessPiece = b[i][j];
                    if (chessPiece != null) {
                        if (chessPiece.isWhite() == playerTurn) {
                            if (chessPiece.getType().equals("Rook")) {
                                if (((Rook) chessPiece).canCastle()) {
                                    boolean sideOfQueen = false;
                                    boolean sideOfKing = false;
                                    if (chessPiece.getYCoordinate() == 7) {
                                        if (b[i][4] == null && b[i][5] == null && b[i][6] == null) {
                                            sideOfQueen = true;
                                        }
                                    }
                                    if (chessPiece.getYCoordinate() == 0) {
                                        if (b[i][1] == null && b[i][2] == null) {
                                            sideOfKing = true;
                                        }
                                    }

                                    if (sideOfQueen && sideOfKing) {
                                        x = 3;
                                    } else if (sideOfQueen && !sideOfKing) {
                                        x = 2;
                                    } else if (sideOfKing && !sideOfQueen) {
                                        x = 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return x;
    }

    private Collection<Move> addCastle(Piece chessPiece, Collection<Move> move, Piece[][] b) {
        if (chessPiece.getType().equals("King")) {
            int c = checkCastle(chessPiece, b);

            // side of King
            if (c == 1) {
                move.add(
                        new Move(
                                chessPiece.getXCoordinate(), chessPiece.getYCoordinate() - 2, false,
                                true
                        )
                );
            } else if (c == 2) {
                move.add(
                        new Move(
                                chessPiece.getXCoordinate(), chessPiece.getYCoordinate() + 2, false,
                                true
                        )
                );
            } else if (c == 3) {
                move.add(
                        new Move(
                                chessPiece.getXCoordinate(), chessPiece.getYCoordinate() + 2, false,
                                true
                        )
                );
                move.add(
                        new Move(
                                chessPiece.getXCoordinate(), chessPiece.getYCoordinate() - 2, false,
                                true
                        )
                );
            }
        }

        return move;
    }

    private Collection<Move> checkForPawnCapture(
            Piece chessPiece, Collection<Move> move, Piece[][] b
    ) {

        Collection<Move> removeMoves = new TreeSet<>();
        int x = 0;
        int y1 = chessPiece.getYCoordinate() + 1;
        int y2 = chessPiece.getYCoordinate() - 1;

        if (chessPiece.isWhite()) {
            x = chessPiece.getXCoordinate() + 1;
        } else {
            x = chessPiece.getXCoordinate() - 1;
        }

        if (move.size() > 1) {
            for (Move m : move) {
                if (chessPiece.isWhite()) {
                    if (m.getX() == chessPiece.getXCoordinate() + 2) {
                        if (b[m.getX() - 1][m.getY()] != null) {
                            removeMoves.add(m);
                        }
                    }
                } else {
                    if (m.getX() == chessPiece.getXCoordinate() - 2) {
                        if (b[m.getX() + 1][m.getY()] != null) {
                            removeMoves.add(m);
                        }
                    }
                }
            }
        }

        Piece p1 = null;
        Piece p2 = null;

        if (isInBound(x, y1)) {
            p1 = b[x][y1];
        }

        if (isInBound(x, y2)) {
            p2 = b[x][y2];
        }

        if ((p1 != null) && (chessPiece.isWhite() != p1.isWhite())) {
            move.add(new Move(x, y1, true));
        }

        if ((p2 != null) && (chessPiece.isWhite() != p2.isWhite())) {
            move.add(new Move(x, y2, true));
        }

        for (Move m : removeMoves) {
            System.out.println(m.getX() + " " + m.getY());
            move.remove(m);
        }

        return move;
    }

    private Collection<Move> enPassant(Piece chessPiece, Collection<Move> move, Piece[][] b) {

        if (chessPiece.getType().equals("Pawn")) {
            int row = 0;
            if (chessPiece.isWhite()) {
                row = 4;
            } else {
                row = 3;
            }

            if (chessPiece.getXCoordinate() == row) {
                if (chessPiece.getYCoordinate() + 1 < 8) {
                    Piece p = board[chessPiece.getXCoordinate()][chessPiece.getYCoordinate() + 1];

                    if (p != null) {
                        if (p.getType().equals("Pawn")) {
                            if (p.isWhite() != chessPiece.isWhite()) {
                                if (row == 4) {
                                    move.add(
                                            new Move(
                                                    chessPiece.getXCoordinate() + 1,
                                                    chessPiece.getYCoordinate() + 1, false, true,
                                                    true
                                            )
                                    );
                                } else if (row == 3) {
                                    move.add(
                                            new Move(
                                                    chessPiece.getXCoordinate() - 1,
                                                    chessPiece.getYCoordinate() + 1, false, true,
                                                    true
                                            )
                                    );
                                }
                            }
                        }
                    }
                }

                if (chessPiece.getYCoordinate() - 1 >= 0) {
                    Piece piece = board[chessPiece.getXCoordinate()][chessPiece.getYCoordinate()
                            - 1];
                    if (piece != null) {
                        if (piece.getType().equals("Pawn")) {
                            if (piece.isWhite() != chessPiece.isWhite()) {
                                if (row == 4) {
                                    move.add(
                                            new Move(
                                                    chessPiece.getXCoordinate() + 1,
                                                    chessPiece.getYCoordinate() - 1, false, true,
                                                    true
                                            )
                                    );
                                } else if (row == 3) {
                                    move.add(
                                            new Move(
                                                    chessPiece.getXCoordinate() - 1,
                                                    chessPiece.getYCoordinate() - 1, false, true,
                                                    true
                                            )
                                    );
                                }
                            }
                        }
                    }
                }
            }
        }

        return move;
    }

    // Private helper methods to alter the moves

    private Collection<Move> deleteVerticalAndHorizontalCollision(
            Collection<Move> move,
            Piece chessPiece, Piece[][] b
    ) {

        Iterator<Move> rookIter = move.iterator();
        Collection<Move> removeRookMove = new TreeSet<>();
        int leftB = 0;
        int rightB = 7;
        int topBound = 0;
        int bottomBound = 7;

        while (rookIter.hasNext()) {
            Move m = rookIter.next();
            int x = m.getX();
            int y = m.getY();
            Piece p = b[x][y];

            if (p != null) {
                if (x == chessPiece.getXCoordinate()) {
                    if (p.isWhite() != chessPiece.isWhite()) {
                        m.setCaptured(true);
                    } else {
                        removeRookMove.add(m);
                    }

                    if (y > chessPiece.getYCoordinate()) {
                        if (y < rightB) {
                            rightB = y;
                        }
                    } else {
                        if (y > leftB) {
                            leftB = y;
                        }
                    }
                }

                if (y == chessPiece.getYCoordinate()) {
                    if (p.isWhite() != chessPiece.isWhite()) {
                        m.setCaptured(true);
                    } else {
                        removeRookMove.add(m);
                    }

                    if (x > chessPiece.getXCoordinate()) {
                        if (x < bottomBound) {
                            bottomBound = x;
                        }
                    } else {
                        if (x > topBound) {
                            topBound = x;
                        }
                    }
                }
            }
        }

        for (Move m : removeRookMove) {
            move.remove(m);
        }

        for (Move m : move) {
            if (!isOutOfBound(m.getX(), m.getY(), rightB, leftB, topBound, bottomBound)) {
                if (m.getX() == chessPiece.getXCoordinate()
                        || m.getY() == chessPiece.getYCoordinate()) {
                    removeRookMove.add(m);
                }
            }
        }

        return move;
    }

    private Collection<Move> deleteDiagonalCollision(
            Collection<Move> move, Piece chessPiece, Piece[][] b
    ) {

        Iterator<Move> bishopIter = move.iterator();
        Collection<Move> removeBishopMove = new TreeSet<>();
        int topLB = 7;
        int bottomLB = 7;
        int topRB = 7;
        int bottomRB = 7;

        while (bishopIter.hasNext()) {
            Move m = bishopIter.next();
            int x = m.getX();
            int y = m.getY();
            Piece p = b[x][y];

            if (p != null) {
                if (p.isWhite() != chessPiece.isWhite()) {
                    m.setCaptured(true);
                } else {
                    removeBishopMove.add(m);
                }

                if (y < chessPiece.getYCoordinate()) {
                    if (x < chessPiece.getXCoordinate()) {
                        int newY = chessPiece.getYCoordinate() - y;
                        if (newY < topLB) {
                            topLB = newY;
                        }
                    } else if (x > chessPiece.getXCoordinate()) {
                        int newY = chessPiece.getYCoordinate() - y;
                        if (newY < bottomLB) {
                            bottomLB = newY;
                        }
                    }
                } else if (y > chessPiece.getYCoordinate()) {
                    if (x < chessPiece.getXCoordinate()) {
                        int newY = y - chessPiece.getYCoordinate();
                        if (newY < topRB) {
                            topRB = newY;
                        }
                    } else if (x > chessPiece.getXCoordinate()) {
                        int newY = y - chessPiece.getYCoordinate();
                        if (newY < bottomRB) {
                            bottomRB = newY;
                        }
                    }
                }
            }
        }

        for (Move m : removeBishopMove) {
            move.remove(m);
        }

        for (Move m : move) {
            if (!diagonalBound(
                    m.getX(), m.getY(), chessPiece.getXCoordinate(), chessPiece.getYCoordinate(),
                    topLB, topRB, bottomLB, bottomRB
            )) {
                removeBishopMove.add(m);
            }
        }

        return move;
    }

    private Collection<Move> deleteCollision(Piece chessPiece, Collection<Move> move, Piece[][] b) {

        switch (chessPiece.getType()) {
            case "Knight":
            case "King":
                Iterator<Move> iterator = move.iterator();
                Collection<Move> moveToDelete = new TreeSet<>();
                while (iterator.hasNext()) {
                    Move m = iterator.next();
                    Piece p = b[m.getX()][m.getY()];
                    if (p != null) {
                        if (p.isWhite() != chessPiece.isWhite()) {
                            m.setCaptured(true);
                        } else {
                            moveToDelete.add(m);
                        }
                    }
                }
                for (Move m : moveToDelete) {
                    move.remove(m);
                }

                if (chessPiece.getType().equals("King")) {
                    ((King) chessPiece).updateCastle();
                }
                move = addCastle(chessPiece, move, b);
                break;
            case "Queen":
                move = deleteVerticalAndHorizontalCollision(move, chessPiece, b);
                move = deleteDiagonalCollision(move, chessPiece, b);
                break;
            case "Pawn":
                move.removeIf(move1 -> b[move1.getX()][move1.getY()] != null);
                move = checkForPawnCapture(chessPiece, move, b);
                move = deleteVerticalAndHorizontalCollision(move, chessPiece, b);
                move = enPassant(chessPiece, move, b);
                break;
            case "Rook":
                move = deleteVerticalAndHorizontalCollision(move, chessPiece, b);
                ((Rook) chessPiece).updateCastle();
                break;
            case "Bishop":
                move = deleteDiagonalCollision(move, chessPiece, b);
                break;
            default:
                break;
        }

        return move;
    }

    private void updatePrawn() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece chessPiece = board[i][j];
                if (chessPiece != null) {
                    if (chessPiece.getType().equals("Pawn")) {
                        if (chessPiece.isWhite()) {
                            if (chessPiece.getXCoordinate() == 7) {
                                board[i][j] = new Queen(i, j, true);
                            }
                        } else {
                            if (chessPiece.getXCoordinate() == 0) {
                                board[i][j] = new Queen(i, j, false);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isMoveCheck(Piece king, Map<Piece, Collection<Move>> rivalMoves) {
        for (Map.Entry<Piece, Collection<Move>> e : rivalMoves.entrySet()) {
            Piece chessPiece = e.getKey();
            Collection<Move> move = e.getValue();

            for (Move m : move) {
                if ((m.getX() == king.getXCoordinate()) && (m.getY() == king.getYCoordinate())) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isMoveStalemate() {
        if (!check) {
            for (Map.Entry<Piece, Collection<Move>> e : currentMove.entrySet()) {
                Collection<Move> move = e.getValue();
                if (!move.isEmpty()) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    private boolean isMoveCheckMate() {
        if (check) {
            for (Map.Entry<Piece, Collection<Move>> e : currentMove.entrySet()) {
                Collection<Move> move = e.getValue();
                if (!move.isEmpty()) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    private Piece[][] copy(Piece[][] arr) {
        Piece[][] cArr = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece chessPiece = arr[i][j];
                if (chessPiece != null) {
                    cArr[i][j] = chessPiece.replicate();
                }
            }
        }

        return cArr;
    }

    private Piece getKingPiece(boolean playerTurn) {
        for (Piece[] chessPiece : board) {
            for (Piece p : chessPiece) {
                if (p != null) {
                    if (p.isWhite() == playerTurn) {
                        if (p.getType().equals("King")) {
                            return p;
                        }
                    }
                }
            }
        }

        return null;
    }

    private Piece[][] moveChessPieces(int x, int y, Move move, Piece[][] b) {
        Piece chessPiece = b[x][y];
        int newX = move.getX();
        int newY = move.getY();

        b[x][y] = null;
        b[newX][newY] = chessPiece;
        b[newX][newY].setX(newX);
        b[newX][newY].setY(newY);
        return b;
    }

    private Map<Piece, Collection<Move>> getUpdatedRivalMoves(Piece[][] b) {

        Map<Piece, Collection<Move>> newMoves = new HashMap<Piece, Collection<Move>>();

        for (Piece[] chessPiece : b) {
            for (Piece p : chessPiece) {
                if (p != null) {
                    Collection<Move> move = p.getMove(p.getXCoordinate(), p.getYCoordinate());
                    if (p.isWhite() != playerTurn) {
                        move = deleteCollision(p, move, b);
                        newMoves.put(p, move);
                    }
                }
            }
        }

        return newMoves;
    }

    public Map<Piece, Collection<Move>> canTheKingMove(
            Piece king, Map<Piece, Collection<Move>> rivalMoves,
            Map<Piece, Collection<Move>> currentMove
    ) {

        Collection<Move> move = currentMove.get(king);
        Collection<Move> removeMoves = new TreeSet<>();
        if (move != null) {
            for (Move m : move) {
                Piece[][] b = copy(board);
                b = moveChessPieces(king.getXCoordinate(), king.getYCoordinate(), m, b);
                Piece kingCopy = b[m.getX()][m.getY()];
                Map<Piece, Collection<Move>> newMove = getUpdatedRivalMoves(b);
                if (isMoveCheck(kingCopy, newMove)) {
                    removeMoves.add(m);
                }
            }
        }

        for (Move m : removeMoves) {
            move.remove(m);
        }

        currentMove.put(king, move);
        return currentMove;
    }

    public Map<Piece, Collection<Move>> pieceBlocked(
            Piece king, Map<Piece, Collection<Move>> rivalMoves,
            Map<Piece, Collection<Move>> currentMove
    ) {
        for (Map.Entry<Piece, Collection<Move>> e : currentMove.entrySet()) {
            Piece chessPiece = e.getKey();
            Collection<Move> move = e.getValue();
            Collection<Move> removeMoves = new TreeSet<>();

            for (Move m : move) {
                Piece[][] b = copy(board);
                b = moveChessPieces(chessPiece.getXCoordinate(), chessPiece.getYCoordinate(), m, b);
                Map<Piece, Collection<Move>> newMove = getUpdatedRivalMoves(b);

                if (chessPiece.getType().equals("King")) {
                    if (isMoveCheck(b[m.getX()][m.getY()], newMove)) {
                        removeMoves.add(m);
                    }
                } else {
                    if (isMoveCheck(king, newMove)) {
                        removeMoves.add(m);
                    }
                }
            }

            for (Move m : removeMoves) {
                move.remove(m);
            }

            currentMove.put(chessPiece, move);
        }

        return currentMove;
    }

    public void captureCastle(Piece king) {

        if (check) {
            Collection<Move> move = currentMove.get(king);
            Collection<Move> removeMoves = new TreeSet<>();

            for (Move m : move) {
                if (m.getCastle()) {
                    removeMoves.add(m);
                }
            }

            for (Move m : removeMoves) {
                move.remove(m);
            }

            currentMove.put(king, move);
        }
    }

    public void updateMove() {
        currentMove.clear();
        Map<Piece, Collection<Move>> rivalMoves = new HashMap<Piece, Collection<Move>>();

        for (Piece[] chessPiece : board) {
            for (Piece p : chessPiece) {
                if (p != null) {
                    Collection<Move> move = p.getMove(p.getXCoordinate(), p.getYCoordinate());
                    if (p.isWhite() == playerTurn) {
                        move = deleteCollision(p, move, board);
                        currentMove.put(p, move);
                    } else {
                        move = deleteCollision(p, move, board);
                        rivalMoves.put(p, move);
                    }
                }
            }
        }

        updatePrawn();
        Piece king = getKingPiece(playerTurn);
        check = isMoveCheck(king, rivalMoves);
        currentMove = pieceBlocked(king, rivalMoves, currentMove);
        captureCastle(king);
        checkmate = isMoveCheckMate();
        stalemate = isMoveStalemate();
    }

}
