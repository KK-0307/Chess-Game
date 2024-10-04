

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGame implements Runnable {

    public static void main(String[] args) {

        ImageIcon icon = new ImageIcon("files/chess_image.png");
        JPanel panel = new JPanel();

        JLabel l = new JLabel("Welcome to Kayan's Chess Game!!! :D" +
                " Instructions will be found on the " +
                "right hand side of the board for your convenience." +
                " When you are ready to play, close the " +
                "pop-up or press OK");
        l.setBounds(0,0,200, 64);
        l.setFont(new Font("Georgia", Font.BOLD, 12));
        l.setHorizontalAlignment(SwingConstants.CENTER);

        JOptionPane.showMessageDialog(null,l, "Welcome Window", JOptionPane.PLAIN_MESSAGE,
                icon);

        SwingUtilities.invokeLater(new ChessGame());
    }

    @Override
    public void run() {
        final JFrame frame = new JFrame("Kayan's Chess Game");
        frame.setLocation(0, 0);

        final JPanel statPanel = new JPanel();
        frame.add(statPanel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        statPanel.add(status);

        final ChessBoard board = new ChessBoard(status);
        frame.add(board, BorderLayout.CENTER);

        final JPanel controlPanel = new JPanel();
        frame.add(controlPanel, BorderLayout.NORTH);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.resetGame();
            }
        });

        controlPanel.add(reset);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        board.resetGame();
    }
}
