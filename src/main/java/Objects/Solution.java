package Objects;

import java.util.List;
import java.util.Random;

public class Solution implements Interface.Solution {
    private final static int MAX_ENERGY_PENALTY = 1;

    private static Random rand = new Random();

    private static List<Project> projectList;
    private static List<Solution> solutionList;
    private Student student;
    private Project project;

    public Solution(Student student, List<Project> projectList, List<Solution> solutionList) {
        this.student = student;
        this.projectList = projectList;
        this.solutionList = solutionList;
        modify();
    }

    public void modify() {
        if(project != null)
            projectList.add(project);

        int index = rand.nextInt(projectList.size());
        project = projectList.get(index);
    }

    @Override
    public Student getStudent() {
        return student;
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public double getEnergy() {
        int weight = 1;
        double energy = 0;

        energy += hcProjectAllocation() * weight;
        energy += hcStream() * weight;
        energy += scPreference() * weight;

        return energy;
    }

    @Override
    public String toString() {
        return getStudent().getName() + ", " + getStudent().getGPA() + ", " + getProject().getTitle() + ", " + getEnergy();
    }

    //  Tests if the student is in the correct stream to do the project
    private int hcStream() {
        if(student.getFocus().isCompatible(project.getFocus())) {
            return 0;
        } else {
            return MAX_ENERGY_PENALTY;
        }
    }

    private int scPreference(){
        int index = 11;
        int noOfProjects = 10;
        for(int i = 0; i < noOfProjects; i++){
            if(project == student.getPreference(i))
                index = i;
        }

        return index;
    }

    //  Tests if the project is allocated to multiple people
    private int hcProjectAllocation() {
        int count = 0;

        for(Solution s : solutionList) {
            if(s.getProject() == getProject()) {
                count ++;
            }
        }

        return (count-1) * MAX_ENERGY_PENALTY;
    }
}
