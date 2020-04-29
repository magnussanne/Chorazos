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

        container.add(loadFileButton());

        container.add(startSearchButton(ga));
        return container;
    }

    private JPanel saPanel(Visualization visual) {
        JPanel container = new JPanel();
        SimulatedAnnealing sa = new SimulatedAnnealing(visual);

        container.add(loadFileButton());

        container.add(startSearchButton(sa));
        return container;
    }

    private JPanel hcPanel(Visualization visual) {
        JPanel container = new JPanel();
        HillClimbing hc = new HillClimbing(visual);

        container.add(loadFileButton());

        container.add(startSearchButton(hc));
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
