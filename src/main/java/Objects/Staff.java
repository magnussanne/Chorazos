package Objects;

import Interface.Focus;
import Interface.Project;

public class Staff implements Interface.Staff {
	
	private String staffMember;
	private String researchActivity;
	private String researchArea;
	private Focus specialFocus;
	private Project[] projects;
	
	public Staff(String staffMember, String researchActivity, String researchArea, Focus specialFocus){
			this.staffMember = staffMember;
			this.researchActivity = researchActivity;
			this.researchArea = researchArea;
			this.specialFocus = specialFocus;
	}

	public String getName() {
		return staffMember;
	}

	public String getActivity(int index) {
		return researchActivity;
	}

	public String getArea(int index) {
		return researchArea;
	}

	public Focus getFocus() {
		return specialFocus;
	};

	public Project getProject(int index) {
		return projects[index];
	}

	@Override
	public String toString() {
		return "name: " +staffMember+ ", Research Activity: "+researchActivity+ ", Research Area: "+researchArea+ ", Special Focus: "+specialFocus +"\n";
	}

	public static void main(String[] args) {
		Staff s = new Staff("John", "Java", "", Focus.CS);
		System.out.println(s.toString());
	}
}
