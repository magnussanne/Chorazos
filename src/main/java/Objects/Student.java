package Objects;

import Interface.Focus;
import Interface.Project;

import java.util.List;
import java.util.Random;

public class Student implements Interface.Student {
    private String name;
    private int number;
    private Focus study;
    private List<Project> preference;

    public Student(String name, int number, Focus study) {
        this.name = name;
        this.number = number;
        this.study = study;
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

    public Project getPreference(int index) {
        return preference.get(index);
    }

    public void setPreference(int index, Project project) {
        this.preference.set(index, project);
    }

    public String toString() {
        String out = "Name: " + getName();
        out += "\tNumber: " + getNumber();
        return out + "\tFocus: " + getFocus();
    }

    public void chooseProjects(List<Project> projects, int numberPreferences) {
        for(int i=0; i<numberPreferences; i++) {
            Project p = projects.get(randomNumber(projects.size()));
            this.preference.set(i, p);
        }
    }

    private static int randomNumber(int max) {
        Random rand = new Random();
        double mean = 2;
        double num;

        do {
            num = (rand.nextGaussian() + mean)*0.25;
        } while (0 > num || num > 1);

        return (int) Math.round(num*max);
    }

    public static void main(String[] args) {
        Student s = new Student("John Smith", 12345, Focus.CS);
        System.out.println(s.toString());
    }
}
