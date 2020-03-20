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
    private List<Project> preference = new ArrayList<>();

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

    public String toString() {
        String out = "Name: " + getName();
        out += "\tNumber: " + getNumber();
        out += "\tFocus: " + getFocus();

        for(Project p : preference) {
            out += "\t" + p.getTitle();
        }

        return out + "\n";
    }

    public void setProjects(List<Project> projects, int numberPreferences) {
        for(int i=0; i<numberPreferences; i++) {
            Project p = projects.get(randomNumber(projects.size()));

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

    private static int randomNumber(int max) {
        Random rand = new Random();
        double mean = 2;
        double num;

        do {
            num = (rand.nextGaussian() + mean);
        } while (0 > num || num > 4);

        return (int) Math.round(num*max*0.25);
    }

    public static void main(String[] args) {
        List<Project> p = new ArrayList();
        p.add(new Project("t1", Focus.DS));
        p.add(new Project("t2", Focus.CS));
        p.add(new Project("t3"));

        Student s = new Student("John Smith", 12345, Focus.CS);
        s.setProjects(p, 2);
        System.out.println(s.toString());
    }
}
