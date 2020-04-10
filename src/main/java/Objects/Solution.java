package Objects;

import java.util.List;
import java.util.Random;
import java.text.DecimalFormat;

public class Solution implements Interface.Solution {
    private static DecimalFormat df = new DecimalFormat("#.##");
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

    public double getFitness() {
        return 1 - getEnergy();
    }

    @Override
    public String toString() {
        return getStudent().getName() + ", " + df.format(getStudent().getGPA()) + ", " + getProject().getTitle() + ", " + getEnergy();
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

        //  Max GPA is 4.1, and max index value is 11. 4.1*11 gives 45.1,
        //  which we divide with to get a value between 1 and 0
        return ((double) index * student.getGPA() ) / 45.1;
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
