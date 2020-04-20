package Functions;

import Objects.*;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.*;

public class GeneticAlgorithm {
    private double Population_Size = 1000;

        public List<SolutionPermutation> generateInitialPopulation(List<Student> studentList, List<Project> projectList) {
            List<SolutionPermutation> populationList = new ArrayList<>();

            for(int i = 0; i < Population_Size; i++){
                List<Solution> solutionList = new ArrayList<Solution>();

                for (Student s : studentList) {
                    solutionList.add(new Solution(s, projectList, solutionList));
                }

                SolutionPermutation sp = new SolutionPermutation(solutionList);
                populationList.add(sp);
            }

            return populationList;
        }
}
