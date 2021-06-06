import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class EmployeeManagement {
	
	public static void main(String[] args) {	
		
		backMeUp();				
		new ValidateUser();		
//		String empLevel = ValidateUser.empLevel;
//		String myName = ValidateUser.name;
//		displayRoleWise(empLevel, myName);
	}
	
	public static void displayRoleWise(String empLevel, String myName){
		Scanner sc = new Scanner(System.in);
		
		int choice = 1;
		
		switch (empLevel) {
			case "HR":				
				while (choice != 4) {
					System.out.println("---------------------------");
					System.out.println("1. Add Employee");
					System.out.println("2. Delete Employee");
					System.out.println("3. View All Employees");
					System.out.println("4. Logout");
					System.out.println("---------------------------");
					System.out.print("Enter Your Choice: ");
					choice = sc.nextInt();	
					sc.nextLine();
					if (choice == 1) {
						System.out.print("Enter Name: ");
						String name = sc.nextLine();
						System.out.print("Enter Employee ID: ");
						int emp_id = sc.nextInt();
						sc.nextLine();
						System.out.print("Enter Designation: ");
						String dsgn = sc.nextLine();
						int tl_id = 0, manager_id = 0;
						
						ArrayList<Employee> empList = readDataFromFile();
						Employee emp = null;
						
						if (dsgn.equalsIgnoreCase("employee")){
							System.out.print("Enter Team Leader ID to be Assigned: ");
							tl_id = sc.nextInt();sc.nextLine();
							emp = new Employee(name, dsgn, emp_id, tl_id, manager_id);
						}
						
						else if (dsgn.equalsIgnoreCase("tl")) {
							System.out.print("Enter Manager ID to be Assigned: ");
							manager_id = sc.nextInt();sc.nextLine();
							emp = new TL(name, dsgn, emp_id, tl_id, manager_id);
						}
						
						else if (dsgn.equalsIgnoreCase("manager")) {
							emp = new Manager(name, dsgn, emp_id, tl_id, manager_id);
						}
						
						else if (dsgn.equalsIgnoreCase("hr")) {
							emp = new HR(name, dsgn, emp_id, tl_id, manager_id);
						}
						
						else {
							System.out.println("Please Enter Correct Designation !");
						}
						
						empList.add(emp);
						writeDataToFile(empList);
						System.out.println("---------------------------");
						emp.setPassword();
						
					}
					if (choice == 2) {
						System.out.print("Enter Employee ID to Delete: ");
						int id = sc.nextInt();sc.nextLine();
						ArrayList<Employee> empList = readDataFromFile();
						for (int i = 0; i < empList.size(); i++) 
							if (empList.get(i).emp_id == id)
								empList.remove(i);
						
						writeDataToFile(empList);	
						System.out.println("Selected Employee Deleted Successfully");
					}
					if (choice == 3) {			
						ArrayList<Employee> empList = readDataFromFile();
						for (int i = 0; i < empList.size(); i++)
							System.out.println(empList.get(i).toString());
					}
				}
				break;
				
			case "Manager":
				while (choice != 3) {
					System.out.println("1. View Team Leaders Under Me Details");
					System.out.println("2. View Employees Under Me TL");
					System.out.println("3. Logout");
					System.out.print("Enter Your Choice: ");
					choice = sc.nextInt(); sc.nextLine();
					ArrayList<Employee> empList = readDataFromFile();
					System.out.println("---------------------------");
					
					if (choice == 1) {
						Manager.viewTLUnderMe(empList, myName);
					}
					
					if (choice == 2) {
						System.out.print("Enter TL Name: ");
						String tl_name = sc.nextLine();
						TL.viewEmployeeUnderMe(empList, tl_name);
					}
					System.out.println("---------------------------");
				}
				break;
				
			case "TL":
				while (choice != 2) {
					System.out.println("1. View Employee Under Me Details");
					System.out.println("2. Logout");
					System.out.print("Enter Your Choice: ");
					choice = sc.nextInt(); sc.nextLine();
					System.out.println("---------------------------");
					
					if (choice == 1) {
						ArrayList<Employee> empList = readDataFromFile();
						TL.viewEmployeeUnderMe(empList, myName);
					}
					System.out.println("---------------------------");
				}
				break;
				
			case "Employee":
				while (choice != 2) {
					System.out.println("1. View My Details");
					System.out.println("2. Logout");
					System.out.print("Enter Your Choice: ");
					choice = sc.nextInt(); sc.nextLine();
					System.out.println("---------------------------");
					if (choice == 1) {
						System.out.print("Enter your Employee ID: ");
						int id = sc.nextInt();sc.nextLine();
						ArrayList<Employee> empList = readDataFromFile();
						boolean flag = true;
						for (int i = 0; i < empList.size(); i++) 
							if (empList.get(i).emp_id == id) {
								System.out.println(empList.get(i).toString());
								flag = false;
								break;
							}
						if (flag)
							System.out.println("Employee ID Not Found! Re-Enter");
					}
					System.out.println("---------------------------");
				}
				
				break;
		}
		sc.close();
	}
	
	public static void writeDataToFile(ArrayList<Employee> empList) {
		try {
			FileOutputStream fos = new FileOutputStream(new File("data/Employee.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(empList);
            oos.close();
            fos.close();	
		}
		catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error writing initializing stream");
        } 
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Employee> readDataFromFile() {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		
		try {
			FileInputStream fis = new FileInputStream(new File("data/Employee.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            empList = (ArrayList<Employee>) ois.readObject();
            fis.close();
            ois.close();
		}
		catch (ClassNotFoundException e) {
			System.out.println("Class not Found");
		} catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            // System.out.println("No Data to Read From File");
        } 
		
		return empList;
	}
	
	public static void backMeUp(){
		Timer time = new Timer();             
		Backup bk = new Backup(); 			
        time.schedule(bk, 0, 5000); // 5000ms is 5 seconds (60 X 60 X 1000) for 1 hour
        // this backups the file every 5 second
	}
}