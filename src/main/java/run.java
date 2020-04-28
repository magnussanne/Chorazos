import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class run {
    public static void main(String[] args) {
        createGUI();
    }
    private static void createGUI(){
        JFrame mainWindow = new JFrame("Chorazos");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(1000, 720);
        mainWindow.setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Genetic Algorithm", makePanel("Genetic Algorithm goes here"));
        tabbedPane.addTab("Simulated Annealing", makePanel("Simulated Annealing goes here"));
        tabbedPane.addTab("Hill Climbing", makePanel("Hill Climbing goes here"));

        mainWindow.add(tabbedPane);
    }
    private static JPanel makePanel(String text) {
        JPanel container = new JPanel();
        container.add(new Label(text));
        container.setLayout(new GridLayout(1, 2));
        container.add(new Visualization(new ArrayList<>(), new ArrayList<>()));
        return container;
    }
}
