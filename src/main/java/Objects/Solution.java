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

    public Solution(Student student, List<Project> projectList, List<Solution> solutionList) {
        this.student = student;
        Solution.projectList = projectList;

        do {
            modify();
        } while(invalid(solutionList));
    }

    public void modify() {
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

    public void setProject(Project project) {
        this.project = project;
    }

    public static List<Project> getProjectList() {
        return projectList;
    }

    public boolean invalid(List<Solution> solutionList) {
        if(hcStream() || hcProjectAllocation(solutionList)) {
            return true;
        }

        return false;
    }

    @Override
    public double getEnergy(List<Solution> solutionList, double gpaImportance) {
        if(invalid(solutionList))
            return 1;

        double energy = scPreference() * (student.getGPA() * gpaImportance);
        energy /= (gpaImportance * 630);
        return energy;
    }


    @Override
    public double getFitness(List<Solution> solutionList, double gpaImportance) {
        return 1 - getEnergy(solutionList, gpaImportance);
    }

    @Override
    public String toString() {
        return getStudent().getNumber() + ", " + getStudent().getName() + ", " + getProject().getTitle();
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

    public int scPreference(){
        int index = 50;
        for(int i = 0; i < projectList.size(); i++){
            if(project == student.getPreference(i))
                index = i + 1;
        }

        return index;
    }

    public String getPreference(){
        int index = 21;
        for(int i = 0; i < projectList.size(); i++){
            if(project == student.getPreference(i))
                index = i + 1;
        }

        String preference = "";

        if(index == 21){
            preference = "NA";
        }else{
            preference += index;
        }

        return preference;
    }
}
