package IO.Input.CSV;

import Interface.Focus;
import Objects.Project;
import Objects.Student;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ReadStudents extends Read {
    public static void Read(String FileName, List list, List<Project> projectList) throws FileNotFoundException {
        Scanner sc = setScanner(FileName);

        while(sc.hasNext()) {
            String[] tokens = sc.next().split(",");
            Student student = new Student(tokens[0], Integer.parseInt(tokens[1]), Focus.getFocus(tokens[2]));

            for(int i=3; i<tokens.length; i++) {
                for(Project p : projectList) {
                    if(tokens[i].equals(p.getId().toString())) {
                        student.addPreference(p);
                    }
                }
            }

            list.add(student);
        }
    }
}
