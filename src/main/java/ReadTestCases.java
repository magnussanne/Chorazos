import Functions.Search;
import Functions.SimulatedAnnealing;
import Objects.*;
import IO.Input.CSV.*;

import java.io.FileNotFoundException;
import java.util.*;

public class ReadTestCases {
    public static int NUMBER_CHANGES = 5;
    public static int INITIAL_TEMPERATURE = 100;
    public static int TEMPERATURE_CHANGE = 10;
    private int[] preferenceArray;

    private static List<Staff> staffList = new ArrayList<>();
    private static List<Student> studentList = new ArrayList<>();
    private static List<Project> projectList = new ArrayList<>();
    private static List<Solution> solutionList = new ArrayList<>();
    private SolutionPermutation s1;


    public static void main(String[] args) throws FileNotFoundException {
        ReadProjects.Read("project.csv", projectList);
        ReadStaff.Read( "staff.csv", staffList, projectList);
        ReadStudents.Read("student.csv", studentList, projectList);

        for(Student s : studentList) {
            solutionList.add(new Solution(s, projectList));
        }

        SolutionPermutation s0 = new SolutionPermutation(solutionList);

        int[] preferenceArray = s0.getPreferenceInfo();
        System.out.println(preferenceArray[1] + " students got an average of their " + preferenceArray[0] + " choice," +
                " while " + preferenceArray[2] + " students did not get any of their choices");

        SimulatedAnnealing SA = new SimulatedAnnealing();
        SolutionPermutation s1 = SA.solve(s0, 300);

        preferenceArray = s1.getPreferenceInfo();
        System.out.println(preferenceArray[1] + " students got an average of their " + preferenceArray[0] + " choice," +
                " while " + preferenceArray[2] + " students did not get any of their choices");

        System.out.println(s0.getEnergy() + " <---> " + s1.getEnergy());
    }
}
