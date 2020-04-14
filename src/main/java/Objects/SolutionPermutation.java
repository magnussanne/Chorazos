package Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolutionPermutation implements Interface.SolutionPermutation {
    private List<Solution> solutionList;

    public SolutionPermutation(SolutionPermutation other) {
        solutionList = new ArrayList<>();

        for(Solution s : other.getSolutionList()) {
            solutionList.add(new Solution(s));
        }
    }

    public SolutionPermutation(List<Solution> solutionList) {
        this.solutionList = solutionList;
    }

    public double getEnergy() {
        double energy = 0;

        for(Solution s : solutionList) {
            energy += s.getEnergy();
        }

        return energy;
    }

    public double getFitness() {
        double fitness = 0;

        for(Solution s : solutionList) {
            fitness += s.getFitness();
        }

        return fitness;
    }

    public void modify(int NUMBER_CHANGES) {
        Random rand = new Random();

        for(int i=0; i<NUMBER_CHANGES; i++) {
            int r = rand.nextInt(solutionList.size());
            solutionList.get(r).modify();
        }
    }

    public List<Solution> getSolutionList() {
        return solutionList;
    }
}
