package Objects;

import java.util.List;
import java.util.Random;
import java.text.DecimalFormat;

public class Solution implements Interface.Solution {
    private static DecimalFormat df = new DecimalFormat("#.##");
    private static Random rand = new Random();

    private static List<Project> projectList;
    private Student student;
    private Project project;

    public Solution(Solution other) {
        this.student = other.getStudent();
        this.project = other.getProject();
    }

    public Solution(Student student, List<Project> projectList) {
        this.student = student;
        Solution.projectList = projectList;
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
        if(hcStream()) {
            return 1;
        }

        return ((double) scPreference() * student.getGPA()) / 45.1;
    }

    public double getFitness() {
        return 1 - getEnergy();
    }

    @Override
    public String toString() {
        return getStudent().getName() + ", " + df.format(getStudent().getGPA()) + ", " + getProject().getTitle() + ", " + df.format(getEnergy());
    }

    //  Tests if the student is in the correct stream to do the project
    private boolean hcStream() {
        if(student.getFocus().isCompatible(project.getFocus())) {
            return false;
        } else {
            return true;
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
}
