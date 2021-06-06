import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Scanner;

public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public String emp_name;
	public String emp_designation;
	public int emp_id;
	public int tl_id;
	public int manager_id;
	
	Employee() {
		
	};
	
	public Employee(String emp_name, String emp_designation, int emp_id, int tl_id, int manager_id) {
		super();
		this.emp_name = emp_name;
		this.emp_designation = emp_designation;
		this.emp_id = emp_id;
		this.tl_id = tl_id;
		this.manager_id = manager_id;
	}



	@Override
	public String toString() {
		return "Name: " + emp_name + "\nID: " + emp_id + "\nDesignation: " + emp_designation + '\n'; 
	}

	public void setPassword(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Set Up User Name and Password for New Employee");
		System.out.print("Enter Username: ");
		String username = sc.nextLine();
		System.out.print("Enter Password: ");
		String password = sc.nextLine();
		String hashedPassword = ValidateUser.getMd5(password);
		
		 
		try {
			Writer output = new BufferedWriter(new FileWriter("data/up.txt", true));
			output.append("\n" + username + "," + hashedPassword + "," + emp_designation);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
