package Interface;

public interface Student {
    String getName();
    int getNumber();
    Focus getFocus();
    Project getPreference(int index);
    void setPreference(int index, Project project);
    String toString();
}
