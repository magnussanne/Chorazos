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
        tabbedPane.addTab("Genetic Algorithm", gaPanel());
        tabbedPane.addTab("Simulated Annealing", saPanel());
        tabbedPane.addTab("Hill Climbing", hcPanel());

        mainWindow.add(tabbedPane);
    }
    private static JPanel gaPanel() {
        JPanel container = new JPanel();
        container.add(new Label());
        container.setLayout(new GridLayout(1, 2));
        container.add(new Visualization(new ArrayList<>(), new ArrayList<>()));
        return container;
    }
    private static JPanel saPanel() {
        JPanel container = new JPanel();
        container.add(new Label());
        container.setLayout(new GridLayout(1, 2));
        container.add(new Visualization(new ArrayList<>(), new ArrayList<>()));
        return container;
    }
    private static JPanel hcPanel() {
        JPanel container = new JPanel();
        container.add(new Label());
        container.setLayout(new GridLayout(1, 2));
        container.add(new Visualization(new ArrayList<>(), new ArrayList<>()));
        return container;
    }
}
