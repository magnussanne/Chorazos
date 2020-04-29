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
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        container.add(loadFileButton(), c);
        JSlider p = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 2;
        container.add(p, c);
        JSlider m = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        container.add(m, c);
        JSlider n = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        container.add(n, c);
        JSlider r = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        container.add(r, c);
        JSlider e = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        container.add(e, c);
        JSlider gpa = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;
        container.add(gpa, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 8;
        c.anchor = GridBagConstraints.PAGE_END;
        container.add(startSearchButton(), c);
        JButton export = new JButton("Export");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 8;
        container.add(export, c);
        return container;
    }

    private JPanel saPanel() {
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        container.add(loadFileButton(), c);
        JSlider changes = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 2;
        container.add(changes, c);
        JSlider temp = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        container.add(temp, c);
        JSlider initial = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        container.add(initial, c);
        JSlider changeRate = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        container.add(changeRate, c);
        JSlider gpa = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        container.add(gpa, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 7;
        c.anchor = GridBagConstraints.PAGE_END;
        container.add(startSearchButton(), c);
        JButton export = new JButton("Export");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 7;
        container.add(export, c);
        return container;
    }

    private JPanel hcPanel() {
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        container.add(loadFileButton(), c);
        JSlider changes = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 2;
        container.add(changes, c);
        JSlider gpa = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        container.add(gpa, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.PAGE_END;
        container.add(startSearchButton(), c);
        JButton export = new JButton("Export");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        container.add(export, c);
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
