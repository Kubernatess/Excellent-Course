package bean;

public class Course {
	private String id;
	private String name;
	private String description;
	private String major;
	private String team;
	private String teacherIdentity;
	public String getTeacherIdentity() {
		return teacherIdentity;
	}
	public void setTeacherIdentity(String teacherIdentity) {
		this.teacherIdentity = teacherIdentity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
