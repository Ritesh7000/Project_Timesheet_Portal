package logIn_Dashboard;

public class viewUserData {
		String user_name,selected_date,task_name,task_description,project_manager,project_name,time_spent;
		public viewUserData(String user_name,String selected_date,String task_name,String task_description,String project_manager,String project_name,String time_spent)
		{
			this.user_name = user_name;
			this.selected_date = selected_date;
			this.task_name = task_name;
			this.task_description = task_description;
			this.project_manager = project_manager;
			this.project_name = project_name;
			this.time_spent = time_spent;
		}
		
		public String getuser_name() {
			return user_name;
		}
		
		public String getselected_date() {
			return selected_date;
		}
		
		public String gettask_name() {
			return task_name;
		}
		
		public String gettask_description() {
			return task_description;
		}
		
		public String getproject_manager() {
			return project_manager;
		}
		
		public String getproject_name() {
			return project_name;
		}
		
		public String gettime_spent() {
			return time_spent;
		}
		
}
