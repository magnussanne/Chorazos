package Functions;

import IO.Input.CSV.ReadProjects;
import IO.Input.CSV.ReadStudents;
import Objects.Project;
import Objects.Solution;
import Objects.SolutionPermutation;
import Objects.Student;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Visualization extends JPanel {
    Color background = Color.BLACK;
    Color drawings = Color.CYAN;

    private List<Student> studentList;
    private List<Project> projectList;
    private SolutionPermutation solution;
    private static int Generation;

    public Visualization(List<Student> studentList, List<Project> projectList) {
        setPreferredSize(new Dimension(500, 500));
        this.Generation = 1;
        this.studentList = studentList;
        this.projectList = projectList;

        setBackground(background);
        repaint();
    }

    public void drawSolution(SolutionPermutation solution) {
        this.solution = solution;
        repaint();
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D g = (Graphics2D) graphics;
        drawChromosome(g);
    }

    private void drawChromosome(Graphics2D g) {
        int studentHeight = (getHeight()-30) / (this.studentList.size()+1);
        int projectHeight = (getHeight()-30) / (this.projectList.size()+1);

        g.setColor(drawings);
        g.drawString("Generation: " + this.Generation++, 30, 30);

        for(int i=1; i<=this.studentList.size(); i++) {
            g.fillOval(30, studentHeight*i + 30, 3, 3);
        }

        for(int i=1; i<=this.projectList.size(); i++) {
            g.fillOval(getWidth()-30, projectHeight*i + 30, 3, 3);
        }

        connectSolutions(g);
    }

    private void connectSolutions(Graphics2D g) {
        int studentHeight = (getHeight()-30) / (this.studentList.size()+1);
        int projectHeight = (getHeight()-30) / (this.projectList.size()+1);
        int count = 1;

        for(Solution s : this.solution.getSolutionList()) {
            int startX = 30;
            int endX = getWidth()-30;

            int startY = studentHeight * count + 30;
            int endY = projectHeight * (this.projectList.indexOf(s.getProject())+1) + 30;

            g.drawLine(startX, startY, endX, endY);

            count++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<Student> studentList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();
        List<Solution> solutionList = new ArrayList<>();

        ReadProjects.Read("project.csv", projectList);
        ReadStudents.Read("student.csv", studentList, projectList);
        Visualization visual = new Visualization(studentList, projectList);

        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("Genetic Algorithms");
            frame.setResizable(false);
            frame.add(visual, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        GeneticAlgorithm ga = new GeneticAlgorithm(visual);
        ga.search(studentList, projectList);
    }
}