package Functions;

import Objects.SolutionPermutation;

public class SimulatedAnnealing {
    private double InitialTemp = 900000;
    private double tempChange = 0.999;


    public SolutionPermutation solve(SolutionPermutation s0) {
        double temp = InitialTemp;

        while(temp > 1) {
            SolutionPermutation s1 = modify(s0, temp);

            if(s1.getEnergy() < s0.getEnergy())
                s0 = s1;

            temp *= tempChange;
        }

        return s0;
    }

    public SolutionPermutation modify(SolutionPermutation s0, double temp) {
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
