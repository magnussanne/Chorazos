import Objects.*;
import IO.Input.CSV.*;

import java.io.FileNotFoundException;
import java.util.*;

public class ReadTestCases {
    public static int NUMBER_CHANGES = 5;
    public static int INITIAL_TEMPERATURE = 100;
    public static int TEMPERATURE_CHANGE = 10;

    private static List<Staff> staffList = new ArrayList<>();
    private static List<Student> studentList = new ArrayList<>();
    private static List<Project> projectList = new ArrayList<>();
    private static List<Solution> solutionList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        ReadProjects.Read("project.csv", projectList);
        ReadStaff.Read( "staff.csv", staffList, projectList);
        ReadStudents.Read("student.csv", studentList, projectList);

        for(Student s : studentList) {
            solutionList.add(new Solution(s, projectList, solutionList));
        }

        for(Solution s : solutionList) {
            System.out.println(s.toString());
        }

        SolutionPermutation curr = new SolutionPermutation(solutionList);
        double e1=curr.getEnergy();

        for(int i=INITIAL_TEMPERATURE; i>0; i-=TEMPERATURE_CHANGE) {
            curr.modify(NUMBER_CHANGES);

            double e0 = e1;
            e1 = curr.getEnergy();

            System.out.println(e0 + "->" + e1);
        }
    }
}
