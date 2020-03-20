package Objects;

import Interface.Focus;

import java.util.List;

public class Staff implements Interface.Staff {
	
	private String name;
	private String[] activity;
	private String[] area;
	private Focus focus;
	private List<Project> projects;
	
	public Staff(String name, String activity, String area, Focus focus){
			this.name = name;
			setActivity(activity);
			setArea(area);
			this.focus = focus;
	}

	public String getName() {
		return name;
	}

	public String getActivity(int index) {
		return activity[index];
	}

	private String getActivity() {
		String out = "";

		for(String s : activity) {
			out += s + ", ";
		}

		return out.substring(0, out.length()-2);
	}

	private void setActivity(String activity) {
		this.activity = activity.split(", ");
	}

	public String getArea(int index) {
		return area[index];
	}

	private String getArea() {
		String out = "";

		for(String s : area) {
			out += s + ", ";
		}

		return out.substring(0, out.length()-2);
	}

	private void setArea(String area) {
		this.area = area.split(", ");
	}

	public Focus getFocus() {
		return focus;
	}

	public Project getProject(int index) {
		return projects.get(index);
	}

	public List<Project> getProjects() {
		return projects;
	}

	public static void addProjects(){
		CreateProjects create = new CreateProjects();
		this. projects = create.createProjects(area, focus);
	}

	@Override
	public String toString() {
		String out = "Name: " + getName() +"\n";
		out += "Activity: " + getActivity()+"\n";
		out += "Area: " + getArea()+"\n";
		return out + "Focus: " + getFocus()+"\n";
	}

	public static void main(String[] args) {
		Staff s = new Staff("John", "Java, MatLab, Lisp", "Artificial Intelligence", Focus.CS);
		System.out.println(s.toString());
	}
}
