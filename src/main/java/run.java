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


public class run {
    private static final int Number_Preferences = 10;

    public static void main(String[] args) throws IOException {
        List<Staff> staff = ReadStaffMembers.getStaffDetails();
        List<Student> students = CreateStudents.createStudents();
        List<Project> projects = new ArrayList<>();

        for(Staff s : staff) {
            projects.addAll(s.getProjects());
        }

        Collections.shuffle(projects);

        for(Student s : students) {
            s.setProjects(projects, Number_Preferences);
        }

        GenerateProject.writeProjectsToSpreadsheet(staff);
        GenerateStudentPref.writeStudentPrefToSpreadsheet(students, 60);

        WriteToCSVFile.Write(staff, "staff.csv");
        WriteToCSVFile.Write(projects, "project.csv");
        WriteToCSVFile.Write(students, "student.csv");
    }
}
