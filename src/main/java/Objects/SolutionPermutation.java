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
            energy += s.getEnergy(solutionList);
        }

        return energy;
    }

    public double getFitness() {
        double fitness = 0;

        for(Solution s : solutionList) {
            fitness += s.getFitness(solutionList);
        }

        return fitness;
    }

    public int[] getPreferenceInfo(){
        int[] preferenceArray;
        preferenceArray = new int[3];
        preferenceArray[0] = 0; // the average preference
        preferenceArray[1] = 0; // the number of students who received one of their preferences
        preferenceArray[2] = 0; // the number of students who did not receive one of their preferences

        for(Solution s : solutionList){
            int pref = s.scPreference();
            if(pref > 10)
                preferenceArray[2]++;
            else{
                preferenceArray[0] += pref;
                preferenceArray[1]++;
            }
        }

        preferenceArray[0] /= preferenceArray[1];

        return preferenceArray;
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
