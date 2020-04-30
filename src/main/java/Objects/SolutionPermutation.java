package Objects;

import Interface.Focus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public double getEnergy(double gpaImportance) {
        double energy = 0;

        for(Solution s : solutionList) {
            energy += s.getEnergy(solutionList, gpaImportance);
        }

        return energy;
    }

    public double getFitness(double gpaImportance) {
        double fitness = 0;

        for(Solution s : solutionList) {
            fitness += s.getFitness(solutionList, gpaImportance);
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

    public SolutionPermutation combine(SolutionPermutation other, int mutationProbability) {
        SolutionPermutation s1 = new SolutionPermutation(this);
        Random rand = new Random();
        int r;

        for(int i=0; i<s1.size(); i++) {
            r = rand.nextInt(2);

            if(r == 1) {
                s1.setGene(i, other.getProject(i));
            }
        }

        r = rand.nextInt(100)+1;
        if(r < mutationProbability)
            s1.modify(1);

        return s1;
    }

    private void setGene(int index, Project project) {
        solutionList.get(index).setProject(project);
    }

    public Project getProject(int index) {
        return solutionList.get(index).getProject();
    }

    public int size() {
        return solutionList.size();
    }

    public double getGpaImportance() {
        return getGpaImportance();
    }

    public List<Solution> getSolutionList() {
        return solutionList;
    }

    public int compare(SolutionPermutation other, double gpaImportance) {
        if(getFitness(gpaImportance) == 0 || other.getFitness(gpaImportance) == 0) {
            return 0;
        }

        if(getFitness(gpaImportance) > other.getFitness(gpaImportance)) {
            return -1;
        } else if(getFitness(gpaImportance) == other.getFitness(gpaImportance)) {
            return 0;
        } else {
            return 1;
        }
    }

    public String toString() {
        String out = "Student Number, Student Name, Student GPA, Project UUID, Project Title\n";
        for(Solution s : solutionList) {
            out += s.toString() + "\n";
        }

        return out;
    }
}
