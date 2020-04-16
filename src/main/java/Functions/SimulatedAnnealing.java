package Functions;

import Objects.SolutionPermutation;

public class SimulatedAnnealing {
    private double InitialTemp = 5000;


    public SolutionPermutation solve(SolutionPermutation s0) {
        double currentEnergy = s0.getEnergy();
        double temp = InitialTemp;

        while(s0.invalid()) {
            s0 = modify(s0, temp);
            currentEnergy = s0.getEnergy();
            temp = temp * 0.03;
            System.out.println(s0.getEnergy());
        }

        return s0;
    }

    private SolutionPermutation modify(SolutionPermutation s0, double temp) {
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
        }    }

    private double calculateProbability(double cur, double next, double temp) {
        if(next < cur)
            return 1;

        return Math.exp((cur-next)/temp);
    }

}
