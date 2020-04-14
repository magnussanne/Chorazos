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
            solutionList.add(new Solution(s, projectList));
        }

        for(Solution s : solutionList) {
            System.out.println(s.toString());
        }

        SolutionPermutation s0 = new SolutionPermutation(solutionList);

        for(int i=0; i<100; i++) {
            SolutionPermutation s1 = new SolutionPermutation(s0);
            s1.modify(10);

            System.out.println(s0.getEnergy() + " -> " + s1.getEnergy());
            if(s1.getEnergy() < s0.getEnergy()) {
                s0 = s1;
            }
        }
    }
}
