package Functions;

import Objects.*;

import java.util.*;

public class GeneticAlgorithm {
    private final int P = 1000;     //  Population size
    private final double M = 0.1;   //  Cull bottom M%
    private final double N = 0.1;   //  Mate top N%
    private final int R = 10;       //  Stop after R iterations without improvement
    private final int E = 5;        //  % Chance of mutation

    public SolutionPermutation search(List<Student> studentList, List<Project> projectList) {
        List<SolutionPermutation> population = generateInitialPopulation(studentList, projectList);

        sort(population);
        System.out.println("Lowest Population Fitness: " + population.get(P-1).getFitness() + "\tHighest Population Fitness: " + population.get(0).getFitness());

        update(population);

        sort(population);
        System.out.println("Lowest Population Fitness: " + population.get(P-1).getFitness() + "\tHighest Population Fitness: " + population.get(0).getFitness());

        int j = 0;
        SolutionPermutation temp = population.get(P-1);

        while(j < R){

            temp = population.get(P-1);

            sort(population);
            System.out.println(population.get(0).getFitness() + "\t" + population.get(population.size()-1).getFitness());

            update(population);

            if(temp.getFitness() > population.get(population.size() - 1).getFitness()){
                j++;
            }
            else{
                j = 0;
            }
        }

        return population.get(P-1);
    }

    public List<SolutionPermutation> generateInitialPopulation(List<Student> studentList, List<Project> projectList) {
        List<SolutionPermutation> populationList = new ArrayList<>();

        for(int i = 0; i < P; i++){
            List<Solution> solutionList = new ArrayList<Solution>();

            for (Student s : studentList) {
                solutionList.add(new Solution(s, projectList, solutionList));
            }
            SolutionPermutation sp = new SolutionPermutation(solutionList);
            populationList.add(sp);
        }

        return populationList;
    }

    private void update(List<SolutionPermutation> population) {
        sort(population);
        cull(population);
        breed(population);
    }

    private void sort(List<SolutionPermutation> population) {
        population.sort((o1, o2) -> o1.compare(o2));
    }

    private void cull(List<SolutionPermutation> population) {
        int numberToCull = (int) (population.size() * M);

        for(int i=0; i<numberToCull; i++) {
            population.remove(population.size()-1);
        }
    }

    private void breed(List<SolutionPermutation> population) {
        int numberToBreed = (int) (population.size() * N);
        List<SolutionPermutation> BreedingList = new ArrayList<>();
        Random rand = new Random();

        for(int i=0; i<numberToBreed; i++) {
            BreedingList.add(population.get(i));
        }

        while(population.size() < P) {
            SolutionPermutation mother = BreedingList.get(rand.nextInt(BreedingList.size()));
            SolutionPermutation father = BreedingList.get(rand.nextInt(BreedingList.size()));

            population.add(mother.combine(father, E));
        }
    }
}
