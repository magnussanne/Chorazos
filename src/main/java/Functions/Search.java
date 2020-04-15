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

public class Search {
    private static List<Solution> solutionList = new ArrayList<>();
    private static List<SolutionPermutation> solutionPermList = new ArrayList<>();
    public SolutionPermutation hillClimb(SolutionPermutation s0) {
        SolutionPermutation s1 = new SolutionPermutation(s0);
        for(int i =0; i<100; i++) {
            s1.modify(10);
            solutionPermList.add(s1);

        }
        return s0;
    }
    public static void main(String[] args) throws FileNotFoundException {
        SolutionPermutation s0 = new SolutionPermutation(solutionList);
        SolutionPermutation s1 = new SolutionPermutation(s0);
        for(int i =0; i<100; i++) {
            s1.modify(10);
            solutionPermList.add(s1);

        }
        System.out.println(solutionPermList);
    }
}
