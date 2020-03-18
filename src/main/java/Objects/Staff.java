package main.java.Objects;
public class Staff {
	
	private String staffMember;
	private String researchActivity;
	private String researchArea;
	private String specialFocus;
	
	public Staff(String staffMember, String researchActivity, String researchArea, String specialFocus){
			this.staffMember = staffMember;
			this.researchActivity = researchActivity;
			this.researchArea = researchArea;
			this.specialFocus = specialFocus;
		}
	@Override
	public String toString() {
		return "name: " +staffMember+ "Research Activity: "+researchActivity+ "Research Area: "+researchArea+ "Special Focus: "+specialFocus; 
	}
}
