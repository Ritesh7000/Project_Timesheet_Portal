package logIn_Dashboard;

public class Manager_data {
	String project_name,project_id;
	public Manager_data(String project_name,String project_id){
		this.project_name = project_name;
		this.project_id = project_id;
	}
	public String getproject_name() {
		return project_name;
	}
	public String getproject_id() {
		return project_id;
	}
}
