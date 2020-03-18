package Objects;

import Interface.Focus;
import Interface.Project;

public class Student implements Interface.Student {
    private String name;
    private Long number;
    private Focus study;
    private Project[] preference;

    public String getName() {
        return this.name;
    }

    public Long getNumber() {
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
}
