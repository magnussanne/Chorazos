package Objects;

import Objects.Solution;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SolutionPermutation implements Interface.SolutionPermutation {
    private List<Solution> solutionList;
    private double energy;

    public SolutionPermutation(List<Solution> solutionList) {
        this.solutionList = solutionList;
        energy = getEnergy();
    }

    public double getEnergy() {
        double energy = 0;

        for(Solution s : solutionList) {
            energy += s.getEnergy();
        }

        return energy;
    }

    public void modify(int NUMBER_CHANGES) {
        Random rand = new Random();

        for(int i=0; i<NUMBER_CHANGES; i++) {
            int r = rand.nextInt(solutionList.size());
            solutionList.get(r).modify();
        }
    }
}
