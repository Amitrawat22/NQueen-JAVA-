package nqueen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPanel extends JPanel {
    private static final int ROW = 8;
    private static final int WIDTH = 600;
    private static final int CELL = WIDTH / ROW;

    private boolean[][] board = new boolean[ROW][ROW];
    private boolean solving = false;

    public BoardPanel() {
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!solving && SwingUtilities.isLeftMouseButton(e)) {
                    solving = true;
                    new Thread(() -> {
                        solve(0);
                    }).start();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw grid
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= WIDTH; i += CELL) {
            g.drawLine(i, 0, i, WIDTH);
            g.drawLine(0, i, WIDTH, i);
        }

        // Draw queens
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < ROW; col++) {
                if (board[row][col]) {
                    g.setColor(Color.RED);
                    g.fillOval(col * CELL + 10, row * CELL + 10, CELL - 20, CELL - 20);
                }
            }
        }
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < col; i++)
            if (board[row][i]) return false;

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j]) return false;

        for (int i = row, j = col; i < ROW && j >= 0; i++, j--)
            if (board[i][j]) return false;

        return true;
    }

    private boolean solve(int col) {
        if (col >= ROW) return true;

        for (int i = 0; i < ROW; i++) {
            if (isSafe(i, col)) {
                board[i][col] = true;
                repaint();
                sleep(200);

                if (solve(col + 1)) return true;

                board[i][col] = false;
                repaint();
                sleep(200);
            }
        }
        return false;
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
