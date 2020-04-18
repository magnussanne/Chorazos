package Functions;

import Objects.Solution;
import Objects.SolutionPermutation;

import java.util.ArrayList;
import java.util.List;

public class HillClimbing {
    private static List<Solution> solutionList = new ArrayList<>();
    private static List<SolutionPermutation> solutionPermList = new ArrayList<>();

    public SolutionPermutation solve(SolutionPermutation s0) {
        SolutionPermutation s1;
        int runLoop = 0;

        while(runLoop == 0) {
            s1 = iterate(s0);
            if(s0 == s1)
                runLoop = 1;
            else{
                s0 = s1;
            }
        }

        return s0;
    }

    private SolutionPermutation iterate(SolutionPermutation s0) {
        for(int i =0; i<100; i++) {
            SolutionPermutation s1 = new SolutionPermutation(s0);
            s1.modify(50);
            solutionPermList.add(s1);
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