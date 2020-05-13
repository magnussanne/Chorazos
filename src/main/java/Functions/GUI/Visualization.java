package Functions.GUI;

import Objects.Project;
import Objects.Solution;
import Objects.SolutionPermutation;
import Objects.Student;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
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
        double studentHeight = ((double) getHeight()-120) / ((double) this.studentList.size()+1);
        double projectHeight = ((double) getHeight()-120) / ((double) this.projectList.size()+1);

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

            String[] temp = solution.getPreferenceSummary().split("\n");
            stringLen = (int) g.getFontMetrics().getStringBounds(temp[0], g).getWidth();
            start = getWidth()/2 - stringLen/2;
            g.drawString(temp[0], start, getHeight()-45);

            stringLen = (int) g.getFontMetrics().getStringBounds(temp[1], g).getWidth();
            start = getWidth()/2 - stringLen/2;
            g.drawString(temp[1], start, getHeight()-30);

            stringLen = (int) g.getFontMetrics().getStringBounds(temp[2], g).getWidth();
            start = getWidth()/2 - stringLen/2;
            g.drawString(temp[2], start, getHeight()-15);
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
        double studentHeight = ((double) getHeight()-120) / ((double) this.studentList.size()+1);
        double projectHeight = ((double) getHeight()-120) / ((double) this.projectList.size()+1);

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
}