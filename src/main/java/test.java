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

        ReadProjects.Read("project.csv", projectList);
        ReadStaff.Read( "staff.csv", staffList, projectList);
        ReadStudents.Read("student.csv", studentList, projectList);

        System.out.println(staffList.toString());
        System.out.println(studentList.toString());
        System.out.println(projectList.toString());
    }
}
