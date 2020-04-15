package Functions;

import IO.Input.CSV.ReadProjects;
import IO.Input.CSV.ReadStaff;
import IO.Input.CSV.ReadStudents;
import Objects.Solution;
import Objects.SolutionPermutation;

import Objects.Student;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Search {
    private static List<Solution> solutionList = new ArrayList<>();
    private static List<SolutionPermutation> solutionPermList = new ArrayList<>();
    public SolutionPermutation hillClimb(SolutionPermutation s0) {

        for(int i =0; i<100; i++) {
            SolutionPermutation s1 = new SolutionPermutation(s0);
            s1.modify(10);
            solutionPermList.add(s1);
            System.out.println(s1.getEnergy());
        }
        return findMinEnergy(solutionPermList);
    }
   public static SolutionPermutation findMinEnergy(List<SolutionPermutation> solutionPermList) {
       SolutionPermutation curr = solutionPermList.get(0);
        for(SolutionPermutation s : solutionPermList) {
           if(s.getEnergy() < curr.getEnergy()) {
                curr = s;

           }
       }
        return curr;
    }
}
