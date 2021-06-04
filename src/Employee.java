import java.io.Serializable;

public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public String emp_name;
	public int emp_id;
	public String emp_designation;
	
	Employee() {
		
	};
	
	Employee(String emp_name, int emp_id, String emp_designation) {
		this.emp_name = emp_name;
		this.emp_id = emp_id;
		this.emp_designation = emp_designation;
	}
	
	@Override
	public String toString() {
		return "Name: " + emp_name + "\nID: " + emp_id + "\nDesignation: " + emp_designation + '\n'; 
	}

}
