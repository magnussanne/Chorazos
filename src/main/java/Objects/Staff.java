package Objects;

import Interface.Focus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Staff implements Interface.Staff {
	private static final int Number_Templates = 6;
	private Random rand = new Random();
	
	private String name;
	private String[] activity;
	private String[] area;
	private Focus focus;
	private List<Project> projects = new ArrayList<>(ng );
	
	public Staff(String name, String activity, String area, Focus focus){
			this.name = name;
			setActivity(activity);
			setArea(area);
			this.focus = focus;

			createProjects();
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

	private void createProjects() {
		String fullTitle;
		String[] titleStart = {"using excel to analys patterns in ", "security analysis of ",
				"using rfid to improve ", "using ai to streamline ", "worshipping cthulu in ", "view of dagon in "};

		int i = rand.nextInt(Number_Templates);
		fullTitle = titleStart[i] + area[0];
		Project project1 = new Project(fullTitle, focus);

		projects.add(project1);

		i += Number_Templates / 3;
		i %= Number_Templates;

		if (area.length > 1) {
			i = rand.nextInt(Number_Templates);
			fullTitle = titleStart[i] + area[1];
			Project project2 = new Project(fullTitle, focus);

			projects.add(project2);

			i = rand.nextInt(Number_Templates);
			fullTitle = titleStart[i] + area[0];
			Project project3 = new Project(fullTitle, focus);

			projects.add(project3);
		}

		if (area.length > 1) {
			i = rand.nextInt(Number_Templates);
			fullTitle = titleStart[i] + area[1];
			Project project2 = new Project(fullTitle, focus);

			projects.add(project2);

			i = rand.nextInt(Number_Templates);
			fullTitle = titleStart[i] + area[2];
			Project project3 = new Project(fullTitle, focus);

			projects.add(project3);
		} else {

			i = rand.nextInt(Number_Templates);
			fullTitle = titleStart[i] + area[0];
			Project project2 = new Project(fullTitle, focus);

			projects.add(project2);

			i = rand.nextInt(Number_Templates);
			fullTitle = titleStart[i] + area[0];
			Project project3 = new Project(fullTitle, focus);

			projects.add(project3);
		}
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

		for(Project p : s.getProjects()) {
			System.out.println(p.toString());
		}
	}
}
