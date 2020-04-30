package IO.Input.CSV;

import Interface.Focus;
import Objects.Project;
import Objects.Student;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ReadStudents extends Read {
    public static void Read(String FileName, List studentList, List<Project> projectList) throws FileNotFoundException {
        Scanner sc = setScanner(FileName);

        while(sc.hasNext()) {
            String[] tokens = sc.next().split(",");
            Student student = new Student(tokens[0], Integer.parseInt(tokens[1]), Focus.getFocus(tokens[2]));

            for(int i=3; i<tokens.length; i++) {
                for(int j=0; j<projectList.size(); j++) {
                    Project p = projectList.get(j);

                    if(tokens[i].equals(p.getId().toString())) {
                        student.addPreference(p);
                    }
                }
            }

            studentList.add(student);
        }
    }

    public static void Read(File file, List studentList, List<Project> projectList) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\n");

        while(sc.hasNext()) {
            String[] tokens = sc.next().split(",");
            Student student = new Student(tokens[0], Integer.parseInt(tokens[1]), Focus.getFocus(tokens[2]));

            for(int i=3; i<tokens.length; i++) {
                for(int j=0; j<projectList.size(); j++) {
                    Project p = projectList.get(j);

                    if(tokens[i].equals(p.getId().toString())) {
                        student.addPreference(p);
                    }
                }
            }

            studentList.add(student);
        }
    }
}
