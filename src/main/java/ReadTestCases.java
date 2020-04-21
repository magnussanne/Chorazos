import Functions.GeneticAlgorithm;
import Functions.HillClimbing;
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
        ReadStaff.Read("staff.csv", staffList, projectList);
        ReadStudents.Read("student.csv", studentList, projectList);

        for (Student s : studentList) {
            solutionList.add(new Solution(s, projectList, solutionList));
        }

        SolutionPermutation s0 = new SolutionPermutation(solutionList);

        SolutionPermutation s1 = new SolutionPermutation(s0);

        System.out.println("Hill Climbing:");
        HillClimbing hill = new HillClimbing();
        s1 = hill.solve(s0);

        System.out.println(s0.getEnergy() + " -> " + s1.getEnergy());
        int[] preferenceArray = s1.getPreferenceInfo();
        System.out.println(preferenceArray[1] + " students got an average of their " + preferenceArray[0] + " choice," +
                " while " + preferenceArray[2] + " students did not get any of their choices");

        System.out.println("\nSimulated Annealing:");
        SimulatedAnnealing annealing = new SimulatedAnnealing();
        SolutionPermutation s2 = annealing.solve(s0);

        System.out.println(s0.getEnergy() + " -> " + s2.getEnergy());
        int[] preferenceArray2 = s2.getPreferenceInfo();
        System.out.println(preferenceArray2[1] + " students got an average of their " + preferenceArray2[0] + " choice," +
                " while " + preferenceArray2[2] + " students did not get any of their choices");

        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.search(studentList, projectList);
    }
}