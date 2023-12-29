import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    private static final int SIZE = 15;
    private static final int CELL_SIZE = 30;
    private static final int[][] board = new int[SIZE][SIZE];
    private static String player1, player2;
    private static int currentPlayer = 1;
    private static BoardPanel boardPanel;

    public static void main(String[] args) {

        JFrame frame = new JFrame("OMOK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardPanel = new BoardPanel();
        frame.add(boardPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    private static String getCurrentPlayerName() {
        return (currentPlayer == 1) ? player1 : player2;
    }

    private static boolean checkWin() {
        int player = currentPlayer;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 4; j++) {
                if (board[i][j] == player && board[i][j + 1] == player && board[i][j + 2] == player &&
                        board[i][j + 3] == player && board[i][j + 4] == player) {
                    return true;
                }
            }
        }

        for (int i = 0; i < SIZE - 4; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == player && board[i + 1][j] == player && board[i + 2][j] == player &&
                        board[i + 3][j] == player && board[i + 4][j] == player) {
                    return true;
                }
            }
        }

        for (int i = 0; i < SIZE - 4; i++) {
            for (int j = 0; j < SIZE - 4; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player && board[i + 2][j + 2] == player &&
                        board[i + 3][j + 3] == player && board[i + 4][j + 4] == player) {
                    return true;
                }
            }
        }

        for (int i = 0; i < SIZE - 4; i++) {
            for (int j = 4; j < SIZE; j++) {
                if (board[i][j] == player && board[i + 1][j - 1] == player && board[i + 2][j - 2] == player &&
                        board[i + 3][j - 3] == player && board[i + 4][j - 4] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    static class BoardPanel extends JPanel {
        BoardPanel() {
            setPreferredSize(new Dimension(SIZE * CELL_SIZE, SIZE * CELL_SIZE));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX() / CELL_SIZE;
                    int y = e.getY() / CELL_SIZE;
                    if (x < SIZE && y < SIZE && board[x][y] == 0) {
                        board[x][y] = currentPlayer;
                        if (checkWin()) {
                            JOptionPane.showMessageDialog(BoardPanel.this, "Player " + currentPlayer +" wins!");
                        } else {
                            switchPlayer();
                            repaint();
                        }
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    g.setColor(Color.BLACK);
                    g.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                    if (board[i][j] == 1) {
                        g.setColor(Color.BLACK);
                        g.fillOval(i * CELL_SIZE + 5, j * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                    } else if (board[i][j] == 2) {
                        g.setColor(Color.WHITE);
                        g.fillOval(i * CELL_SIZE + 5, j * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                    }
                }
            }
        }
    }
}