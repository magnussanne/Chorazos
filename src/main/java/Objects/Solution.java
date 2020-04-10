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
        if(hcProjectAllocation() || hcStream()) {
            return 1;
        }

        return scPreference();
    }

    @Override
    public String toString() {
        return getStudent().getName() + ", " + getStudent().getGPA() + ", " + getProject().getTitle() + ", " + getEnergy();
    }

    //  Tests if the student is in the correct stream to do the project
    private boolean hcStream() {
        if(student.getFocus().isCompatible(project.getFocus())) {
            return false;
        } else {
            return true;
        }
    }

    private double scPreference(){
        int index = 11;
        int noOfProjects = 10;
        for(int i = 0; i < noOfProjects; i++){
            if(project == student.getPreference(i))
                index = i;
        }

        return ((double) index ) / 11;
    }

    //  Tests if the project is allocated to multiple people
    private boolean hcProjectAllocation() {
        int count = 0;

        for(Solution s : solutionList) {
            if(s.getProject() == getProject()) {
                count++;
            }
        }

        if(count > 1)
            return true;
        else
            return false;
    }
}
