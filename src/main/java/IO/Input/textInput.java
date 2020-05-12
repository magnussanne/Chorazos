package IO.Input;

import Objects.Project;
import Objects.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class textInput {
    private Scanner sc;
    private String delim;
    private int nameIndex;
    private int numberIndex;
    private int gpaIndex;
    private int preferenceStartIndex;

    public textInput(File file, String delim) throws FileNotFoundException {
        this.sc = new Scanner(file);
        this.sc.useDelimiter("\n");
        this.delim = delim;

        TitleList();
    }

    public void TitleList(){
        String[] titleString = sc.next().split(this.delim);
        for(int i = 0; i<25; i++){
            if(titleString[i].toLowerCase() == "student")
                this.nameIndex = i;
            else if(titleString[i].toLowerCase() == "student number")
                this.numberIndex = i;
            else if(titleString[i].toLowerCase() == "gpa")
                this.gpaIndex = i;
            else if(titleString[i].toLowerCase() == "1")
                this.preferenceStartIndex = i;
        }
    }

    public void Read(List<Student> studentList, List<Project> projectList) {
        while(this.sc.hasNext()) {
            String[] tokens = sc.next().split(this.delim);
            List<Project> preference = new ArrayList<>();

            String name = tokens[nameIndex];
            int number = Integer.parseInt(tokens[numberIndex]);
            double gpa;
            if(gpaIndex == -1)
                gpa = 3;
            else
                gpa = Double.parseDouble(tokens[gpaIndex]);


            for(int i=preferenceStartIndex; i<preferenceStartIndex + 20; i++) {
                try {
                    Project project = null;

                    for (Project p : projectList) {
                        if (p.getTitle().equals(tokens[i])) {
                            project = p;
                        }
                    }

                    if (project == null) {
                        project = new Project(tokens[i]);
                        projectList.add(project);
                    }

                    preference.add(project);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    i = 23;
                }
            }

            Student s = new Student(name, number, gpa, preference);
            studentList.add(s);
        }
    }

    public boolean stopError() {
        return false;
    }

    public String getStopError() {
        return "Stop Reason";
    }

    public boolean gpaPresent() {
        return false;
    }

    public String getUnusedColumns() {
        return "List Unused Columns";
    }
}