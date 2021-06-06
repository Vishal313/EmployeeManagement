import java.util.ArrayList;

public class Manager extends Employee{
	
	private static final long serialVersionUID = 1L;

	private ArrayList<Integer> empUnderManagerList = new ArrayList<Integer>();
	
	public Manager(String emp_name, int emp_id, String emp_designation) {
		super(emp_name, emp_id, emp_designation);
	}
	
	public void addEmployee(int empID) {
		empUnderManagerList.add(empID);
	}
}
