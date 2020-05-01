package Functions;

import Objects.Project;
import Objects.SolutionPermutation;
import Objects.Student;

import javax.swing.*;
import java.util.List;

public interface Search {
    SolutionPermutation solve(List<Student> studentList, List<Project> projectList);
    void setParameters(List<JSlider> sliders);
}
