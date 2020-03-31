package Objects;

import java.util.List;
import java.util.Random;

public class Solution implements Interface.Solution {
    private static Random rand = new Random();

    private static List<Project> projectList;
    private Student student;
    private Project project;

    public Solution(Student student, List<Project> projectList) {
        this.student = student;
        this.projectList = projectList;
        modify();
    }

    public Solution(Student student) {
        this.student = student;
        modify();
    }

    public void modify() {
        if(project != null)
            projectList.add(project);

        int index = rand.nextInt(projectList.size());
        project = projectList.remove(index);
    }

    @Override
    public void setStudent(Student student) {

    }

    @Override
    public Student getStudent() {
        return student;
    }

    @Override
    public void setProject(Project project) {

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
}
