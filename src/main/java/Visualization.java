import Objects.Project;
import Objects.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Visualization extends JPanel {
    private List<Student> studentList;
    private List<Project> projectList;

    public Visualization(List<Student> studentList, List<Project> projectList) {
        this.studentList = studentList;
        this.projectList = projectList;

        setBackground(Color.BLACK);
    }
}