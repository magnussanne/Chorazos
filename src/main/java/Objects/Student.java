package Objects;

import Interface.Focus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static Interface.Focus.CSDS;

public class Student implements Interface.Student {
    private String name;
    private int number;
    private Focus study;
    private double GPA;
    private List<Project> preference = new ArrayList<>();


    public Student(String name, int number, Focus study) {
        this.name = name;
        this.number = number;
        this.study = study;
        Random randGPA = new Random();
        this.GPA = 2.0*randGPA.nextDouble()+2.0;
    }

    public String getName() {
        return this.name;
    }

    public int getNumber() {
        return this.number;
    }

    public Focus getFocus() {
        return study;
    }

    public double getGPA() { return GPA; }

    public Project getPreference(int index) {
        return preference.get(index);
    }

    public String toString() {
        String out = getName() + "," + getNumber() + "," + getFocus() + "," + getGPA();

        for(Project p : preference) {
            out += "," + p.getId();
        }

        return out;
    }

    public void addPreference(Project project) {
        preference.add(project);
    }

    public void setProjects(List<Project> projects, int numberPreferences) {
        for(int i=0; i<numberPreferences; i++) {
            Random rand = new Random();
            int num;
            do {
                 num = (int) Math.round(rand.nextGaussian() * 108 + 325);
            } while(num < 0 || num > 749);

            Project p = projects.get(num);

            if(preference.contains(p)) {
                i--;
            } else {
                if (p.getFocus() == CSDS || p.getFocus() == getFocus())
                    preference.add(i, p);
                else
                    i--;
            }
        }
    }
}
