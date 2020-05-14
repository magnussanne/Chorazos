package Functions.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Image extends JPanel {
    public Image() throws IOException {
        setPreferredSize(new Dimension(500, 500));

        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("Chorazos.jpg");
        ImageIcon image = new ImageIcon(ImageIO.read(stream));
        JLabel label = new JLabel("", image, JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.CENTER);
    }
}
