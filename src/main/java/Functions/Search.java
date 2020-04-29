package Functions;

import Objects.Project;
import Objects.SolutionPermutation;
import Objects.Student;

import java.util.List;

public interface Search {
    SolutionPermutation solve(List<Student> studentList, List<Project> projectList);
}
