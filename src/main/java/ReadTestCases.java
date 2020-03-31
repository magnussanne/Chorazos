import Objects.Project;
import Objects.Solution;
import Objects.Staff;
import Objects.Student;
import IO.Input.CSV.*;
import org.apache.poi.ss.formula.functions.T;

import java.io.FileNotFoundException;
import java.util.*;

public class ReadTestCases {
    public static void main(String[] args) throws FileNotFoundException {
        List<Staff> staffList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();

        ReadProjects.Read("project.csv", projectList);
        ReadStaff.Read( "staff.csv", staffList, projectList);
        ReadStudents.Read("student.csv", studentList, projectList);

        List<Solution> solutionsList = new ArrayList<>();

        for(int i=0; i<studentList.size(); i++) {
            Solution s;

            if(i == 0) {
                s = new Solution(studentList.get(i), projectList);
            } else {
                s = new Solution(studentList.get(i));
            }

            solutionsList.add(s);
        }

        for(Solution s : solutionsList) {
            System.out.println(s.toString());
        }
    }
}
