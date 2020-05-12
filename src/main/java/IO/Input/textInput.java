package IO.Input;

import Objects.Project;
import Objects.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class textInput {
    private final int MAX_NUMBER_COLUMNS = 50;

    private Scanner sc;
    private String delim;
    private String[] titleString;
    private int nameIndex;
    private int numberIndex;
    private int gpaIndex;
    private int preferenceStartIndex;
    private int preferenceEndIndex;

    public textInput(File file, String delim) throws FileNotFoundException {
        this.sc = new Scanner(file);
        this.sc.useDelimiter("\n");
        this.delim = delim;

        this.nameIndex = -1;
        this.numberIndex = -1;
        this.gpaIndex = -1;
        this.preferenceStartIndex = -1;

        TitleList();
    }

    public void TitleList(){
        this.titleString = sc.next().split(this.delim);
        int currentMax = -1;
        int currentMin = -1;

        for(int i=0; i<MAX_NUMBER_COLUMNS; i++) {
            try {
                if(titleString[i].toLowerCase().equals("student"))
                    this.nameIndex = i;
                else if(titleString[i].toLowerCase().equals("student number"))
                    this.numberIndex = i;
                else if(titleString[i].toLowerCase().equals("gpa"))
                    this.gpaIndex = i;
                else {
                    try {
                        int t = Integer.parseInt(titleString[i]);

                        if(t > currentMax || currentMax == -1) {
                            currentMax = t;
                            this.preferenceEndIndex = i;
                        }
                        if(t < currentMin || currentMin == -1) {
                            currentMin = t;
                            this.preferenceStartIndex = i;
                        }
                    } catch (NumberFormatException e) {}
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                i = MAX_NUMBER_COLUMNS;
            }
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

            for(int i=this.preferenceStartIndex; i<this.preferenceEndIndex; i++) {

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
                    i = this.preferenceEndIndex;
                }
            }

            Student s = new Student(name, number, gpa, preference);
            studentList.add(s);
        }
    }

    public boolean stopError() {
        if(this.nameIndex == -1) {
            return true;
        } else if(this.numberIndex == -1) {
            return true;
        } else if(this.preferenceStartIndex == -1) {
            return true;
        }

        return false;
    }

    public String getStopError() {
        String out = "";
        if(this.nameIndex == -1) {
            out += "Student Name, ";
        }
        if(this.numberIndex == -1) {
            out += "Student Number, ";
        }
        if(this.preferenceStartIndex == -1) {
            out += "Project Preferences (Please ensure preference titles are in number values, not text), ";
        }

        return out.substring(0, out.length()-2);
    }

    public boolean gpaPresent() {
        return !(this.gpaIndex == -1);
    }

    public boolean unusedColumns() {
        for(int i=0; i<titleString.length; i++) {
            if(!usedColumn(i))
                return true;
        }

        return false;
    }

    private boolean usedColumn(int index) {
        if(this.nameIndex == index) {
            return true;
        } else if(this.numberIndex == index) {
            return true;
        } else if(this.gpaIndex == index) {
            return true;
        } else if(this.preferenceStartIndex <= index && index <= this.preferenceEndIndex) {
            return true;
        }

        return false;
    }

    public String getUnusedColumns() {
        this.titleString[nameIndex] = "";
        this.titleString[numberIndex] = "";

        if(gpaIndex != -1)
            this.titleString[gpaIndex] = "";

        for(int i=this.preferenceStartIndex; i<=this.preferenceEndIndex; i++) {
            this.titleString[i] = "";
        }

        String out = "";
        for(String s: this.titleString) {
            if(!s.equals("")) {
                out += s + ", ";
            }
        }

        return out.substring(0, out.length()-2);
    }
}