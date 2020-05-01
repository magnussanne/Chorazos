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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Visualization extends JPanel {
    private static DecimalFormat df = new DecimalFormat("#.##");

    private Color background = Color.BLACK;
    private Color drawings = Color.CYAN;

    private List<Student> studentList;
    private List<Project> projectList;
    private SolutionPermutation solution;
    private int solutionSize;
    private static int Generation;
    private int function;

    private long time;

    public Visualization() {
        setPreferredSize(new Dimension(500, 500));
        this.time = System.currentTimeMillis();

        setBackground(Color.WHITE);
        repaint();
    }

    public void loadValues(List<Student> studentList, List<Project> projectList) {
        this.Generation = 1;
        this.studentList = studentList;
        this.projectList = projectList;

        setBackground(background);
    }

    public void drawSolution(int function, SolutionPermutation solution) {
        this.Generation++;
        this.solution = solution;
        this.solutionSize = this.solution.size();
        this.function = function;

        if(System.currentTimeMillis()-time > 10) {
            update(getGraphics());
            time = System.currentTimeMillis();
        }

        repaint();
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D g = (Graphics2D) graphics;

        if(studentList != null) {
            drawChromosome(g);
        }
    }

    private void drawChromosome(Graphics2D g) {
        double studentHeight = ((double) getHeight()-60) / ((double) this.studentList.size()+1);
        double projectHeight = ((double) getHeight()-60) / ((double) this.projectList.size()+1);

        g.setColor(drawings);
        String s = "Generation: " + this.Generation;
        int stringLen = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        int start = getWidth()/2 - stringLen/2;
        g.drawString(s, start, 15);

        if(this.solution != null) {
            if (function == 0) {
                s = "Energy: " + df.format(this.solution.getEnergy());
            } else if (function == 1) {
                s = "Fitness: " + df.format(this.solution.getFitness());
            }

            stringLen = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
            start = getWidth()/2 - stringLen/2;
            g.drawString(s, start, 30);
        }

        for(int i=1; i<=this.studentList.size(); i++) {
            g.fillOval(30, (int) (studentHeight*i) + 45, 3, 3);
        }

        for(int i=1; i<=this.projectList.size(); i++) {
            g.fillOval(getWidth()-30, (int) (projectHeight*i) + 45, 3, 3);
        }

        connectSolutions(g);
    }

    private void connectSolutions(Graphics2D g) {
        double studentHeight = ((double) getHeight()-60) / ((double) this.studentList.size()+1);
        double projectHeight = ((double) getHeight()-60) / ((double) this.projectList.size()+1);

        for(int i=1; i<=solutionSize; i++) {
            Solution s = this.solution.getSolutionList().get(i-1);
            int startX = 30;
            int endX = getWidth()-30;

            double startY = studentHeight * i + 45;
            double endY = projectHeight * (this.projectList.indexOf(s.getProject())+1) + 45;

            g.drawLine(startX, (int) startY, endX, (int) endY);
            i++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<Student> studentList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();

        ReadProjects.Read("project.csv", projectList);
        ReadStudents.Read("student.csv", studentList, projectList);

        Visualization visual = new Visualization();
        visual.loadValues(studentList, projectList);

        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
                ga.solve(studentList, projectList);
                break;
            case 1:
                sa.solve(studentList, projectList);
                break;
            case 2:
                hc.solve(studentList, projectList);
                break;
        }

    }
}