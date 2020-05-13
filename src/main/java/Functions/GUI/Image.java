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
}
