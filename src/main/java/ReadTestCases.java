import Functions.Search;
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
        SolutionPermutation s1 = new SolutionPermutation(s0);
        s1.modify(10);

        System.out.println(s0.getEnergy() + " -> " + s1.getEnergy());

        int[] preferenceArray = new int[3];
        preferenceArray = s1.getPreferenceInfo();
        System.out.println(preferenceArray[1] + " students got an average of their " + preferenceArray[0] + " choice," +
                " while " + preferenceArray[2] + " students did not get any of their choices");

        Search search = new Search();
        s1 = search.hillClimb(s0);
        System.out.println(s0.getEnergy() + " -> " + s1.getEnergy());
    }
}
