//DBから取得した生徒情報が格納されているAdminを継承し、
//そこに生徒のログイン時間フィールドを追加する

package object;

public class Student {
	
	private int id;
	private String name;
	private String password;
	private String department;
	private boolean login_flag;
	private String attendance_time;
	private String leaving_time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public boolean isLogin_flag() {
		return login_flag;
	}
	public void setLogin_flag(boolean login_flag) {
		this.login_flag = login_flag;
	}
	
	public String getAttendance_time() {
		return attendance_time;
	}
	public void setAttendance_time(String attendance_time) {
		this.attendance_time = attendance_time;
	}
	
	public void setLeaving_time(String leaving_time) {
		this.leaving_time = leaving_time;
	}
	public String getLeaving_time() {
		return leaving_time;
	}
}
