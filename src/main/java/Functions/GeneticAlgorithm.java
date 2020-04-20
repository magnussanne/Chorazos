package Functions;

import IO.Input.CSV.ReadProjects;
import IO.Input.CSV.ReadStaff;
import IO.Input.CSV.ReadStudents;
import Objects.*;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.*;

public class GeneticAlgorithm {
    private static List<Student> studentList = new ArrayList<>();
    private static List<Project> projectList = new ArrayList<>();
    private static List<Solution> solutionList = new ArrayList<>();
    private double Population_Size = 1000;

        public List<Solution> population() throws FileNotFoundException {
            ReadProjects.Read("project.csv", projectList);
            ReadStudents.Read("student.csv", studentList, projectList);

            for(int i = 0; i < Population_Size; i++){
                for (Student s : studentList) {
                    solutionList.add(new Solution(s, projectList, solutionList));
                }
            }
            return solutionList;
        }
}
