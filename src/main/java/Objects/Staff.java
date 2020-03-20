package Objects;

import Interface.Focus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Staff implements Interface.Staff {
	private Random rand = new Random();
	
	private String name;
	private String[] activity;
	private String[] area;
	private Focus focus;
	private List<Project> projects = new ArrayList<>();
	
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
		String[] titleStart = {
				"Using excel to analyse patterns in ",
				"Security analysis of ",
				"Using rfid to improve ",
				"Using ai to streamline ",
				"Worshipping cthulu in ",
				"View of dagon in "
		};

		int numberProjects = rand.nextInt(5) + 1;

		for(int count=0; count<numberProjects; count++) {
			Project p;

			int template = rand.nextInt(titleStart.length);
			int activityIndex = rand.nextInt(activity.length);
			String title = titleStart[template] + activity[activityIndex];

			int f = rand.nextInt(2);
			if(f == 0) {
				p = new Project(title, focus);
			} else {
				p = new Project(title, Focus.CSDS);
			}

			projects.add(p);
		}
	}

	@Override
	public String toString() {
		String out = "Name: " + getName();
		out += "\tActivity: " + getActivity();
		out += "\tArea: " + getArea();
		out += "\tFocus: " + getFocus();

		for(Project p : projects) {
			out += "\t" + p.getTitle();
		}

		return out + "\n";
	}

	public static void main(String[] args) {
		Staff s = new Staff("John", "Java, MatLab, Lisp", "Artificial Intelligence", Focus.CS);
		System.out.println(s.toString());

		for(Project p : s.getProjects()) {
			System.out.println(p.toString());
		}
	}
}
