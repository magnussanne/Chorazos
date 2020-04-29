package Functions;

import Objects.Project;
import Objects.Solution;
import Objects.SolutionPermutation;
import Objects.Student;

import java.util.ArrayList;
import java.util.List;

public class SimulatedAnnealing implements Search {
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
                visual.drawSolution(s1);
            }
        }

        return s0;
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
        int numberChanges = 30;

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
