package Objects;

import Interface.Focus;

import java.util.UUID;

import static Interface.Focus.CSDS;
import static java.util.UUID.randomUUID;

public class Project implements Interface.Project {
    private UUID id;
    private String title;
    private Focus focus;

    public Project(String id, String title, Focus focus) {
        this.id = UUID.fromString(id);
        this.title = title;
        this.focus = focus;
    }

    public Project(String title, Focus focus) {
        this.id = randomUUID();
        this.title = title;
        this.focus = focus;
    }

    public Project(String title) {
        this.title = title;
        this.focus = CSDS;
    }

    public UUID getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public Focus getFocus() {
        return focus;
    }

    public String toString() {
        return getId() + "," + getTitle() + "," + getFocus();
    }

    public static void main(String[] args) {
        Project p = new Project("Creating an AI to cause mayhem", Focus.CS);
        System.out.println(p.toString());
    }
}