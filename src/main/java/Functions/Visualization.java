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
    private List<Solution> solution;
    private int solutionSize;
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
        this.Generation++;
        this.solution = solution.getSolutionList();
        this.solutionSize = this.solution.size();
        repaint();
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D g = (Graphics2D) graphics;
        drawChromosome(g);
    }

    private void drawChromosome(Graphics2D g) {
        double studentHeight = ((double) getHeight()-60) / ((double) this.studentList.size()+1);
        double projectHeight = ((double) getHeight()-60) / ((double) this.projectList.size()+1);

        g.setColor(drawings);
        g.drawString("Generation: " + this.Generation, 30, 30);

        for(int i=1; i<=this.studentList.size(); i++) {
            g.fillOval(30, (int) (studentHeight*i) + 30, 3, 3);
        }

        for(int i=1; i<=this.projectList.size(); i++) {
            g.fillOval(getWidth()-30, (int) (projectHeight*i) + 30, 3, 3);
        }

        connectSolutions(g);
    }

    private void connectSolutions(Graphics2D g) {
        double studentHeight = ((double) getHeight()-60) / ((double) this.studentList.size()+1);
        double projectHeight = ((double) getHeight()-60) / ((double) this.projectList.size()+1);

        for(int i=1; i<=solutionSize; i++) {
            Solution s = this.solution.get(i-1);
            int startX = 30;
            int endX = getWidth()-30;

            double startY = studentHeight * i + 30;
            double endY = projectHeight * (this.projectList.indexOf(s.getProject())+1) + 30;

            g.drawLine(startX, (int) startY, endX, (int) endY);
            i++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<Student> studentList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();
        List<Solution> solutionList = new ArrayList<>();

        ReadProjects.Read("project.csv", projectList);
        ReadStudents.Read("student.csv", studentList, projectList);
        for (Student s : studentList) {
            solutionList.add(new Solution(s, projectList, solutionList));
        }
        SolutionPermutation sp = new SolutionPermutation(solutionList);

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
        SimulatedAnnealing sa = new SimulatedAnnealing(visual);
        HillClimbing hc = new HillClimbing(visual);

        switch (0) {
            case 0:
                ga.search(studentList, projectList);
                break;
            case 1:
                sa.solve(sp);
                break;
            case 2:
                hc.solve(sp);
                break;
        }

    }
}