package Interface;

import java.util.List;

public interface Student {
    String getName();
    int getNumber();
    Focus getFocus();
    double getGPA();
    Project getPreference(int index);
    void setProjects(List<Objects.Project> projects, int numberPreferences);
    String toString();
}
