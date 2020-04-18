import Functions.CreateStudents;
import IO.Output.CSV.WriteToCSVFile;
import IO.Output.Excel.GenerateProject;
import IO.Output.Excel.GenerateStudentPref;
import IO.Input.ReadStaffMembers;
import Objects.Project;
import Objects.Staff;
import Objects.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CreateTestCase {
    private static final int Number_Students = 500;
    private static final int Number_Preferences = 10;

    public static void main(String[] args) throws IOException {
        List<Staff> staff = ReadStaffMembers.getStaffDetails(Number_Students/2);
        List<Student> students = CreateStudents.createStudents(Number_Students);
        List<Project> projects = new ArrayList<>();

        for(Staff s : staff) {
            projects.addAll(s.getProjects());
        }

        Collections.shuffle(projects);

        for(Student s : students) {
            s.setProjects(projects, Number_Preferences);
        }

        GenerateProject.writeProjectsToSpreadsheet(staff);
        GenerateStudentPref.writeStudentPrefToSpreadsheet(students, Number_Students);

        WriteToCSVFile.Write(staff, "staff.csv");
        WriteToCSVFile.Write(projects, "project.csv");
        WriteToCSVFile.Write(students, "student.csv");
    }
}
