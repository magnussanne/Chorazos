package Interface;

public interface Student {
    String getName();
    Long getNumber();
    Focus getFocus();
    Project getPreference(int index);
    void setPreference(int index, Project project);
}
