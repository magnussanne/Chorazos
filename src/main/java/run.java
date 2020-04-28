import javax.swing.*;
import java.awt.*;

public class run {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Chorazos");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(720, 500);
        mainWindow.setVisible(true);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Genetic Algorithm", makePanel("Genetic Algorithm goes here"));
        tabbedPane.addTab("Simulated Annealing", makePanel("Simulated Annealing goes here"));
        tabbedPane.addTab("Hill Climbing", makePanel("Hill Climbing goes here"));
        mainWindow.getContentPane().add(tabbedPane);
    }
    private static JPanel makePanel(String text) {
        JPanel p = new JPanel();
        p.add(new Label(text));
        p.setLayout(new GridLayout(1, 1));
        return p;
    }
}
