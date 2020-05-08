package Functions.GUI;

import javax.swing.*;
import java.awt.*;

public class Image extends JPanel {
    public Image() {
        setPreferredSize(new Dimension(500, 500));

        ImageIcon image = new ImageIcon("src/main/resources/Chorazos.jpg");
        JLabel label = new JLabel("", image, JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.add(new Image(), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
