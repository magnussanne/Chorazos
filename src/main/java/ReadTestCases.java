import Functions.Search.GeneticAlgorithm;
import Functions.Search.HillClimbing;
import Functions.Search.SimulatedAnnealing;
import Objects.*;
import IO.Input.CSV.*;

import java.io.FileNotFoundException;
import java.util.*;

public class ReadTestCases {
    public static int NUMBER_CHANGES = 5;
    public static int INITIAL_TEMPERATURE = 50;
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

        SolutionPermutation s0;

        System.out.println("Hill Climbing:");
        HillClimbing hill = new HillClimbing();
        s0 = hill.solve(studentList, projectList);

        System.out.println(s0.getEnergy(0.5));
        System.out.println(s0.getPreferenceSummary());

        System.out.println("\nSimulated Annealing:");
        SimulatedAnnealing annealing = new SimulatedAnnealing();
        s0 = annealing.solve(studentList, projectList);

        System.out.println(s0.getEnergy(0.5));
        System.out.println(s0.getPreferenceSummary());

        System.out.println("\nGenetic Algorithm:");
        GeneticAlgorithm ga = new GeneticAlgorithm();
        s0 = ga.solve(studentList, projectList);
        System.out.println(s0.getPreferenceSummary());

        System.out.println(s0.toString());
    }
}