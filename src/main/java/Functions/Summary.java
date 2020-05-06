package Functions;

import javax.swing.*;
import java.awt.*;

public class Summary extends JPanel {
    public Summary() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.WHITE);
        repaint();
    }

    public static void main(String[] args) {
        Summary summary = new Summary();

        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.add(summary, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }
}
