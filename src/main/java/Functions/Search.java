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
    private List<Solution> solutionList = new ArrayList<>();

    public SolutionPermutation hillClimb(SolutionPermutation s0) {
        for(int lastChange = 0; lastChange < 100000; lastChange++) {
            SolutionPermutation s1 = lowestEnergy(createList(s0));

            if(s1.getEnergy() < s0.getEnergy()) {
                s0 = s1;
                lastChange = 0;
            }
        }

        return s0;
    }

    public List<SolutionPermutation> createList(SolutionPermutation s0) {
        List<SolutionPermutation> solutionPermList = new ArrayList<>();

        for(int i=0; i<50; i++) {
            SolutionPermutation s1 = new SolutionPermutation(s0);
            s1.modify(20);
            solutionPermList.add(s1);
        }

        return solutionPermList;
    }

    public SolutionPermutation lowestEnergy(List<SolutionPermutation> solutionPermList) {
        SolutionPermutation s0 = solutionPermList.remove(0);
        double minEnergy = s0.getEnergy();

        for(SolutionPermutation s : solutionPermList) {
            if(s.getEnergy() < minEnergy) {
                minEnergy = s.getEnergy();
                s0 = s;
            }
        }

        return s0;
    }
}
