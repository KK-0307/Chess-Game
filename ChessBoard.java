

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ChessBoard extends JPanel {

    private Board chessBoard;
    private JLabel status;
    private int xCoordinate;
    private int yCoordinate;
    private boolean chosenChessPiece = false;

    // Game constants
    public static final int COURT_WIDTH = 1400;
    public static final int COURT_HEIGHT = 800;
    private static final int SQUARE_DIM = 80;
    private static final int TOP_OFFSET = 40;
    private static final int LEFT_OFFSET = 206;

    public ChessBoard(JLabel status) {

        setBorder((BorderFactory.createLineBorder(Color.PINK)));

        setFocusable(true);

        chessBoard = new Board();
        this.status = status;

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent event) {
                chessBoard.updateMove();
                Point point = event.getPoint();

                int x = (point.y - TOP_OFFSET) / 80;
                int y = (point.x - LEFT_OFFSET) / 80;

                if (x < 8 && x >= 0 && y >= 0 && y < 8) {
                    if (chosenChessPiece) {
                        if (chessBoard.isTheMoveValid(xCoordinate, yCoordinate, x, y)) {
                            chessBoard.moveChessPiece(xCoordinate, yCoordinate, x, y, true);
                        } else {
                            chessBoard.deselectChessPiece(x, y);
                        }

                        chosenChessPiece = false;
                    } else {
                        boolean success = chessBoard.selectChessPiece(x, y);
                        if (success) {
                            chosenChessPiece = true;
                            xCoordinate = x;
                            yCoordinate = y;
                        }
                    }
                }

                chessBoard.updateMove();
                updateStatus();
                repaint();
            }
        });
    }

    public void resetGame() {
        chessBoard.resetBoard();
        status.setText("It is White's Turn to Play");
        repaint();

        requestFocusInWindow();
    }

    private void updateStatus() {
        if (chessBoard.getPlayerTurn()) {
            status.setText("It is White's Turn to Play!");
            if (chessBoard.getCheck()) {
                status.setText("Under Check, the turn goes to White!");
                if (chessBoard.getCheckmate()) {
                    status.setText("Checkmate! Pink is the Winner!");
                }
            }
        } else {
            status.setText("It is Pink's Turn to Play!");
            if (chessBoard.getCheck()) {
                status.setText("Under Check, the turn goes to Pink!");
                if (chessBoard.getCheckmate()) {
                    status.setText("Checkmate! White is the Winner!");
                }
            }
        }
    }

    private int[][] numberOfCapturedPieces(
            List<Piece> whiteCaptured, List<Piece> pinkCaptured, int[][] total
    ) {

        for (Piece chessPiece : whiteCaptured) {
            switch (chessPiece.getType()) {
                case "Pawn":
                    total[0][0]++;
                    break;
                case "Queen":
                    total[0][1]++;
                    break;
                case "Rook":
                    total[0][2]++;
                    break;
                case "Knight":
                    total[0][3]++;
                    break;
                case "Bishop":
                    total[0][4]++;
                    break;
                case "King":
                    total[0][5]++;
                    break;
                default:
                    break;
            }
        }

        for (Piece chessPiece : pinkCaptured) {
            switch (chessPiece.getType()) {
                case "Pawn":
                    total[1][0]++;
                    break;
                case "Queen":
                    total[1][1]++;
                    break;
                case "Rook":
                    total[1][2]++;
                    break;
                case "Knight":
                    total[1][3]++;
                    break;
                case "Bishop":
                    total[1][4]++;
                    break;
                case "King":
                    total[1][5]++;
                    break;
                default:
                    break;
            }
        }

        return total;
    }

    private String getImagePath(String path, boolean isWhite) {
        String piece = "chess_pieces/";
        switch (path) {
            case "Pawn":
                piece = piece + "pawn";
                break;
            case "Queen":
                piece = piece + "queen";
                break;
            case "Rook":
                piece = piece + "rook";
                break;
            case "Knight":
                piece = piece + "knight";
                break;
            case "Bishop":
                piece = piece + "bishop";
                break;
            case "King":
                piece = piece + "king";
                break;
            default:
                break;
        }

        if (!isWhite) {
            piece = piece + "_pink.png";
        } else {
            piece = piece + "_white.png";
        }

        return piece;
    }

    @Override
    public void paintComponent(Graphics g) {

        Toolkit tool = Toolkit.getDefaultToolkit();
        super.paintComponent(g);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int color = (i + j) % 2;
                Piece chessPiece = chessBoard.getChessPiece(i, j);
                boolean moves = chessBoard.doesMoveExist(i, j);

                if (color == 1) {
                    g.setColor(new Color(231, 84, 128));
                } else {
                    g.setColor(new Color(252, 251, 244));
                }

                if (moves) {
                    g.setColor(new Color(105, 105, 105));
                }

                g.fillRect(
                        SQUARE_DIM * j + LEFT_OFFSET, SQUARE_DIM * i + TOP_OFFSET, SQUARE_DIM,
                        SQUARE_DIM
                );

                if (chessPiece != null) {
                    String imagePath = getImagePath(chessPiece.getType(), chessPiece.isWhite());
                    Image image = tool.getImage(imagePath);
                    g.drawImage(
                            image, SQUARE_DIM * j + LEFT_OFFSET + 10,
                            SQUARE_DIM * i + TOP_OFFSET + 10,
                            this
                    );
                }
            }
        }

        List<Piece> whiteCaptured = chessBoard.getWhiteCaptured();
        List<Piece> pinkCaptured = chessBoard.getPinkCaptured();
        int[][] total = new int[2][6];

        for (int[] num : total) {
            Arrays.fill(num, 0);
        }

        g.setColor(Color.PINK);
        total = numberOfCapturedPieces(whiteCaptured, pinkCaptured, total);
        HashMap<Integer, String> convertIntToPiece = new HashMap<Integer, String>();
        convertIntToPiece.put(0, "Pawn");
        convertIntToPiece.put(1, "Queen");
        convertIntToPiece.put(2, "Rook");
        convertIntToPiece.put(3, "Knight");
        convertIntToPiece.put(4, "Bishop");
        convertIntToPiece.put(5, "King");

        Font f = g.getFont();
        Font newF = f.deriveFont(f.getSize() * 1.2F);
        g.setFont(newF);

        g.drawString("Pink's Captured Pieces", 850, 40);
        g.drawString("White's Captured Pieces", 20, 40);

        for (int i = 0; i < total.length; i++) {
            int displayed = 0;
            for (int j = 0; j < total[i].length; j++) {
                int num = total[i][j];
                String type = convertIntToPiece.get(j);

                if (num != 0) {
                    if (i == 0) {
                        String imagePath = getImagePath(type, true);
                        Image image = tool.getImage(imagePath);
                        g.drawImage(image, 880, 60 + 70 * displayed, this);
                        String times = "x" + num;
                        g.drawString(times, 940, 100 + 70 * displayed);
                    } else {
                        String imagePath = getImagePath(type, false);
                        Image image = tool.getImage(imagePath);
                        g.drawImage(image, 40, 60 + 70 * displayed, this);
                        String times = "x" + num;
                        g.drawString(times, 100, 100 + 70 * displayed);
                    }

                    displayed++;
                }
            }
        }

        g.setFont(new Font("Georgia", Font.BOLD, 14));
        g.drawString("Welcome to Kayan's Pink Inspired Chess Game", 1040, 46);
        g.setFont(new Font("Georgia", Font.ITALIC, 12));
        g.drawString("As you'll come to notice this chess game is a little different", 1050, 80);
        g.drawString("than the normal one, both visually and functionally.", 1050, 92);
        g.drawString("If you are a little like me, someone who doesn't know all the", 1050, 104);
        g.drawString("intricacies of chess and sometimes forgets the different moves,", 1050, 116);
        g.drawString("then fear not, because this is the chess game for you!", 1050, 128);
        g.setFont(new Font("Georgia", Font.BOLD, 14));
        g.drawString("How to play the game:", 1040, 158);
        g.setFont(new Font("Georgia", Font.ITALIC, 12));
        g.drawString("1. White will have the first move", 1050, 192);
        g.drawString("2. To move any desired chess piece, click on the piece", 1050, 206);
        g.drawString("3. Stuck? Click on the piece and potential moves will appear", 1050, 220);
        g.drawString("4. To move the piece, click on one of the grey squares", 1050, 234);
        g.drawString("5. The first person to checkmate, wins the game", 1050, 248);
        g.setFont(new Font("Georgia", Font.BOLD, 14));
        g.drawString("Note:", 1040, 278);
        g.setFont(new Font("Georgia", Font.ITALIC, 12));
        g.drawString("1. On the bottom the status will be shown",1050, 306);
        g.drawString("2. A piece can only be moved if it is your turn", 1050, 320);
        g.drawString("3. The captured pieces will be shown on either side of board", 1050, 334);
        g.drawString("4. To reset the game from the start, click the reset button", 1050, 348);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
