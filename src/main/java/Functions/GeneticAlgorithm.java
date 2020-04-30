package Functions;

import Objects.*;

import javax.swing.*;
import java.util.*;

public class GeneticAlgorithm implements Search {
    private int P = 1000;         //  Population size
    private double M = 0.325;     //  Cull bottom M%
    private double N = 1;         //  Mate top N%
    private int R = 15;           //  Stop after R iterations without improvement
    private int E = 15;           //  % Chance of mutation

    private Visualization visual;

    public GeneticAlgorithm(){
        visual = null;
    }
    public GeneticAlgorithm(Visualization visual) {
        this.visual = visual;
    }

    public SolutionPermutation solve(List<Student> studentList, List<Project> projectList) {
        List<SolutionPermutation> population = generateInitialPopulation(studentList, projectList);
        SolutionPermutation temp;

        for(int j=0; j<R; j++){
            temp = population.get(P-1);
            update(population);

            if(temp.getFitness() < population.get(P-1).getFitness()) {
                j = 0;
            }
        }

        return population.get(P-1);
    }

    public void setParameters(List<JSlider> sliders) {
        this.P = sliders.get(0).getValue();
        this.M = ((double) sliders.get(1).getValue() / 100);
        this.N = ((double) sliders.get(2).getValue() / 100);
        this.R = sliders.get(3).getValue();
        this.E = sliders.get(4).getValue();

        int GPA = sliders.get(5).getValue();
    }

    private List<SolutionPermutation> generateInitialPopulation(List<Student> studentList, List<Project> projectList) {
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

        if(visual != null) {
            visual.drawSolution(population.get(P-1));
        }

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
