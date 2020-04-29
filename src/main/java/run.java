import Functions.*;
import IO.Input.CSV.ReadProjects;
import IO.Input.CSV.ReadStudents;
import Objects.Project;
import Objects.Student;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class run {
    private List<Student> studentList;
    private List<Project> projectList;
    private Visualization visual;

    public static void main(String[] args) {
        run gui = new run();
        gui.createGUI();
    }

    private void createGUI(){
        JFrame mainWindow = new JFrame("Chorazos");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(1000, 720);
        mainWindow.setLayout(new GridLayout(1, 2));

        this.visual = new  Visualization();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Genetic Algorithm", gaPanel(visual));
        tabbedPane.addTab("Simulated Annealing", saPanel(visual));
        tabbedPane.addTab("Hill Climbing", hcPanel(visual));

        mainWindow.add(tabbedPane);
        mainWindow.add(visual);
        mainWindow.setVisible(true);
    }

    private JPanel gaPanel(Visualization visual) {
        JPanel container = new JPanel();
        GeneticAlgorithm ga = new GeneticAlgorithm(visual);

        JLabel pLabel = new JLabel();
        pLabel.setText("Population Size");
        JLabel mLabel = new JLabel();
        mLabel.setText("Percentage to be culled");
        JLabel nLabel = new JLabel();
        nLabel.setText("Percentage to be mated");
        JLabel rLabel = new JLabel();
        rLabel.setText("Number of iterations without improvement before terminating");
        JLabel eLabel = new JLabel();
        eLabel.setText("Chance of Mutation");
        JLabel gpaLabel = new JLabel();
        gpaLabel.setText("Influence of GPA");

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
        c.gridy = 1;
        container.add(pLabel, c);
        c.gridx = 1;
        container.add(p, c);
        JSlider m = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        container.add(mLabel, c);
        c.gridx = 1;
        container.add(m, c);
        JSlider n = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        container.add(nLabel, c);
        c.gridx = 1;
        container.add(n, c);
        JSlider r = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        container.add(rLabel, c);
        c.gridx = 1;
        container.add(r, c);
        JSlider e = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        container.add(eLabel, c);
        c.gridx = 1;
        container.add(e, c);
        JSlider gpa = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        container.add(gpaLabel, c);
        c.gridx = 1;
        container.add(gpa, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 7;
        c.anchor = GridBagConstraints.PAGE_END;
        container.add(startSearchButton(ga), c);
        JButton export = new JButton("Export");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        container.add(export, c);
        return container;
    }

    private JPanel saPanel(Visualization visual) {
        JPanel container = new JPanel();
        SimulatedAnnealing sa = new SimulatedAnnealing(visual);

        JLabel changesLabel = new JLabel();
        changesLabel.setText("Number of Changes per iteration");
        JLabel tempLabel = new JLabel();
        tempLabel.setText("Initial Temperature");
        JLabel changeRateLabel = new JLabel();
        changeRateLabel.setText("Change in Temperature");
        JLabel gpaLabel = new JLabel();
        gpaLabel.setText("Influence of GPA");

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
        c.gridy = 1;
        container.add(changesLabel, c);
        c.gridx = 1;
        container.add(changes, c);
        JSlider temp = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        container.add(tempLabel, c);
        c.gridx = 1;
        container.add(temp, c);
        JSlider changeRate = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        container.add(changeRateLabel, c);
        c.gridx = 1;
        container.add(changeRate, c);
        JSlider gpa = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        container.add(gpaLabel, c);
        c.gridx = 1;
        container.add(gpa, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.PAGE_END;
        container.add(startSearchButton(sa), c);
        JButton export = new JButton("Export");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        container.add(export, c);
        return container;
    }

    private JPanel hcPanel(Visualization visual) {
        JPanel container = new JPanel();
        HillClimbing hc = new HillClimbing(visual);

        JLabel changesLabel = new JLabel();
        changesLabel.setText("Number of Changes per iteration");
        JLabel gpaLabel = new JLabel();
        gpaLabel.setText("Influence of GPA");
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
        container.add(changesLabel, c);
        c.gridx = 1;
        container.add(changes, c);
        JSlider gpa = new JSlider();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        container.add(gpaLabel, c);
        c.gridx = 1;
        container.add(gpa, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.PAGE_END;
        container.add(startSearchButton(hc), c);
        JButton export = new JButton("Export");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        container.add(export, c);
        return container;
    }

    private JButton loadFileButton() {
        JButton loadFile = new JButton("Load Inputs");

        loadFile.addActionListener(e -> {
            this.projectList = new ArrayList<>();
            this.studentList = new ArrayList<>();

            try {
                ReadProjects.Read("project.csv", this.projectList);
                ReadStudents.Read("student.csv", this.studentList, this.projectList);

                this.visual.loadValues(this.studentList, this.projectList);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        return loadFile;
    }

    private JButton startSearchButton(Search algorithm) {
        JButton runButton = new JButton("Run");

        runButton.addActionListener(e -> {
            if(projectList == null || studentList == null) {
                System.out.println("Error: Null List");
            } else {
                algorithm.solve(this.studentList, this.projectList);
            }
        });

        return runButton;
    }
}
