package nqueen;

import javax.swing.*;

public class NqueenFrame extends JFrame {
    public NqueenFrame() {
        setTitle("N-Queen Visualizer");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BoardPanel panel = new BoardPanel();
        add(panel);
        setVisible(true);
    }
}
