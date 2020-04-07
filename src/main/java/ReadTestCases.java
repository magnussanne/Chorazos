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

        SolutionPermutation initialSolution = new SolutionPermutation(solutionList);

        for(int i=0; i<1000; i++) {
            SolutionPermutation newSolution = new SolutionPermutation(initialSolution.getList());
            newSolution.modify(NUMBER_CHANGES);

            System.out.println(initialSolution.getList().toString() + "->" + newSolution.getList().toString());

            if(newSolution.getEnergy() < initialSolution.getEnergy()) {
                initialSolution = newSolution;
            }
        }
    }
}
