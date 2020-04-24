package bean;

public class Record {
	private String courseID;
	private String tabIndex;
	private int columnIndex;
	private String studentIdentity;
	private int hitcount;
	private float score;
	public int getHitcount() {
		return hitcount;
	}
	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getTabIndex() {
		return tabIndex;
	}
	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}
	public int getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	public String getStudentIdentity() {
		return studentIdentity;
	}
	public void setStudentIdentity(String studentIdentity) {
		this.studentIdentity = studentIdentity;
	}
	
}
