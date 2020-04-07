package Objects;

import java.util.List;
import java.util.Random;

public class Solution implements Interface.Solution {
    private final static int MAX_ENERGY_PENALTY = 1;

    private static Random rand = new Random();

    private static List<Project> projectList;
    private Student student;
    private Project project;

    public Solution(Student student, List<Project> projectList) {
        this.student = student;
        this.projectList = projectList;
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
    public float getEnergy() {
        return 0;
    }

    @Override
    public String toString() {
        return getStudent().getName() + ", " + getProject().getTitle() + ", " + getEnergy();
    }

    //  Tests if the student is in the correct stream to do the project
    private int constraintSteam() {
        if(student.getFocus().isCompatible(project.getFocus())) {
            return 0;
        } else {
            return MAX_ENERGY_PENALTY;
        }
    }
}
