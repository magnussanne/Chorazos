import Objects.Project;
import Objects.Staff;
import Objects.Student;
import IO.Input.CSV.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        List<Staff> staffList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();

        ReadStaff.Read( "staff.csv", staffList);
        ReadStudents.Read("student.csv", studentList);
        ReadProjects.Read("project.csv", projectList);

        for(Project s: projectList) {
            System.out.println(s.toString());
        }
    //    System.out.println(studentList.toString());
    //    System.out.println(projectList.toString());
    }
}
