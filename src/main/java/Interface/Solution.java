package Interface;

import java.util.List;

public interface Solution {
    Objects.Student getStudent();
    Objects.Project getProject();
    double getEnergy(List<Objects.Solution> solutionList);
    double getFitness(List<Objects.Solution> solutionList);
}
