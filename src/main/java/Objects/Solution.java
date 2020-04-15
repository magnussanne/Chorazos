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
        this.projectList = other.getProjectList();
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

    public static List<Project> getProjectList() {
        return projectList;
    }

    @Override
    public double getEnergy(List<Solution> solutionList) {
        if(hcStream() || hcProjectAllocation(solutionList)) {
            return 1;
        }

        return ((double) scPreference() * student.getGPA()) / 45.1;
    }

    @Override
    public double getFitness(List<Solution> solutionList) {
        return 1 - getEnergy(solutionList);
    }

    @Override
    public String toString() {
        return getStudent().getName() + ", " + df.format(getStudent().getGPA()) + ", " + getProject().getTitle();
    }

    //  Tests if the student is in the correct stream to do the project
    private boolean hcStream() {
        if(student.getFocus().isCompatible(project.getFocus())) {
            return false;
        } else {
            return true;
        }
    }

    //  Tests if the project is allocated to multiple people
    private boolean hcProjectAllocation(List<Solution> solutionList) {
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
