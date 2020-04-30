package Functions;

import Objects.Project;
import Objects.Solution;
import Objects.SolutionPermutation;
import Objects.Student;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HillClimbing implements Search {
    private static List<Solution> solutionList = new ArrayList<>();
    private static List<SolutionPermutation> solutionPermList = new ArrayList<>();

    private int N = 50;
    private double gpaImportance = 0.5;

    private Visualization visual;

    public HillClimbing(){
        visual = null;
    }
    public HillClimbing(Visualization visual) {
        this.visual = visual;
    }

    public SolutionPermutation solve(List<Student> studentList, List<Project> projectList) {
        SolutionPermutation s0 = createSolutionPermutation(studentList, projectList);
        SolutionPermutation s1;
        int runLoop = 0;

        while(runLoop == 0) {
            s1 = iterate(s0);
            if(s0 == s1)
                runLoop = 1;
            else{
                s0 = s1;
            }

            if(visual != null) {
                visual.drawSolution(0, s1);
            }
        }

        return s0;
    }

    public void setParameters(List<JSlider> sliders) {
        this.N = sliders.get(0).getValue();
        this.gpaImportance = ((double) sliders.get(0).getValue() / 100);
    }

    public void resetParameters() {
        this.N = 50;
    }

    private SolutionPermutation createSolutionPermutation(List<Student> studentList, List<Project> projectList) {
        List<Solution> solutionList = new ArrayList<>();

        for (Student s : studentList) {
            solutionList.add(new Solution(s, projectList, solutionList));
        }
        SolutionPermutation sp = new SolutionPermutation(solutionList);
        return sp;
    }

    private SolutionPermutation iterate(SolutionPermutation s0) {
        for(int i =0; i<100; i++) {
            SolutionPermutation s1 = new SolutionPermutation(s0);
            s1.modify(N);
            solutionPermList.add(s1);
        }

        return findMinEnergy(solutionPermList);
    }
    private SolutionPermutation findMinEnergy(List<SolutionPermutation> solutionPermList) {
        SolutionPermutation curr = solutionPermList.get(0);
        for(SolutionPermutation s : solutionPermList) {
            if(s.getEnergy(this.gpaImportance) < curr.getEnergy(this.gpaImportance)) {
                curr = s;
            }
        }
        return curr;
    }
}