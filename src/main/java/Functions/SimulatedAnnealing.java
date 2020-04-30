package Functions;

import Objects.Project;
import Objects.Solution;
import Objects.SolutionPermutation;
import Objects.Student;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SimulatedAnnealing implements Search {
    private int N = 30;
    private double InitialTemp = 900000;
    private double tempChange = 0.999;

    private Visualization visual;

    public SimulatedAnnealing(){
        visual = null;
    }
    public SimulatedAnnealing(Visualization visual) {
        this.visual = visual;
    }

    public SolutionPermutation solve(List<Student> studentList, List<Project> projectList) {
        SolutionPermutation s0 = createSolutionPermutation(studentList, projectList);
        double temp = InitialTemp;

        while(temp > 1) {
            SolutionPermutation s1 = modify(s0, temp);

            if(s1.getEnergy() < s0.getEnergy())
                s0 = s1;

            temp *= tempChange;

            if(visual != null) {
                visual.drawSolution(0, s1);
            }
        }

        return s0;
    }

    public void setParameters(List<JSlider> sliders) {
        this.N = sliders.get(0).getValue();
        this.InitialTemp = sliders.get(1).getValue();
        this.tempChange = ((double) sliders.get(2).getValue() / 10000);

        int GPA = sliders.get(3).getValue();
    }

    public void resetParameters() {
        int N = 30;
        double InitialTemp = 900000;
        double tempChange = 0.999;
    }

    private SolutionPermutation createSolutionPermutation(List<Student> studentList, List<Project> projectList) {
        List<Solution> solutionList = new ArrayList<>();

        for (Student s : studentList) {
            solutionList.add(new Solution(s, projectList, solutionList));
        }
        SolutionPermutation sp = new SolutionPermutation(solutionList);
        return sp;
    }

    private SolutionPermutation modify(SolutionPermutation s0, double temp) {
        int numberChanges = N;

        for(int count=0; true; count++) {
            SolutionPermutation s1 = new SolutionPermutation(s0);
            s1.modify(numberChanges);

            double probability = calculateProbability(s0.getEnergy(), s1.getEnergy(), temp);

            if(count == 500) {
                if(numberChanges > 1) {
                    numberChanges -= 1;
                    count = 0;
                } else {
                    count ++;
                }
            }

            if (probability > Math.random())
                return s1;
        }
    }

    private double calculateProbability(double cur, double next, double temp) {
        if(next < cur)
            return 1;

        return Math.exp((cur-next)/temp);
    }

}
