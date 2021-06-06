import java.util.ArrayList;

public class TL extends Employee{
	
	private static final long serialVersionUID = 1L;

	public static ArrayList<Integer> empUnderTlList = new ArrayList<Integer>(); 

	public TL(String emp_name, int emp_id, String emp_designation) {
		super(emp_name, emp_id, emp_designation);
	}
	
	public static void addEmployee(int empID) {
		empUnderTlList.add(empID);
	}
	
	public void printList() {
		System.out.println(empUnderTlList);
	}
	
	public static void printAkash(ArrayList<Employee> empList) {
		for (int i = 0; i < empList.size(); i++) {
			Employee temp = empList.get(i);
			if (temp.emp_id == 100) {
				System.out.println(temp.toString());
				System.out.println(((TL) temp).empUnderTlList);
			}
		}
	}
	
}
