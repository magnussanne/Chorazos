import Functions.Visualization;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class run {
    public static void main(String[] args) {
        run gui = new run();
        gui.createGUI();
    }

    private void createGUI(){
        JFrame mainWindow = new JFrame("Chorazos");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(1000, 720);
        mainWindow.setLayout(new GridLayout(1, 2));

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Genetic Algorithm", gaPanel());
        tabbedPane.addTab("Simulated Annealing", saPanel());
        tabbedPane.addTab("Hill Climbing", hcPanel());

        mainWindow.add(tabbedPane);
        mainWindow.add(new Visualization(new ArrayList<>(), new ArrayList<>()));
        mainWindow.setVisible(true);
    }

    private JPanel gaPanel() {
        JPanel container = new JPanel();
        container.add(loadFileButton());

        return container;
    }

    private JPanel saPanel() {
        JPanel container = new JPanel();
        container.add(loadFileButton());

        return container;
    }

    private JPanel hcPanel() {
        JPanel container = new JPanel();
        container.add(loadFileButton());

        return container;
    }

    private JButton loadFileButton() {
        JButton loadFile = new JButton("Load Inputs");

        return loadFile;
    }

    private JButton startSearchButton() {
        JButton runButton = new JButton("Run");

        return runButton;
    }
}
