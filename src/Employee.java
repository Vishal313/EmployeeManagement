import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Scanner;

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

	public void setPassword(){
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
