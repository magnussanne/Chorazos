package Objects;

import Interface.Focus;

import static Interface.Focus.CSDS;

public class Project implements Interface.Project {
    private String title;
    private Focus focus;

    public Project(String title, Focus focus) {
        this.title = title;
        this.focus = focus;
    }

    public Project(String title) {
        this.title = title;
        this.focus = CSDS;
    }

    public String getTitle() {
        return title;
    }

    public Focus getFocus() {
        return focus;
    }

    public String toString() {
        String out = "Title: " + getTitle();
        return out + "\tFocus: " + getFocus() + "\n";
    }

    public static void main(String[] args) {
        Project p = new Project("Creating an AI to cause mayham", Focus.CS);
        System.out.println(p.toString());
    }
}