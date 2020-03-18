package Objects;

import Interface.Focus;
import Interface.Project;

public class Student implements Interface.Student {
    private String name;
    private int number;
    private Focus study;
    private Project[] preference;

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
        return preference[index];
    }

    public void setPreference(int index, Project project) {
        this.preference[index] = project;
    }

    public String toString() {
        String out = "Name: " + getName();
        out += ", Number: " + getNumber();
        out += ", Focus: " + getFocus();
        return out;
    }

    public static void main(String args[]) {
        Student s = new Student("John Smith", 12345, Focus.CS);

        System.out.println("Name: " + s.getName());
        System.out.println("Number: " + s.getNumber());
        System.out.println("Focus: " + s.getFocus());
        System.out.println("String: " + s.toString());
    }
}
