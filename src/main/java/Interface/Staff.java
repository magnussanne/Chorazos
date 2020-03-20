package Interface;

import java.util.List;

public interface Staff {
    String getName();
    String getActivity(int index);
    String getArea(int index);
    Focus getFocus();
    Project getProject(int index);
    List<Objects.Project> getProjects();
}
