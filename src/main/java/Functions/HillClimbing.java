package Functions;

import Objects.Project;
import Objects.Solution;
import Objects.SolutionPermutation;
import Objects.Student;

import java.util.ArrayList;
import java.util.List;

public class HillClimbing implements Search {
    private static List<Solution> solutionList = new ArrayList<>();
    private static List<SolutionPermutation> solutionPermList = new ArrayList<>();

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

    private SolutionPermutation iterate(SolutionPermutation s0) {
        for(int i =0; i<100; i++) {
            SolutionPermutation s1 = new SolutionPermutation(s0);
            s1.modify(50);
            solutionPermList.add(s1);
        }

        return findMinEnergy(solutionPermList);
    }
    private static SolutionPermutation findMinEnergy(List<SolutionPermutation> solutionPermList) {
        SolutionPermutation curr = solutionPermList.get(0);
        for(SolutionPermutation s : solutionPermList) {
            if(s.getEnergy() < curr.getEnergy()) {
                curr = s;
            }
        }
        return curr;
    }
}