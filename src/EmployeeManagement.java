import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class EmployeeManagement {
	
	public static void main(String[] args) {	
		
//		backMeUp();				
//		new ValidateUser();		
//		String empLevel = ValidateUser.empLevel;
//		displayRoleWise(empLevel);
		displayRoleWise("HR");
//		Employee tl = new TL("Babu", 23, "HR");
//		((TL) tl).addEmployee(1);
//		((TL) tl).printList();
//		System.out.println(tl.toString());
//		ArrayList<Employee> empList = readDataFromFile();
//		TL.printAkash(empList);
		
	}
	
	public static void displayRoleWise(String empLevel){
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
						int id = sc.nextInt();
						sc.nextLine();
						System.out.print("Enter Designation: ");
						String dsgn = sc.nextLine();
						
						ArrayList<Employee> empList = readDataFromFile();
						
						
						Employee emp = null;
						if (dsgn.equalsIgnoreCase("employee")){
							emp = new Employee(name, id, dsgn);
							
							System.out.print("Assign Employee To the Team Lead ID : ");
							int team_id = sc.nextInt();sc.nextLine();
							for (int i = 0; i < empList.size(); i++) {
								if (empList.get(i).emp_id == team_id) {
									empList.get(i).toString();
									((TL) empList.get(i)).addEmployee(id);
									((TL) empList.get(i)).printList();
								}
							}
						}
						
						if (dsgn.equalsIgnoreCase("tl")) {
							emp = new TL(name, id, dsgn);
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
				break;
				
			case "TL":
				while (choice != 2) {
					System.out.println("1. View Employee Under Me Details");
					System.out.println("2. Logout");
					choice = sc.nextInt(); sc.nextLine();
//					if (choice == 1) {
//						ArrayList<Employee> empList = readDataFromFile();
//					}
				}
				break;
				
			case "Employee":
				while (choice != 2) {
					System.out.println("1. View My Details");
					System.out.println("2. Logout");
					choice = sc.nextInt(); sc.nextLine();
					if (choice == 1) {
						System.out.print("Enter your Employee ID: ");
						int id = sc.nextInt();sc.nextLine();
						ArrayList<Employee> empList = readDataFromFile();
						boolean flag = true;
						for (int i = 0; i < empList.size(); i++) 
							if (empList.get(i).emp_id == id) {
								System.out.println(empList.get(i).toString());
								flag = false;
							}
						if (flag)
							System.out.println("Employee ID Not Found! Re-Enter");
					}
				}
				
				break;
			
		}
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
            System.out.println("Error reading initializing stream");
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