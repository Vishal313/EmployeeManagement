import java.util.ArrayList;

public class Manager extends Employee{
	
	private static final long serialVersionUID = 1L;

	public Manager(String emp_name, String emp_designation, int emp_id, int tl_id, int manager_id) {
		super(emp_name, emp_designation, emp_id, tl_id, manager_id);
	}
	
	public static void viewTLUnderMe(ArrayList<Employee> empList, String myName) {
		int mng_id = 0;
		
		for (int i = 0; i < empList.size(); i++) 
			if (empList.get(i).emp_name.equalsIgnoreCase(myName))
				mng_id = empList.get(i).emp_id;
		
		for (int i = 0; i < empList.size(); i++) 
			if (empList.get(i).manager_id == mng_id)
				System.out.println(empList.get(i).toString());
	}
}
