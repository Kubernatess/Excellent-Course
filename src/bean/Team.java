package bean;

import java.util.List;

public class Team {
	private String teacherIdentity;
	private String courseName;
	private List<String> member;
	public String getTeacherIdentity() {
		return teacherIdentity;
	}
	public void setTeacherIdentity(String teacherIdentity) {
		this.teacherIdentity = teacherIdentity;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public List<String> getMember() {
		return member;
	}
	public void setMember(List<String> member) {
		this.member = member;
	}
	
}
